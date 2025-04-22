package its.codingtest.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 못 생긴 수
 * - 직접 풀이 못함
 * - DP와 three pointer
 * - DP가 이미 처리된 결과값을 가지고 연산 속도 높이는 거라 이 문제도 해당
 */
public class p381 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;

    private static void input() {
        N = inputProcessor.nextInt(); // 1 ~ 1000, n번째 못 생긴 수를 찾는다
    }

    private static void pro() {
        int[] dp = new int[N + 1];
        dp[0] = 1;

        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        int next2 = 2;
        int next3 = 3;
        int next5 = 5;

        // if-else가 아닌 이유는 2 * 3, 3 * 2 중복 되는 경우 갱신을 해줘야 하기 때문
        for (int i = 1; i <= N; i++) {
            dp[i] = Math.min(next2, Math.min(next3, next5));

            if (dp[i] == next2) {
                i2 += 1;
                next2 = dp[i2] * 2;
            }

            if (dp[i] == next3) {
                i3 += 1;
                next3 = dp[i3] * 3;
            }

            if (dp[i] == next5) {
                i5 += 1;
                next5 = dp[i5] * 5;
            }
        }

        sb.append(dp[N - 1]);
        System.out.println(Arrays.toString(dp));
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
