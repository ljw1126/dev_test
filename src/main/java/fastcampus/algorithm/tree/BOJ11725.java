package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리의 부모 찾기(실버2)
 * https://www.acmicpc.net/problem/11725
 *
 * - 노드 수 N ,최대 100000
 * - 간선 수 N - 1
 * - dfs, bfs 시간 복잡도 O(V + E)
 */
public class BOJ11725 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static List<Integer>[] ADJ;
    static int[] PARENT;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }
    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N - 1; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ[from].add(to);
            ADJ[to].add(from);
        }

        PARENT = new int[100001]; // 400Kb (int : 4byte)
        Arrays.fill(PARENT, -1);
    }

    private static void pro() {
        dfs(1, 0);

        for(int i = 2; i <= N; i++) {
            sb.append(PARENT[i]).append("\n");
        }
    }

    private static void dfs(int node, int prev) {
        PARENT[node] = prev;

        for(int next : ADJ[node]) {
            if(PARENT[next] != -1) continue;

            dfs(next, node);
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
