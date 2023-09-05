package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 최소 스패닝 트리(골드4)
 * https://www.acmicpc.net/problem/1197
 *
 * - 직접 풀이
 * - 음수 cost 있다하면 절대값 취했는데, 그게 오답 원인이었음
 * - 시간 복잡도 : ElogE (정렬이 가장 많이 소요됨)
 * - 크루스칼 알고리즘 풀이
 */
public class BOJ1197 {
    static StringBuilder sb = new StringBuilder();

    static int V, E;

    static List<Node> info = new ArrayList<>();

    static int[] parent;

    static long INF = 1000000L;

    static class Node implements Comparable<Node> {
        int from;
        int to;
        long cost;

        public Node(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if(cost > other.cost) return 1;
            else if(cost == other.cost) return 0;
            return -1;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 노드의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수

        for(int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken()); // 음수 있다하여 절대값 처리 했는데, 그게 틀렸음

            info.add(new Node(from, to, cost));
        }
    }

    // 부모 노드를 찾으면서 갱신
    static int getParent(int node) {
        if(parent[node] == node) return node; // 재귀 종료 조건

        return parent[node] = getParent(parent[node]);
    }

    // 합집합, 부모 노드가 작은 쪽으로 갱신
    static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    // 두 노드의 부모가 같은 경우 이미 합집합에 포함되어 있는 상태(= 사이클 형성), 같지 않는 경우 합집합 처리
    static boolean unionFindParent(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        return parentA == parentB;
    }

    static void pro() {
        Collections.sort(info); // 비용이 가장 작은 것을

        parent = new int[V + 1];
        for(int i = 1; i <= V; i++) parent[i] = i;

        long result = 0L;
        for(Node node : info) {
            if(!unionFindParent(node.from, node.to)) {
                result += node.cost;
                union(node.from, node.to);
            }
        }

        System.out.println(result);
        System.out.println(Arrays.toString(parent));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
