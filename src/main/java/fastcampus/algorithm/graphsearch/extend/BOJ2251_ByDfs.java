package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2251_ByDfs {
    static StringBuilder sb = new StringBuilder();

    static int A, B, C;
    static boolean[][][] VISIT;

    static int[] LIMIT;
    static boolean[] POSSIBLE;

    private static class Bottle {
        int[] arr;

        public Bottle(int[] arr) {
            this.arr = arr;
        }

        public Bottle move(int from, int to, int[] limit) {
            int[] _arr = new int[3];
            for(int i = 0; i < 3; i++) {
                _arr[i] = this.arr[i];
            }

            if(_arr[from] + _arr[to] <= limit[to]) {
                _arr[to] += _arr[from];
                _arr[from] = 0;
            } else {
                _arr[from] -= (limit[to] - _arr[to]);
                _arr[to] = limit[to];
            }

            return new Bottle(_arr);
        }
    }


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    /**
     * 초기 세번째 물통(c)만 가득 차 있다
     * 첫번쨰 물통이 비어 있을 때, 세번째 물통에 담겨있는 물의 양을 구하라
     */
    private static void pro() {
        dfs(new Bottle(new int[] {0, 0, C}));

        for(int i = 0; i <= C; i++) {
            if(POSSIBLE[i]) {
                sb.append(i).append(" ");
            }
        }
    }

    private static void dfs(Bottle bottle) {
        VISIT[bottle.arr[0]][bottle.arr[1]][bottle.arr[2]] = true;
        if(bottle.arr[0] == 0) POSSIBLE[bottle.arr[2]] = true;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == j) continue;

                Bottle next = bottle.move(i, j, LIMIT);
                if(!VISIT[next.arr[0]][next.arr[1]][next.arr[2]]) {
                    dfs(next);
                }
            }
        }
    }


    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        A = inputProcessor.nextInt();
        B = inputProcessor.nextInt();
        C = inputProcessor.nextInt();

        VISIT = new boolean[201][201][201];
        LIMIT = new int[]{A, B, C};
        POSSIBLE = new boolean[201];
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
