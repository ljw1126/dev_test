package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로탐색(실버1) https://www.acmicpc.net/problem/2178
 *
 * BFS의 특성을 이용하는 문제
 * - 간선의 가중치가 없는 상태에서 거리 구하는 문제
 */
public class BOJ2178 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static String[] GRAPH;

    static boolean[][] VISIT;

    static int[][] DISTANCE;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        DISTANCE = new int[N][M];
        VISIT = new boolean[N][M];

        GRAPH = new String[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            GRAPH[i] = st.nextToken();
        }
    }

    static void bfs(int startX, int startY) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                DISTANCE[i][j] = -1;
            }
        }

        Queue<Integer> que = new LinkedList<>();
        que.add(startX);
        que.add(startX);

        VISIT[startX][startY] = true;
        DISTANCE[startX][startY] = 1; // DISTANCE로 VISIT 역할 가능

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int i = 0; i < 4; i++) { // 격자형 문제
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(GRAPH[nx].charAt(ny) == '0') continue;
                if(VISIT[nx][ny]) continue;

                que.add(nx);
                que.add(ny);
                VISIT[nx][ny] = true;
                DISTANCE[nx][ny] = DISTANCE[x][y] + 1;
            }
        }
    }

    static void pro() {
        bfs(0, 0);
        System.out.println(DISTANCE[N - 1][M - 1]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
