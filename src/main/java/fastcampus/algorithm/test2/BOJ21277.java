package fastcampus.algorithm.test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 짠돌이 호석(골3)
 * https://www.acmicpc.net/problem/21277
 *
 * - 직접 풀이 못함
 * - 시간복잡도 : 4회전 * 100 * 100 * 50 * 50 = 4 * 25 * 10^6 = 10^8 = 1억
 * - 회전은 구현했는데, shift 했을때 puzzle과 Board를 비교하는 방법에 대해 떠올리지 못함
 * -- (2,2)로 shift 하고 BOARD에 합칠때 puzzle 좌표를 그대로 유지한 상태로 보면 좀 더 이해가 됨 (happy case)
 * -- 넓이와 높이를 구할 때 Math.min 처리해주는 이유는 shift 를 (0, y) 위치로 했을 때를 생각해 볼 것
 */
public class BOJ21277 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int[][] BOARD, PUZZLE2;
    static int N1, M1, N2, M2;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    /**
     * Math.min()의 경우 음수 영역에 이동한 경우 처리 위해
     * - (0,2)로 이동시 최대 width는 6이 되야 함
     */
    private static void pro() {

        int[][] rotated = PUZZLE2;
        int ans = Integer.MAX_VALUE;
        for(int r = 1; r <= 4; r++) {

            for(int x = -50; x <= 50; x++) {
                for(int y = -50; y <= 50; y++) {
                    if(isPossible(x, y, rotated)) {
                        int width = Math.max(N1, N2 + x - 1) - Math.min(1, x) + 1;
                        int height = Math.max(M1, M2 + y - 1) - Math.min(1, y) + 1;

                        ans = Math.min(ans, width * height);
                    }
                }
            }

            if(r < 4) {
                int temp = N2;
                N2 = M2;
                M2 = temp;
                rotated = rotate(rotated, N2, M2);
            }
        }

        sb.append(ans);
    }


    /**
     * BOARD[x][y] = 1인 영역에 대해서만 확인한다
     * 매칭을 했을 때 이미지 때문에 헷갈리는데,
     * 표를 그려보고 BOARD (2,2) 위치에 puzzle (1,1)을 매핑시켜서 그려보면 이해가 된다
     */
    private static boolean isPossible(int shiftX, int shiftY, int[][] puzzle) {
        for(int x = 1; x <= N1; x++) {
            for(int y = 1; y <= M1; y++) {
                if(BOARD[x][y] == 0) continue;

                int dx = x - shiftX + 1;
                int dy = y - shiftY + 1;
                if(dx >= 1 && dx <= N2 && dy >= 1 && dy <= M2 && puzzle[dx][dy] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[][] rotate(int[][] puzzle, int row, int col) {
        int[][] result = new int[row + 1][col+ 1];

        for(int r = 1; r <= row; r++) {
            for(int c = 1; c <= col; c++) {
                result[r][c] = puzzle[col - c + 1][r];
            }
        }

        return result;
    }

    private static void input() {
        BOARD = new int[51][51];

        N1 = inputProcessor.nextInt();
        M1 = inputProcessor.nextInt();
        for(int i = 1; i <= N1; i++) {
            String line = inputProcessor.nextLine();
            String[] tokens = line.split("");
            for(int j = 1; j <= M1; j++) {
                BOARD[i][j] = Integer.parseInt(tokens[j - 1]);
            }
        }

        N2 = inputProcessor.nextInt();
        M2 = inputProcessor.nextInt();
        PUZZLE2 = new int[N2 + 1][M2 + 1];
        for(int i = 1; i <= N2; i++) {
            String line = inputProcessor.nextLine();
            String[] tokens = line.split("");
            for(int j = 1; j <= M2; j++) {
                PUZZLE2[i][j] = Integer.parseInt(tokens[j - 1]);
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
            while(st == null || !st.hasMoreElements()) {
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
