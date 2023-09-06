package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6497_prime {
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static int totalCost;

    static List<Edge>[] adj;

    static boolean[] visit;

    static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return cost - edge.cost; // 오름차순 정렬
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line;

        while(!"0 0".equals((line = br.readLine()))) {
            st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken()); // 집의 수(노드)
            m = Integer.parseInt(st.nextToken()); // 길의 수(간선)

            totalCost = 0;
            adj = new ArrayList[n + 1];
            for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();

            for(int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                adj[from].add(new Edge(to, cost));
                adj[to].add(new Edge(from, cost));

                totalCost += cost;
            }

            pro(totalCost);
        }
    }

    static void pro(int _totalCost) {
        visit = new boolean[n + 1];

        Queue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(0, 0)); // 시작 노드를 0으로 고정

        int result = 0;
        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            result += cur.cost;

            for(Edge next : adj[cur.idx]) {
                if(visit[next.idx]) continue;

                que.add(next);
            }
        }

        sb.append(_totalCost - result).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();

        // 결과 출력
        System.out.println(sb.toString());
    }
}
