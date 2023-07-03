package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2 * N 타일링 (실버3) https://www.acmicpc.net/problem/11726
 */
public class BOJ11726 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] DP;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        preProcess();
    }

    static void preProcess() {
        DP = new int[N + 1];
        DP[1] = 1;
        DP[2] = 2;

        for(int i = 3; i <= N; i++) {
            DP[i] = (DP[i - 1] + DP[i - 2]) % 10007;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(DP[N]);
    }
}
