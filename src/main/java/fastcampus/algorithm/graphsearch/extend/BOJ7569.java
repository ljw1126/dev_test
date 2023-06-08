package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * 3차원 배열에 N : 열, M : 행이라서 선언시 주의 요하는 문제
 */
public class BOJ7569 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, H;

    static int[][][] box, distance;

    static int[][] direction = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {1, 0, 0}, {0, -1, 0}, {-1, 0, 0}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로, 열
        M = Integer.parseInt(st.nextToken()); // 가로, 행
        H = Integer.parseInt(st.nextToken()); // 높이

        distance = new int[M + 1][N + 1][H + 1];
        box = new int[M + 1][N + 1][H + 1];
        for(int k = 1; k <= H; k++) {
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        for(int k = 1; k <= H; k++) {
            for(int i = 1; i <= M; i++) {
                for(int j = 1; j <= N; j++) {
                    distance[i][j][k] = -1;
                    if(box[i][j][k] == 1) {
                        que.add(i);
                        que.add(j);
                        que.add(k);
                        distance[i][j][k] = 0;
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

                if(nx < 1 || ny < 1 || nz < 1 || nx > M || ny > N || nz > H) continue;
                if(distance[nx][ny][nz] != -1 || box[nx][ny][nz] != 0) continue;

                que.add(nx);
                que.add(ny);
                que.add(nz);

                distance[nx][ny][nz] = distance[x][y][z] + 1;
            }
        }
    }

    static void pro() {
        bfs();
        int ans = 0;

        // label 을 처음 사용
        Loop1 :
        for(int k = 1; k <= H; k++) {
            for(int i = 1; i <= M; i++) {
                for(int j = 1; j <= N; j++) {
                    if(box[i][j][k] == 0 && distance[i][j][k] == -1) {
                        ans = -1;
                        break Loop1;
                    }

                    if(box[i][j][k] != -1 && ans < distance[i][j][k]) {
                        ans = Math.max(ans, distance[i][j][k]);
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
