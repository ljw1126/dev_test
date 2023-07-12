package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파일 합치기 (골드3) https://www.acmicpc.net/problem/11066
 *
 * 직접 풀이 못함
 * sum[][] 의 경우 (N^2)
 * DP[][] 의 경우 (N^3)
 */
public class BOJ11066 {

    static StringBuilder sb = new StringBuilder();

    static int T, K;

    static int[] A;

    static int[][] SUM, DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            K = Integer.parseInt(br.readLine());

            A = new int[K + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= K; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            SUM = new int[K + 1][K + 1];
            DP = new int[K + 1][K + 1];

            pro();
        }
    }

    static void preprocess() { // i ~ j 까지의 합을 미리 구함
        for(int i = 1; i <= K; i++) {
            for(int j = i; j <= K; j++) {
                SUM[i][j] = SUM[i][j - 1] + A[j]; // 실수
            }
        }
    }

    static void pro() {
        preprocess();

        for(int len = 2; len <= K; len++) { // 구간 길이
            for(int i = 1; i <= (K - len + 1); i++) { // 시작 지점
                int j = i - 1 + len; // 종료 지점
                DP[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k + 1][j] + SUM[i][j]);
                }
            }
        }

        sb.append(DP[1][K]).append("\n");
    }

    static void inputForTopDown() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            K = Integer.parseInt(br.readLine());

            A = new int[K + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= K; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            SUM = new int[K + 1][K + 1];
            DP = new int[K + 1][K + 1];

            preprocess();

            for(int i = 1; i <= K; i++) {
                for(int j = 1; j <= K; j++) {
                    if(i == j) DP[i][j] = 0;
                    else DP[i][j] = Integer.MAX_VALUE;
                }
            }

            sb.append(rec(1, K)).append("\n");
        }
    }

    // Top-Down방식의 경우 형식화된 프로세스 형태를 그대로 따라감
    static int rec(int x, int y) {
        if(x == y) return DP[x][y];
        if(DP[x][y] != Integer.MAX_VALUE) return DP[x][y];

        int value = Integer.MAX_VALUE;
        for(int z = x; z < y; z++) {
            value = Math.min(value, rec(x, z) + rec(z + 1, y) + SUM[x][y]);
        }

        return DP[x][y] = value;
    }

    public static void main(String[] args) throws Exception {
        //input();

        inputForTopDown();
        System.out.println(sb);
    }
}
