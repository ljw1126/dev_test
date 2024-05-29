package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문자열 지옥에 빠진 호석
 * <p>
 * (실수*) 어떤 문자열이 몇번 등장 했는지 기록할 변수 필요
 * M[문자열 S] = 문자열 S의 등장 횟수
 * 모든 탐색을 미리해서, M이라는 자료구조를 채워 둠
 * => HashMap을 사용하는게 모두 평균 O(1)만큼 소요
 * <p>
 * 탐색 경우의 수
 * = 시작 위치 * (k 문자열 개수만큼 될 때까지 매번 8방향 가능) // 신이 좋아하는 문자열의 길이 : 최대 5
 * = NM * 8^k = 100 * 8^5 = 100 *  32768 (1초안에 가능)
 * <p>
 * 실수
 * - 재귀 함수 종료 조건 실수 (K 개만큼 주어진다 했지, 신이 원하는 단어 길이가 k라고 안함)
 * - HashMap 자료구조 사용하여 전처리 하지 않고 매번 호출하여 시간 초과
 * - 2중 반복문으로 모든 노드에 대한 탐색을 해야하는 부분을 생각하는데 시간 오래 걸림
 * <p>
 * int dx = x + DIR[i][0]; --> (x + DIR[i][0]) >= N 이면 0으로
 * int dy = y + DIR[i][1]; --> (y + DIR[i][1]) >= M 이면 0으로
 * <p>
 * if(dx < 0) dx = N - 1;
 * if(dx >= N) dx = 0;
 * if(dy < 0) dy = M - 1;
 * if(dy >= M) dy = 0;
 */
public class BOJ20166 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MAX_LENGTH = 5;
    private static int N, M, K;
    private static String[][] ALPHABET;
    private static Map<String, Integer> TEXT_MAP = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 행
        M = inputProcessor.nextInt(); // 열
        K = inputProcessor.nextInt(); // 신이 좋아하는 문자열

        ALPHABET = new String[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String text = inputProcessor.nextLine();
            String[] tokens = text.split("");
            for (int j = 1; j <= M; j++) {
                ALPHABET[i][j] = tokens[j - 1];
            }
        }
    }

    private static void pro() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                TEXT_MAP.put(ALPHABET[i][j], TEXT_MAP.getOrDefault(ALPHABET[i][j], 0) + 1);
                rec(i, j, ALPHABET[i][j], 1);
            }
        }
        for (int i = 1; i <= K; i++) {
            String text = inputProcessor.nextLine();
            sb.append(TEXT_MAP.getOrDefault(text, 0)).append("\n");
        }
    }

    private static final int[][] DIR = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    private static void rec(int x, int y, String alphabet, int count) {
        if (count >= MAX_LENGTH) return; // >= 안해서 10배 용량 차이남

        for (int i = 0; i < 8; i++) {
            int dx = x + DIR[i][0];
            int dy = y + DIR[i][1];

            if (dx == 0) {
                dx = N;
            }

            if (dx > N) {
                dx = 1;
            }

            if (dy == 0) {
                dy = M;
            }

            if (dy > M) {
                dy = 1;
            }

            String next = alphabet + ALPHABET[dx][dy];
            TEXT_MAP.put(next, TEXT_MAP.getOrDefault(next, 0) + 1);
            rec(dx, dy, next, count + 1);
        }
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
