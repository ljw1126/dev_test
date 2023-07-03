package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * RGB거리 (실버1) https://www.acmicpc.net/problem/1149
 *
 *
 */
public class BOJ1149 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] RGB;

    static int[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        RGB = new int[N + 1][3];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }


    static void pro() {
        DP = new int[N + 1][3];

        for(int i = 1; i <= N; i++) Arrays.fill(DP[i], Integer.MAX_VALUE);

        DP[1][0] = RGB[1][0];
        DP[1][1] = RGB[1][1];
        DP[1][2] = RGB[1][2];

        for(int i = 2; i <= N; i++) { // 집
            DP[i][0] = Math.min(DP[i-1][1], DP[i-1][2]) + RGB[i][0];
            DP[i][1] = Math.min(DP[i-1][0], DP[i-1][2]) + RGB[i][1];
            DP[i][2] = Math.min(DP[i-1][0], DP[i-1][1]) + RGB[i][2];
        }

        int result = Arrays.stream(DP[N]).min().getAsInt();
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
