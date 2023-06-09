package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * R행 C열 (최대 50 이하)
 *
 * 비어 있는 곳 '.'
 * 물이 차있는 지역 '*'
 * 돌 'X'
 * 비버의 굴 'D'
 * 고슴도치 위치 'S'
 *
 * 매분 마다 고슴도치 이동, 물도 비어있는 칸으로 확장됨
 * - 물과 고슴도치는 돌을 통과할 수 없다.
 * - 고슴도치는 물이 차있는 지역으로 이동 불가
 * - 물도 비버의 소굴로 이동불가
 * - (중요) 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찾을 예정인 칸에 고슴도치는 이동할 수 없다
 *
 *
 * 물이 차는 시간 구할 경우 : O(N^2)
 * 고슴도치 이동 구할 경우 : O(N^2)
 *
 * 시간 복잡도 : O(N^2 + N^2) = O(5000) 정도 연산 수행
 */
public class BOJ3055 {
    static StringBuilder sb = new StringBuilder();

    static int R, C;

    static String[] MAP;

    static int[][] WATER_TIMING, HEDGEHOG_DIST;

    static boolean[][] VISIT;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        WATER_TIMING = new int[R][C];
        HEDGEHOG_DIST = new int[R][C];

        MAP = new String[R];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            MAP[i] = st.nextToken();
        }
    }

    static void initVisitArray() {
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

    static void calWaterTiming() {
        Queue<Integer> que = new LinkedList<>();

        initVisitArray();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(MAP[i].charAt(j) == '*') {
                    que.add(i);
                    que.add(j);
                    VISIT[i][j] = true;
                }
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(VISIT[nx][ny]) continue;
                if(MAP[nx].charAt(ny) == '.') {
                    VISIT[nx][ny] = true;
                    WATER_TIMING[nx][ny] = WATER_TIMING[x][y] + 1;
                    que.add(nx);
                    que.add(ny);
                }
            }
        }
    }

    static void moveHedgehog() {
        Queue<Integer> que = new LinkedList<>();

        initVisitArray();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(MAP[i].charAt(j) == 'S') {
                    HEDGEHOG_DIST[i][j] = 0;
                    VISIT[i][j] = true;
                    que.add(i);
                    que.add(j);
                }
            }
        }

        // 고슴도치 이동 시작
        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(VISIT[nx][ny]) continue;
                // 도착지, 물, 시작점의 경우 0이기 때문에 조건 필터링 필요
                if(WATER_TIMING[nx][ny] != 0 && WATER_TIMING[nx][ny] <= HEDGEHOG_DIST[x][y] + 1) continue;

                if(MAP[nx].charAt(ny) == '.'|| MAP[nx].charAt(ny) == 'D') {
                    que.add(nx);
                    que.add(ny);
                    VISIT[nx][ny] = true;
                    HEDGEHOG_DIST[nx][ny] = HEDGEHOG_DIST[x][y] + 1;
                }
            }
        }
    }

    static void pro() {
        calWaterTiming();

        moveHedgehog();

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(MAP[i].charAt(j) == 'D') {
                    if(HEDGEHOG_DIST[i][j] == 0) sb.append("KAKTUS");
                    else sb.append(HEDGEHOG_DIST[i][j]);
                }
            }
        }

        System.out.println(sb);

        for(int[] i : WATER_TIMING) System.out.println(Arrays.toString(i));
        for(int[] i : HEDGEHOG_DIST) System.out.println(Arrays.toString(i));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
