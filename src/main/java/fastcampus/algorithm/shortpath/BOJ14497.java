package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 주난의 난 (골드4)
 * https://www.acmicpc.net/problem/14497
 */
public class BOJ14497 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int x1, y1, x2, y2;

    static String[] field;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행, 세로
        M = Integer.parseInt(st.nextToken()); // 열, 가로

        st = new StringTokenizer(br.readLine());
        // 주난의 위치
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());

        // 범인의 위치
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());

        field = new String[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            field[i] = st.nextToken();
        }
    }

    static class Info implements Comparable<Info> {
        int x;
        int y;
        int dist;

        public Info(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info info) {
            return dist - info.dist; // 오름차순
        }
    }

    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static void dijkstra(int startX, int startY) {
        Queue<Info> que = new PriorityQueue<>();
        que.add(new Info(startX, startY, 0));

        boolean[][] visit = new boolean[N][M];
        visit[startX][startY] = true;

        while (!que.isEmpty()) {
            Info cur = que.poll();

            if (cur.x == x2 - 1 && cur.y == y2 - 1) {
                System.out.println(cur.dist);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int dx = cur.x + dir[i][0];
                int dy = cur.y + dir[i][1];

                if (dx < 0 || dy < 0 || dx >= N || dy >= M) continue;
                if (visit[dx][dy]) continue;

                visit[dx][dy] = true;
                int dist = (field[dx].charAt(dy) == '0') ? cur.dist : cur.dist + 1;
                que.add(new Info(dx, dy, dist));
            }
        }
    }

    static void pro() {
        dijkstra(x1 - 1, y1 - 1);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
