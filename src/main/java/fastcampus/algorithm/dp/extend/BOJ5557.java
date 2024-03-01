package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 1학년(골드5) https://www.acmicpc.net/problem/5557
 *
 * 2차원 배열을 사용
 * 상향식 O(N)
 * 하향식 O(2^n) , Memorization 기법 활용
 *
 * 최대치는 long
 *
 * top-down 하향식, 재귀 풀이 ( 마치 리프 노드의 값을 다 더해주는 느낌, 트리의 DP 풀이 방식)
 * https://steady-coding.tistory.com/171
 */
public class BOJ5557 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N;
    static int[] DATA;
    static long[][] DP;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();

        //for(long[] _dp : DP) Arrays.fill(_dp, -1);
        //System.out.println(executeTopDown(1, A[1])); ??
    }

    private static void input() {
        N = inputProcessor.nextInt();

        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }

        DP = new long[N + 1][21];
        DP[1][DATA[1]] = 1;

        for(int i = 2; i <= N; i++) Arrays.fill(DP[i], -1);
    }

    private static void pro() {
        //bottomUp();

        long result = topDown(N - 1, DATA[N]);
        sb.append(result);
    }

    private static void bottomUp() {
        for(int i = 2; i < N; i++) {
            for(int j = 0; j <= 20; j++) {
                if(DP[i - 1][j] > 0) {
                    int v1 = j + DATA[i];
                    int v2 = j - DATA[i];

                    if(isValid(v1)) DP[i][v1] += DP[i - 1][j];

                    if(isValid(v2)) DP[i][v2] += DP[i - 1][j];
                }
            }
        }

        sb.append(DP[N - 1][DATA[N]]);
    }

    private static boolean isValid(int value) {
        return 0 <= value && value <= 20;
    }

    private static long topDown(int depth, int target) {
        if(depth < 1) return 0L;
        if(!isValid(target)) return 0L;
        if(DP[depth][target] != -1) return DP[depth][target];

        DP[depth][target] = 0;
        DP[depth][target] += topDown(depth - 1, target + DATA[depth - 1]);
        DP[depth][target] += topDown(depth - 1, target - DATA[depth - 1]);

        return DP[depth][target];
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
            while(st == null || !st.hasMoreElements()) {
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
    /*
    static long executeTopDown(int depth, int value) {
        if(value < 0 || value > 20) return 0L;
        if(depth == N - 1) {
            return value == A[N] ? 1L : 0L;
        }

        if(DP[depth][value] != -1) return DP[depth][value];

        return DP[depth][value] = executeTopDown(depth + 1, value + A[depth + 1])
                + executeTopDown(depth + 1, value - A[depth + 1]);
    }
     */
}
