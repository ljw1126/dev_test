package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 곡예비행(골4)
 * https://www.acmicpc.net/problem/21923
 * <p>
 * - 두번째 풀이 (직접 품)
 * - dp
 * - long[][] up, down 두개 구해서 합산한 결과가 최대를 찾음
 */
public class BOJ21923 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;
    private static long[][] FORWARD;
    private static long[][] REVERSE;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        FORWARD = new long[N + 1][M + 1];
        REVERSE = new long[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int value = inputProcessor.nextInt();
                FORWARD[i][j] = value;
                REVERSE[i][j] = value;
            }
        }
    }


    private static void pro() {
        forward();
        reverse();

        long result = Long.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                result = Math.max(result, FORWARD[i][j] + REVERSE[i][j]);
            }
        }

        sb.append(result);
    }

    private static void forward() {
        for (int row = N; row >= 1; row--) {
            if (row == N) continue;
            FORWARD[row][1] += FORWARD[row + 1][1];
        }

        for (int col = 1; col <= M; col++) {
            if (col == 1) continue;

            FORWARD[N][col] += FORWARD[N][col - 1];
        }

        for (int row = N - 1; row >= 1; row--) {
            for (int col = 2; col <= M; col++) {
                FORWARD[row][col] += Math.max(FORWARD[row][col - 1], FORWARD[row + 1][col]);
            }
        }
    }

    private static void reverse() {
        for (int row = N; row >= 1; row--) {
            if (row == N) continue;
            REVERSE[row][M] += REVERSE[row + 1][M];
        }

        for (int col = M; col >= 1; col--) {
            if (col == M) continue;

            REVERSE[N][col] += REVERSE[N][col + 1];
        }

        for (int row = N - 1; row >= 1; row--) {
            for (int col = M - 1; col >= 1; col--) {
                REVERSE[row][col] += Math.max(REVERSE[row + 1][col], REVERSE[row][col + 1]);
            }
        }
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
