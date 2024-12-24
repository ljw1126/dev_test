package its.codingtest.greedy;

import its.codingtest.Practice;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p311 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;
    private static int[] FEAR;

    private static void input() {
        N = inputProcessor.nextInt();
        FEAR = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            FEAR[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        Arrays.sort(FEAR);

        int result = 0;
        int count = 0;
        for(int i = 1; i <= N; i++) {
            count += 1;
            if(count >= FEAR[i]) {
                result += 1;
                count = 0;
            }
        }

        sb.append(result);
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
