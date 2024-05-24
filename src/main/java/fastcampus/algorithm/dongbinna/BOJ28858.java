package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ28858 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, K;
    private static int[] Si, Di, Pi;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 카드의 개수
        K = inputProcessor.nextInt(); // 카드 섞ㅇ은 횟수

        Si = new int[N + 1]; // K번 카드 섞은 후 결과
        for (int i = 1; i <= N; i++) {
            Si[i] = inputProcessor.nextInt();
        }

        Di = new int[N + 1]; // Di번재 카드를 i번재로 이동하기
        for (int i = 1; i <= N; i++) {
            Di[i] = inputProcessor.nextInt();
        }

        Pi = new int[N + 1];
    }

    private static void pro() {
        while (K > 0) {
            K -= 1;

            for (int i = 1; i <= N; i++) {
                Pi[Di[i]] = Si[i];
            }

            for (int i = 1; i <= N; i++) {
                Si[i] = Pi[i];
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(Si[i]).append(" ");
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
