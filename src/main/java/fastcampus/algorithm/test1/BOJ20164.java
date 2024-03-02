package fastcampus.algorithm.test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 홀수 홀릭 호석(골5)
 * https://www.acmicpc.net/problem/20164
 *
 * 3자리수 이상 일때
 * - i, j 범위 정할 때 실수했는데 손으로 그리는게 빠름
 */
public class BOJ20164 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    private static String N;
    private static int MIN_VALUE, MAX_VALUE;


    public static void main(String[] args) throws IOException {
        input();

        rec(N ,0);
        sb.append(MIN_VALUE).append(" ").append(MAX_VALUE);

        output();
    }

    private static void input() {
        N = inputProcessor.nextLine();

        MAX_VALUE = 0;
        MIN_VALUE = Integer.MAX_VALUE;
    }

    private static int oddCount(int value) {
        int result = 0;

        while(value > 0) {
            int mod = (value % 10); // 여기 실수 value %= 10 하면 값이 완전히 틀림

            if(mod % 2 == 1) result += 1;

            value /= 10;
        }

        return result;
    }

    private static void rec(String value, int cnt) {
        int len = value.length();
        if(len == 1) {
            int total = cnt + oddCount(Integer.parseInt(value));
            // 최대값, 최소값 갱신
            MIN_VALUE = Math.min(MIN_VALUE, total);
            MAX_VALUE = Math.max(MAX_VALUE, total);
            return;
        }

        if(len == 2) {
            int _value = Integer.parseInt(value);
            int v1 = (_value % 10); // 일의 자리
            int v2 = (_value / 10); // 십의 자리

            rec(String.valueOf(v1 + v2), cnt + oddCount(v1) + oddCount(v2));
            return;
        }

        for(int i = 0; i <= len - 3; i++) {
            for(int j = i + 1; j <= len - 2; j++) {
                String v1 = value.substring(0, i + 1);
                String v2 = value.substring(i + 1, j + 1);
                String v3 = value.substring(j + 1, len);

                int _v1 = Integer.parseInt(v1);
                int _v2 = Integer.parseInt(v2);
                int _v3 = Integer.parseInt(v3);

                rec(String.valueOf(_v1 + _v2 + _v3), cnt + oddCount(_v1) + oddCount(_v2) + oddCount(_v3));
            }
        }
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
