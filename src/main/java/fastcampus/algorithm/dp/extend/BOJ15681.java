package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리와 쿼리(골5)
 * https://www.acmicpc.net/problem/15681
 *
 * - 직접 풀이
 * - 시간 복잡도 : O(V + E)
 * - 트리 유형에서도 풀었던 건데 좋은 문제
 */
public class BOJ15681 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static String NEW_LINE = System.lineSeparator();
    static int N, R, Q;
    static List<Integer>[] ADJ;
    static int[] SUBTREE;

    public static void main(String[] args) throws IOException {
        input();
        preprocess();

        for(int i = 1; i <= Q; i++) {
            int u = inputProcessor.nextInt();
            sb.append(SUBTREE[u]).append(NEW_LINE);
        }

        output();
    }

    private static void preprocess() {
        dfs(R, -1);
    }

    private static void dfs(int node, int prev) {
        SUBTREE[node] = 1;

        for(int child : ADJ[node]) {
            if(child == prev) continue;

            dfs(child, node);
            SUBTREE[node] += SUBTREE[child];
        }
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 정점의 수
        R = inputProcessor.nextInt(); // 루트
        Q = inputProcessor.nextInt(); // 쿼리의 수

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N - 1; i++) {
            int u = inputProcessor.nextInt();
            int v = inputProcessor.nextInt();

            ADJ[u].add(v);
            ADJ[v].add(u);
        }

        SUBTREE = new int[N + 1];
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
