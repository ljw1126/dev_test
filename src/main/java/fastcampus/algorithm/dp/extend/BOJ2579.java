package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * 계단오르기(실버3)
 * https://www.acmicpc.net/problem/2579
 *
 * - DP[i][0] : i - 1번째 계단을 밟지 않고 오르는 경우 max(DP[i - 2][0], DP[i - 2][1]) + SETP[i];
 * - DP[i][1] : i - 1번째 계단을 밝고, 오르는 경우 (이때 연속된 세칸을 밟으면 안됨), DP[i - 1][0] + STEP[i]
 * - 시간복잡도 : O(N)
 * - 최대치 : Integer 범위내
 */
public class BOJ2579 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N;
    static int[][] DP;
    static int[] STEP;

    static int[][] COME;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        STEP = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            STEP[i] = inputProcessor.nextInt();
        }

        DP = new int[N + 1][2];
        DP[1][0] = STEP[1];

        if(N >= 2) {
            DP[2][0] = STEP[2];
            DP[2][1] = DP[1][0] + STEP[2];
        }

        COME = new int[N + 1][2]; // 백트래킹 기록용
        COME[0][0] = -1;
        COME[0][1] = -1;
    }

    private static void pro() {
        //bottom-up
        bottomUp();
        backtracking();


        //top-down
        //int result = Math.max(topDown(N , 0), topDown(N , 1));
        //sb.append(result);
    }

    private static void bottomUp() {
        for(int i = 3; i <= N; i++) {
            DP[i][0] = Math.max(DP[i - 2][0], DP[i - 2][1]) + STEP[i];
            DP[i][1] = DP[i - 1][0] + STEP[i];

            // backtracking
            COME[i][0] = DP[i - 2][0] > DP[i - 2][1] ? 0 : 1;
            COME[i][1] = 0;
        }

        sb.append(Math.max(DP[N][0], DP[N][1]));

    }

    private static int topDown(int n, int idx) {
        // 종료 조건
        if(n <= 2) return DP[n][idx];
        // 메모리제이션
        if(DP[n][idx] != 0) return DP[n][idx];

        DP[n][0] = Math.max(topDown(n - 2, 0), topDown(n - 2, 1)) + STEP[n];
        DP[n][1] = topDown(n - 1, 0) + STEP[n];

        return DP[n][idx];
    }

    // hard
    private static void backtracking() {
        // backtracking
        Deque<Integer> stack = new ArrayDeque<>();
        int idx = N;
        int p = -1;
        if(DP[N][0] > DP[N][1]) {
            stack.push(DP[N][0]);
            p = COME[N][0];
            idx = N - 2;
        } else {
            stack.push(DP[N][1]);
            p = COME[N][1];
            idx = N - 1;
        }

        while(idx > 0) {
            stack.push(DP[idx][p]);
            int temp = COME[idx][p];

            if(p == 0) {
                idx -= 2;
            } else {
                idx -= 1;
            }

            p = temp;
        }

        StringBuilder backtracking = new StringBuilder();
        while(!stack.isEmpty()) {
            backtracking.append(stack.pop()).append(" ");
        }

        System.out.println(backtracking);
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
}
