package fastcampus.algorithm.exam3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *트리 디자이너 호석 (골1)
 * https://www.acmicpc.net/problem/22253
 *
 * - 직접 풀이하지 못함
 * - O(N)
 * - 트리 + 동적프로그래밍
 * - 불을 켜는 경우와 켜지 않는 경우, 2가지에 대한 처리가 필요
 * - dfs방식 이므로 top-down
 *
 * - 불을 켤때 value가 오름차순이 되야 함
 * - dp[idx][V[idx]] = 1 로 초기화
 * 1) 불을 켜지 않는 경우는 0~9까지 순회하면서 더해줌 (누적)
 * 2) 불을 켜는 경우는 V[x] 이상 ~ 9까지 순회하면서 더해줌(누적)
 */
public class BOJ22253 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] V;

    static int MOD = 1000000007; // 오타 10억에 7

    static List<Integer>[] adj;

    static int[][] dp;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 정점의 개수

        st = new StringTokenizer(br.readLine());
        V = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
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

        dp = new int[N + 1][10]; // 0 ~ 9
    }

    static void dp(int idx, int prev) {
        dp[idx][V[idx]] = 1;

        for(int child : adj[idx]) {
            if(child == prev) continue; // 부모 노드인 경우

            dp(child, idx);

            // 선택 하지 않는 경우
            for(int i = 0; i <= 9; i++) {
                dp[idx][i] += dp[child][i];
                dp[idx][i] %= MOD;
            }

            // 선택하는 경우 V[idx] 이상의 값만 더할 수 있다
            for(int i = V[idx]; i <= 9; i++) {
                dp[idx][V[idx]] += dp[child][i];
                dp[idx][V[idx]] %= MOD;
            }
        }
    }

    static void pro() {
        dp(1, -1);

        int ans = 0;
        for(int i = 0; i <= 9; i++) {
            ans += dp[1][i];
            ans %= MOD;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
