package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 알파벳(골4)
 * https://www.acmicpc.net/problem/1987
 *
 * 직접 풀이
 */
public class BOJ1987 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int R, C;
    private static char[][] board;

    private static void input() {
        R = inputProcessor.nextInt(); // 세로
        C = inputProcessor.nextInt(); // 가로

        board = new char[R][C];
        for(int i = 0; i < R; i++) {
            String line = inputProcessor.nextLine();
            for(int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }
    }

    private static void pro() {
        boolean[] alphabet = new boolean[27];
        alphabet[board[0][0] - 'A'] = true;

        sb.append(rec(0, 0, 1, alphabet));
    }

    private static int[][] dir = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static int rec(int x, int y, int dist, boolean[] alphabet) {
        int result = dist;
        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx < 0 || dy < 0 || dx >= R || dy >= C) continue;
            if(alphabet[board[dx][dy] - 'A']) continue;

            alphabet[board[dx][dy] - 'A'] = true;
            result = Math.max(result, rec(dx, dy, dist + 1, alphabet));
            alphabet[board[dx][dy] - 'A'] = false;
        }

        return result;
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
