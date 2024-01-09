package fastcampus.algorithm.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ20168 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, A, B, C;

    static List<Node>[] adj;

    static int RESULT;

    static boolean[] VISIT;

    static int[] DIST;
    static class Node {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 교차로 수(노드)
        M = Integer.parseInt(st.nextToken()); // 골목 개수(간선)
        A = Integer.parseInt(st.nextToken()); // 출발점
        B = Integer.parseInt(st.nextToken()); // 도착지
        C = Integer.parseInt(st.nextToken()); // 가진 돈

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to, cost));
            adj[to].add(new Node(from, cost));
        }

        RESULT = Integer.MAX_VALUE;
        VISIT = new boolean[N + 1]; // 방문 여부
    }

    static void dfs(int node, int parent, int fee, int maxValue) {
        if(node == B) { // 도착했을 때
            if(fee <= C) RESULT = Math.min(RESULT, maxValue);
            return;
        }

        for(Node next : adj[node]) {
            if(VISIT[next.to]) continue;
            if(next.to == parent) continue;
            if(fee + next.cost > C) continue;

            dfs(next.to, node,fee + next.cost, Math.max(maxValue, next.cost));
        }

        VISIT[node] = true;
    }

    static class Info {
        int node;
        int max;

        public Info(int node, int max) {
            this.node = node;
            this.max = max;
        }
    }
    static void dijkstra(int start) {
        Queue<Info> que = new LinkedList<>();
        que.add(new Info(start, -1));

        VISIT[start] = true;
        DIST = new int[N + 1];
        Arrays.fill(DIST, Integer.MAX_VALUE);
        DIST[start] = 0;

        while(!que.isEmpty()) {
            Info info = que.poll();

            if(info.node == B) {
                RESULT = Math.min(RESULT, info.max);
                return;
            }

            for(Node next : adj[info.node]) {
                if(VISIT[next.to]) continue;
                if(DIST[info.node] + next.cost > C) continue;

                VISIT[next.to] = true;
                DIST[next.to] = DIST[info.node] + next.cost;
                que.add(new Info(next.to, Math.max(info.max, next.cost)));
            }
        }


    }

    static void rec(int node, int total, int max) {
        if(total > C) return;
        if(node == B) return;


    }

    static void pro() {
        //dfs(A,-1, 0, -1);

        dijkstra(A);
        System.out.println(RESULT == Integer.MAX_VALUE ? -1 : RESULT);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
