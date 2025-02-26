package backjun.bruteforce;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 스도쿠 (골4)
 * https://www.acmicpc.net/problem/2580
 *
 * - 3 * 3 그리드 유효성 검사 누락하여 실패
 */
public class BOJ2580 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static final int completed = (1 << 10) - 2;

    private static boolean finish;
    private static int[][] board;
    private static int[] row, col, pos;
    private static List<int[]> targets;

    private static void input() {
        row = new int[9];
        col = new int[9];
        pos = new int[9];

        targets = new ArrayList<>();

        board = new int[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                int v = inputProcessor.nextInt();

                board[i][j] = v;

                if(v == 0) {
                    targets.add(new int[]{i, j});
                    continue;
                }

                int flag = 1 << v;
                row[i] |= flag;
                col[j] |= flag;

                // (2, 2) = 0, (0,3) = 1, (0, 6) = 2
                int group = (i / 3) * 3 + j / 3;
                pos[group] |= flag;
            }
        }
    }

    private static void pro() {
        rec(0);
    }

    private static void rec(int idx) {
        if(finish) return;
        if(idx == targets.size()) {

            for(int i = 0; i < 9; i++) {
                if(row[i] != completed || col[i] != completed || pos[i] != completed) {
                    return;
                }
            }

            finish = true;
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }

            return;
        }

        int[] arr = targets.get(idx);
        int x = arr[0];
        int y = arr[1];
        int group = (x / 3) * 3 + y / 3;

        for(int i = 1; i <= 9; i++) {
            // 가로, 세로, 그룹이 맞아야지 스도쿠
            int flag = 1 << i;
            if((row[x] & flag) != 0) continue;
            if((col[y] & flag) != 0) continue;
            if((pos[group] & flag) != 0) continue;

            row[x] |= flag;
            col[y] |= flag;
            pos[group] |= flag;
            board[x][y] = i;

            rec(idx + 1);

            board[x][y] = 0;
            row[x] &= ~flag;
            col[y] &= ~flag;
            pos[group] &= ~flag;
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
