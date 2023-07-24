package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1309 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int MOD = 9901;

    static int[][] DP;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        DP = new int[N + 1][3];
        int result = (topDown(N, 0) + topDown(N , 1) + topDown(N , 2)) % MOD;

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();

        br.close();
    }

    static int topDown(int height, int cageNumber) {
        if(height == 1) return DP[height][cageNumber] = 1;
        if(DP[height][cageNumber] > 0) return DP[height][cageNumber];

        if(cageNumber == 0) {
            DP[height][cageNumber] = (topDown(height - 1, 0) + topDown(height - 1, 1) + topDown(height - 1, 2)) % MOD;
        } else if(cageNumber == 1) {
            DP[height][cageNumber] = (topDown(height - 1, 0) + topDown(height - 1, 2)) % MOD;
        } else {
            DP[height][cageNumber] = (topDown(height - 1, 0) + topDown(height - 1, 1)) % MOD;
        }

        return DP[height][cageNumber];
    }

    public static void main(String[] args) throws Exception {
        input();
    }

}
