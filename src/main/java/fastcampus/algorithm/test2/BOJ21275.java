package fastcampus.algorithm.test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 폰 호석만 (실버2)
 * https://www.acmicpc.net/problem/21275
 *
 * - 진법 변환 : result * base + v 반복 (이때 result는 초기 0)
 * - N진법 변환에서 값이 N보다 크면 해당 진법으로 표현할 수 없는 수라는 것을 의미한다
 * - 오버 플로우 확인시 value * base + v >= MAX_VALUE 이면 이미 오버 플로우가 발생하므로 이항 처리한다
 * - z z 입력은 같은 a, b진법이 불가라는 조건 때문에 Impossible 이 나온다
 */
public class BOJ21275 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static String BLANK = " ";
    static String MULTIPLE = "Multiple";
    static String IMPOSSIBLE = "Impossible";
    static long MAX_VALUE = Long.MAX_VALUE;

    static String A, B;


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        long ansX = -1L;
        long ansA = 0L;
        long ansB = 0L;

        for(int i = 2; i <= 36; i++) {
            for(int j = 2; j <= 36; j++) {
                if(i == j) continue;

                long v1 = convert(A, i);
                if(v1 == -1) continue;

                long v2 = convert(B, j);
                if(v2 == -1) continue;
                if(v1 != v2) continue;

                if(ansX == -1) {
                    ansX = v1;
                    ansA = i;
                    ansB = j;
                } else {
                    sb.append(MULTIPLE);
                    return;
                }
            }
        }

        if(ansX == -1) sb.append(IMPOSSIBLE);
        else sb.append(ansX).append(BLANK).append(ansA).append(BLANK).append(ansB);
    }

    private static long convert(String b, int base) {
        long value = 0;

        for(int i = 0; i < b.length(); i++) {
            char c = b.charAt(i);
            int v = getValue(c);

            if(v >= base) return -1;
            if(value >= (MAX_VALUE - v) / base) return -1;

            value *= base;
            value += v;
        }

        return value;
    }

    private static int getValue(char c) {
        if(Character.isDigit(c)) {
            return c - '0';
        }

        return c - 'a' + 10;
    }

    private static void input() {
        A = inputProcessor.next();
        B = inputProcessor.next();

        A = A.toLowerCase();
        B = B.toLowerCase();
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
