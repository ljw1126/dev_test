package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 원상복구(large), 골2
 * https://www.acmicpc.net/problem/22863
 * <p>
 * - 직접 풀이 못함
 * - 방향이 그래프를 나타낼 줄은 상상도 못함
 * - cycle 배열을 만들고, 0번 인덱스부터 채운다
 */
public class BOJ22863 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N;
    private static long K;
    private static int[] Si, Di;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        K = inputProcessor.nextLong();

        Si = new int[N + 1]; // 섞은 후 카드 배치
        for (int i = 1; i <= N; i++) {
            Si[i] = inputProcessor.nextInt();
        }

        Di = new int[N + 1]; // Di 번재 카드를 i번재로 가져오는 작업
        for (int i = 1; i <= N; i++) {
            Di[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        boolean[] finished = new boolean[N + 1];
        int[] cycle = new int[N + 1];
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (finished[i]) continue;

            int size = 0; // 중요*
            int j = i;
            while (true) {
                cycle[size++] = j;
                j = Di[j];
                finished[j] = true;

                if (j == i) break;
            }

            for (int z = 0; z < size; z++) {
                int from = cycle[z];
                int to = cycle[(int) ((z + K) % size)];
                result[to] = Si[from];
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
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
