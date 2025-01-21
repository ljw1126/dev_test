package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 약수 구하기 - 브3
 * https://www.acmicpc.net/problem/2501
 *
 * - 완전 탐색
 */
public class BOJ2501 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n, k;

    private static void input() {
        n = inputProcessor.nextInt();
        k = inputProcessor.nextInt();
    }

    private static void pro() {
        int count = 0;
        int result = 0;

        for(int i = 1; i <= n; i++) {
            int mod = n % i;
            if(mod == 0) {
                count += 1;
            }

            if(count == k) {
                result = i;
                break;
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
