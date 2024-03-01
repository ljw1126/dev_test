package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1,2,3 더하기 5(실버2) https://www.acmicpc.net/problem/15990
 *
 * 앞에 숫자에서 1,2,3중 뭐로 끝났는지 알아야 하니 2차원 배열 사용
 * -직접 풀이
 *
 * i = 5일때
 * i - 1 에서 1을 붙일 수 있는 건 2,3인 경우
 * i - 2 에서 2를 붙일 수 있는 건 1,3인 경우
 * i - 3 에서 3을 붙일 수 있는 건 1,2인 경우
 *
 * 시간복잡도 : O(N)
 */
public class BOJ15990 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int MOD = 1000000009;
    static int T, N;
    static long[][] DP;


    public static void main(String[] args) throws IOException {
        init();
        //bottomUp();

        T = inputProcessor.nextInt();
        while(T > 0) {
            N = inputProcessor.nextInt();

            long result = 0L;
            for(int i = 1; i <= 3; i++) {
                //result += DP[N][i];
                result += topDown(N, i);
                result %= MOD;
            }

            sb.append(result).append("\n");

            T -= 1;
        }



        output();
    }

    private static long topDown(int n, int i) {
        if(n < 1) return 0L;
        if(DP[n][i] != -1) return DP[n][i];

        if(i == 1) {
            DP[n][1] = (topDown(n - 1, 2) + topDown(n - 1, 3)) % MOD;
        } else if(i == 2) {
            DP[n][2] = (topDown(n - 2, 1) + topDown(n - 2, 3)) % MOD;
        } else if(i == 3) {
            DP[n][3] = (topDown(n - 3, 1) + topDown(n - 3, 2)) % MOD;
        }

        return DP[n][i];
    }

    private static void init() {
        DP = new long[100001][4];
        DP[1][1] = 1; // 1
        DP[2][2] = 1; // 2

        DP[3][1] = 1; // 2 + 1
        DP[3][2] = 1; // 1 + 2
        DP[3][3] = 1; // 3

        DP[4][1] = 2;
        DP[4][3] = 1;

        for(int i = 5; i <= 100000; i++) {
            Arrays.fill(DP[i], -1);
        }
    }

    private static void bottomUp() {
        for(int i = 5; i <= 100000; i++) {
            DP[i][1] = (DP[i - 1][2] + DP[i - 1][3]) % MOD;
            DP[i][2] = (DP[i - 2][1] + DP[i - 2][3]) % MOD;
            DP[i][3] = (DP[i - 3][1] + DP[i - 3][2]) % MOD;
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
