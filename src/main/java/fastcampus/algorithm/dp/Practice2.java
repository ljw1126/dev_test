package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Practice2 {

    static StringBuilder sb = new StringBuilder();

    static int T;
    static int N;

    static int RESULT;

    static int[] DATA;

    static int[][] SUM;

    static int[][] DP;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;
            N = Integer.parseInt(br.readLine());
            DATA = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) DATA[i] = Integer.parseInt(st.nextToken());

            DP = new int[N + 1][N + 1];

            // 프로세스 수행
            preProcess();
            pro();

            // 결과
            sb.append(RESULT).append("\n");
        }
    }

    static void preProcess() {
        SUM = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = i; j <= N; j++) {
                SUM[i][j] = SUM[i][j - 1] + DATA[j];
            }
        }
    }

    static void pro() {

        for(int len = 2; len <= N; len++) {
            for(int i = 1; i <= (N - len + 1); i++) {
                int j = i - 1+ len;

                DP[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k + 1][j] + SUM[i][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
