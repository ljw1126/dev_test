package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 수열(실버3)
 * https://www.acmicpc.net/problem/2559
 *
 * -100 ~ 100까지 값을 가지는데
 * 결과값 초기화를 0으로 해버리는 실수로 삽실
 */
public class BOJ2559 {

    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        K = inputProcessor.nextInt();

        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for(int L = 1, R = 0; L <= N - K + 1; L++) {

            while(R + 1 <= L + K - 1) {
                R += 1;
                sum += A[R];
            }

            result = Math.max(result, sum);
            sum -= A[L];
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
