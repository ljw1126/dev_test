package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 짠돌이 호석(골드3)
 * https://www.acmicpc.net/problem/21277
 * <p>
 * - 구현 문제
 * - 직접 풀이 못함 (난이도 높음)
 * - 4회전 > x, y 좌표 2중 for문 > 가능하냐
 * <p>
 * 중요*
 * shift 했을 때의 row, col 계산
 * rotate 한쪽 처리
 * 그리고 한쪽을 고정 시키고, 순차 조회 했을 때 rotate 한 puzzle 과 shift 비용 연산하여 겹치지만 않는 경우를 찾네 ..
 * <p>
 * 240529. 시간은 걸렸지만 직접 풀이, (0,0) 기준으로 하니 좀 쉽게 풀이 한듯함
 */
public class BOJ21277 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N1, M1, N2, M2;
    private static int[][] WINDOW, PUZZLE;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N1 = inputProcessor.nextInt();
        M1 = inputProcessor.nextInt();
        WINDOW = new int[N1][M1];
        for (int i = 0; i < N1; i++) {
            String line = inputProcessor.nextLine();
            for (int j = 0; j < M1; j++) {
                WINDOW[i][j] = line.charAt(j) - '0';
            }
        }

        N2 = inputProcessor.nextInt();
        M2 = inputProcessor.nextInt();
        PUZZLE = new int[N2][M2];
        for (int i = 0; i < N2; i++) {
            String line = inputProcessor.nextLine();
            for (int j = 0; j < M2; j++) {
                PUZZLE[i][j] = line.charAt(j) - '0';
            }
        }
    }

    private static void pro() {
        int[][] copied = new int[N2][M2];
        for (int i = 0; i < N2; i++) {
            for (int j = 0; j < M2; j++) {
                copied[i][j] = PUZZLE[i][j];
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= 4; i++) {
            for (int x = -50; x <= 50; x++) {
                for (int y = -50; y <= 50; y++) {
                    if (isPossible(x, y, copied, N2, M2)) {
                        int minW = Math.min(0, y);
                        int maxW = Math.max(M1, y + M2);
                        int minH = Math.min(0, x);
                        int maxH = Math.max(N1, x + N2);

                        int area = (maxW - minW) * (maxH - minH);
                        result = Math.min(result, area);
                    }
                }
            }

            if (i == 4) break;

            int temp = N2;
            N2 = M2;
            M2 = temp;
            copied = rotate(copied, N2, M2);
        }

        sb.append(result);
    }

    // 좌표 기준으로 퍼즐일 끼울수 있는가 ?
    // puzzle 이 1인 영역에 대해서만, window도 1 인 경우 false 반환
    private static boolean isPossible(int x, int y, int[][] puzzle, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (puzzle[i][j] == 0) continue;

                int dx = i + x;
                int dy = j + y;

                if (dx >= 0 && dx < N1 && dy >= 0 && dy < M1 && WINDOW[dx][dy] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[][] rotate(int[][] puzzle, int row, int col) {
        int[][] rotated = new int[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                rotated[r][c] = puzzle[col - c - 1][r];
            }
        }

        return rotated;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        private StringTokenizer st;
        private BufferedReader br;

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
