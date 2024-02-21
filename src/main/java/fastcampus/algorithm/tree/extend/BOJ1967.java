package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리의 지름(골4)
 * https://www.acmicpc.net/problem/1967
 */
public class BOJ1967 {

    static StringBuilder sb = new StringBuilder();

    static int N;

    static List<Edge>[] adj;
    static boolean[] visit;

    static int MAX = 0;
    static int LEAF;

    static class Edge {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 노드 수

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }

    }

    static void dfs(int idx, int cost) {
        visit[idx] = true;
        if(MAX < cost) {
            MAX = cost;
            LEAF = idx;
        }

        for(Edge next : adj[idx]) {
            if(!visit[next.idx]) {
                dfs(next.idx, cost + next.cost);
            }
        }
    }

    static void pro() {
        visit = new boolean[N + 1];
        dfs(1, 0);

        Arrays.fill(visit, false);
        dfs(LEAF, 0);

        System.out.println(MAX);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
