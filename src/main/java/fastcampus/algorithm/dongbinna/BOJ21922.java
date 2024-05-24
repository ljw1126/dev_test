package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21922 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;
    private static int[][] ROOM;
    private static List<AirCondition> AIR_CONTINONS = new ArrayList<>();

    private static class AirCondition {
        private int x;
        private int y;

        public AirCondition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 세로
        M = inputProcessor.nextInt(); // 가로

        ROOM = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                ROOM[i][j] = inputProcessor.nextInt();
                if (ROOM[i][j] == 9) {
                    AIR_CONTINONS.add(new AirCondition(i, j));
                }
            }
        }
    }

    private static final int[][] DIR = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
    };

    private static class Wind {
        private int x;
        private int y;
        private int dir;

        public Wind(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public Wind direct(int dx, int dy) {
            return new Wind(dx, dy, this.dir);
        }
    }

    private static void pro() {
        Deque<Wind> que = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N + 1][M + 1][4];
        boolean[][] checked = new boolean[N + 1][M + 1];

        for (AirCondition airCondition : AIR_CONTINONS) {
            Arrays.fill(visited[airCondition.x][airCondition.y], true);
            checked[airCondition.x][airCondition.y] = true;

            for (int i = 0; i < 4; i++) {
                int dx = airCondition.x + DIR[i][0];
                int dy = airCondition.y + DIR[i][1];

                if (dx < 1 || dy < 1 || dx > N || dy > M) continue;

                visited[dx][dy][i] = true;
                que.add(new Wind(dx, dy, i));
                checked[dx][dy] = true;
            }
        }

        while (!que.isEmpty()) {
            Wind cur = que.poll();

            int nextDir = turn(cur.dir, ROOM[cur.x][cur.y]);
            int dx = cur.x + DIR[nextDir][0];
            int dy = cur.y + DIR[nextDir][1];

            if (dx < 1 || dy < 1 || dx > N || dy > M) continue;
            if (visited[dx][dy][nextDir]) continue;

            que.add(new Wind(dx, dy, nextDir));
            visited[dx][dy][nextDir] = true;
            checked[dx][dy] = true;
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (checked[i][j]) {
                    cnt += 1;
                }
            }
        }

        sb.append(cnt);
    }

    private static int turn(int dir, int stuff) {
        if (stuff == 1) {
            if (dir == 2 || dir == 3) {
                return dir;
            } else if (dir == 0) {
                return 1;
            } else if (dir == 1) {
                return 0;
            }
        }

        if (stuff == 2) {
            if (dir == 0 || dir == 1) {
                return dir;
            } else if (dir == 2) {
                return 3;
            } else if (dir == 3) {
                return 2;
            }
        }

        if (stuff == 3) {
            if (dir == 0) {
                return 3;
            } else if (dir == 1) {
                return 2;
            } else if (dir == 2) {
                return 1;
            } else if (dir == 3) {
                return 0;
            }
        }

        if (stuff == 4) {
            if (dir == 0) {
                return 2;
            } else if (dir == 1) {
                return 3;
            } else if (dir == 2) {
                return 0;
            } else if (dir == 3) {
                return 1;
            }
        }

        return dir;
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
