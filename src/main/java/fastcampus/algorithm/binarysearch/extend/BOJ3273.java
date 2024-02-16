package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 두 수의 합(실버3)
 *
 * - 직접 풀이
 * - 중복 없이 쌍을 구해야 한다
 * - n^2 풀이시 시간초과, 최대치는 200만
 * - 투포인터 처럼 풀이 함
 */
public class BOJ3273 {
    static StringBuilder sb = new StringBuilder();
    static int N, X;
    static int[] A;

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();

        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }

        X = inputProcessor.nextInt();
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void pro() {
        Arrays.sort(A, 1, N + 1); // 정렬 보장

        int result = binarySearch(A, X, 1, N);

        sb.append(result);
    }

    private static int binarySearch(int[] arr, int target, int start, int end) {
        int L = start;
        int R = end;
        int result = 0;
        while(L < R) {
            int sum = arr[L] + arr[R];

            if(sum == target) {
                result += 1;
                L += 1;
            } else if(sum > target) {
                R -= 1;
            } else {
                L += 1;
            }
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
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
    }
}
