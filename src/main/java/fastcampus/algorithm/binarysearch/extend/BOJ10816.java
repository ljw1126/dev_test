package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 숫자 카드2(실버4) https://www.acmicpc.net/problem/10816
 *
 * N, M이 각각 50만개이기때문에 O(N^2)으로 문제 풀이시 시간초과 발생가능 => 이진탐색으로 풀이
 *
 * HashMap 사용해도 풀이 가능하지 않나 싶음
 *
 * // 240216 다시 풀이, 이진탐색 부등호 달라짐
 */
public class BOJ10816 {

    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] A, B;
    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }

        M = inputProcessor.nextInt();
        B = new int[M + 1];
        for(int i = 1; i <= M; i++) {
            B[i] = inputProcessor.nextInt();
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int lowerBound(int[] arr, int target, int start, int end) {
        int L = start;
        int R = end;

        while(L <= R) {
            int mid = (L + R) / 2;

            if(arr[mid] >= target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return L;
    }

    private static int upperBound(int[] arr, int target, int start, int end) {
        int L = start;
        int R = end;

        while(L <= R) {
            int mid = (L + R) / 2;

            if(arr[mid] <= target) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return L;
    }

    private static void pro() {
        Arrays.sort(A, 1, N + 1);

        for(int i = 1; i <= M; i++) {
            int lowerBound = lowerBound(A, B[i], 1, N);
            int upperBound = upperBound(A, B[i], 1, N);

            sb.append(upperBound - lowerBound).append(" ");
            //sb.append(lowerBound).append(",").append(upperBound).append("\n");
        }
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
