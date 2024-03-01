package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  카드 구매 (실버1)
 *  https://www.acmicpc.net/problem/11052
 *
 * - 직접 풀이 못함
 * - 시간 복잡도 : O(N^2)
 */
public class BOJ11052 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N;
    static int[] P, DP;

    public static void main(String[] args) throws IOException {
        input();

        //bottomUp();
        sb.append(topDown(N));

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        P = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            P[i] = inputProcessor.nextInt();
        }

        DP = new int[N + 1];
        Arrays.fill(DP, -1);
        DP[0] = 0;
        DP[1] = P[1];
    }

    private static void bottomUp() { // bottom-up 방식
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= i; j++) {
                DP[i] = Math.max(DP[i], DP[i - j] + P[j]);
            }
        }

        sb.append(DP[N]);
    }

    private static int topDown(int i) { // top-down 방식
        if(i == 0) return 0;
        if(DP[i] != -1) return DP[i];

        for(int j = 1; j <= i; j++) {
            DP[i] = Math.max(DP[i], topDown(i - j) + P[j]);
        }

        return DP[i];
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
