package fastcampus.algorithm.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240216
public class BOJ7795_other {
    private static int T, N, M;
    private static int[] A, B;
    private static StringBuilder sb = new StringBuilder();
    private static void pro() throws IOException {
        InputProcessor inputProcessor = new InputProcessor();
        T = inputProcessor.nextInt();
        while(T > 0) {
            N = inputProcessor.nextInt();
            M = inputProcessor.nextInt();

            A = new int[N];
            B = new int[M];

            for(int i = 0; i < N; i++) {
                A[i] = inputProcessor.nextInt();
            }

            for(int i = 0; i < M; i++) {
                B[i] = inputProcessor.nextInt();
            }

            sb.append(binarySearch()).append("\n");
            T -= 1;
        }
    }

    private static int binarySearch() {
        Arrays.sort(B); // 정렬

        int result = 0;
        for(int i = 0; i < N; i++) {
            result += lowerBound(A[i], B, 0, M - 1);
        }

        return result;
    }

    // target보다 작은 숫자는 몇개인가
    private static int lowerBound(int target, int[] b, int start, int end) {
        int L = start;
        int R = end;
        while(L <= R) {
            int mid = (L + R) / 2;
            if(b[mid] < target) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return L;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
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
    }
}
