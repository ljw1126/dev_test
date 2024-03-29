package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 파일 합치기 (골드3) https://www.acmicpc.net/problem/11066
 *
 * 직접 풀이 못함
 * sum[][] 의 경우 (N^2)
 * DP[][] 의 경우 (N^3)
 *
 * 재풀이시 직접 풀이 (240228)
 */
public class BOJ11066 {

    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();

    static int T, N;
    static int[] FILES;
    static long[][] DP;
    static int[][] SUM;


    public static void main(String[] args) throws IOException {
        T = inputProcessor.nextInt();
        while(T > 0) {
            input();

            preprocess();

            //bottomUp();
            //sb.append(DP[1][N]).append(NEW_LINE);

            sb.append(topDown(1, N)).append(NEW_LINE);

            T -= 1;
        }

        output();
    }

    private static void preprocess() {
        SUM = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            SUM[i][i] = FILES[i];
            for(int j = i + 1; j <= N; j++) {
                SUM[i][j] = SUM[i][j - 1] + FILES[j];
            }
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();

        FILES = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            FILES[i] = inputProcessor.nextInt();
        }
    }

    private static void bottomUp() {
        DP = new long[N + 1][N + 1];

        for(int len = 2; len <= N; len++) {
            for(int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;

                DP[i][j] = Integer.MAX_VALUE; // 초기화*
                for(int k = i; k < j; k++) {
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k + 1][j] + SUM[i][j]);
                }
            }
        }
    }

    private static long topDown(int a, int b) {
        DP = new long[N + 1][N + 1];
        // 초기화
        for(int i = 1; i <= N; i++) {
            Arrays.fill(DP[i], 1, N + 1, Integer.MAX_VALUE);
        }

        return rec(a, b);
    }

    private static long rec(int i, int j) {
        if(i == j) return 0L;
        if(DP[i][j] != Integer.MAX_VALUE) return DP[i][j];

        for(int k = i; k < j; k++) {
            DP[i][j] = Math.min(DP[i][j], rec(i, k) + rec(k + 1, j) + SUM[i][j]);
        }

        return DP[i][j];
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
