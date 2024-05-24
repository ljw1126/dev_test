package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 가희와 키워드 (실2)
 * https://www.acmicpc.net/problem/22233
 * <p>
 * - HashSet 자료구조 문제
 */
public class BOJ22233 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;
    private static Set<String> KEYWORDS = new HashSet<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 메모장에 적은 키워드 개수
        M = inputProcessor.nextInt(); // 블로그에 쓴 글의 개수
        for (int i = 1; i <= N; i++) {
            String keyword = inputProcessor.nextLine();
            KEYWORDS.add(keyword);
        }
    }

    private static void pro() {
        for (int i = 1; i <= M; i++) {
            String text = inputProcessor.nextLine();
            String[] keywords = text.split("\\,");
            for (String keyword : keywords) {
                KEYWORDS.remove(keyword);
            }

            sb.append(KEYWORDS.size()).append("\n");
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
