package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 행렬 곱셈 순서 (골드3) https://www.acmicpc.net/problem/11049
 *
 * - 행렬의 곱셈 규칙에 대해 이해하고 있어야 함
 * - 로직은 BOJ11066 파일 합치기와 거의 비슷
 * - 시간복잡도 O(N^3)
 *
 * 새로운 문제를 만나니 시야가 계속 좁아지네
 * row date에 대한 조합을 구하는 형태에 대해 숙지해두자
 */
public class BOJ11049 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] MATRIX;
    static int[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        MATRIX = new int[N + 1][2];
        DP = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            MATRIX[i][0] = row;
            MATRIX[i][1] = col;
        }
    }

    static void pro() {
        for(int len = 2; len <= N; len++) {
            for(int i = 1; i <= (N - len + 1); i++) {
                int j = i - 1 + len;
                DP[i][j] = Integer.MAX_VALUE;

                for(int k = i; k < j; k++) {
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k + 1][j] + (MATRIX[i][0] * MATRIX[k + 1][0] * MATRIX[j][1]));
                }
            }
        }

        System.out.println(DP[1][N]);
    }

    static int rec(int x, int y) {
        if(x == y) return DP[x][y];
        if(DP[x][y] != Integer.MAX_VALUE) return DP[x][y];

        int value = Integer.MAX_VALUE;
        for(int z = x; z < y; z++) {
            value = Math.min(value, rec(x, z) + rec(z + 1, y) + (MATRIX[x][0] * MATRIX[z + 1][0] * MATRIX[y][1]));
        }

        return DP[x][y] = value;
    }

    static void proByTopDown() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i > j) continue;

                if(i != j) DP[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println(rec(1, N));
    }

    public static void main(String[] args) throws Exception {
        input();
        //pro();

        proByTopDown();
    }
}
