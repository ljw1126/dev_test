package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 계산 오르기 (실버3) https://www.acmicpc.net/problem/2579
 *
 * 처음 점화식 문제 풀이로 상향식으로 풀이 가능하나
 * 2차원 배열로 풀 경우 backtracking 까지 생각해 볼 수 있으므로 풀어 볼 수 있도록 하자
 */
public class BOJ2579 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] DP;

    static int[] DATA;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = Integer.parseInt(br.readLine());
        }

        DP = new int[N + 1][2];
    }

    static void pro() {
        DP[1][0] = DATA[1];

        if(N >= 2) {
            DP[2][0] = DATA[2];
            DP[2][1] = DATA[1] + DATA[2];
        }

        for(int i = 3; i <= N; i++) {
            DP[i][0] = Math.max(DP[i - 2][0], DP[i - 2][1]) + DATA[i];
            DP[i][1] = DP[i - 1][0] + DATA[i];
        }

        System.out.println(Math.max(DP[N][0], DP[N][1]));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
