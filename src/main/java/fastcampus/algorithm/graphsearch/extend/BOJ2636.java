package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 치즈 (골드4) https://www.acmicpc.net/problem/2636
 *
 * 참고
 * https://steady-coding.tistory.com/173
 */
public class BOJ2636 {
    static StringBuilder sb = new StringBuilder();

    static int R, C, TIME, COUNT;

    static int[][] FIELD;

    static boolean[][] VISIT;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        VISIT = new boolean[R][C];
        FIELD = new int[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                int value = Integer.parseInt(st.nextToken());
                FIELD[i][j] = value;
            }
        }

        br.close();
    }

    static void bfs(int startX, int startY) {
        Queue<Integer> que = new LinkedList<>();
        que.add(startX);
        que.add(startY);
        VISIT[startX][startY] = true;

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(VISIT[nx][ny]) continue;

                if(FIELD[nx][ny] == 1) {
                    FIELD[nx][ny] = 2;
                    COUNT += 1;
                } else if(FIELD[nx][ny] == 0){
                    que.add(nx);
                    que.add(ny);
                    VISIT[nx][ny] = true;
                }
            }
        }
    }

    static boolean remainCheese() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(FIELD[i][j] == 2) {
                    FIELD[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(FIELD[i][j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    static void initVisit() {
        if(VISIT == null) {
            VISIT = new boolean[R][C];
        } else {
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    VISIT[i][j] = false;
                }
            }
        }
    }

    static void pro() {
        while(remainCheese()) {
            COUNT = 0;
            TIME += 1;
            initVisit();

            bfs(0, 0);
        }

        sb.append(TIME).append("\n").append(COUNT);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
