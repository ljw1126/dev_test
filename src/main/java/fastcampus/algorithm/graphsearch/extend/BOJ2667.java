package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 단지 번호 붙이기 (실버1) https://www.acmicpc.net/problem/2667
 *
 * -시간 복잡도 : O(V + E)
 * -문자열 저장 후 charAt()으로 비교 처리하는 것도 방법
 */
public class BOJ2667 {
    static StringBuilder sb = new StringBuilder();
    static int N, COUNT;
    static int[][] MATRIX;
    static boolean[][] VISIT;
    static int[][] DIR = {{0 , 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void bfs(int startX, int startY) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(startX);
        que.add(startY);
        VISIT[startX][startY] = true;

        COUNT = 1;
        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIR[i][0];
                int ny = y + DIR[i][1];

                if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if(VISIT[nx][ny]) continue;
                if(MATRIX[nx][ny] == 0) continue;

                VISIT[nx][ny] = true;
                COUNT += 1;
                que.add(nx);
                que.add(ny);
            }
        }
    }

    private static void dfs(int x, int y) {
        VISIT[x][y] = true;
        COUNT += 1;

        for(int i = 0; i < 4; i++) {
            int nx = x + DIR[i][0];
            int ny = y + DIR[i][1];

            if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
            if(MATRIX[nx][ny] == 0) continue;
            if(VISIT[nx][ny]) continue;

            dfs(nx, ny);
        }
    }

    private static void pro() {
        List<Integer> result = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(MATRIX[i][j] == 1 && !VISIT[i][j]) {
                    COUNT = 0;
                    dfs(i, j);
                    result.add(COUNT);
                }
            }
        }

        Collections.sort(result);

        sb.append(result.size()).append("\n");
        for(int v : result) {
            sb.append(v).append("\n");
        }
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();

        MATRIX = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            String line = inputProcessor.nextLine();
            for(int j = 1; j <= N; j++) {
                MATRIX[i][j] = line.charAt(j - 1) - '0';
            }
        }


        VISIT= new boolean[N + 1][N + 1];
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static class InputProcessor {
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
