package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 오르막 수(실버1) https://www.acmicpc.net/problem/11057
 *
 * N 최대 1000
 * 수는 0부터 시작가능
 */
public class BOJ11057 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        DP = new int[N + 1][10];
    }

    static void pro() {
        for(int i = 0; i <= 9; i++) DP[1][i] = 1;

        for(int len = 2; len <= N; len ++) {
            for(int num = 0; num < 10; num++) {
                for(int prev = 0; prev <= num; prev++) {
                    DP[len][num] += DP[len - 1][prev];
                    DP[len][num] %= 10007;
                }
            }
        }

        int result = 0;
        for(int value : DP[N]) {
            result += value;
            result %= 10007;
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();

        pro();
    }
}
