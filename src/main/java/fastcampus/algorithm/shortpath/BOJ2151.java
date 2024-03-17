package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *거울설치(골3)
 * https://www.acmicpc.net/problem/2151
 *
 * - 직접 풀이 못함
 * - 거울을 45도 오른쪽 방향만 생각했는데, 왼쪽 45도, 설치하지 않는 경우도 고려해야 했음
 */
public class BOJ2151 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N;
    static char[][] HOUSE;
    static List<Door> DOORS = new ArrayList<>();

    // 북남동서
    static int[][] DIR = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static class Door {
        int x;
        int y;

        public Door(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        HOUSE = new char[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            String line = inputProcessor.nextLine();
            for(int j = 1; j <= N; j++) {
                HOUSE[i][j] = line.charAt(j - 1);

                if(HOUSE[i][j] == '#') { // 문
                    DOORS.add(new Door(i, j));
                }
            }
        }
    }

    private static void pro() {
        bfs(DOORS.get(0), DOORS.get(1)); // 한쪽 방향으로만 가야 한다고 생각하는데..
    }

    private static class Node implements Comparable<Node>{
        int x;
        int y;
        int dir;
        int mirror;

        public Node(int x, int y, int dir, int mirror) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }

        @Override
        public int compareTo(Node o) {
            return this.mirror - o.mirror; // 오름차순
        }
    }

    private static void bfs(Door start, Door end) {
        boolean[][][] visit = new boolean[N + 1][N + 1][4];
        Queue<Node> que = new PriorityQueue<>();
        for(int i = 0; i < 4; i++) {
            que.add(new Node(start.x, start.y, i, 0));
            visit[start.x][start.y][i] = true;
        }

        int result = Integer.MAX_VALUE;
        while(!que.isEmpty()) {
            Node cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;

            // 도착하는 경우
            if(x == end.x && y == end.y) {
                result = Math.min(result, cur.mirror);
                continue;
            }

            int dx = x + DIR[dir][0];
            int dy = y + DIR[dir][1];

            if(dx < 1 || dy < 1 || dx > N || dy > N) continue;
            if(HOUSE[dx][dy] == '*') continue;
            if(visit[dx][dy][dir]) continue;

            visit[dx][dy][dir] = true; // 방문 처리
            if(HOUSE[dx][dy] == '!') { // 거울인 경우
                // 오른쪽 45도
                que.add(new Node(dx, dy, (dir + 2) % 4, cur.mirror + 1));

                // 왼쪽 45도
                que.add(new Node(dx, dy, (3 - dir), cur.mirror + 1));
            }

            // 설치하지 않는 경우
            que.add(new Node(dx, dy, dir, cur.mirror));
        }

        sb.append(result);
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
                st = new StringTokenizer(nextLine());
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
