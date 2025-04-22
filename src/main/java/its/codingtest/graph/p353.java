package its.codingtest.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 - 인구이동(골4)
 * https://www.acmicpc.net/problem/16234
 * <p>
 * - 직접 풀이 못함
 * - groupNo == N * N 일때 종료 조건
 * - bfs를 돌때 union, cnt, sum 다 구하는거 생각하지 못함
 * - 시간 복잡도는 O(2000(V + E)), 인구 이동 발생하는 일수가 2000번 보다 작거나 같은 입력만 주어지므로 bfs는 보통 O(V + E)
 */
public class p353 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static final int[][] DIR = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    private static int N, L, R;
    private static int[][] COUNTRY_INFO;

    private static void input() {
        N = inputProcessor.nextInt(); // N * N
        L = inputProcessor.nextInt();
        R = inputProcessor.nextInt();

        COUNTRY_INFO = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                COUNTRY_INFO[i][j] = inputProcessor.nextInt();
            }
        }
    }

    private static void pro() {
        int days = 0;
        int[][] groups = new int[N + 1][N + 1];
        while (true) {
            for (int i = 1; i <= N; i++) {
                Arrays.fill(groups[i], 1, N + 1, -1);
            }

            int groupNo = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (groups[i][j] == -1) {
                        groups[i][j] = groupNo;
                        bfs(i, j, groups);
                        groupNo += 1;
                    }
                }
            }

            if (groupNo == N * N) break; // 기가 막히다

            days += 1;
        }

        sb.append(days);
    }

    // bfs를 돌때 연합이랑, 카운터 다 구하네
    private static void bfs(int startX, int startY, int[][] groups) {
        Deque<Country> queue = new ArrayDeque<>();
        queue.add(new Country(startX, startY));

        int sum = COUNTRY_INFO[startX][startY];
        int cnt = 1;

        List<Country> union = new ArrayList<>();
        union.add(new Country(startX, startY));

        while (!queue.isEmpty()) {
            Country cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int dx = cur.x + DIR[i][0];
                int dy = cur.y + DIR[i][1];

                if (dx < 1 || dy < 1 || dx > N || dy > N) continue;
                if (groups[dx][dy] != -1) continue;

                int diff = Math.abs(COUNTRY_INFO[cur.x][cur.y] - COUNTRY_INFO[dx][dy]);
                if (L <= diff && diff <= R) {
                    groups[dx][dy] = groups[cur.x][cur.y];
                    Country next = new Country(dx, dy);
                    queue.add(next);

                    union.add(next);
                    sum += COUNTRY_INFO[dx][dy];
                    cnt += 1;
                }
            }
        }

        if (cnt == 1) return;

        int avg = sum / cnt;
        for (Country c : union) {
            COUNTRY_INFO[c.x][c.y] = avg;
        }
    }

    private static class Country {
        private final int x;
        private final int y;

        public Country(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

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
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
