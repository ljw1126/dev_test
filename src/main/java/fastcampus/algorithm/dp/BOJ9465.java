package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 스티커(실버1)
 *
 * 직접 품, 최대치 10^7
 */
public class BOJ9465 {
    static StringBuilder sb = new StringBuilder();

    static int T, N;
    static int[][] DP, DATA;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            N = Integer.parseInt(br.readLine());
            DATA = new int[2][N + 1];

            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= N; j++) {
                    DATA[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            DP = new int[2][N + 1];
            pro();
        }
    }

    static void pro() {
        DP[0][1] = DATA[0][1];
        DP[1][1] = DATA[1][1];

        for(int i = 2; i <= N; i++) {
            DP[0][i] = DATA[0][i] + Math.max(Math.max(DP[0][i - 2], DP[1][i - 2]), DP[1][i - 1]);
            DP[1][i] = DATA[1][i] + Math.max(Math.max(DP[0][i - 2], DP[1][i - 2]), DP[0][i - 1]);
        }

        int result = Math.max(DP[0][N], DP[1][N]);
        sb.append(result).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
