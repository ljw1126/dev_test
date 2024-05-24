package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21941 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static String S;
    private static int M;
    private static List<List<Word>> INFO = new ArrayList<>();
    private static int[] DP;

    private static class Word {
        private int len;
        private int score;

        public Word(int len, int score) {
            this.len = len;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        S = inputProcessor.nextLine();
        M = inputProcessor.nextInt();

        INFO = new ArrayList<>();
        for (int i = 0; i <= S.length(); i++) {
            INFO.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            String keyword = inputProcessor.next();
            int score = inputProcessor.nextInt();

            int len = keyword.length();
            if (len >= score) continue;

            int idx = 0;
            while (idx < S.length()) {
                int pos = S.indexOf(keyword, idx);
                if (pos == -1) break;

                INFO.get(pos).add(new Word(len, score));
                idx = pos + 1;
            }
        }
    }

    private static void pro() {
        DP = new int[S.length() + 1];
        for (int i = 0; i < S.length(); i++) {
            DP[i + 1] = Math.max(DP[i + 1], DP[i] + 1);
            for (Word info : INFO.get(i)) {
                DP[i + info.len] = Math.max(DP[i + info.len], DP[i] + info.score);
            }
        }

        sb.append(DP[S.length()]);
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
