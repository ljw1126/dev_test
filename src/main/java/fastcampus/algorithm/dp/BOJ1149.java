package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * RGB 거리(실버1) https://www.acmicpc.net/problem/1149
 *
 * 상향식, 하향식 둘다 연습해보기 좋은 문제
 */
public class BOJ1149 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] A, DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1][4];
        DP = new int[N + 1][4];

        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void executeBottomUp() {
        DP[1][1] = A[1][1];
        DP[1][2] = A[1][2];
        DP[1][3] = A[1][3];

        for(int i = 2; i <= N; i++) {
            DP[i][1] = Math.min(DP[i - 1][2], DP[i - 1][3]) + A[i][1];
            DP[i][2] = Math.min(DP[i - 1][1], DP[i - 1][3]) + A[i][2];
            DP[i][3] = Math.min(DP[i - 1][1], DP[i - 1][2]) + A[i][3];
        }

        int result = Integer.MAX_VALUE;
        for(int i = 1; i <= 3; i++) {
            result = Math.min(result, DP[N][i]);
        }

        System.out.println(result);
    }

    static void inputForTopDown() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N][3];
        DP = new int[N][3];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            Arrays.fill(DP[i], -1);
        }

        DP[0][0] = A[0][0];
        DP[0][1] = A[0][1];
        DP[0][2] = A[0][2];
    }
    static int executeTopDown(int row, int col) {
        if(row == 0) return DP[row][col];

        if(DP[row][col] != -1) return DP[row][col];

        int value;
        if(col == 0) {
            value = Math.min(
                    executeTopDown(row - 1, 1),
                    executeTopDown(row - 1, 2)
            );
        } else if(col == 1) {
            value = Math.min(
                    executeTopDown(row - 1, 0),
                    executeTopDown(row - 1, 2)
            );
        } else { // col == 2
            value = Math.min(
                    executeTopDown(row - 1, 0),
                    executeTopDown(row - 1, 1)
            );
        }

        return DP[row][col] = value + A[row][col];
    }

    public static void main(String[] args) throws Exception {
        //input();
        //executeBottomUp();


        inputForTopDown();

        int result = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++) {
            result = Math.min(result, executeTopDown(N - 1, i));
        }

        System.out.println(result);
    }
}
