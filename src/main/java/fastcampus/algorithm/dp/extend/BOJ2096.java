package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2096 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] MAX_DP, MIN_DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        MAX_DP = new int[3];
        MIN_DP = new int[3];

        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int x3 = Integer.parseInt(st.nextToken());

            if(i == 1) {
                MAX_DP[0] = MIN_DP[0] = x1;
                MAX_DP[1] = MIN_DP[1] = x2;
                MAX_DP[2] = MIN_DP[2] = x3;
                continue;
            }

            int prevMaxDP0 = MAX_DP[0];
            int prevMAXDP1 = MAX_DP[1];
            MAX_DP[0] = Math.max(MAX_DP[0], MAX_DP[1]) + x1;
            MAX_DP[1] = Math.max(Math.max(prevMaxDP0, MAX_DP[1]), MAX_DP[2]) + x2;
            MAX_DP[2] = Math.max(prevMAXDP1, MAX_DP[2]) + x3;

            int prevMinDP0 = MIN_DP[0];
            int prevMinDP1 = MIN_DP[1];
            MIN_DP[0] = Math.min(MIN_DP[0], MIN_DP[1]) + x1;
            MIN_DP[1] = Math.min(Math.min(prevMinDP0, MIN_DP[1]), MIN_DP[2]) + x2;
            MIN_DP[2] = Math.min(prevMinDP1, MIN_DP[2]) + x3;
        }

        int max = Arrays.stream(MAX_DP).max().getAsInt();
        int min = Arrays.stream(MIN_DP).min().getAsInt();
        sb.append(max).append(" ").append(min);

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
