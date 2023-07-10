package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 줄어들지 않아(실버1) https://www.acmicpc.net/problem/2688
 *
 * 상향식, 하향식 연습하기 좋은 기본 문제
 */
public class BOJ2688 {
    static StringBuilder sb = new StringBuilder();

    static int T, N;

    static long[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while(T > 0) {
            T -= 1;
            N = Integer.parseInt(br.readLine());

            long result = 0L;
            for(int i = 0; i <= 9; i++) result += DP[N][i];

            sb.append(result).append("\n");
        }
    }

    static void preProcess() {
        DP = new long[65][10];

        for(int i = 0; i <= 9; i++) DP[1][i] = 1;

        for(int i = 2; i <= 64; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int k = j; k <= 9; k++) {
                    DP[i][j] += DP[i - 1][k];
                }
            }
        }
    }

    static long executeByTopDown(int depth, int last) {
        if(depth == 1) return 1L;

        if(DP[depth][last] != -1) return DP[depth][last];

        long value = 0;
        for(int i = last; i <= 9; i++) {
            value += executeByTopDown(depth - 1, i);
        }

        return DP[depth][last] = value;
    }

    static void inputForTopDown() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        DP = new long[65][10];
        for(int i = 0; i <= 64; i++) Arrays.fill(DP[i], - 1);

        while(T > 0) {
            T -= 1;
            N = Integer.parseInt(br.readLine());

            long result = 0L;
            for(int i = 0; i <= 9; i++) result += executeByTopDown(N, i);

            sb.append(result).append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        /* bottom-up
        preProcess();
        input();
        System.out.println(sb);
         */

        inputForTopDown();
        System.out.println(sb);
    }
}
