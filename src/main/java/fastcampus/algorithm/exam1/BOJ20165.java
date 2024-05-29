package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 인내의 도미노 장인 호석 (골드5)
 * <p>
 * 시뮬레이션 문제
 * - 상황 표현
 * - 코드로 정확히 구현 가능한가 확인하는 문제
 * <p>
 * 한번의 공격은 O(N) 시간이, 한 번의 수비는 O(1)의 시간이 걸리므로
 * 총시간 복잡도는 O(R * N)이 된다
 */
public class BOJ20165 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static String NEW_LINE = System.lineSeparator();
    private static int N, M, R;
    private static int[][] BOARD;
    private static boolean[][] RESULT;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 행
        M = inputProcessor.nextInt(); // 열
        R = inputProcessor.nextInt(); // 라운드

        BOARD = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int value = inputProcessor.nextInt();
                BOARD[i][j] = value;
            }
        }

        RESULT = new boolean[N + 1][M + 1];
    }

    private static void pro() {
        int score = 0;
        for (int i = 1; i <= R; i++) {
            score += attack();
            defense();
        }

        sb.append(score).append(NEW_LINE);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sb.append(RESULT[i][j] ? "F" : "S").append(" ");
            }
            sb.append(NEW_LINE);
        }
    }

    private static int attack() {
        int x = inputProcessor.nextInt();
        int y = inputProcessor.nextInt();
        int[] dir = getDirection(inputProcessor.next());

        if (RESULT[x][y]) {
            return 0;
        }

        Deque<Integer> que = new ArrayDeque();
        que.add(x);
        que.add(y);
        que.add(BOARD[x][y]);
        RESULT[x][y] = true;

        int count = 1;

        while (!que.isEmpty()) {
            int _x = que.poll();
            int _y = que.poll();
            int _v = que.poll();

            int dx = _x + dir[0];
            int dy = _y + dir[1];

            if (dx < 1 || dy < 1 || dx > N || dy > M) continue;
            if (_v - 1 <= 0) continue;

            int power = _v - 1;
            if (!RESULT[dx][dy]) {
                count += 1;
                RESULT[dx][dy] = true;
                power = Math.max(power, BOARD[dx][dy]);
            }

            que.add(dx);
            que.add(dy);
            que.add(power);
        }

        return count;
    }

    private static final int[][] DIR = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private static int[] getDirection(String text) {
        switch (text) {
            case "E":
                return DIR[0];
            case "W":
                return DIR[1];
            case "S":
                return DIR[2];
            case "N":
                return DIR[3];
            default:
                throw new RuntimeException();
        }
    }

    private static void defense() {
        int x = inputProcessor.nextInt();
        int y = inputProcessor.nextInt();
        RESULT[x][y] = false;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
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
