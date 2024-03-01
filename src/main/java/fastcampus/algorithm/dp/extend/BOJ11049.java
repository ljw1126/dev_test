package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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
    static InputProcessor inputProcessor = new InputProcessor();

    static int N;
    static int[][] MATRIX;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        input();

        preprocess();
        //bottomUp();
        //sb.append(DP[1][N]);

        sb.append(topDown(1, N));

        output();
    }


    private static void input() {
        N = inputProcessor.nextInt();

        MATRIX = new int[N + 1][2];
        for(int i = 1; i <= N; i++) {
            MATRIX[i][0] = inputProcessor.nextInt();
            MATRIX[i][1] = inputProcessor.nextInt();
        }
    }

    private static void preprocess() {
        DP = new int[N + 1][N + 1];
        for(int i = 1; i < N; i++) {
            Arrays.fill(DP[i], 1, N + 1, Integer.MAX_VALUE);

            DP[i][i] = 0;
            DP[i][i + 1] = MATRIX[i][0] * MATRIX[i][1] * MATRIX[i + 1][1];
        }
    }

    private static void bottomUp() {
        for(int len = 3; len <= N; len++) {
            for(int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;

                DP[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k + 1][j] + (MATRIX[i][0] * MATRIX[k][1] * MATRIX[j][1])); // 행렬 곱셈 공식
                }
            }
        }
    }

    private static int topDown(int a, int b) {
        if(a == b) return DP[a][b];
        if(DP[a][b] != Integer.MAX_VALUE) return DP[a][b];

        for(int k = a; k < b; k++) {
            DP[a][b] = Math.min(DP[a][b], topDown(a, k) + topDown(k + 1, b) + (MATRIX[a][0] * MATRIX[k][1] * MATRIX[b][1]));
        }

        return DP[a][b];
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
