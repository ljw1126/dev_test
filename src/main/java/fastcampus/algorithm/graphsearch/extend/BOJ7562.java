package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 나이트 이동(실버1)
 * https://www.acmicpc.net/problem/7562
 *
 * - I : 300
 * - 노드 : 300 * 300
 * - 간선 : 300 * 300 * 8 (나이트 이동 방향)
 *
 * 시간복잡도 : O(V + E)
 */
public class BOJ7562 {

    static StringBuilder sb = new StringBuilder();
    static int[][] DIR = {
            {2, 1}, {-2, -1}, {-2, 1}, {2, -1},
            {1, 2}, {-1, -2}, {-1, 2}, {1, -2}
    };
    static int T, I, X1, Y1, X2, Y2;
    static int[][] MATRIX;
    static int[][] DIST;

    public static void main(String[] args) throws IOException {
        input();

        output();
    }

    private static void pro(int x1, int y1, int x2, int y2) {
        bfs(x1, y1, x2, y2);

        sb.append(DIST[x2][y2]).append("\n");
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        T = inputProcessor.nextInt();

        while(T > 0) {
            I = inputProcessor.nextInt();
            MATRIX = new int[I][I];
            DIST = new int[I][I];
            // 시작 위치
            X1 = inputProcessor.nextInt();
            Y1 = inputProcessor.nextInt();

            // 도착 위치
            X2 = inputProcessor.nextInt();
            Y2 = inputProcessor.nextInt();

            pro(X1, Y1, X2, Y2);

            T -= 1;
        }
    }

    private static void bfs(int x1, int y1, int x2, int y2) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(x1);
        que.add(y1);

        for(int i = 0; i < I; i++) {
            Arrays.fill(DIST[i], -1);
        }
        DIST[x1][y1] = 0;

        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for(int i = 0; i < 8; i++) {
                int nx = x + DIR[i][0];
                int ny = y + DIR[i][1];

                if(nx < 0 || ny < 0 || nx >= I || ny >= I) continue;
                if(DIST[nx][ny] != -1) continue;

                DIST[nx][ny] = DIST[x][y] + 1;
                que.add(nx);
                que.add(ny);
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
