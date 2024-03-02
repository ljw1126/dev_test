package fastcampus.algorithm.test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 광부 호석(플5)
 * https://www.acmicpc.net/problem/21279
 *
 * 직접 풀이 못함
 * - while문안에서 CNT > C일때 제거하는게 제대로 처리되지 않는 예외 케이스가 있었던듯하다
 */
public class BOJ21279 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int MAX = 100000;

    static int N, C;
    static long SUM, CNT;
    static List<Stone>[] X, Y;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        int x = 0;
        int y = MAX;
        long ans = 0L;
        while(y >= 0 && x <= MAX) {
            add(x, y);

            while(CNT > C) { // 여기가 문제였던듯하다
                del(x, y);
                y -= 1;
            }

            if(CNT <= C) {
                ans = Math.max(ans, SUM);
            }

            x += 1;
        }

        sb.append(ans);
    }

    private static void add(int x, int y) {
        for(Stone stone : X[x]) {
            if(stone.y <= y) {
                SUM += stone.v;
                CNT += 1;
            }
        }
    }

    // y 높이에 있는 stone 중에 0 ~ 현재 x 까지 위치한 돌을 지움
    private static void del(int x, int y) {
        for(Stone stone : Y[y]) {
            if(stone.x <= x) {
                SUM -= stone.v;
                CNT -= 1;
            }
        }
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 광물의 개수
        C = inputProcessor.nextInt(); // 호석이가 가진 돈

        X = new ArrayList[MAX + 1];
        Y = new ArrayList[MAX + 1];
        for(int i = 0; i <= MAX; i++) {
            X[i] = new ArrayList<>();
            Y[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int v = inputProcessor.nextInt(); // 아름다운 정도

            X[x].add(new Stone(x, y, v));
            Y[y].add(new Stone(x, y, v));
        }
    }

    private static class Stone {
        int x;
        int y;
        int v;

        public Stone(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
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
