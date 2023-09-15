package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21277_3 {
    static StringBuilder sb = new StringBuilder();

    static int N1, M1, N2, M2;

    static int[][] board;
    static int[][] puzzle;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());

        board = new int[51][51];
        for(int i = 1; i <= N1; i++) {
            String line = br.readLine();

            for(int j = 1; j <= M1; j++) {
                board[i][j] = line.charAt(j - 1) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());

        puzzle = new int[51][51];
        for(int i = 1; i <= N2; i++) {
            String line = br.readLine();

            for(int j = 1; j <= M2; j++) {
                puzzle[i][j] = line.charAt(j - 1) - '0';
            }
        }
    }

    static int[][] rotate(int[][] p, int row, int col) {
        int[][] temp = new int[col + 1][row + 1];

        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                temp[j][row - i + 1] = p[i][j];
            }
        }

        return temp;
    }

    static boolean possible(int shiftX, int shiftY) {

        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= 50; j++) {
                if(board[i][j] == 0) continue;

                // board[i][j] = 1이고 , puzzle도 1이면 false 반환
                int dx = i - shiftX + 1;
                int dy = j - shiftY + 1;
                if(dx >= 1 && dy >= 1 && dx <= N2 && dy <= M2 && puzzle[dx][dy] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static void pro() {
        int ans = Integer.MAX_VALUE;

        for(int rot = 1; rot <= 4; rot++) {
            puzzle = rotate(puzzle, N2, M2);
            int temp = N2;
            N2 = M2;
            M2 = temp;

            for(int shiftX = -50; shiftX <= 50; shiftX++) {
                for(int shiftY = -50; shiftY <= 50; shiftY++) {
                    int row = Math.max(N1, N2 + shiftX - 1) - Math.min(1, shiftX) + 1;
                    int col = Math.max(M1, M2 + shiftY - 1) - Math.min(1, shiftY) + 1;

                    if(row * col < ans && possible(shiftX, shiftY)) {
                        ans = row * col;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
