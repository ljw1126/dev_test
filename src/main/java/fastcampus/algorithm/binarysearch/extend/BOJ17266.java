package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 어두운 굴다리 (실버4) https://www.acmicpc.net/problem/17266
 *
 * 최소 높이 구하는 문제 -> 조건 만족시 R을 줄여줌
 */
public class BOJ17266 {

    // 굴다리 길이 N이 최대 10만 (최대치)
    // 가로등의 높이(h)를 정했을대, 모든 굴다리를 비출 수 있는가? (이대 최소가 되는 높이는 ?)
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] LAMP;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    public static void pro() {
        int L = 0;
        int R = 100001;
        int result = 0;
        while(L <= R) {
            int height = (L + R) / 2;

            if(isPossible(height)) {
                result = height;
                R = height - 1; // 최소 높이를 구해야 하므로
            } else {
                L = height + 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(int height) {
        int light = 0;

        for(int i = 1; i <= M; i++) {
            if(light + height >= LAMP[i]) {
                light = LAMP[i] + height;
            } else {
                return false;
            }
        }

        return light >= N; // 빛이 굴다리 길이 N 이상 비추는가
    }

    public static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 굴다리의 길이
        M = inputProcessor.nextInt(); // 가로등의 개수

        LAMP = new int[M + 1];
        for(int i = 1; i <= M; i++) {
            LAMP[i] = inputProcessor.nextInt();
        }
    }

    public static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static class InputProcessor {
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
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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
