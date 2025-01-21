package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 미친로봇(골4)
 * https://www.acmicpc.net/problem/1405
 *
 * - 백트래킹 문제
 * - 직접 풀이 못함
 * - 로봇이 같은 곳을 한 번보다 많이 이동하지 않을때 로봇의 이동경로가 단순하다
 *   - 이는 곧 방문했던 위치를 재방문하지 않는걸 뜻함
 * - 최대 로봇이 14번 움직일 수 있기 때문에 방문 배열 크기를 boolean[31][31]로 잡는다
 *   - x,y가 1 ~ 29까지 가능
 * - 예제1의 경우 2 25 25 25 25 로 각각 1/4에 해당한다
 *   - (15,15)에서 시작할대 n = 2번 이동할 대
 *     - 동 동 1/16
 *     - 동 남 1/16
 *     - 동 북 1/16
 *     - .. 총 12개가 구해지고 더하면 12/16 = 3/4 = 0.75가 된다
 */
public class BOJ1405 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    // 동, 서, 남, 북
    private static final int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private static int n;
    private static double[] percentage;
    private static double result;

    private static void input() {
        n = inputProcessor.nextInt();

        percentage = new double[4];
        for(int i = 0; i < 4; i++) {
            percentage[i] = inputProcessor.nextInt() * 0.01;
        }
    }

    private static void pro() {
        boolean[][] visited = new boolean[31][31];

        // 시작점 방문 처리
        visited[15][15] = true;
        rec(15, 15, 0, visited, 1.0);

        sb.append(result);
    }

    // N은 14보다 작거나 같기 때문에 (15,15) 시작할 경우 x,y 각각 1 ~ 29까지가 최대 범위
    private static void rec(int x, int y, int cnt, boolean[][] visited, double total) {
        if(cnt == n) {
            result += total;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx <= 0 || dy <= 0 || dx >= 30 || dy >= 30) continue;
            if(visited[dx][dy]) continue;

            visited[dx][dy] = true;
            rec(dx, dy, cnt + 1, visited, total * percentage[i]);
            visited[dx][dy] = false;
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
