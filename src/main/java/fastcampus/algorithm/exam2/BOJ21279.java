package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 광부 호석(플래티넘5)
 * https://www.acmicpc.net/problem/21279
 * <p>
 * - 직절 풀이 못함
 * - 일단 n^2 풀이는 25 * 10^10 이라 시간초과 (가치 없음)
 * - 좌표는 최대 10^5 * 10^5 = 10^10 개가 있네
 * - 광물의 개수 N (최대50만)
 * - 호석이가 가진 돈 C (<= N)
 * - 아름다운 정도 v (최대 10^8)
 * - 최대치 노드가 50만이고 가진 돈C가 50만일때 50만개 선택 가능하고, vi가 전부 10^8 최대치 이면 => long 처리
 */
public class BOJ21279 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int MAX = 100_001;
    private static int N, C, COUNT;
    private static long SUM, RESULT;

    private static List<List<Stone>> ROWS, COLS;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 광물의 개수
        C = inputProcessor.nextInt(); // 보유 돈

        ROWS = new ArrayList<>();
        COLS = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            ROWS.add(new ArrayList<>());
            COLS.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int v = inputProcessor.nextInt(); // 아름다움 정도

            ROWS.get(x).add(new Stone(y, v));
            COLS.get(y).add(new Stone(x, v));
        }
    }

    private static class Stone {
        private int idx;
        private int score;

        public Stone(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }
    }

    private static void pro() {
        int W = 0;
        int H = MAX - 1;
        while (W < MAX && H > 0) {
            work(ROWS.get(W), H);

            while (COUNT > C) {
                drop(COLS.get(H), W);
                H -= 1;
            }

            RESULT = Math.max(RESULT, SUM);
            W += 1;
        }

        sb.append(RESULT);
    }

    // 광석을 캔다
    private static void work(List<Stone> stones, int height) {
        for (Stone stone : stones) {
            if (stone.idx > height) continue;

            COUNT += 1;
            SUM += stone.score;
        }
    }

    // 광석을 버린다
    private static void drop(List<Stone> stones, int width) {
        for (Stone stone : stones) {
            if (stone.idx > width) continue;

            COUNT -= 1;
            SUM -= stone.score;
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        private StringTokenizer st;
        private BufferedReader br;

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
