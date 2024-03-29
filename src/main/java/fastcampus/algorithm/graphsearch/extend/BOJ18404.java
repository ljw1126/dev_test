package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 현명한 나이트(실버1)
 * https://www.acmicpc.net/problem/18404
 *
 * - N * N 체스판
 * - M개의 상대편 말
 * - 노드 수 : N * N, 간선의 수 N * N * 8(나이트 이동 방향)
 * - 시간 복잡도 : O(V + E) = O(N * N * 8)
 *
 * - bfs로 이동 거리 구한 후 DIST 조회하도록 함
 */
public class BOJ18404 {
    static StringBuilder sb = new StringBuilder();
    static int MAX_VALUE = 250001;
    static int[][] DIR = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };
    static int N, M, X1, Y1, X2, Y2;
    static int[][] DIST;

    public static void main(String[] args) throws IOException {
        input();

        output();
    }

    private static void pro(int x1, int y1) {
        bfs(x1, y1);
    }

    private static void bfs(int startX, int startY) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(startX);
        que.add(startY);

        for(int i = 1; i <= N; i++) Arrays.fill(DIST[i], MAX_VALUE);
        DIST[startX][startY] = 0;

        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for(int i = 0; i < 8; i++) {
                int nx = x + DIR[i][0];
                int ny = y + DIR[i][1];

                if(nx < 1 || ny < 1 || nx > N || ny > N) continue;

                if(DIST[nx][ny] > DIST[x][y] + 1) {
                    DIST[nx][ny] = DIST[x][y] + 1;
                    que.add(nx);
                    que.add(ny);
                }
            }
        }
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // N * N
        M = inputProcessor.nextInt(); // 상대편 말의 위치

        // 나이트 위치
        X1 = inputProcessor.nextInt();
        Y1 = inputProcessor.nextInt();

        DIST = new int[N + 1][N + 1];
        pro(X1, Y1);

        for(int i = 1; i <= M; i++) {
            X2 = inputProcessor.nextInt();
            Y2 = inputProcessor.nextInt();

            sb.append(DIST[X2][Y2]).append(" ");
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
