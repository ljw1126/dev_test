package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 덩치 - 실버5
 * https://www.acmicpc.net/problem/7568
 *
 * - 직접풀이
 * - 브루트 포스
 */
public class BOJ7568 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n;
    private static int[][] persons;

    private static void input() {
        n = inputProcessor.nextInt();

        persons = new int[n][2];
        for(int i = 0; i < n; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();

            persons[i][0] = x;
            persons[i][1] = y;
        }
    }

    private static void pro() {
        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            int[] person = persons[i];
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(i == j) continue;

                int[] other = persons[j];
                if(person[0] < other[0] && person[1] < other[1]) {
                    count += 1;
                }
            }

            result[i] = 1 + count;
        }

        for(int i = 0; i < n; i++) {
            sb.append(result[i]).append(" ");
        }
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
