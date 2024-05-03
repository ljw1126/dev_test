package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 골3. 백양로 브레이크
 * 플로이드 워셜, 최단거리 문제
 * 직접 풀이 못함
 * -> 플로이드 워셜 초기화만 살하면 풀리는 쉬운 문제였음
 * -> 연결된 경우 비용 0으로 설정, 단방향인 경우 반대편은 1로 초기화 그리고 플로이드 워셜 돌리면 됨
 * <p>
 * <p>
 * 시간 복잡도 : O(N^3) = O(250^3)
 * 참고. https://steady-coding.tistory.com/103
 */
public class BOJ11562 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MAX_DIST = 251;
    private static int N, M, K;
    private static int[][] MATRIX;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 노드 수
        M = inputProcessor.nextInt(); // 간선 수

        MATRIX = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(MATRIX[i], MAX_DIST);
            MATRIX[i][i] = 0;
        }

        for (int i = 1; i <= M; i++) {
            int u = inputProcessor.nextInt();
            int v = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            MATRIX[u][v] = 0;
            MATRIX[v][u] = (b == 1) ? 0 : 1; // b = 1 양방향
        }
    }

    private static class Direct {
        private int from;
        private int to;

        public Direct(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    private static void pro() {
        preProcess();

        K = inputProcessor.nextInt();
        for (int i = 1; i <= K; i++) {
            int s = inputProcessor.nextInt();
            int e = inputProcessor.nextInt();

            sb.append(MATRIX[s][e]).append("\n");
        }
    }

    // 플로이드 워셜
    private static void preProcess() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    MATRIX[i][j] = Math.min(MATRIX[i][j], MATRIX[i][k] + MATRIX[k][j]);
                }
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
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
