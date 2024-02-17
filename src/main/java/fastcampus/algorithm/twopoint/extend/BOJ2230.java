package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 수고르기(골드5)
 *
 * - 직접 풀이 못함
 * - 이진탐색과 투포인터 둘다 해봄
 */
public class BOJ2230 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt(); // 차이가 M이상 나면서 작은 경우

        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int L = 1;
        int R = N;

        while(L < R) {
            int mid = (L + R) / 2;

            if(arr[mid] < target) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return R;
    }

    private static void pro() {
        Arrays.sort(A, 1, N + 1);

        int result = Integer.MAX_VALUE;

        // target 이상이 되는 최소값
        for(int i = 1; i <= N; i++) {
            int idx = binarySearch(A, A[i] + M);

            if(A[idx] - A[i] >= M) {
                result = Math.min(result, A[idx] - A[i]);
            }
        }

        sb.append(result);
    }

    private static void output() throws IOException {
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
