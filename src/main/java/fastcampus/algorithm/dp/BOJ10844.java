package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 쉬운 계단 수
 *
 * 시간 복잡도 O(N^2)
 */
public class BOJ10844 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] DP;

    static int MOD = 1000000000;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N + 1][10];
        for(int i = 1; i <=9; i++) {
            DP[1][i] = 1;
        }
    }

    static void pro() {
        for(int i = 2; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                DP[i][j] = 0;
                if(j > 0) DP[i][j] += DP[i - 1][j - 1];
                if(j < 9) DP[i][j] += DP[i - 1][j + 1];

                DP[i][j] %= MOD;
            }
        }

        int result = 0;
        for(int v : DP[N]) {
            result += v;
            result %= MOD;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
