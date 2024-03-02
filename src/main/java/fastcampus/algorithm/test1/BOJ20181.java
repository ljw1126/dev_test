package fastcampus.algorithm.test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 꿈틀꿈틀 호석 애벌레(골드2)
 *
 * - 최대치 long
 * - 투포인터 기법 활용
 * - O(N) 시간 복잡도 소요
 */
public class BOJ20181 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, K;
    static int[] DATA;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static class Info {
        int start;
        int energy;

        public Info(int start, int energy) {
            this.start = start;
            this.energy = energy;
        }
    }

    private static void pro() {
        List<Info>[] information = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            information[i] = new ArrayList<>();
        }

        int sum = 0;
        for (int L = 1, R = 0; L <= N; L++) {

            while (R + 1 <= N && sum < K) {
                R += 1;
                sum += DATA[R];
            }

            if (sum >= K) {
                information[R].add(new Info(L, sum - K));
            }

            sum -= DATA[L];
        }


        long[] dp = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1];
            for (Info info : information[i]) {
                dp[i] = Math.max(dp[i], dp[info.start - 1] + info.energy);
            }
        }

        sb.append(dp[N]);
    }

    private static void input() {
        N = inputProcessor.nextInt();
        K = inputProcessor.nextInt();

        DATA = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
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
