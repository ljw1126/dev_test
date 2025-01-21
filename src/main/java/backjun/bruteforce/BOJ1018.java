package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 체스판 다시 칠하기 - 실버4
 * https://www.acmicpc.net/problem/1018
 * 
 * - 직접 풀이 못함 
 * - 2 * 2 를 그려보고 white, black 보드의 관계를 이해하는게 포인트 였음
 * - white 페인팅 수를 구하면, black은 (64 - white 수)로 구할 수 있었음
 */
public class BOJ1018 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n, m;
    private static String[] board;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();

        board = new String[n];
        for(int i = 0; i < n; i++) {
            board[i] = inputProcessor.nextLine();
        }
    }

    private static void pro() {
        int result = 65;
        for(int i = 0; i <= n - 8; i++) {
            for(int j = 0; j <= m - 8; j++) {
                result = Math.min(result, calculate(i, j, board));
            }
        }

        sb.append(result);
    }

    private static final String[] whiteBoard = {
            "WBWBWBWB",
            "BWBWBWBW"
    };

    private static int calculate(int startX, int startY, String[] board) {
        int repaint = 0;
        for(int i = 0; i < 8; i++) {
            int x = startX + i;
            for(int j = 0; j < 8; j++) {
                int y = startY + j;

                if (board[x].charAt(y) != whiteBoard[x % 2].charAt(j)) { // j 조심 ..
                    repaint += 1;
                }
            }
        }

        return Math.min(repaint, 64 - repaint); // 2 * 2로 그려보면 white로 칠하는 횟수, black으로 칠하는 횟수가 64 - white로 구할 수 있다
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
