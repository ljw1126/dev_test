package its.codingtest.shortpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 화성 탐사 - ACM-ICPC
 * - 최단 경로 구하는 문제
 * - O(ElogV)
 * 입력
 * 3
 * 3
 * 5 5 4
 * 3 9 1
 * 3 2 7
 * 5
 * 3 7 2 0 1
 * 2 8 0 9 1
 * 1 2 1 8 1
 * 9 8 9 2 0
 * 3 6 5 1 5
 * 7
 * 9 0 5 1 1 5 3
 * 4 1 2 1 6 5 3
 * 0 7 6 1 6 8 5
 * 1 1 7 8 3 2 3
 * 9 4 0 7 6 4 1
 * 5 8 3 2 4 8 3
 * 7 4 8 4 8 3 4
 * <p>
 * 출력
 * 20
 * 19
 * 36
 */
public class p388 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        output();
    }

    private static int T, N;
    private static int[][] COST;

    private static void input() {
        T = inputProcessor.nextInt();
        while (T > 0) {
            N = inputProcessor.nextInt();

            COST = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    COST[i][j] = inputProcessor.nextInt();
                }
            }

            pro();

            T -= 1;
        }
    }

    private static final int[][] DIR = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    private static void pro() {
        Deque<Machine> que = new ArrayDeque<>();
        que.add(new Machine(0, 0, COST[0][0]));

        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = COST[0][0];

        while (!que.isEmpty()) {
            Machine cur = que.poll();

            if (dist[cur.x][cur.y] < cur.dist) continue;

            for (int i = 0; i < 4; i++) {
                int dx = cur.x + DIR[i][0];
                int dy = cur.y + DIR[i][1];

                if (dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
                if (dist[dx][dy] > cur.dist + COST[dx][dy]) {
                    dist[dx][dy] = cur.dist + COST[dx][dy];
                    que.add(new Machine(dx, dy, dist[dx][dy]));
                }
            }
        }

        sb.append(dist[N - 1][N - 1]).append("\n");
    }

    private static class Machine {
        private final int x;
        private final int y;
        private final int dist;

        public Machine(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
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
