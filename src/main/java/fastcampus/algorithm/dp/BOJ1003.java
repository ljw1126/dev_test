package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 피보나치 함수(실버3) https://www.acmicpc.net/problem/1003
 */
public class BOJ1003 {
    static StringBuilder sb = new StringBuilder();

    static int T, N;

    static int[] DP;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while(T > 0) {
            T -= 1;

            N = Integer.parseInt(br.readLine());

            if(N == 0) sb.append(1).append(" ").append(0).append("\n");
            else sb.append(DP[N - 1]).append(" ").append(DP[N]).append("\n");
        }
    }

    static void fibonacci() {
        DP = new int[41];
        DP[0] = 0;
        DP[1] = 1;
        for(int i = 2; i <= 40; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
        }
    }

    public static void main(String[] args) throws Exception {
        fibonacci();
        input();
        System.out.println(sb);
    }
}
