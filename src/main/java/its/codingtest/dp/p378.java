package its.codingtest.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 백준 - 퇴사(실3)
 * https://www.acmicpc.net/problem/14501
 * <p>
 * - 직접풀이 못함
 * - dp[i] = Math.max(dp[t[i] + i] + p[i], maxValue)
 * 이때 t[i] + i <= N + 1 인 경우에만 오늘이 N = 10이고, i = 8, t = 3일때
 * 상담일에서 당일을 포함해야하니깐 dp[N + 2]로 칸을 늘리고 하는게 연산이 편함
 */
public class p378 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;
    private static int[] T, P;

    private static void input() {
        N = inputProcessor.nextInt();
        T = new int[N + 1];
        P = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            T[i] = inputProcessor.nextInt(); // 상담 일수
            P[i] = inputProcessor.nextInt(); // 페이
        }
    }

    // i번째 페이 + P[T[i]] 페이
    private static void pro() {
        int[] dp = new int[N + 2];
        int max = 0;

        for (int i = N; i > 0; i--) {
            int day = T[i] + i; // 오늘 i일부터 T[i]까지 퇴사 전이라면 페이를 받을 수 있다

            if (day <= N + 1) { // 당일 포함이니깐
                dp[i] = Math.max(max, dp[day] + P[i]);
                max = dp[i];
            } else {
                dp[i] = max;
            }
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dp[i]);
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
