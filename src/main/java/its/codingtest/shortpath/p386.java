package its.codingtest.shortpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 정확한 순위 - K대회 문제
 * - 플로이드 워셜 O(V^3)
 * - A에서 B로 도달이 가능하거나, B에서 A로 도달이 가능하면 '성적 비교'가 가능하다
 * <p>
 * <p>
 * 입력
 * 6 6
 * 1 5
 * 3 4
 * 4 2
 * 4 6
 * 5 2
 * 5 4
 * <p>
 * 출력 1
 */
public class p386 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static final int MAX_DIST = 501;
    private static int N, M;
    private static int[][] FLOYED;

    private static void input() {
        N = inputProcessor.nextInt(); // 학생들의 수
        M = inputProcessor.nextInt(); // 성적을 비교한 횟수(간선)

        FLOYED = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(FLOYED[i], MAX_DIST);
            FLOYED[i][i] = 0;
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            FLOYED[from][to] = 1;
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

        int result = 0;
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (FLOYED[i][j] != MAX_DIST || FLOYED[j][i] != MAX_DIST) cnt++;
            }

            if (cnt == N) {
                result += 1;
            }
        }

        sb.append(result);
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
