package its.codingtest.implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 - 치킨 배달(골5)
 * https://www.acmicpc.net/problem/15686
 */
public class p332 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, M, RESULT;
    private static List<Home> HOMES;
    private static List<Chicken> CHICKENS;

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        HOMES = new ArrayList<>();
        CHICKENS = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int v = inputProcessor.nextInt();
                if(v == 1) {
                    HOMES.add(new Home(i, j));
                } else if(v == 2) {
                    CHICKENS.add(new Chicken(i, j));
                }
            }
        }

        RESULT = Integer.MAX_VALUE;
    }

    private static class Home {
        private final int x;
        private final int y;

        public Home(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Chicken {
        private final int x;
        private final int y;

        public Chicken(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void pro() {
        int[] selected = new int[M];
        rec(0, 0, selected);

        sb.append(RESULT);
    }

    private static void rec(int idx, int count, int[] selected) {
        if(count == M) {
            int total = 0;
            for(Home h : HOMES) {
                int dist = Integer.MAX_VALUE;
                for(int i = 0; i < selected.length; i++) {
                    Chicken c = CHICKENS.get(selected[i]);
                    dist = Math.min(dist, Math.abs(h.x - c.x) + Math.abs(h.y - c.y));
                }
                total += dist;
            }

            RESULT = Math.min(RESULT, total);
            return;
        }

        if(idx >= CHICKENS.size()) return;

        selected[count] = idx;
        rec(idx + 1, count + 1, selected);

        selected[count] = -1;
        rec(idx + 1, count, selected);
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
