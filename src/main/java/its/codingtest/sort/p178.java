package its.codingtest.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * 위에서 아래로
 */
public class p178 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;
    private static int[] DATA;

    private static void input() {
        N = inputProcessor.nextInt();

        DATA = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }
    }

    /* https://parkseryu.tistory.com/156
     * primitive 타입은 오름차순 정렬 후 거꾸로 출력하는 방법 밖에 없다
     * wrapper클래스는 Collections.
     */
    private static void pro() {
        IntStream.of(DATA)
                .skip(1)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .forEach(v -> sb.append(v).append(" "));
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
