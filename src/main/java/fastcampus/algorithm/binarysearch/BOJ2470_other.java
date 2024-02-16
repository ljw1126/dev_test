package fastcampus.algorithm.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 두 용액
 *
 * 시간 복잡도 : O(N)
 * 최대치 : int 범위내
 */
public class BOJ2470_other {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] liquid;

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        liquid = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            liquid[i] = inputProcessor.nextInt();
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }



    private static void pro() {
        Arrays.sort(liquid, 1, N + 1); // toIndex : exclusive

        int L = 1;
        int R = N;
        int result = Integer.MAX_VALUE;
        int v1 = -1;
        int v2 = -1;

        while(L < R) {
            int sum = liquid[L] + liquid[R];

            if(Math.abs(sum) < result) {
                result = Math.abs(sum);
                v1 = liquid[L];
                v2 = liquid[R];
            }

            if(sum > 0) R -= 1;
            else L += 1;
        }

        sb.append(v1).append(" ").append(v2);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }
    }
}
