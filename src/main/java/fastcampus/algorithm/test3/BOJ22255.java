package fastcampus.algorithm.test3;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 호석사우루스(골2)
 * https://www.acmicpc.net/problem/22255
 *
 * - 직접 풀었는데, 공간 더 차지함
 * - 방향이라는 상태값*이 포함된 다익스트라
 * - 상태값 저장 위한 4차원 배열 쓸 필요없이 3차원만으로도 충분했음
 * - 노드 수 100 * 100, 간선 수 4 * 10^4
 * - 다익스트라 시간복잡도 : O(ElogV)
 */
public class BOJ22255 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, M, S1, E1, S2, E2;
    static int[][] BOARD;
    static int[][][] DIST;

    // 상하 좌우
    static int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        dijkstra(S1, E1, S2, E2);
    }

    private static class Dino implements Comparable<Dino> {
        int x;
        int y;
        int k;
        int dist;

        public Dino(int x, int y, int k, int dist) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.dist = dist;
        }

        @Override
        public int compareTo(Dino o) {
            return this.dist - o.dist;
        }

    }


    private static void dijkstra(int s1, int e1, int s2, int e2) {
        Queue<Dino> que = new PriorityQueue<>();
        que.add(new Dino(s1, e1, 0, 0));

        DIST = new int[N + 1][M + 1][3];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                Arrays.fill(DIST[i][j], Integer.MAX_VALUE);
            }
        }
        DIST[s1][e1][0] = 0;

        int result = Integer.MAX_VALUE;
        while(!que.isEmpty()) {
            Dino cur = que.poll();

            //이거빼먹음
            if(DIST[cur.x][cur.y][cur.k] < cur.dist) continue;

            if(cur.x == s2 && cur.y == e2) {
                result = Math.min(result, cur.dist);
                continue;
            }

            int start = 0;
            int end = 4;

            if(cur.k + 1 == 1) end = 2;
            if(cur.k + 1 == 2) start = 2;

            for(int i = start; i < end; i++) {
                int dx = cur.x + DIR[i][0];
                int dy = cur.y + DIR[i][1];

                if(dx < 1 || dy < 1 || dx > N || dy > M) continue;
                if(BOARD[dx][dy] == -1) continue;

                // next만으로도 상태 정보가 충분했음
                int next = (cur.k + 1) % 3;
                if(DIST[dx][dy][next] > cur.dist + BOARD[dx][dy]) {
                    DIST[dx][dy][next] = cur.dist + BOARD[dx][dy];
                    que.add(new Dino(dx, dy, next, DIST[dx][dy][next]));
                }
            }
        }


        sb.append(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        //시작 좌표
        S1 = inputProcessor.nextInt();
        E1 = inputProcessor.nextInt();

        //종료 좌표
        S2 = inputProcessor.nextInt();
        E2 = inputProcessor.nextInt();

        BOARD = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                BOARD[i][j] = inputProcessor.nextInt();
            }
        }

    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
