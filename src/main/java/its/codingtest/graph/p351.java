package its.codingtest.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 - 감시 피하기 (골5)
 * https://www.acmicpc.net/problem/18428
 * -직접 풀이
 * - 최대 36칸에서 벽을 3개 세우는 조합 36C3
 */
public class p351 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;
    private static String[][] SCHOOL;
    private static List<BLANK> BLANKS;
    private static List<TEACHER> TEACHERS;
    private static boolean RESULT;

    private static void input() {
        N = inputProcessor.nextInt();
        SCHOOL = new String[N + 1][N + 1];

        BLANKS = new ArrayList<>();
        TEACHERS = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                String v = inputProcessor.next();
                SCHOOL[i][j] = v;

                if ("T".equals(v)) {
                    TEACHERS.add(new TEACHER(i, j, -1));
                } else if ("X".equals(v)) {
                    BLANKS.add(new BLANK(i, j));
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

    private static class TEACHER {
        private final int x;
        private final int y;
        private final int dir;

        public TEACHER(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private static void pro() {
        createDummy(0, 0);
        sb.append(RESULT ? "YES" : "NO");
    }

    // 최대 36칸에서 벽을 3개 세우는 조합 36C3
    private static void createDummy(int idx, int cnt) {
        if (RESULT) return;
        if (cnt == 3) {
            if (survive()) {
                RESULT = true;
            }
            return;
        }

        if (idx >= BLANKS.size()) return;

        BLANK blank = BLANKS.get(idx);
        SCHOOL[blank.x][blank.y] = "O";
        createDummy(idx + 1, cnt + 1);

        SCHOOL[blank.x][blank.y] = "X";
        createDummy(idx + 1, cnt);
    }

    private static final int[][] DIR = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static boolean survive() {
        Deque<TEACHER> queue = new ArrayDeque<>();
        for (TEACHER t : TEACHERS) {
            for (int i = 0; i < 4; i++) {
                queue.add(new TEACHER(t.x, t.y, i));
            }
        }

        while (!queue.isEmpty()) {
            TEACHER cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int i = cur.dir;

            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if (dx < 1 || dy < 1 || dx > N || dy > N) continue;
            if ("S".equals(SCHOOL[dx][dy])) return false;
            if ("O".equals(SCHOOL[dx][dy])) continue;

            queue.add(new TEACHER(dx, dy, i));
        }

        return true;
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
