package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * RGB 거리(실버1) https://www.acmicpc.net/problem/1149
 *
 * 상향식, 하향식 둘다 연습해보기 좋은 문제
 * 시간복잡도 : O(N)
 */
public class BOJ1149 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int[][] DATA, DP;
    static int N;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        DATA = new int[N + 1][4];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= 3; j++) {
                DATA[i][j] = inputProcessor.nextInt();
            }
        }

        DP = new int[N + 1][4];
        for(int i = 1; i <= 3; i++) {
            DP[1][i] = DATA[1][i];
        }
    }

    private static void pro() {
        //bottomUp();

        int result = Math.min(topDown(N, 1), Math.min(topDown(N , 2), topDown(N , 3)));
        sb.append(result);
    }

    private static void bottomUp() {
        for(int i = 2; i <= N; i++) {
            DP[i][1] = Math.min(DP[i - 1][2], DP[i - 1][3]) + DATA[i][1];
            DP[i][2] = Math.min(DP[i - 1][1], DP[i - 1][3]) + DATA[i][2];
            DP[i][3] = Math.min(DP[i - 1][1], DP[i - 1][2]) + DATA[i][3];
        }

        int result = Math.min(DP[N][1], Math.min(DP[N][2], DP[N][3]));
        sb.append(result);
    }

    private static int topDown(int row, int col) {
        if(row < 1) return 0;
        if(DP[row][col] != 0) return DP[row][col];

        if(col == 1) {
            DP[row][col] = Math.min(topDown(row - 1, 2), topDown(row - 1, 3)) + DATA[row][col];
        } else if(col == 2) {
            DP[row][col] = Math.min(topDown(row - 1, 1), topDown(row - 1, 3)) + DATA[row][col];
        } else if(col == 3) {
            DP[row][col] = Math.min(topDown(row - 1, 1), topDown(row - 1, 2)) + DATA[row][col];
        }

        return DP[row][col];
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
