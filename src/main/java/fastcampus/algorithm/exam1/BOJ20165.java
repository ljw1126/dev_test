package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 인내의 도미노 장인 호석 (골드5)
 *
 * 시뮬레이션 문제
 * - 상황 표현
 * - 코드로 정확히 구현 가능한가 확인하는 문제
 *
 * 한번의 공격은 O(N) 시간이, 한 번의 수비는 O(1)의 시간이 걸리므로
 * 총시간 복잡도는 O(R * N)이 된다
 */
public class BOJ20165 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, R;

    static int RESULT;

    static int[][] board;
    static char[][] boardStatus;
    static void pro() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 라운드

        board = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boardStatus = new char[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(boardStatus[i], 'S');
        }

        while(R > 0) {
            R -= 1;

            // 공격
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            attack(x, y, dir);

            // 수비
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            boardStatus[x][y] = 'S'; // 다시 세우기
        }
    }

    static void attack(int x, int y, char dir) {
        if(boardStatus[x][y] == 'F') return;

        int dirX = 0;
        int dirY = 0;
        if(dir == 'E') { // 동
            dirY = 1;
        } else if(dir == 'W') { //서
            dirY = -1;
        } else if(dir == 'S') { // 남
            dirX = 1;
        } else if(dir == 'N'){ // 북
            dirX = -1;
        }

        int dx = x;
        int dy = y;
        int cnt = board[x][y];

        while(dx >= 1 && dx <= N && dy >= 1 && dy <= M && cnt >= 1) { //범위 확인
            if(cnt == 1 && boardStatus[dx][dy] == 'F') break;
            if(boardStatus[dx][dy] == 'S') RESULT += 1;

            boardStatus[dx][dy] = 'F';

            cnt = Math.max(cnt - 1, board[dx][dy] - 1); // board[dx][dy] = 0이 아니다 보니 번거로운 if문하나더 추가됨

            dx += dirX;
            dy += dirY;
        }
    }

    static void print() {
        sb.append(RESULT).append("\n");
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                sb.append(boardStatus[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        pro();
        print();
    }
}
