package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 토마토(골드5) https://www.acmicpc.net/problem/7569
 *
 * 3차원 배열 선언시 주의 필요
 */
public class BOJ7569 {
    static int M, N, H;

    static int[][][] BOX;
    static int[][][] DAYS;

    static int[][] direction = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, -1, 0}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken()); // 토마토 박스 번호

        DAYS = new int[N + 1][M + 1][H + 1];
        BOX = new int[N + 1][M + 1][H + 1];

        for(int k = 1; k <= H; k++) {
            for(int i = 1; i <= N; i++) { // 세로
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= M; j++) { // 가로
                    BOX[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();

        // 토마토 찾기
        for(int k = 1; k <= H; k++) {
            for(int i = 1; i <= N; i++) { // 세로
                for(int j = 1; j <= M; j++) { // 가로
                    DAYS[i][j][k] = -1;
                    if(BOX[i][j][k] == 1) {
                        que.add(i);
                        que.add(j);
                        que.add(k);
                        DAYS[i][j][k] = 0;
                    }
                }
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll(), z = que.poll();

            for(int i = 0; i < 6; i++) {
                int nx = x + direction[i][0];
                int ny = y + direction[i][1];
                int nz = z + direction[i][2];

                if(nx < 1 || ny < 1 || nz < 1 || nx > N || ny > M || nz > H) continue;
                if(BOX[nx][ny][nz] != 0 || DAYS[nx][ny][nz] != -1) continue;

                que.add(nx);
                que.add(ny);
                que.add(nz);

                DAYS[nx][ny][nz] = DAYS[x][y][z] + 1;
            }
        }
    }

    static void pro() {
        bfs();

        int maxDay = 0;
        Loop1 :
        for(int k = 1; k <= H; k++) {
            for(int i = 1; i <= N; i++) { // 세로
                for(int j = 1; j <= M; j++) { // 가로
                    if(BOX[i][j][k] == 0 && DAYS[i][j][k] == -1) {
                        maxDay = -1;
                        break Loop1;
                    }

                    if(BOX[i][j][k] == 0 && maxDay < DAYS[i][j][k]) {
                        maxDay = DAYS[i][j][k];
                    }
                }
            }
        }

        System.out.println(maxDay);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
