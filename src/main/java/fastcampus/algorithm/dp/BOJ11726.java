package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 2 * N 타일링 (실버3) https://www.acmicpc.net/problem/11726
 *
 * 점화식 : DP[i] = (DP[i - 1] + DP[i - 2]) % MOD
 * 시간 복잡도 : O(N)
 */
public class BOJ11726 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int MOD = 10007;
    static int N;
    static int[] DP;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
    }

    private static void pro() {
        DP = new int[N + 1];
        DP[1] = 1;

        if(N >= 2) {
            DP[2] = 2;
        }

        for(int i = 3; i <= N; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
            DP[i] %= MOD;
        }

        sb.append(DP[N]);
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
