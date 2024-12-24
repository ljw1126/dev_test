package its.codingtest.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class p315 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, M;
    private static int[] BALLS;

    private static void input() {
        N = inputProcessor.nextInt(); // 볼링공의 개수
        M = inputProcessor.nextInt(); // 공의 최대 무게

        BALLS = new int[M + 1]; // 무케별로 카운팅
        for(int i = 1; i <= N; i++) {
            int v = inputProcessor.nextInt();
            BALLS[v] += 1;
        }
    }

    private static void pro() {
        int result = 0;

        // 훨씬 깔끔하네 .. 결국 자기 자신을 뺀 개수에, 현재 무게의 개수를 곱하면 조합이 구해지지
        for(int i = 1; i <= M; i++) {
            N -= BALLS[i];
            result += (BALLS[i] * N);
        }

        sb.append(result);
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
