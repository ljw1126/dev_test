package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2661 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n;
    private static boolean finished;
    private static void input() {
        n = inputProcessor.nextInt();

    }

    private static void pro() {
        rec(0, 0, new StringBuilder());
    }

    private static void rec(int len, int prev, StringBuilder current) {
        if(finished) return;
        if(len == n) {
            finished = true;
            sb.append(current.toString());
            return;
        }

        for(int i = 1; i <= 3; i++) {
            if(i == prev) continue;

            current.append(i);
            if(possible(current)) {
                rec(len + 1, i, current);
            }

            current.deleteCharAt(current.length() - 1);
        }
    }

    private static boolean possible(StringBuilder s) {
        int len = s.length();
        for(int i = 2; i <= len / 2; i++) {
            String back = s.substring(len - i, len);
            String front = s.substring(len - i * 2, len - i);
            if(front.equals(back)) return false;
        }

        return true;
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
