package fastcampus.algorithm.test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문자열 지옥에 빠진 호석(골4)
 * https://www.acmicpc.net/problem/20166
 *
 * - 직접 풀이
 * - 탐색 경우의 수
 * = 시작 위치 * (k 문자열 개수만큼 될 때까지 매번 8방향 가능) // 신이 좋아하는 문자열의 길이 : 최대 5
 * = NM * 8^k = 100 * 8^5 = 100 *  32768 (1초안에 가능)
 */
public class BOJ20166 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();
    static int FIVE = 5;
    static String[][] ARR;
    static int[][] DIR = {
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0},
            {1, 1},
            {1, -1},
            {-1, -1},
            {-1, 1}
    };

    static int N, M, K;
    static Map<String, Integer> COUNT_MAP = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();

        pro();
        for(int i = 1; i <= K; i++) {
            String text = inputProcessor.nextLine();
            sb.append(COUNT_MAP.getOrDefault(text, 0)).append(NEW_LINE);
        }

        output();
    }

    private static void pro() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                COUNT_MAP.put(ARR[i][j], COUNT_MAP.getOrDefault(ARR[i][j], 0) + 1);
                rec(1, i, j, ARR[i][j]);
            }
        }
    }

    private static void rec(int depth, int x, int y, String text) {
        if(depth == FIVE) {
            return;
        }

        for(int i = 0; i < 8; i++) {
            int dx = x + DIR[i][0];
            if(dx < 1) dx = N;
            if(dx > N) dx = 1;

            int dy = y + DIR[i][1];
            if(dy < 1) dy = M;
            if(dy > M) dy = 1;

            String next = text + ARR[dx][dy];
            COUNT_MAP.put(next, COUNT_MAP.getOrDefault(next, 0) + 1);
            rec(depth + 1, dx, dy, next);
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();
        K = inputProcessor.nextInt();

        ARR = new String[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            String text = inputProcessor.nextLine();
            String[] tokens = text.split("");
            for(int j = 1; j <= M; j++) {
                ARR[i][j] = tokens[j - 1];
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
