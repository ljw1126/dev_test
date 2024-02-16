package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 수 찾기 (실버4)
 * https://www.acmicpc.net/problem/1920
 *
 * 시간복잡도 N^2으로 풀이시 시간초과 발생가능 -> 이진 탐색으로 풀이
 *
 *  모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
 *  = int 범위
 *
 * - 있냐/없냐를 판별하는데 몇개있는지 구하고 있었음(=요구사항 이해 부족)
 * - Arrays.sort()시 범위 지정 누락
 */
public class BOJ1920 {

    static int N, M;
    static int[] A, X;
    static StringBuilder sb = new StringBuilder();

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }

        M = inputProcessor.nextInt();
        X = new int[M + 1];
        for(int i = 1; i <= M; i++) {
            X[i] = inputProcessor.nextInt();
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean binarySearch(int start, int end, int[] arr, int target) {
        int L = start;
        int R = end;

        while(L <= R) {
            int mid = (L + R) / 2;

            if(arr[mid] == target) return true;
            else if(arr[mid] < target) L = mid + 1;
            else R = mid - 1;
        }

        return false;
    }


    private static void pro() {
        Arrays.sort(A, 1, N + 1); // 범위 지정 누락 실수

        for(int i = 1; i <= M; i++) {
            if(binarySearch(1, N, A, X[i])) sb.append("1").append("\n");
            else sb.append("0").append("\n");
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
    }
}
