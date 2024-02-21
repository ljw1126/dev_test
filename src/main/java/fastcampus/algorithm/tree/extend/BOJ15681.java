package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리와 쿼리(골드5) https://www.acmicpc.net/problem/15681
 *
 * 설명까지 있는 좋은 문제
 * - 트리 부모 구하기, 서브 트리 수 구하기
 * - 시간복잡도 : O(V + E)
 */
public class BOJ15681 {
    static StringBuilder sb = new StringBuilder();

    static int N, R, Q;
    static List<Integer>[] ADJ;
    static List<Integer> QUERY = new LinkedList<>();
    static int[] SUBTREE;


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }
    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 정점의 수
        R = inputProcessor.nextInt(); // 루트의 번호
        Q = inputProcessor.nextInt(); // 쿼리의 수

        SUBTREE = new int[N + 1];

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

        for(int i = 1; i <= Q; i++) {
            QUERY.add(inputProcessor.nextInt());
        }
    }

    private static void pro() {
        dfs(R, -1);

        for(int q : QUERY) {
            sb.append(SUBTREE[q]).append("\n");
        }
    }

    private static void dfs(int node, int prev) {
        SUBTREE[node] = 1;

        for(int child : ADJ[node]) {
            if(child == prev) continue;

            dfs(child, node);
            SUBTREE[node] += SUBTREE[child];
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
