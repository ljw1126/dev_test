package its.codingtest.dp;

import org.bouncycastle.util.Arrays;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p380 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;
    private static int[] POWER;

    private static void input() {
        N = inputProcessor.nextInt();
        POWER = new int[N];
        for (int i = 0; i < N; i++) {
            POWER[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int l = 0, r = N - 1;
        while (l < r) {
            int temp = POWER[l];
            POWER[l] = POWER[r];
            POWER[r] = temp;
            l += 1;
            r -= 1;
        }

        int[] dp = new int[N]; // dp[i] = i번째 수를 마지막으로 하는 수열을 길이, 가장 긴 오름차순 수열
        Arrays.fill(dp, 1); // 자기 자신 1

        // 연산을 반복하지 않기 위해 dp 상향식 사용
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (POWER[j] < POWER[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }

        sb.append(N - max);
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
