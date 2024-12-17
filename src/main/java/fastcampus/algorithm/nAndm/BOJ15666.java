package fastcampus.algorithm.nAndm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 중복 허용 순열인데 중복되는 순열은 제거해야함
 * - O(N^M) 보다 작음
 */
public class BOJ15666 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int N, M;
    private static int[] SELECTED, DATA;

    private static void input() {
        N = inputProcessor.nextInt(); // 1 ~ N
        M = inputProcessor.nextInt(); // 개수

        DATA = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }

        SELECTED = new int[M + 1];
        Arrays.sort(DATA, 1, N + 1);
    }

    private static void pro() {
        rec(1, 0);
    }

    private static void rec(int start, int cnt) {
        if (cnt == M) {
            appendResult();
            return;
        }

        int last = 0;
        for (int i = start; i <= N; i++) {
            if (DATA[i] == last) continue;

            last = DATA[i];
            SELECTED[cnt] = DATA[i];

            rec(i, cnt + 1);

            SELECTED[cnt] = -1;
        }
    }

    private static void appendResult() {
        for (int i = 0; i < M; i++) {
            sb.append(SELECTED[i]).append(" ");
        }
        sb.append("\n");
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
