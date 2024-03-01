package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 포도주 시식(실버1) https://www.acmicpc.net/problem/2156
 *
 * - 직절 풀지 못함 (24/2/27)
 * - 시간복잡도 O(N)
 *
 * 연속으로 3잔을 마실 수 없을 때 아래의 경우에 따라 DP 테이블을 채우면 DP[N]에 최대값이 구해짐
 * case1. i 번째 포도주를 마시지 않는 경우 = DP[i - 1]
 * case2. i 번재, i - 1번째 포도주를 마시고, DP[i - 3]을 마시는 경우
 * case3. i번째 포도주를 마시고, DP[i - 2]를 마시는 경우
 *
 * top-down 재귀 방식으로 풀이 가능
 * https://st-lab.tistory.com/135
 */
public class BOJ2156 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int[] DATA;
    static int N;
    static int[] DP;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        DP = new int[N + 1];

        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }

        Arrays.fill(DP, -1);

        // 초기화
        DP[0] = 0;
        DP[1] = DATA[1];
        if(N >= 2) {
            DP[2] = DATA[1] + DATA[2];
        }
    }

    private static void pro() {
        //bottomUp();

        topDown(N);
        sb.append(DP[N]);
    }

    private static void bottomUp() {
        for(int i = 3; i <= N; i++) {
            DP[i] = DP[i - 1];
            DP[i] = Math.max(DP[i], Math.max(DATA[i - 1] + DP[i - 3], DP[i - 2]) + DATA[i]);
        }

        sb.append(DP[N]);
    }

    private static int topDown(int n) {
        if(n < 1) return 0;
        if(DP[n] != -1) return DP[n];

        DP[n] = topDown(n - 1);
        DP[n] = Math.max(DP[n], Math.max(DATA[n - 1] + topDown(n - 3), topDown(n - 2)) + DATA[n]);

        return DP[n];
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
