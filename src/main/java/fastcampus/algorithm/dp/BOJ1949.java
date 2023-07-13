package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 우수마을(골드2) https://www.acmicpc.net/problem/1949
 *
 * rooted tree 를 알고 시작했는나
 * 루트 노드는 무엇을 골라야 하고, 초기화는 어떻게 해야 하고
 * 조합은 또 어떻게 해야하는지 전혀 알수 없었다
 * ==> DFS 알아도 방법이 떠오르지 않음
 */
public class BOJ1949 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] DATA;
    static List<Integer>[] adj;

    static int[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        DATA = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            DATA[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }
    }

    static void dfs(int node, int prev) {
        DP[node][0] = 0;
        DP[node][1] = DATA[node];

        for(int child : adj[node]) {
            if(prev == child) continue;

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
