package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 동물원(실버1)
 * https://www.acmicpc.net/problem/1309
 *
 * - 직접 풀이
 * - 시간복잡도 : O(N)
 * - 아무것도 놓지 않는 경우 1로 둔 DP[0] = 1;
 * - DP[N - 1] + 2 * DP[N - 1] - (DP[i - 1] - DP[i - 2]);
 * -- DP[N - 1] : 빈 우리를 놓는 경우
 * -- 2 * DP[N - 1] : 좌, 우 사자 우리를 놓는 경우, 이때 예외 케이스 제거해야 함
 * --  - (DP[i - 1] - DP[i - 2]) : 예외 케이스 제거 DP[1]과 DP[0]을 생각해보기
 *
 * 최종 점화식이 2 * DP[N - 1] + DP[i - 2] 나옴
 */
public class BOJ1309 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int MOD = 9901;
    static int N;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        DP = new int[N + 1][3]; // 0 : 아무것도 놓지 않는 경우, 1 : 가로에 놓는 경우, 2 : 세로에 놓는 경우
        for(int i = 0; i <= N; i++) Arrays.fill(DP[i], -1);

        DP[0][0] = 1;
        DP[0][1] = 0;
        DP[0][2] = 0;

        DP[1][0] = 1;
        DP[1][1] = 1;
        DP[1][2] = 1;
    }

    private static void pro() {
        //bottomUp();

        int result = 0;
        for(int i = 0; i < 3; i++) {
            result += topDown(N, i);
            result %= MOD;
        }

        sb.append(result);
    }

    private static int topDown(int n, int idx) {
        if(DP[n][idx] != -1 || n < 2) return DP[n][idx];

        if(idx == 0) {
            DP[n][0] = topDown(n - 1, 0) + topDown(n - 1, 1) + topDown(n - 1, 2);
            DP[n][0] %= MOD;
        } else if(idx == 1) {
            DP[n][1] = topDown(n - 1, 0) + topDown(n - 1, 2);
            DP[n][1] %= MOD;
        } else if(idx == 2) {
            DP[n][2] = topDown(n - 1, 0) + topDown(n - 1, 1);
            DP[n][2] %= MOD;
        }

        return DP[n][idx];
    }

    private static void bottomUp() {
        for(int i = 2; i <= N; i++) {
            DP[i][0] = DP[i - 1][0] + DP[i - 1][1] + DP[i - 1][2];
            DP[i][1] = DP[i - 1][0] + DP[i - 1][2];
            DP[i][2] = DP[i - 1][0] + DP[i - 1][1];

            DP[i][0] %= MOD;
            DP[i][1] %= MOD;
            DP[i][2] %= MOD;
        }

        int result = 0;
        for(int value : DP[N]) {
            result += value;
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
