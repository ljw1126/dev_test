package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 연결 요소의 개수 https://www.acmicpc.net/problem/11724
 */
public class BOJ11724 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static List<Integer>[] adj;

    static boolean[] visit;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        for(int i = 1; i <= N; i++) Collections.sort(adj[i]);
    }

    static void dfs(int start) {
        visit[start] = true;

        for(int n : adj[start]) {
            if(visit[n]) continue;

            dfs(n);
        }
    }

    static void pro() {
        int ans = 0;
        for(int i = 1; i <= N; i++) {
            if(!visit[i]) {
                ans += 1;
                dfs(i);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
