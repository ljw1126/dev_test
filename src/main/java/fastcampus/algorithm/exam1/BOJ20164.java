package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
홀수 홀릭 호석 (골드5)
https://www.acmicpc.net/problem/20164

'완전 탐색 접근'을 통해서 모든 경우를 직접 하나 찾아야 함
9자리 수에 대해 재귀 함수 호출 처리
모든 경우의 수가 1억을 절대 넘지 않음 (감)

재귀 함수 조건
1. 종료 조건 -> x가 한 자리 수일 때
2. 아닌 경우 -> 분할 하여 탐색

실수*
- 3자리 이상인 경우, 3분할을 하는데 이때 수의 개수는 상관이 없었음

 */
public class BOJ20164 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, MIN_VALUE, MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        MIN_VALUE = Integer.MAX_VALUE;
        MAX_VALUE = 0;
    }

    private static void pro() {
        rec(N, 0);
        sb.append(MIN_VALUE).append(" ").append(MAX_VALUE);
    }

    private static void rec(int value, int count) {
        String s = String.valueOf(value);
        if (s.length() == 1) {
            if (isOdd(value)) {
                count += 1;
            }
            MIN_VALUE = Math.min(MIN_VALUE, count);
            MAX_VALUE = Math.max(MAX_VALUE, count);
            return;
        }

        if (s.length() == 2) {
            int ten = value / 10;
            int one = value % 10;

            if (isOdd(ten)) {
                count += 1;
            }
            if (isOdd(one)) {
                count += 1;
            }

            rec(ten + one, count);
        }

        if (s.length() >= 3) {
            for (int i = 0; i < s.length() - 2; i++) {
                for (int j = i + 1; j < s.length() - 1; j++) {
                    int v1 = Integer.parseInt(s.substring(0, i + 1));
                    int v2 = Integer.parseInt(s.substring(i + 1, j + 1));
                    int v3 = Integer.parseInt(s.substring(j + 1));

                    int cnt = 0;
                    cnt += oddCount(v1);
                    cnt += oddCount(v2);
                    cnt += oddCount(v3);

                    int sum = v1 + v2 + v3;

                    rec(sum, count + cnt);
                }
            }
        }
    }

    private static boolean isOdd(int value) {
        return value % 2 == 1;
    }

    private static int oddCount(int value) {
        int count = 0;
        int v = value;
        while (v > 0) {
            if (isOdd(v % 10)) {
                count += 1;
            }

            v /= 10;
        }

        return count;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
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
