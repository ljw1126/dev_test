package fastcampus.algorithm.twopoint.extend;

import fastcampus.algorithm.twopoint.Practice;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 수들의 합2(실버4)
 * https://www.acmicpc.net/problem/2003
 *
 * 최대치 : 3 * 10^8 (int 범위내)
 * 투포인터 풀이시
 * 시간 복잡도 : O(N)
 * 공간 복잡도 : O(N)
 */
public class BOJ2003 {
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
        M = inputProcessor.nextInt();

        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        int result = 0;
        int sum = 0;
        for(int L = 1, R = 0; L <= N; L++) {

            while(R + 1 <= N && sum < M) {
                R += 1;
                sum += A[R];
            }

            if(sum == M) {
                result += 1;
            }

            sum -= A[L];
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
