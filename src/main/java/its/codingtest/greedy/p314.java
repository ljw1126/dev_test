package its.codingtest.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 만들 수 없는 금액
 * - 직접 풀이 x
 * - 1 ~ (target - 1) 까지 만들 수 있음을 나타낸다
 */
public class p314 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, MAX;
    private static int[] COIN;

    private static void input() {
        N = inputProcessor.nextInt(); // 동전의 개수
        COIN = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            COIN[i] = inputProcessor.nextInt();
            MAX += COIN[i];
        }
    }

    private static void pro() {
        Arrays.sort(COIN, 1, N + 1);

        int target = 1;
        for(int i = 1; i <= N; i++) {
            if(target < COIN[i]) {
                break;
            }
            // 1 ~ (target - 1) 까지 만들 수 있다는 의미
            target += COIN[i];
        }

        sb.append(target);
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
