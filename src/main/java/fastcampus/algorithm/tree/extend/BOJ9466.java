package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 텀 프로젝트(골3)
 * https://www.acmicpc.net/problem/9466
 *
 * - 숫자 고르기 2668과 동일
 */
public class BOJ9466 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int T, N;
    static int[] STUDENT;
    static boolean[] VISIT, FINISH;

    public static void main(String[] args) throws IOException {
        T = inputProcessor.nextInt();
        while(T > 0) {
            input();
            pro();
            T -= 1;
        }

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        STUDENT = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            STUDENT[i] = inputProcessor.nextInt();
        }

        VISIT = new boolean[N + 1];
        FINISH = new boolean[N + 1];
    }

    public static void pro() {
        List<Integer> team = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(!FINISH[i]) dfs(i, team);
        }

        sb.append(N - team.size()).append("\n");
    }

    private static void dfs(int node, List<Integer> result) {
        VISIT[node] = true;

        int next = STUDENT[node];
        if(!VISIT[next]) {
            dfs(next, result);
        } else if(!FINISH[next]){
            int a = next;
            int b = node;
            while(a != b) {
                result.add(a);
                a = STUDENT[a];
            }
            result.add(b);
        }

        FINISH[node] = true;
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
