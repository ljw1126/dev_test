package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 전력난(골드4)
 * https://www.acmicpc.net/problem/6497
 *
 * - 직접 풀이
 * - n과 m을 반대로 표현
 * - 절약할 수 있는 최대 비용 -> 전체 비용 - 최소 비용 = 절약 비용
 * - 테스트 케이스가 하나만 표출되어 있어서, 초기화 대문에 조금 시간 소비
 * - 시간 복잡도 ElogE = 20만log20만 // 정렬이 가장 많은 시간 복잡도 잡음
 */
public class BOJ6497 {
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static List<Edge> edgeList;

    static int[] parent;

    static int totalCost;

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
        public int compareTo(Edge edge) {
            if(cost < edge.cost) return -1;
            else if(cost == edge.cost) return 0;
            return 1;
        }
    }


    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line;
        while(!"0 0".equals(line = br.readLine())) {
            st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken()); // 집의 수(노드)
            m = Integer.parseInt(st.nextToken()); // 길의 수(간선)

            totalCost = 0;
            edgeList = new ArrayList<>();
            for(int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edgeList.add(new Edge(from, to, cost));
                totalCost += cost;
            }

            // 비용 오름차순 정렬
            Collections.sort(edgeList);

            // 부모 노드 초기화
            parent = new int[n + 1];
            for(int i = 1; i <= n; i++) parent[i] = i;

            //크루스칼 알고리즘 수행
            pro();
        }
    }

    static int getParent(int node){
        if(parent[node] == node) return node;

        return parent[node] = getParent(parent[node]);
    }

    static void union(int a, int b) {
        int _a = getParent(a);
        int _b = getParent(b);

        if(_a < _b) parent[_b] = _a;
        else parent[_a] = _b;
    }

    static boolean unionFind(int a, int b) {
        int _a = getParent(a);
        int _b = getParent(b);

        return _a == _b;
    }

    static void pro() {
        int result = 0;
        for(Edge e : edgeList) {
            if(!unionFind(e.from, e.to)) {
                union(e.from, e.to);
                result += e.cost;
            }
        }

        sb.append(totalCost - result).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();

        System.out.println(sb.toString());
    }
}
