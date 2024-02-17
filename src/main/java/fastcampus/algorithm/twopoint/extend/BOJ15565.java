package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 귀여운 라이언(실버1)
 *
 * -직접 풀이
 * -int 범위내
 */
public class BOJ15565 {
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

        A = new int[N + 1]; // 라이언 : 1, 어피치 : 2
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }
    }

    private static int inc(int idx) {
        return A[idx] == 1 ? 1 : 0;
    }

    private static int dec(int idx) {
        return A[idx] == 1 ? 1 : 0;
    }

    private static void pro() {
        int count = 0;
        int result = 1000001;

        for(int L = 1, R = 0; L <= N; L++) {

            while(R + 1 <= N && count < K) {
                R += 1;
                count += inc(R);
            }

            if(count == K) {
                result = Math.min(result, R - L + 1);
            }

            count -= dec(L);
        }

        if(result == 1000001) {
            result = -1;
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
