package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 1,2,3 더하기 (실버3) https://www.acmicpc.net/problem/9095
 *
 * 가짜 문제 정의 -> 가짜문제로 풀 수 있는가 ? -> 초기항 정의 -> 점화식
 * 점화식 : DP[i] = DP[i - 1] + DP[i - 2] + DP[i - 3]
 * 시간복잡도 : O(N)
 */
public class BOJ9095 {

    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();
    static int N;
    static int[] DP;

    public static void main(String[] args) throws IOException {
        preprocess();

        int T = inputProcessor.nextInt();
        while(T > 0) {
            input();
            sb.append(DP[N]).append(NEW_LINE);

            T -= 1;
        }

        output();
    }

    private static void preprocess() {
        DP = new int[12];
        // 초기화
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for(int i = 4; i <= 11; i++) {
            DP[i] = DP[i - 1] + DP[i - 2] + DP[i - 3];
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
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
