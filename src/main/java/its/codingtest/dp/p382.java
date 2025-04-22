package its.codingtest.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 편집거리
 * - 직접 풀이 x
 * - 최소 편집 거리 구하는 알고리즘이 따로 있었음
 * - O(NM) // 각 문자열의 길이, 글자가 최대 5,000이니 25 * 10^6
 */
public class p382 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static String A, B;

    private static void input() {
        A = inputProcessor.nextLine().trim();
        B = inputProcessor.nextLine().trim();
    }

    private static void pro() {
        int col = A.length();
        int row = B.length();

        int[][] dp = new int[row + 1][col + 1];

        // 삽입
        for (int i = 1; i <= col; i++) {
            dp[0][i] = i;
        }

        // 삭제
        for (int i = 1; i <= row; i++) {
            dp[i][0] = i;
        }

        // 왼쪽 위 : 교체, 왼쪽 : 삭제, 위 : 삽입
        // 세 가지 방식 중에 최소 값을 가지는 방식으로 처리하면 dp[row][col]에 최소 편집 거리가 기록됨
        for (int i = 1; i <= row; i++) { // 문자열 B
            for (int j = 1; j <= col; j++) { // 문자열 A
                char a = A.charAt(j - 1); // *i, j 주의
                char b = B.charAt(i - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }

                int min = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                dp[i][j] = 1 + min;
            }
        }

        sb.append(dp[row][col]);
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

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
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
