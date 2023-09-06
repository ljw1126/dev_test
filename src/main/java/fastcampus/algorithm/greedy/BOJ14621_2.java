package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *  나만 안되는 연애(골드3)
 *  https://www.acmicpc.net/problem/14621
 *
 *  - 크루스칼 알고리즘
 *  - ElogV
 *  - 같은 성별이 아닌 경우에만 unionFind를 실행하게 된다
 *  - 전체 연결 여부는 edgeCnt 수로 구분 하는데, "노드 - 1 == 간선의 개수" 가 되야 트리가 됨
 */
public class BOJ14621_2 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] school;

    static List<Edge> edgeList;

    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return cost - other.cost;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 노드
        M = Integer.parseInt(st.nextToken()); // 간선

        st = new StringTokenizer(br.readLine());
        school = new int[N + 1]; // 남자 1, 여자 : 0
        for(int i = 1; i <= N; i++) {
            if("M".equals(st.nextToken())) {
                school[i] = 1;
            }
        }

        edgeList = new ArrayList<>();
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(from, to, cost));
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

    static boolean unionFind(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        return parentA == parentB;
    }

    static void pro() {
        // 비용 순 정렬
        Collections.sort(edgeList);

        // 부모 노드 자기 자신으로 초기화
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        // 부모 노드가 다른가 ? 합집합 수행
        // 부모 노드가 같은가 ? 이미 연결되어 있거나, 사이클 발생 가능
        int result = 0;
        int edgeCnt = 0;
        for(Edge edge : edgeList) {

            // 같은 성별이면 연결 불가
            if(school[edge.from] == school[edge.to]) continue;
            if(!unionFind(edge.from, edge.to)) {
                union(edge.from, edge.to);
                result += edge.cost;
                edgeCnt += 1;
            }
        }

        if(N - 1== edgeCnt) sb.append(result);
        else sb.append("-1");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
