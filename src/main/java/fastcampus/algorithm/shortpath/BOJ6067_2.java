package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/** 레이저 통신
* 3차원 배열 활용한 풀이 - 방향 정보 추가하여 중복 방문 방지
*/
public class BOJ6067_2 {

    static int W, H;

    static String[] fieldMap;

    static Point[] points = new Point[2];

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
        int mirror; // 거울 수

        public Info(int x, int y, int dir, int mirror) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }

        @Override
        public int compareTo(Info o) {
            return mirror - o.mirror; // ascending, 오름차순
        }
    }


    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken()); // 가로 (열)
        H = Integer.parseInt(st.nextToken()); // 세로 (행)

        int idx = 0;
        fieldMap = new String[H];
        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            String line = st.nextToken();
            fieldMap[i] = line;

            for(int j = 0; j < W; j++) {
                if(line.charAt(j) == 'C') {
                    points[idx++] = new Point(i, j);
                }
            }
        }

    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static void dijkstra(int startX, int startY) {
        Queue<Info> que = new PriorityQueue<>();
        que.add(new Info(startX, startY, -1, 0)); // 초기 방향 -1

        int[][][] mirrorCnt = new int[H][W][5]; // 3차원 배열로 방향 정보 추가
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                Arrays.fill(mirrorCnt[i][j], Integer.MAX_VALUE);
            }
        }

        while(!que.isEmpty()) {
            Info cur = que.poll();

            if(cur.x == points[1].x && cur.y == points[1].y) {
                System.out.println(cur.mirror);
                return;
            }

            for(int i = 0; i < 4; i++) {
                int dx = cur.x + dir[i][0];
                int dy = cur.y + dir[i][1];

                if(dx < 0 || dy < 0 || dx >= H || dy >= W) continue;
                if(fieldMap[dx].charAt(dy) == '*') continue;

                int mirror = cur.mirror;
                // 초기 방향(-1)아니고, 이전 방향과 다음 방향이 다르면 거울 추가
                if(cur.dir != -1 && cur.dir != i) mirror += 1;

                if(mirrorCnt[dx][dy][i] > mirror) { // 거울 수가 더 적은 경우에만
                    mirrorCnt[dx][dy][i] = mirror;
                    que.add(new Info(dx, dy, i, mirror));
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
