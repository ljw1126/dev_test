package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 배열 합치기(실버5)
 *
 * - 병합 정렬
 * - 투포인터
 */
public class BOJ11728 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 각 배열의 크기
        M = inputProcessor.nextInt();

        A = new int[N + 1];
        B = new int[M + 1];

        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }

        for(int i = 1; i <= M; i++) {
            B[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int L = 1, R = 1;
        while(L <= N && R <= M) {
            if(A[L] < B[R]) {
                sb.append(A[L]).append(" ");
                L += 1;
            } else {
                sb.append(B[R]).append(" ");
                R += 1;
            }
        }

        while(L <= N) {
            sb.append(A[L]).append(" ");
            L += 1;
        }

        while(R <= M) {
            sb.append(B[R]).append(" ");
            R += 1;
        }

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
