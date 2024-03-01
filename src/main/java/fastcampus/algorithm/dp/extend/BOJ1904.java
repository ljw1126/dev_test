package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 01타일(실버3) https://www.acmicpc.net/problem/1904
 */
public class BOJ1904 {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] DP;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            if(i == 1) DP[i] = 1;
            else if(i == 2) DP[i] = 2;
            else DP[i] = (DP[i - 1] + DP[i - 2]) % 15746;
        }

        br.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(DP[N]);
    }
}
