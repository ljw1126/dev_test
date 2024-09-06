package programmers.mdup;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solve2 {
    private static final Data DATA1 = new Data(new int[][]{
            {0, 0, 1, 0, 0, 0},
            {0, 2, 0, 0, 0, 1},
            {0, 0, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0},
    }, 2, 1, 2, 6);
    private static final Data DATA2 = new Data(new int[][]{
            {0, 0, 0, 1},
            {0, 2, 0, 1},
            {2, 0, 0, 1},
            {0, 2, 0, 1}
    }, 2, 2, 1, 5);


    public static void main(String[] args) {
        pro(DATA1);
        pro(DATA2);
    }

    private static final int[][] dir = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    /**
     * board - 0 : 빈칸, 1 : 폭탄, 2: 장애물
     *
     * @param data
     */
    private static void pro(Data data) {
        int[][] board = data.board;
        int k = data.k; // 폭탄의 폭발 범위 (1 ~ 15)
        int ax = data.ax; // 시작 위치
        int ay = data.ay;

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] effected = calcBoomEffect(board, k, rows, cols);
        int[][] dist = getDist(board, ax, ay, rows, cols);

        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (effected[i][j] || board[i][j] != 0) continue; // 폭발 범위이거나, 빈칸이 아니라면
                
                if (dist[i][j] < minDist) { // 최소 거리를 구한다
                    minDist = dist[i][j];
                }
            }
        }

        System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
    }

    private static boolean[][] calcBoomEffect(int[][] board, int k, int rows, int cols) {
        boolean[][] result = new boolean[rows][cols];
        Deque<Boom> que = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != 1) continue;

                result[i][j] = true;
                for (int z = 0; z < 4; z++) {
                    que.add(new Boom(i, j, z, 0));
                }
            }
        }

        while (!que.isEmpty()) {
            Boom boom = que.poll();

            if (boom.endPower(k)) continue;

            int i = boom.dir;
            int dx = boom.x + dir[i][0];
            int dy = boom.y + dir[i][1];

            if (dx < 0 || dy < 0 || dx >= rows || dy >= cols) continue;
            if (board[dx][dy] != 0) continue;

            result[dx][dy] = true;
            que.add(boom.moveTo(dx, dy));
        }

        return result;
    }

    private static class Boom {
        private int x;
        private int y;
        private int dir;
        private int count;

        public Boom(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        public boolean endPower(int k) {
            return this.count == k;
        }

        public Boom moveTo(int dx, int dy) {
            return new Boom(dx, dy, dir, count + 1);
        }
    }

    private static int[][] getDist(int[][] board, int ax, int ay, int rows, int cols) {
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{ax, ay});

        int[][] dist = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[ax][ay] = 0;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];

                if (dx < 0 || dy < 0 || dx >= rows || dy >= cols) continue;
                if (board[dx][dy] != 0) continue;
                if (dist[dx][dy] != Integer.MAX_VALUE) continue;

                dist[dx][dy] = dist[x][y] + 1;
                que.add(new int[]{dx, dy});
            }
        }

        return dist;
    }


    private static class Data {
        private final int[][] board;
        private final int k;
        private final int ax;
        private final int ay;
        private final int result;

        public Data(int[][] board, int k, int ax, int ay, int result) {
            this.board = board;
            this.k = k;
            this.ax = ax;
            this.ay = ay;
            this.result = result;
        }
    }
}
