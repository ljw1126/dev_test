package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 연산 최대로 (골2)
 * https://www.acmicpc.net/problem/21943
 * <p>
 * - 비트 마스킹 기법 활용 풀이가 완전탐색 연습하기 좋다 (https://www.acmicpc.net/source/77039635)
 * - 강의 풀이와 반대로 그룹 완전 탐색시 ArrayList 아닌 LinkedList가 10배나 메모리를 더 차지함
 */
public class BOJ21943 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, P, Q, RESULT;
    private static int[] Xi;
    private static int[] DATA, MULTIPLE;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        Xi = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            Xi[i] = inputProcessor.nextInt();
        }

        P = inputProcessor.nextInt(); // 더하기
        Q = inputProcessor.nextInt(); // 곱하기

        DATA = new int[N + 1];
        MULTIPLE = new int[Q + 1]; // 곱셈을 기준으로 그룹을 나눔
    }

    private static void pro() {
        divideMultiGroup(1, 1);
        sb.append(RESULT);
    }

    private static void divideMultiGroup(int start, int cnt) {
        if (cnt == Q + 1) {
            shuffleNumber(1, 0, 0);
            return;
        }

        for (int i = start; i <= N - 1; i++) { // 실수1. N - 1개만 구하면됨, 실수2. start 처리 안하고 1고정해서 시간초과
            MULTIPLE[cnt] = i;
            divideMultiGroup(i + 1, cnt + 1);
            MULTIPLE[cnt] = 0;
        }
    }

    private static void shuffleNumber(int idx, int cnt, int flag) {
        if (cnt == N) {
            calculate();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if ((flag & (1 << i)) != 0) continue;

            DATA[idx] = Xi[i];
            shuffleNumber(idx + 1, cnt + 1, flag | 1 << i);
            DATA[idx] = 0;
        }
    }

    private static void calculate() {
        int total = 1;
        int idx = 1;
        for (int i = 1; i < Q + 1; i++) {
            int sum = 0;
            for (int j = idx; j <= MULTIPLE[i]; j++) {
                sum += DATA[j];
            }

            total *= sum;
            idx = MULTIPLE[i] + 1;
        }

        if (idx <= N) { // 실수. <= 처리 안해서
            int sum = 0;
            for (int i = idx; i <= N; i++) {
                sum += DATA[i];
            }

            total *= sum;
        }

        RESULT = Math.max(RESULT, total);
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
