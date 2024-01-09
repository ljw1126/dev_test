package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Practice {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] POPULATION;

    static List<Integer>[] adj;

    static int[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        POPULATION = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            POPULATION[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= (N - 1); i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }
    }

    static void dfs(int node, int parent) {
        DP[node][1] = POPULATION[node];

        for(int child : adj[node]) {
            if(child == parent) continue;

            dfs(child, node);
            DP[node][0] += Math.max(DP[child][0], DP[child][1]);
            DP[node][1] += DP[child][0];
        }
    }

    static void pro() {
        DP = new int[N + 1][2];
        dfs(1, -1);

        System.out.println(Math.max(DP[1][0], DP[1][1]));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
