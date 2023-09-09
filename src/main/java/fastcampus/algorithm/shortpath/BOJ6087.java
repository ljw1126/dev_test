package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 레이저 통신(골드3)
 * https://www.acmicpc.net/problem/6087
 */
public class BOJ6087 {
    static StringBuilder sb = new StringBuilder();

    static int W, H;
    static char[][] board;

    static Point[] points = new Point[2]; // 0 : 시작, 1 : 종료

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // 레이저로 연결해야 하는 칸 정보 (2개)
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Info implements Comparable<Info> {
        int x;
        int y;

        int dir; // 이전 방향

        int window; // 사용한 거울 개수

        public Info(int x, int y, int dir, int window) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.window = window;
        }

        @Override
        public int compareTo(Info info) {
            return window - info.window;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return x == info.x && y == info.y && dir == info.dir && window == info.window;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, dir, window);
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken()); // 열 (y축)
        H = Integer.parseInt(st.nextToken()); // 행 (x축)

        int idx = 0;
        board = new char[H][W];
        for(int i = 0; i < H; i++) {
            String line = br.readLine();
            for(int j = 0; j < W; j++) {
                if(line.charAt(j) == 'C') { //
                    points[idx++] = new Point(i, j);
                }

                board[i][j] = line.charAt(j);
            }
        }
    }

    static void dijkstra(int startX, int startY) {
        Queue<Info> que = new PriorityQueue<>();
        que.add(new Info(startX, startY,-1, 0)); // 초기 방향은 -1

        int[][] windows = new int[H][W];
        for(int i = 0; i < H; i++) Arrays.fill(windows[i], Integer.MAX_VALUE);

        windows[startX][startY] = -1;

        Set<Info> unique = new HashSet<>();
        unique.add(new Info(startX, startY,-1, 0));

        // 같은 방향이면 거울이 필요없음
        while(!que.isEmpty()) {
            Info cur = que.poll();

            if(points[1].x == cur.x && points[1].y == cur.y) { // 종료
                System.out.println(cur.window);
                return;
            }

            for(int i = 0; i < 4; i++) {
                int dx = cur.x + dir[i][0];
                int dy = cur.y + dir[i][1];

                if(dx < 0 || dy < 0 || dx >= H || dy >= W) continue;
                if(board[dx][dy] == '*') continue; // * : 벽

                int window = cur.window;
                if(cur.dir != -1 && cur.dir != i) window += 1;

                // 거리가 줄거나, 같은 칸만 방문하면서 중복 방문처리 하지 않는 경우 메모리 초과 발생
                // https://www.acmicpc.net/board/view/109356
                if(windows[dx][dy] >= window) {
                    Info next = new Info(dx, dy, i, window);
                    if(unique.contains(next)) continue;

                    windows[dx][dy] = window;
                    que.add(new Info(dx, dy, i, window));
                    unique.add(next);
                }
            }
        }
    }

    static void pro() {
        dijkstra(points[0].x, points[0].y);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
