package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * List of Unique Number(골드4)
 * https://www.acmicpc.net/problem/13144
 *
 * - 최대치 실수
 * - 1 ~ N 까지의 합 = N ( N + 1) / 2
 */
public class BOJ13144 {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] A, COUNT;


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();

        A = new int[N + 1];
        COUNT = new int[100001];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        long result = 0; // 최대치 실수

        for(int L = 1, R = 0; L <= N; L++) {
            while(R + 1 <= N && COUNT[A[R + 1]] == 0) {
                R += 1;
                COUNT[A[R]] += 1;
            }

            result += (R - L + 1);
            COUNT[A[L]] -= 1;
        }

        sb.append(result);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static class InputProcessor {
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
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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
