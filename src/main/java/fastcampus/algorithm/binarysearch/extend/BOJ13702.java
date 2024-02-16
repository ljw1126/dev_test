package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 이상한 술집(실버3) https://www.acmicpc.net/problem/13702
 *
 * 최대값을 찾으려면
 * 조건 성립시 L = mid + 1; 해야함 (최소값은 반대)
 *
 * long 타입으로 안해서 다 틀림;;
 *
 * 240216
 * L, R 범위 틀림 ..
 *
 * 반례
 * 1 1
 * 2100000000
 *
 * 답 2100000000
 */
public class BOJ13702 {

    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static long[] DRINK;
    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 주전자 개수
        K = inputProcessor.nextInt(); // 사람 수

        DRINK = new long[N + 1];
        for(int i = 1; i <= N; i++) {
            DRINK[i] = inputProcessor.nextLong();
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void pro() {
        long L = 0;
        long R = Integer.MAX_VALUE;
        long result = 0L;

        while(L <= R) {
            long ml = (L + R) / 2;
            if(valid(ml)) {
                result = ml;
                L = ml + 1;
            } else {
                R = ml - 1;
            }
        }

        sb.append(result);
    }

    private static boolean valid(long limit) {
        int count = 0;

        for(int i = 1; i <= N; i++) {
            count += (DRINK[i] / limit);
        }

        return count >= K;
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
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
