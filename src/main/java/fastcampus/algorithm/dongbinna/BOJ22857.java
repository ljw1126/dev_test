package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 가장 긴 짝수 연속한 부분 수열 (small) (실2)
 * https://www.acmicpc.net/problem/22857
 * <p>
 * - 투포인터, DP
 * - 투포인터, O(N)
 * - DP, O(K * N)
 * - 공간복잡도 1Gb 인데 4byte * 5 * 10^6 = 20Mb 로 풀이 가능
 * <p>
 * 앞에 짝수로 반복되다가 마지막에 홀수 반복되는 예외가 있어서 시간 소모
 */
public class BOJ22857 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, K;
    private static int[][] DP;


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 길이
        K = inputProcessor.nextInt(); // 최대 횟수
    }

    private static void pro() {
        DP = new int[K + 1][N + 1];

        int result = 0;
        for (int i = 1; i <= N; i++) {
            int value = inputProcessor.nextInt();

            boolean isOdd = value % 2 == 1; // 홀수
            if (isOdd) {
                DP[0][i] = 0;
            } else {
                DP[0][i] = DP[0][i - 1] + 1;
            }

            result = Math.max(result, DP[0][i]);
            for (int j = 1; j <= K; j++) {
                if (isOdd) {
                    DP[j][i] = DP[j - 1][i - 1];
                } else {
                    DP[j][i] = DP[j][i - 1] + 1;
                }

                result = Math.max(result, DP[j][i]);
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
