package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 오르막수 (실버1)
 * https://www.acmicpc.net/problem/11057
 *
 * 직접 풀이
 * 시간복잡도 : O(N * 10^2)
 * 점화식 : DP[i][j] += DP[i - 1][k] , 이때 k 는 0 ~ j까지
 */
public class BOJ11057 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int MOD = 10007;
    static int[][] DP;
    static int N;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        DP = new int[N + 1][10];
        Arrays.fill(DP[1], 1); // 1 자리인 경우 1로 초기화
    }

    private static void pro() {
        for(int len = 2; len <= N; len++) {
            for(int i = 0; i <= 9; i++) {
                for(int prev = 0; prev <= i; prev++) {
                    DP[len][i] += DP[len - 1][prev];
                    DP[len][i] %= MOD;
                }
            }
        }

        int result = 0;
        for(int i = 0; i <= 9; i++) {
            result += DP[N][i];
            result %= MOD;
        }

        sb.append(result);
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
