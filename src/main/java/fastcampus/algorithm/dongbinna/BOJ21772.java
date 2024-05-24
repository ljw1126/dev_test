package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 가희의 고구마 먹방(골5)
 * https://www.acmicpc.net/problem/21772
 * <p>
 * - 직접 풀이 x
 * - 완전 탐색, dfs
 */
public class BOJ21772 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int R, C, T, START_X, START_Y, RESULT;
    private static String[] ROOM;
    private static boolean[][] GOGUMA;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        R = inputProcessor.nextInt(); // 세로
        C = inputProcessor.nextInt(); // 가로
        T = inputProcessor.nextInt(); // 가희가 이동하는 시간

        boolean findDog = false;
        ROOM = new String[R];
        for (int i = 0; i < R; i++) {
            String line = inputProcessor.nextLine();
            ROOM[i] = line;

            if (!findDog) {
                for (int j = 0; j < C; j++) {
                    if (line.charAt(j) == 'G') {
                        START_X = i;
                        START_Y = j;
                        findDog = true;
                        break;
                    }
                }
            }
        }

        GOGUMA = new boolean[R][C];
    }

    private static void pro() {
        dfs(START_X, START_Y, 0, 0);
        sb.append(RESULT);
    }

    private static final int[][] DIR = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    private static void dfs(int x, int y, int cnt, int goguma) {
        if (cnt == T) {
            RESULT = Math.max(RESULT, goguma);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if (dx < 0 || dy < 0 || dx >= R || dy >= C) continue; // 범위 초과
            if (ROOM[dx].charAt(dy) == '#') continue; // 벽인 경우

            if (ROOM[dx].charAt(dy) == 'S' && !GOGUMA[dx][dy]) { // 고구마인 경우
                GOGUMA[dx][dy] = true;
                dfs(dx, dy, cnt + 1, goguma + 1);
                GOGUMA[dx][dy] = false;
            } else {
                dfs(dx, dy, cnt + 1, goguma);
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
