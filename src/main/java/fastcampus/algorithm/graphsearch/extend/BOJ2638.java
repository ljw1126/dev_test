package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2636 도 풀어 보자
public class BOJ2638 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[][] FIELD;

    static boolean[][] VISIT;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        VISIT = new boolean[N][M];

        FIELD = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                FIELD[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void bfs(int startX, int startY) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startX);
        queue.add(startY);
        VISIT[startX][startY] = true;

        while(!queue.isEmpty()) {
            int x = queue.poll(), y = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(VISIT[nx][ny]) continue;

                if(FIELD[nx][ny] >= 1) {
                    FIELD[nx][ny] += 1;
                } else if(FIELD[nx][ny] == 0){
                    queue.add(nx);
                    queue.add(ny);
                    VISIT[nx][ny] = true;
                }
            }
        }
    }

    static void dfs(int x, int y) {
        VISIT[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + DIRECTION[i][0];
            int ny = y + DIRECTION[i][1];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(VISIT[nx][ny]) continue;

            if(FIELD[nx][ny] >= 1) {
                FIELD[nx][ny] += 1;
            } else {
                dfs(nx, ny);
            }
        }
    }

    static void initVisit() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                VISIT[i][j] = false;
            }
        }
    }

    static boolean melt() {
        boolean result = true;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(FIELD[i][j] >= 3) {
                    result = false;
                    FIELD[i][j] = 0;
                } else if(FIELD[i][j] == 2){
                    FIELD[i][j] = 1;
                }
            }
        }

        return result;
    }

    static void pro() {
        int timing = 0;
        while(true) {
            initVisit();

            dfs(0, 0);

            if(melt()) {
                System.out.println(timing);
                break;
            } else {
                timing += 1;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
