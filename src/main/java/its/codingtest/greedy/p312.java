package its.codingtest.greedy;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 곱하기 혹은 더하기
 * - result와 v가 둘다 1이하면 더하는게 나음
 */
public class p312 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static String S;

    private static void input() {
        S = inputProcessor.nextLine().trim();
    }

    private static void pro() {
        char[] arr = S.toCharArray();
        int result = arr[0] - '0';
        for(int i = 1; i < arr.length; i++) {
            int v = arr[i] - '0';
            if(v <= 1 || result <= 1) { // 둘다 1 이하면 더하는게 나음
                result += v;
            } else {
                result *= v;
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
