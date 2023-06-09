package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간 복잡도 O(N^2) => 테스트 케이스별로 O(2500)
 */
public class BOJ4963 {
    static StringBuilder sb = new StringBuilder();

    static int W, H, CNT;

    static int[][] MAP;

    static boolean[][] VISIT;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        while(!(str = br.readLine()).equals("0 0")) {
            StringTokenizer st = new StringTokenizer(str);
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            MAP = new int[H][W];
            VISIT = new boolean[H][W];

            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++) {
                    MAP[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            CNT = 0;
            pro();
        }

        System.out.println(sb);
        br.close();
    }

    static void bfs(int startX, int startY) {
        Queue<Integer> que = new LinkedList<>();
        que.add(startX);
        que.add(startY);
        VISIT[startX][startY] = true;

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int i = 0; i < 8; i++) {
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if(VISIT[nx][ny]) continue;
                if(MAP[nx][ny] == 0) continue;

                que.add(nx);
                que.add(ny);
                VISIT[nx][ny] = true;
            }
        }
    }

    static void pro() {
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(!VISIT[i][j] && MAP[i][j] == 1) {
                    CNT += 1;
                    bfs(i, j);
                }
            }
        }

        sb.append(CNT).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
