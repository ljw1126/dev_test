package its.codingtest.binarysearch;

import java.io.*;
import java.util.StringTokenizer;

/**
 *  고정점 찾기
 *  - 직접 풀이
 *  - DATA[mid] == mid 인 경우 고정점
 *  - 고정점이 아닌 경우 DATA[mid] < mid 크다면 L = mid + 1 (값이 음수이거나 작으니)
 *
 * 5
 * -15 -6 1 3 7
 *
 * 출력 3
 *
 * 7
 * -15 -4 2 8 9 13 15
 *
 * 출력 2
 *
 * 7
 * -15 -4 3 8 9 13 15
 *
 * 출력 -1
 */
public class p368 {
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
        DATA = new int[N];

        for(int i = 0; i < N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int result = -1;
        int L = 0;
        int R = N;

        while(L <= R) {
            int mid = (L + R) / 2;

            if(mid == DATA[mid]) { // 고정 값인가
                result = mid;
                break;
            }

            if(DATA[mid] < mid) L = mid + 1;
            else R = mid - 1;
        }

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
