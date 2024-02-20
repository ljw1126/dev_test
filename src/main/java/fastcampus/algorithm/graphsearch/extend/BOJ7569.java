package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 토마토(골드5) https://www.acmicpc.net/problem/7569
 *
 * 3차원 배열 선언시 주의 필요
 */
public class BOJ7569 {
    static StringBuilder sb = new StringBuilder();
    static int MAX_VALUE = 1000001;
    static int[][] DIR = {
            {1, 0, 0},
            {-1, 0, 0},
            {0, 1, 0},
            {0, -1, 0},
            {0, 0, 1},
            {0, 0, -1}
    };
    static int N, M, H;
    static int[][][] BOXES;
    static int[][][] DIST;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        bfs();


        int result = 0;

        Loop:
        for(int h = 0; h < H; h++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(BOXES[i][j][h] == 0 && DIST[i][j][h] == MAX_VALUE) {
                        result = -1;
                        break Loop;
                    }

                    if(BOXES[i][j][h] == 0 && DIST[i][j][h] != MAX_VALUE) {
                        result = Math.max(result, DIST[i][j][h]);
                    }
                }
            }
        }

        sb.append(result);
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();

        for(int h = 0; h < H; h++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(BOXES[i][j][h] == 1) { // 익은 토마토
                        que.add(i);
                        que.add(j);
                        que.add(h);
                        DIST[i][j][h] = 0;
                    }
                }
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();
            int z = que.poll();

            for(int i = 0; i < 6; i++) {
                int nx = x + DIR[i][0];
                int ny = y + DIR[i][1];
                int nz = z + DIR[i][2];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= M || nz >= H) continue;
                if(DIST[nx][ny][nz] != MAX_VALUE) continue; // 방문 여부 체크
                if(BOXES[nx][ny][nz] != 0) continue;

                que.add(nx);
                que.add(ny);
                que.add(nz);

                DIST[nx][ny][nz] = DIST[x][y][z] + 1;
            }
        }
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        M = inputProcessor.nextInt();
        N = inputProcessor.nextInt();
        H = inputProcessor.nextInt();

        BOXES = new int[N][M][H];
        DIST = new int[N][M][H];

        // 초기화
        for(int h = 0; h < H; h++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    BOXES[i][j][h] = inputProcessor.nextInt();
                    DIST[i][j][h] = MAX_VALUE;
                }
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
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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
