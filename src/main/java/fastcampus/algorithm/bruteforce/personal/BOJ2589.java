package fastcampus.algorithm.bruteforce.personal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 보물섬(골5)
 * https://www.acmicpc.net/problem/2589
 * <p>
 * - 가장 긴 최단 거리 찾는 문제(말이 꼬아서 시간 소비)
 * - 질문 게시판 참고해서 직접 풀이
 */
public class BOJ2589 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int ROW, COL, RESULT;
    private static String[] FIELDS;
    private static List<Land> LAND = new ArrayList<>();

    private static class Land {
        private int x;
        private int y;

        public Land(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    // 가장 긴 시간이 걸리는 육지 두곳에 나뉘어 묻혀 있다
    // 가장 긴 시간이 걸리는 육지 두곳의 최단거리
    private static void input() {
        ROW = inputProcessor.nextInt();
        COL = inputProcessor.nextInt();

        FIELDS = new String[ROW];
        for (int i = 0; i < ROW; i++) {
            FIELDS[i] = inputProcessor.nextLine();
            for (int j = 0; j < COL; j++) {
                if (FIELDS[i].charAt(j) == 'L') {
                    LAND.add(new Land(i, j));
                }
            }
        }

        RESULT = 2501;
    }

    private static final int[][] DIR = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    private static void pro() {
        int lng = 0;
        for (Land land : LAND) {
            int dist = bfs(land);
            if (lng < dist) {
                lng = dist;
                RESULT = dist;
            }
        }
        sb.append(RESULT);
    }

    // "보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다."
    // = 최단 거리로 갔을때 가장 긴 거리 찾는 문제
    private static int bfs(Land land) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(land.x);
        que.add(land.y);
        que.add(0);

        boolean[][] visited = new boolean[ROW][COL];
        visited[land.x][land.y] = true;

        int result = 0;
        while (!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();
            int dist = que.poll();

            if (result < dist) {
                result = dist;
            }

            for (int i = 0; i < 4; i++) {
                int dx = x + DIR[i][0];
                int dy = y + DIR[i][1];

                if (dx < 0 || dy < 0 || dx >= ROW || dy >= COL) continue;
                if (FIELDS[dx].charAt(dy) == 'W') continue;
                if (visited[dx][dy]) continue;

                que.add(dx);
                que.add(dy);
                que.add(dist + 1);
                visited[dx][dy] = true;
            }
        }

        return result;
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
            while (st == null || !st.hasMoreElements()) {
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
