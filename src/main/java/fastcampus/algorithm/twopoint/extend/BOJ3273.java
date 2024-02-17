package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 두 수의 합(실버3)
 * https://www.acmicpc.net/problem/3273
 */
public class BOJ3273 {
    static StringBuilder sb = new StringBuilder();
    static int N, X;
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

        X = inputProcessor.nextInt();
    }

    private static void pro() {
        Arrays.sort(A, 1, N + 1);

        int result = 0;
        int L = 1;
        int R = N;
        while(L < R) {
            int sum = A[L] + A[R];

            if(sum == X) {
                result += 1;
            }

            if(sum > X) R -= 1;
            else L += 1;
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
