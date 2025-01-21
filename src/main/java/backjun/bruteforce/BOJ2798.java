package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2798 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n, m, diff, result;
    private static int[] cards;

    private static void input() {
        n = inputProcessor.nextInt();
        m = inputProcessor.nextInt();

        cards = new int[n];
        for(int i = 0; i < n; i++) {
            cards[i] = inputProcessor.nextInt();
        }

        diff = Integer.MAX_VALUE;
    }

    private static void pro() {
        rec(0, 0, 0);

        sb.append(result);
    }

    private static void rec(int count, int idx, int value) {
        if(count == 3) {
            int v = m - value;
            if(v >= 0 && v < diff) {
                diff = v;
                result = value;
            }

            return;
        }
        if(value > m) return;
        if(idx >= n) return;

        // 선택한다
        rec(count + 1, idx + 1, value + cards[idx]);

        // 선택 안한다
        rec(count, idx + 1, value);
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
