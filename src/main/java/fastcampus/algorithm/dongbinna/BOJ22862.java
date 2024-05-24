package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 가장 긴 짝수 연속한 부분 수열(large)
 * https://www.acmicpc.net/problem/22862
 * <p>
 * - 투포인터 문제, O(N)
 * - DP 풀이시 메모리 초과 발생
 * -> 제약 조건 1Gb인데, 100만 * 10만 * 4byte = 400Gb 되버림
 */
public class BOJ22862 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, K;
    private static int[] DATA;


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 길이
        K = inputProcessor.nextInt(); // 최대 횟수

        DATA = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {

        int result = 0;
        int cnt = 0;
        for (int L = 1, R = 0; L <= N; L++) {
            while (R + 1 <= N && cnt <= K) {
                R += 1;
                if (DATA[R] % 2 == 1) {
                    cnt += 1;
                }
            }

            result = Math.max(result, R - L - cnt + 1);

            if (DATA[L] % 2 == 1) {
                cnt -= 1;
            }
        }

        sb.append(result);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

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
