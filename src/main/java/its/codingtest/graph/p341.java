package its.codingtest.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 - 연구소 (골4)
 * - 주석 안한게 공간복잡도, 시간도 더 빠름
 * https://www.acmicpc.net/problem/14502
 */
public class p341 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    /*
        벽을 세우는 경우의 수 8C3 = 8 * 7 *6 / 3! = 56가지
        0 : 빈칸, 1 : 벽, 2 : 바이러스
     */

    private static final int[][] DIR = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static int N, M;
    private static int[][] FIELDS;
    private static List<BLANK> BLANKS;
    private static List<Virus> VIRUSES;
    private static int RESULT;

    private static void input() {
        N = inputProcessor.nextInt(); // 세로
        M = inputProcessor.nextInt(); // 가로
        RESULT = 0;

        FIELDS = new int[N + 1][M + 1];
        BLANKS = new ArrayList<>();
        VIRUSES = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int v = inputProcessor.nextInt();
                FIELDS[i][j] = v;

                if (v == 0) { // 빈칸
                    BLANKS.add(new BLANK(i, j));
                } else if (v == 2) { // 바이러스
                    VIRUSES.add(new Virus(i, j));
                }
            }
        }
    }

    private static class BLANK {
        private final int x;
        private final int y;

        public BLANK(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Virus {
        private final int x;
        private final int y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void pro() {
        createWall(0, 0);
        sb.append(RESULT);
    }

    // 완전 탐색인데, 경우의 수가 많아지면 백트래킹 또는 DP를 고려해야 함
    private static void createWall(int count, int idx) {
        if (count == 3) {
            boolean[][] virus = spread();
            int safety = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (FIELDS[i][j] == 0 && !virus[i][j]) {
                        safety += 1;
                    }
                }
            }

            RESULT = Math.max(RESULT, safety);
            return;
        }

        if (idx >= BLANKS.size()) return;

        BLANK cur = BLANKS.get(idx);
        FIELDS[cur.x][cur.y] = 1;
        createWall(count + 1, idx + 1);

        FIELDS[cur.x][cur.y] = 0;
        createWall(count, idx + 1);
    }

    private static boolean[][] spread() {
        boolean[][] visited = new boolean[N + 1][M + 1];
        for (Virus v : VIRUSES) {
            if (visited[v.x][v.y]) continue;
            dfs(v.x, v.y, visited);
        }

        return visited;
    }

    private static void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int dx = DIR[i][0] + x;
            int dy = DIR[i][1] + y;

            if (dx < 1 || dy < 1 || dx > N || dy > M) continue;
            if (visited[dx][dy]) continue;
            if (FIELDS[dx][dy] != 0) continue;

            dfs(dx, dy, visited);
        }
    }

    /*
    private static void rec(int count, int[] walls, boolean[] selected) {
        if (count == 3) {
            int safety = spreadVirus(createWalls(walls));
            RESULT = Math.max(safety, RESULT);
            return;
        }

        for (int i = 0; i < BLANKS.size(); i++) {
            if (selected[i]) continue;

            walls[count] = i;
            selected[i] = true;
            rec(count + 1, walls, selected);
            walls[count] = -1;
            selected[i] = false;
        }
    }

    private static int[][] createWalls(int[] selected) {
        int[][] result = new int[N + 1][M + 1]; // 공강복잡도 차이가 10배남
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                result[i][j] = FIELDS[i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            BLANK blank = BLANKS.get(selected[i]);
            result[blank.x][blank.y] = 1;
        }

        return result;
    }

    private static int spreadVirus(int[][] base) {
        Deque<Virus> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M + 1];
        for (Virus v : VIRUSES) {
            visited[v.x][v.y] = true;
            que.add(v);
        }

        while (!que.isEmpty()) {
            Virus cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int dx = DIR[i][0] + cur.x;
                int dy = DIR[i][1] + cur.y;

                if (dx < 1 || dy < 1 || dx > N || dy > M) continue;
                if (visited[dx][dy]) continue;
                if (base[dx][dy] != 0) continue;

                base[dx][dy] = 2;
                visited[dx][dy] = true;
                que.add(new Virus(dx, dy));
            }
        }

        int safetyZone = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (base[i][j] == 0) safetyZone += 1;
            }
        }

        return safetyZone;
    }
    */

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
