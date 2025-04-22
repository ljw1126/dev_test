package its.codingtest.shortpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 - 플로이드 (골4)
 * - https://www.acmicpc.net/problem/11404
 * - O(V^3)
 */
public class p385 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static final int MAX_COST = 10_000_001;
    private static int N, M;
    private static int[][] FLOYED;

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();
        FLOYED = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(FLOYED[i], MAX_COST);
            FLOYED[i][i] = 0;
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            FLOYED[from][to] = Math.min(FLOYED[from][to], cost);
        }
    }

    private static void pro() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;

                    FLOYED[i][j] = Math.min(FLOYED[i][j], FLOYED[i][k] + FLOYED[k][j]);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (FLOYED[i][j] == MAX_COST) sb.append(0).append(" ");
                else sb.append(FLOYED[i][j]).append(" ");
            }
            sb.append("\n");
        }
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
