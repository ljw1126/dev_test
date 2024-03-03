package fastcampus.algorithm.test3;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ22251 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    // 1 : LEN ON, 0 : LED OFF
    static int[][] DIGIT = {
            {1, 1, 1, 0, 1, 1, 1, 1},
            {0, 0, 1, 0, 0, 1, 0, 0},
            {1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 0, 1, 1, 1},
            {0, 1, 1, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1}
    };

    static int N, K, P, X;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        int cnt = 0;

        for(int floor = 1; floor <= N; floor++) {
            if(floor == X) continue;

            if(isPossible(X, floor)) {
                cnt += 1;
            }
        }

        sb.append(cnt);
    }

    private static boolean isPossible(int cur, int floor) {
        int v1 = cur;
        int v2 = floor;
        int cnt = 0;
        for(int i = 1; i <= K; i++) { // k 자리수
            int d1 = v1 % 10;
            int d2 = v2 % 10;

            cnt += diffCount(d1, d2);

            v1 /= 10;
            v2 /= 10;
        }

        return cnt <= P;
    }

    private static int diffCount(int d1, int d2) {
        int cnt = 0;
        for(int j = 0; j <= 6; j++) {
            if(DIGIT[d1][j] != DIGIT[d2][j]) {
                cnt += 1;
            }
        }
        return cnt;
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 1 ~ N층
        K = inputProcessor.nextInt(); // 자리수
        P = inputProcessor.nextInt(); // 최대 1 ~ P개 LED 반전 가능
        X = inputProcessor.nextInt(); // 현재 위치한 층
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
