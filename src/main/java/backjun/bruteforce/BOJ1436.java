package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 영화감독 숌 - 실5
 * https://www.acmicpc.net/problem/1436
 *
 * - 직접 풀이 못함
 * - https://st-lab.tistory.com/103
 */
public class BOJ1436 {
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
        int num = 666;
        int count = 1;
        while(count != N) {
            num += 1;

            if(String.valueOf(num).contains("666")) {
                count += 1;
            }
        }

        sb.append(num);
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
