package its.codingtest.binarysearch;

import java.io.*;
import java.util.StringTokenizer;

public class p367 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, X;
    private static int[] DATA;

    private static void input() {
        N = inputProcessor.nextInt();
        X = inputProcessor.nextInt(); // 찾는 원소

        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int left = searchLeft(N, X);
        int right = searchRight(N, X);
        int result = right - left;

        sb.append(result == 0 ? -1 : result);
    }

    private static int searchLeft(int n, int x) {
        int L = 1;
        int R = n + 1;

        while(L < R) {
            int mid = (L + R) / 2;

            if(x <= DATA[mid]) {
                R -= 1;
            } else {
                L += 1;
            }
        }

        return R;
    }

    private static int searchRight(int n, int x) {
        int L = 1;
        int R = n + 1;

        while(L < R) {
            int mid = (L + R) / 2;

            if(x < DATA[mid]) {
                R -= 1;
            } else {
                L += 1;
            }
        }

        return R;
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
