package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 암호코드 (골드5)
 * https://www.acmicpc.net/problem/2011
 *
 * - 직접 풀이 못함
 * - 시간복잡도 : 둘다 O(N)
 * - DP[N] = DP[N -1] + DP[N - 2] (DP[N - 1]은 1의 자리, DP[N - 2] 십의 자리가 각각 알파벳 범위 만족할떄 합산)
 */
public class BOJ2011 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int MOD = 1000000;
    static String PASSWORD;
    static long[] DP;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        PASSWORD = inputProcessor.nextLine().trim();

        // DP 초기화
        DP = new long[PASSWORD.length() + 1];
        DP[0] = 1;

        int first = PASSWORD.charAt(0) - '0';
        if(1 <= first && first <= 9) {
            DP[1] = 1;
        }
    }

    private static void pro() {
        topDown(PASSWORD.length());
        sb.append(DP[PASSWORD.length()] == -1 ? 0 : DP[PASSWORD.length()]);

        System.out.println(Arrays.toString(DP));
    }

    private static void bottomUp() {
        for(int i = 2; i <= PASSWORD.length(); i++) {
            int first = PASSWORD.charAt(i - 1) - '0';
            if(1 <= first && first <= 9) {
                DP[i] += DP[i - 1];
                DP[i] %= MOD;
            }

            if(i == 1) continue;

            int ten = PASSWORD.charAt(i - 2) - '0';

            if(ten == 0) continue;

            int value = ten * 10 + first;
            if(10 <= value && value <= 26) {
                DP[i] += DP[i - 2];
                DP[i] %= MOD;
            }
        }
    }

    private static long topDown(int idx) {
        if(idx <= 1) return DP[idx];
        if(DP[idx] != 0) return DP[idx];

        int one = PASSWORD.charAt(idx - 1) - '0';
        if(1 <= one && one <= 9) {
            DP[idx] += topDown(idx - 1);
            DP[idx] %= MOD;
        }

        int ten = PASSWORD.charAt(idx - 2) - '0';
        int value = ten * 10 + one;
        if(10 <= value && value <= 26) {
            DP[idx] += topDown(idx - 2);
            DP[idx] %= MOD;
        }

        return DP[idx];
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
