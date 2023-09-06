package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 전기가 부족해(골드2)
 * https://www.acmicpc.net/problem/10423
 *
 * - 직접 풀이
 * - 간선의 개수가 많으니 프림으로 풀어봄
 * - 최대치는 10^7
 */
public class BOJ10423 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, K;

    static List<Edge>[] adj;

    static int[] powerPlant;

    static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
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

        N = Integer.parseInt(st.nextToken()); // 도시의 개수 (노드)
        M = Integer.parseInt(st.nextToken()); // 케이블 수 (단말)
        K = Integer.parseInt(st.nextToken()); // 발전소의 개수

        // 발전소 정보
        st = new StringTokenizer(br.readLine());
        powerPlant = new int[N + 1];
        for(int i = 1; i <= K; i++) {
            int powerPlantNum = Integer.parseInt(st.nextToken());
            powerPlant[powerPlantNum] = 1; // 발전소이면 1, 아니면 0
        }

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }
    }

    static void pro() {
        boolean[] visit = new boolean[N + 1];

        Queue<Edge> que = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            if(powerPlant[i] == 1) que.add(new Edge(i, 0));
        }

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

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
