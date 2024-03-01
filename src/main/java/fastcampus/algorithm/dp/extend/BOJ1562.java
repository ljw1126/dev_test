package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 계단수 (골드1)
 *
 * i자리에 j숫자로 끝나는 수에 대해 bitmasking 으로 사용여부 파악
 *
 * 시간복잡도 : O(N * 10 * 1024)
 * 공간복잡도 : long : 8byte
 * DP[N + 1][10][1024] = 100 * 10 * 1024 * 8^3 (N은 최대 100)
 */
public class BOJ1562 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int MOD = 1000000000;

    static int N;
    static long[][][] DP;


    public static void main(String[] args) throws IOException {
        input();

        //preprocess();
        //bottomUp();

        topDown();

        output();
    }

    private static void topDown() {
        DP = new long[N + 1][10][1 << 10];
        // 방문하지 않음 처리(메모리제이션)
        for(int len = 1; len <= N; len++) {
            for(int i = 0; i <= 9; i++) {
                Arrays.fill(DP[len][i], -1);
            }
        }

        long result = 0L;
        for(int i = 1; i <= 9; i++) {
            result += rec(1, i, 1 << i); // 1 << i를 시작수로 하는 재귀 함수 호출
            result %= MOD;
        }

        sb.append(result);
    }

    // 시작수에 뒤에 붙이는 형태라, if 조건문 이 bottomUp과 다름. 그리고 MOD 처리 안해서 틀
    private static long rec(int len, int last, int bit) {
        if(len == N) return bit == (1 << 10) - 1 ? 1L : 0L; // 9876543210 == 1023
        if(DP[len][last][bit] != -1) return DP[len][last][bit];

        long result = 0L;
        if(last > 0) {
            result += rec(len + 1, last - 1, bit | 1 << (last - 1));
        }

        if(last < 9) {
            result += rec(len + 1, last + 1, bit | 1 << (last + 1));
        }

        return DP[len][last][bit] = (result %= MOD);
    }

    private static void input() {
        N = inputProcessor.nextInt();
    }

    private static void preprocess() {
        DP = new long[N + 1][10][1 << 10]; // 1 << 10 = 1024, 0 ~ 1023
        for(int i = 0; i < 9; i++) { // bottomUp은 0을 1로 초기화 해야 함 0 -> 10 -> 210 ... -> 9876543210이 된다
            DP[1][i][1 << i] = 1;
        }
    }

    private static void bottomUp() {
        for(int len = 2; len <= N; len++) {
            for(int i = 0; i <= 9; i++) {
                for(int b = 0; b < (1 << 10); b++) {
                    int bit = (b | 1 << i);

                    if(i > 0) DP[len][i][bit] += DP[len - 1][i - 1][b];
                    if(i < 9) DP[len][i][bit] += DP[len - 1][i + 1][b];

                    DP[len][i][bit] %= MOD;
                }
            }
        }


        long result = 0L;
        for(int i = 0; i <= 9; i++) {
            result += DP[N][i][(1 << 10) - 1];
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
