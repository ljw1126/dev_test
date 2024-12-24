package its.codingtest.implementation;

import its.codingtest.Practice;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 백준 - 럭키 스트레이트(브2)
 * https://www.acmicpc.net/problem/18406
 */
public class p321 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;

    private static void input() {
        N = inputProcessor.nextInt();
    }

    private static void pro() {
        String nums = String.valueOf(N);
        int len = nums.length();
        int half = len / 2;

        int v1 = Integer.parseInt(nums.substring(0, half));
        int v2 = Integer.parseInt(nums.substring(half));

        String lucky = calculate(v1) == calculate(v2) ? "LUCKY" : "READY";
        sb.append(lucky);
    }

    private static int calculate(int n) {
        int result = 0;
        int next = n;
        while(next > 0) {
            int mod = next % 10;
            result += mod;
            next /= 10;
        }
        return result;
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
