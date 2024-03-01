package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 스티커(실버1)
 * https://www.acmicpc.net/problem/9465
 *
 * 직접 품, 최대치 10^7
 * 시간복잡도 : O(N)
 */
public class BOJ9465 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int T, N;
    static int[][] DATA;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        T = inputProcessor.nextInt();
        while(T > 0) {
            input();
            pro();
            T -= 1;
        }

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        DATA = new int[3][N + 1];
        for(int i = 1; i <= 2; i++) {
            for(int j = 1; j <= N; j++) {
                DATA[i][j] = inputProcessor.nextInt();
            }
        }

        DP = new int[N + 1][2];
        for(int i = 1; i <= N; i++) Arrays.fill(DP[i], -1);

        DP[0][0] = 0;
        DP[0][1] = 0;
        DP[1][0] = DATA[1][1];
        DP[1][1] = DATA[2][1];
    }

    private static void pro() {
        //bottomUp();

        // top-down
        sb.append(Math.max(topDown(N, 0), topDown(N , 1))).append("\n");
    }

    private static int topDown(int n, int idx) {
        if(n < 0) return 0;
        if(DP[n][idx] != -1) return DP[n][idx];

        if(idx == 0) {
            // 앞에 스티커를 때거나, n - 2 스티커 중 제일 큰 거 선택하거나
            DP[n][0] = topDown(n - 1, 1) + DATA[1][n];
            DP[n][0] = Math.max(DP[n][0], Math.max(topDown(n - 2, 0), topDown(n - 2, 1)) + DATA[1][n]);
        } else if(idx == 1) {
            DP[n][1] = topDown(n - 1, 0) + DATA[2][n];
            DP[n][1] = Math.max(DP[n][1], Math.max(topDown(n - 2, 0), topDown(n - 2, 1)) + DATA[2][n]);
        }

        return DP[n][idx];
    }

    private static void bottomUp() {
        for(int i = 2; i <= N; i++) {
            DP[i][0] = Math.max(DP[i - 1][1], Math.max(DP[i - 2][0], DP[i - 2][1])) + DATA[1][i];
            DP[i][1] = Math.max(DP[i - 1][0], Math.max(DP[i - 2][0], DP[i - 2][1])) + DATA[2][i];
        }

        sb.append(Math.max(DP[N][0], DP[N][1])).append("\n");
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
