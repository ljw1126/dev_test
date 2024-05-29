package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21277_2 {
    static StringBuilder sb = new StringBuilder();

    static int N1, M1;
    static int N2, M2;

    static int[][] board;
    static int[][] puzzle;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new int[51][51];
        st = new StringTokenizer(br.readLine());

        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N1; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M1; j++) {
                board[i][j] = line.charAt(j - 1) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());
        puzzle = new int[N2 + 1][M2 + 1];
        for (int i = 1; i <= N2; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M2; j++) {
                puzzle[i][j] = line.charAt(j - 1) - '0';
            }
        }
    }

    static int[][] rotate(int[][] p, int row, int col) {
        int[][] temp = new int[col + 1][row + 1]; // 90도 회전, 3행 5열 -> 5행 3열 -> 3행 5열

        for (int r = 1; r <= row; r++) { // 5행
            for (int c = 1; c <= col; c++) { // 3열
                temp[c][row - r + 1] = p[r][c];
            }
        }

        for (int c = 1; c <= col; c++) System.out.println(Arrays.toString(temp[c]));

        System.out.println();

        return temp;
    }

    static boolean possible(int shiftX, int shiftY) { // (2, 1)

        for (int x = 1; x <= N1; x++) {
            for (int y = 1; y <= M1; y++) {
                if (board[x][y] == 0) continue;

                // board[x][y] == 1 일 때
                // 예제 입력의 경우 회전 후 (2,2)로 shift 햇을때의 puzzle값이 1인지 확인
                int dx = x - shiftX + 1;
                int dy = y - shiftY + 1;
                if (dx >= 1 && dx <= N2 && dy >= 1 && dy <= M2 && puzzle[dx][dy] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static void pro() {
        for (int c = 1; c <= N1; c++) System.out.println(Arrays.toString(board[c]));

        System.out.println();

        int ans = Integer.MAX_VALUE;
        for (int rot = 1; rot <= 4; rot++) {

            // board는 고정 , puzzle은 (shiftX, shiftY) 좌표 이동
            // 상하 좌우 다 확인해야 하는듯 . 아래 shift 시작을 -50으로 하니 5개 추가로 맞음 (이전 14/19)
            // 일정 음수 영역 -> 어차피 board 범위를 벗어난 위치에 puzzle이 존재하기 대문에 true를 반환한다
            for (int shiftX = -50; shiftX <= 50; shiftX++) {
                for (int shiftY = -50; shiftY <= 50; shiftY++) {
                    // board 고정이고, puzzle shift 했을 때 shift 했을 때 가로, 세로 길이 구함 (예제 1에 해피케이스 생각)
                    int row = Math.max(N1, shiftX + N2 - 1) - Math.min(1, shiftX) + 1;
                    int col = Math.max(M1, shiftY + M2 - 1) - Math.min(1, shiftY) + 1;

                    if (row * col < ans && possible(shiftX, shiftY)) {
                        System.out.println("이동:" + shiftX + "," + shiftY);
                        System.out.println(row + "," + col);
                        ans = Math.min(ans, row * col);
                    }
                }
            }

            puzzle = rotate(puzzle, N2, M2);
            int temp = M2;
            M2 = N2;
            N2 = temp;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
