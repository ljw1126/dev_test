package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20165_SOL {
    static StringBuilder sb = new StringBuilder();

    static int N, M, R;
    static int RESULT;

    static int[][] domino, backup;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 라운드

        domino = new int[N + 1][M + 1];
        backup = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                domino[i][j] = Integer.parseInt(st.nextToken());
                backup[i][j] = domino[i][j];
            }
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
            domino[x][y] = backup[x][y];
        }
    }

    static void attack(int x, int y, char dir) {
        if(domino[x][y] == 0) return;

        int dirX = 0;
        int dirY = 0;
        if(dir == 'E') dirY = 1;
        else if(dir == 'W') dirY = -1;
        else if(dir == 'S') dirX = 1;
        else if(dir == 'N') dirX = -1;

        int cnt = domino[x][y];
        int dx = x;
        int dy = y;
        if(dx >= 1 && dx <= N && dy >= 1 && dy >= M && cnt >= 1) {
            if(domino[x][y] != 0) RESULT += 1;

            cnt = Math.max(cnt - 1, domino[dx][dy] - 1); // F는 0이 되어 있으니 -1이 되어 종료

            domino[dx][dy] = 0;

            dx += dirX;
            dy += dirY;
        }
    }

    static void pro() {
        sb.append(RESULT).append("\n");

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(domino[i][j] == 0) sb.append("F ");
                else sb.append("S ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
