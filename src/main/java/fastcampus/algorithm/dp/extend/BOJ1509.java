package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 팰린드롬 분할(골드1) https://acmicpc.net/problem/1509
 *
 * - 팰린드롬 분할 개수의 최소값을 구하는 문제 (= 분할된 팰린드롬을 합쳐서 원래 글자가 되는 최소 조합 수)
 * https://gre-eny.tistory.com/223
 * https://lotuslee.tistory.com/6
 *
 *
 */
public class BOJ1509 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static Character[] TEXT;
    static boolean[][] PALINDROME; // byte : 1byte, int : 4byte

    public static void main(String[] args) throws IOException {
        input();

        preprocess();
        //bottomUp();
        topDown();

        output();
    }

    private static void topDown() {
        int last = TEXT.length - 1;

        int[] dp = new int[TEXT.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;

        sb.append(rec(last, dp));
    }

    private static int rec(int a, int[] dp) {
        if(a < 1) return 0;
        if(dp[a] != Integer.MAX_VALUE) return dp[a];

        for(int i = 1; i <= a; i++) {
            if(PALINDROME[i][a]) {
                dp[a] = Math.min(dp[a], rec(i - 1, dp) + 1);
            }
        }

        return dp[a];
    }


    private static void input() {
        String input = inputProcessor.nextLine();

        int len = input.length();
        TEXT = new Character[len + 1];

        for(int i = 1; i <= len; i++) {
            TEXT[i] = input.charAt(i - 1);
        }

        PALINDROME = new boolean[len + 1][len + 1];
    }

    private static void preprocess() {
        int len = TEXT.length - 1;
        for(int i = 1; i <= len; i++) {
            PALINDROME[i][i] = true;
        }

        for(int i = 1; i < len; i++) {
            if(TEXT[i] == TEXT[i + 1]) {
                PALINDROME[i][i + 1] = true;
            }
        }

        for(int l = 3; l <= len; l++) {
            for(int i = 1; i <= len - l + 1; i++) {
                int j = i + l - 1;
                if(TEXT[i] == TEXT[j] && PALINDROME[i + 1][j - 1]) {
                    PALINDROME[i][j] = true;
                }
            }
        }
    }

    private static void bottomUp() {
        int[] dp = new int[TEXT.length];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= TEXT.length - 1; i ++) {
            dp[i] = Integer.MAX_VALUE;

            for(int j = 1; j <= i; j++) {
                if(PALINDROME[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        sb.append(dp[TEXT.length - 1]);
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
