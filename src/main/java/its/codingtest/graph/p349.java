package its.codingtest.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 연산자 끼워넣기 (실1)
 * https://www.acmicpc.net/problem/14888
 */
public class p349 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;
    private static int MIN_VALUE, MAX_VALUE;
    private static int[] DATA;
    private static int[] OPERATORS;

    private static void input() {
        N = inputProcessor.nextInt(); // 수의 개수
        DATA = new int[N];
        for (int i = 0; i < N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }

        OPERATORS = new int[4];
        for (int i = 0; i < 4; i++) {
            OPERATORS[i] = inputProcessor.nextInt();
        }

        MIN_VALUE = Integer.MAX_VALUE;
        MAX_VALUE = Integer.MIN_VALUE;
    }

    private static void pro() {
        rec(1, DATA[0]);

        sb.append(MAX_VALUE)
                .append("\n")
                .append(MIN_VALUE);
    }

    private static void rec(int idx, int value) {
        if (idx == N) {
            MIN_VALUE = Math.min(MIN_VALUE, value);
            MAX_VALUE = Math.max(MAX_VALUE, value);
            return;
        }

        if (OPERATORS[0] != 0) { // 덧셈
            OPERATORS[0] -= 1;
            rec(idx + 1, value + DATA[idx]);
            OPERATORS[0] += 1;
        }

        if (OPERATORS[1] != 0) { // 뺄셈
            OPERATORS[1] -= 1;
            rec(idx + 1, value - DATA[idx]);
            OPERATORS[1] += 1;
        }

        if (OPERATORS[2] != 0) { // 곱셈
            OPERATORS[2] -= 1;
            rec(idx + 1, value * DATA[idx]);
            OPERATORS[2] += 1;
        }

        if (OPERATORS[3] != 0) { // 나눗셈
            OPERATORS[3] -= 1;
            rec(idx + 1, value / DATA[idx]);
            OPERATORS[3] += 1;
        }
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
