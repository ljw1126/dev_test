package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *도시 분할 계획(골드4)
 * https://www.acmicpc.net/problem/1647
 *
 * - 직접 풀었다.
 * - 크루스칼 알고리즘 (ElogE)
 * - 최대치 10^6 * 10^3 , int 범위내
 *
 * 글이 좀 안 읽힌다..
 * - 두 마을로 나누는데, 두 마을 사이에 길은 필요 없으므로 없앨 수 있다.
 * - 마을 안에는 집이 하나이상 있어야 한다.
 * - 전체 마을 연결하는 최소 비용에서 가중치가 큰 선 하나 제거하면 두 마을이 된다.
 */
public class BOJ1647 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static List<Info> infoList = new ArrayList<>();

    static int[] parent;

    static class Info implements Comparable<Info> {
        int from;
        int to;
        int cost;

        public Info(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info info) {
            return cost - info.cost; // 오름차순(-1)
        }

        @Override
        public String toString() {
            return "Info{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 집의 개수(노드)
        M = Integer.parseInt(st.nextToken()); // 길의 개수(간선)

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            infoList.add(new Info(from, to, cost));
        }
    }

    static int getParent(int node) {
        if(parent[node] == node) return node;

        return parent[node] = getParent(parent[node]);
    }

    static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    // 부모 노드가 같다는 것은 같은 합집합에 포함되어 잇는 상태(사이클 형성가능)
    static boolean unionFind(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        return parentA == parentB;
    }

    static void pro() {
        Collections.sort(infoList); // 비용 기준 오름차순 정렬

        // 초기 부모는 자기자신으로 설정
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        int total = 0;
        int maxCost = 0;
        for(Info info : infoList) {
            if(!unionFind(info.from, info.to)) {
               total += info.cost;
               maxCost = Math.max(maxCost, info.cost);

               union(info.from, info.to);
            }
        }

        System.out.println(total - maxCost); // 가장 큰 유지비가 드는 간선을 구해서 빼줘도 된다. (나는 조건문으로 마지막 하나 제거, 가장 큰 거니깐)
        System.out.println(Arrays.toString(parent));
        for(Info info : infoList) System.out.println(info);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}

