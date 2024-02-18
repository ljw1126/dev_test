package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 세 용액(골드3)
 * https://www.acmicpc.net/problem/2473
 *
 * - 최대치 long 실수
 */
public class BOJ2473 {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] A;


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        Arrays.sort(A, 1, N + 1);

        long result = Long.MAX_VALUE; // 최대치 실수
        long v1 = 0;
        long v2 = 0;
        long v3 = 0;
        for(int i = 1; i <= N - 2; i++) {

            long pick = A[i];
            int L = i + 1;
            int R = N;
            while(L < R) {
                long sum = pick + A[L] + A[R];

                if(Math.abs(sum) < result) {
                    result = Math.min(result, Math.abs(sum));
                    v1 = pick;
                    v2 = A[L];
                    v3 = A[R];
                }

                if(sum > 0) R -= 1;
                else L += 1;
            }
        }

        sb.append(v1).append(" ").append(v2).append(" ").append(v3);
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
