package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * DFS, 인접리스트로 풀이시 O(V + E) 만큼의 시간 소요
 * O(V + E) = O(500,000 + 499,999)
 */
public class BOJ15900 {

    static StringBuilder sb = new StringBuilder();

    static int ROOT = 1;
    static int N;
    static int[] DEPTH;
    static List<Integer>[] ADJ;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }
    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();

        DEPTH = new int[N + 1];

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N - 1; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            ADJ[a].add(b);
            ADJ[b].add(a);
        }
    }

    private static void pro() {
        dfs(ROOT, -1, 0);

        if(DEPTH[ROOT] % 2 == 1) sb.append("Yes");
        else sb.append("No");
    }

    private static void dfs(int node, int prev, int depth) {
        if(ADJ[node].size() == 1 && ADJ[node].get(0) == prev) {
            DEPTH[node] = depth;
            return;
        }

        for(int child : ADJ[node]) {
            if(child == prev) continue;

            dfs(child, node, depth + 1);
            DEPTH[node] += DEPTH[child];
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
