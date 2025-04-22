package its.codingtest.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 경쟁적 전염 (골5)
 * https://www.acmicpc.net/problem/18405
 * <p>
 * - 우선 순위 큐 사용하는 것과 정렬 조건 생각치 못함
 */
public class p344 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, K, S, X, Y;
    private static int[][] FIELDS;

    private static void input() {
        N = inputProcessor.nextInt(); // N * N
        K = inputProcessor.nextInt(); // 바이러스 번호는 K이하의 자연수

        FIELDS = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                FIELDS[i][j] = inputProcessor.nextInt();
            }
        }

        S = inputProcessor.nextInt(); // S 초 뒤에 (x,y)에 존재하는 바이러스 출력, 없으면 0
        X = inputProcessor.nextInt();
        Y = inputProcessor.nextInt();
    }

    private static class Virus implements Comparable<Virus> {
        private final int no;
        private final int x;
        private final int y;
        private final int cnt;

        public Virus(int no, int x, int y) {
            this(no, x, y, 0);
        }

        public Virus(int no, int x, int y, int cnt) {
            this.no = no;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Virus o) {
            if (this.cnt == o.cnt) {
                return this.no - o.no;
            }

            return this.cnt - o.cnt;
        }
    }

    private static final int[][] DIR = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static void pro() {
        Queue<Virus> queue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (FIELDS[i][j] != 0) {
                    queue.add(new Virus(FIELDS[i][j], i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Virus cur = queue.poll();
            int no = cur.no;
            int x = cur.x;
            int y = cur.y;
            int cnt = cur.cnt;

            if (cnt == S) continue;

            for (int i = 0; i < 4; i++) {
                int dx = DIR[i][0] + x;
                int dy = DIR[i][1] + y;

                if (dx < 1 || dy < 1 || dx > N || dy > N) continue;
                if (FIELDS[dx][dy] != 0) continue;

                FIELDS[dx][dy] = no;
                queue.add(new Virus(no, dx, dy, cnt + 1));
            }
        }

        sb.append(FIELDS[X][Y]);
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
