package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15681 {

    static StringBuilder sb = new StringBuilder();

    static int N, R, Q;

    static List<Integer>[] adj;

    static int[] DP;

    static List<Integer> queries = new ArrayList<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 노드 수
        R = Integer.parseInt(st.nextToken()); // 루트 노드
        Q = Integer.parseInt(st.nextToken()); // Query 수

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList();

        for(int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        for(int i = 1; i <= Q; i++) queries.add(Integer.parseInt(br.readLine()));

        DP = new int[N + 1];
    }

    static void dfs(int node, int prev) {
        DP[node] = 1;

        for(int child : adj[node]) {
            if(child == prev) continue;

            dfs(child, node);
            DP[node] += DP[child];
        }
    }

    static void pro() {
        dfs(R, -1);

        for(int q : queries) sb.append(DP[q]).append("\n");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
