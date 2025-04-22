package its.codingtest.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 두 배열의 원소 교체
 */
public class p182 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, K;
    private static Integer[] A, B;

    private static void input() {
        N = inputProcessor.nextInt(); // 원소 수
        K = inputProcessor.nextInt(); // 최대 K번 바꿔치기 연산을 수행할 수 있다

        A = new Integer[N + 1];
        B = new Integer[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            B[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        Arrays.sort(A, 1, N + 1);
        Arrays.sort(B, 1, N + 1, Collections.reverseOrder());

        for (int i = 1; i <= K; i++) {
            if (A[i] < B[i]) {
                int temp = A[i];
                A[i] = B[i];
                B[i] = temp;
            } else {
                break;
            }
        }

        Integer result = Arrays.stream(A).reduce(0, Integer::sum);
        sb.append(result);
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
