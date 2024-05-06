package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 영상처리(실2)
 * https://www.acmicpc.net/problem/21938
 * <p>
 * BFS, DFS 혼합 문제, 격자형 그래프
 * O(V + E)
 * 설명란에 "상하좌우에 인접해있다면 이 픽셀들은 같은물체로 인식된다" 키워드 잘 보기
 */
public class BOJ21938 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M, T;
    private static int[][] RGB;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 세로
        M = inputProcessor.nextInt(); // 가로

        RGB = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            int col = 1;
            for (int j = 1; j <= 3 * M; j++) {
                sum += inputProcessor.nextInt();
                if (j % 3 == 0) {
                    RGB[i][col] = (sum / 3);
                    sum = 0;
                    col += 1;
                }
            }
        }

        T = inputProcessor.nextInt();
    }

    private static final int[][] DIR = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    private static void pro() {
        bfs(1, 1);

        int count = 0;
        boolean[][] visit = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (RGB[i][j] == 255 && !visit[i][j]) {
                    dfs(i, j, visit);
                    count += 1;
                }
            }
        }

        sb.append(count);
    }

    private static void bfs(int startX, int startY) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(startX);
        que.add(startY);

        boolean[][] visit = new boolean[N + 1][M + 1];
        visit[startX][startY] = true;

        while (!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            if (RGB[x][y] >= T) {
                RGB[x][y] = 255;
            } else {
                RGB[x][y] = 0;
            }

            for (int i = 0; i < 4; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];

                if (dx < 1 || dy < 1 || dx > N || dy > M) continue;
                if (visit[dx][dy]) continue;

                que.add(dx);
                que.add(dy);
                visit[dx][dy] = true;
            }
        }
    }

    private static void dfs(int x, int y, boolean[][] visit) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if (dx < 1 || dy < 1 || dx > N || dy > M) continue;
            if (visit[dx][dy]) continue;
            if (RGB[dx][dy] == 0) continue;

            dfs(dx, dy, visit);
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
