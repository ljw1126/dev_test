package its.codingtest.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 금광
 * <p>
 * 입력
 * 2
 * 3 4
 * 1 3 3 2 2 1 4 1 0 6 4 7
 * 4 4
 * 1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2
 * <p>
 * 출력
 * 19
 * 16
 */
public class p375 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        output();
    }

    private static int T, N, M;
    private static int[][] DATA;

    private static void input() {
        T = inputProcessor.nextInt();
        while (T > 0) {
            N = inputProcessor.nextInt();
            M = inputProcessor.nextInt();

            DATA = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    DATA[i][j] = inputProcessor.nextInt();
                }
            }

            pro();

            T -= 1;
        }
    }

    // 오른쪽 위, 오른쪽, 오른쪽 아래
    private static final int[][] DIR = {
            {-1, 1},
            {0, 1},
            {1, 1}
    };

    private static void pro() {
        bottomUp();
    }

    private static void bottomUp() {
        // 초기화
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            dp[i][0] = DATA[i][0];
        }

        for (int col = 1; col < M; col++) { // 열
            for (int row = 0; row < N; row++) { // 행
                int leftUp = row == 0 ? 0 : dp[row - 1][col - 1];
                int left = dp[row][col - 1];
                int leftDown = row == N - 1 ? 0 : dp[row + 1][col - 1];

                int max = Math.max(leftUp, Math.max(left, leftDown));
                dp[row][col] = DATA[row][col] + max;
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }
        sb.append(result).append("\n");
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
