package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 팰린드롬? (골드4) https://www.acmicpc.net/problem/10942
 *
 * 수열 N ( 1 ~ 2000)
 * 질문의 개수 M ( 1 ~ 10^6)
 *
 * N * N 2차원 배열 선언시 int의 경우 2000 * 2000 * 4byte = 16Mb
 *
 * 3이상의 경우 시작과 끝 수가 동일하고, 양 끝 사이가 펠린드롬이라면 DP[i + 1][j - 1]
 */
public class BOJ10942 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();

    static int N, M;
    static int[] DATA;
    static int[][] PALINDROME; // boolean : 1byte, int : 4byte

    public static void main(String[] args) throws IOException {
        input();

        preprocess();
        //bottomUp();

        M = inputProcessor.nextInt();
        for(int i = 1; i <= M; i++) {
            int s = inputProcessor.nextInt();
            int e = inputProcessor.nextInt();

            //sb.append(PALINDROME[s][e]).append(NEW_LINE);
            sb.append(topDown(s, e)).append(NEW_LINE);
        }

        output();
    }

    private static int topDown(int s, int e) {
        if(s == e) return 1;
        if(PALINDROME[s][e] != -1) return PALINDROME[s][e];

        return PALINDROME[s][e] = (DATA[s] == DATA[e] && topDown(s + 1, e - 1)  == 1) ? 1 : 0;
    }

    /**
     * top-down 사용하려면 방문하지 않았다는 의미를 표기할 필요가 있는데
     * boolean은 true/false 뿐이라서 정보 표현하기 어려움
     */
    private static void preprocess() {
        PALINDROME = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(PALINDROME[i], 1, N + 1, -1);
            PALINDROME[i][i] = 1;
        }

        for(int i = 1; i < N; i++) {
            if(DATA[i] == DATA[i + 1]) PALINDROME[i][i + 1] = 1;
            else PALINDROME[i][i + 1] = 0;
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();

        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }
    }

    private static void bottomUp() {
        for(int len = 3; len <= N; len++) {
            for(int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                if(DATA[i] == DATA[j] && PALINDROME[i + 1][j - 1] == 1) {
                    PALINDROME[i][j] = 1;
                }
            }
        }
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
