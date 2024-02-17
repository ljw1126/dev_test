package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 좋다(골드4)
 * https://www.acmicpc.net/problem/1253
 *
 * - 직접 풀이 못함
 * - 시간복잡도 : O(N^2)
 */
public class BOJ1253 {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        A = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }
    }

    private static boolean isGood(int[] arr, int idx) {
        int L = 1;
        int R = N;
        int target = arr[idx];
        while(L < R) {
            if(L == idx) L += 1;
            else if(R == idx) R -= 1;
            else {
                int sum = arr[L] + arr[R];

                if (sum == target) return true;
                else if (sum < target) L += 1;
                else R -= 1;
            }
        }

        return false;
    }


    private static void pro() {
        Arrays.sort(A, 1, N + 1); // O(NlogN)

        int result = 0;
        for(int i = 1; i <= N; i++) {
            if(isGood(A, i)) { // O(N)
                result += 1;
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
