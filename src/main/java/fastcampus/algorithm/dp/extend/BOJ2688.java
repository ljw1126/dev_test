package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 줄어들지 않아(실버1)
 * https://www.acmicpc.net/problem/2688
 *
 * 상향식, 하향식 연습하기 좋은 기본 문제
 * 시간복잡도 : O(64 * 10^2)
 */
public class BOJ2688 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int T, N;
    static long[][] DP;

    public static void main(String[] args) throws IOException {
        //preprocess(); // 전처리 - bottom-up
        pro(); // top-down 초기화

        T = inputProcessor.nextInt();
        while(T > 0) {
            N = inputProcessor.nextInt();

            long result = 0L;
            for(int i = 0; i <= 9; i++) {
                //result += DP[N][i];
                result += topDown(N, i);
            }

            sb.append(result).append("\n");

            T -= 1;
        }

        output();
    }

    private static void pro() {
        DP = new long[65][10];
        Arrays.fill(DP[1], 1);
    }

    private static void preprocess() {
        DP = new long[65][10];
        Arrays.fill(DP[1], 1);

        bottomUp();
    }

    private static void bottomUp() {
        for(int len = 2; len <= 64; len++) {
            for(int i = 0; i <= 9; i++) {
                for(int j = 0; j <= i; j++) {
                    DP[len][i] += DP[len - 1][j];
                }
            }
        }
    }

    private static long topDown(int n, int idx) {
        if(DP[n][idx] != 0) return DP[n][idx];

        for(int k = 0; k <= idx; k++) {
            DP[n][idx] += topDown(n - 1, k);
        }

        return DP[n][idx];
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
