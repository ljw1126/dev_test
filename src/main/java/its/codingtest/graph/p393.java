package its.codingtest.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 여행 계획
 *
 * 5 4
 * 0 1 0 1 1
 * 1 0 1 1 0
 * 0 1 0 0 0
 * 1 1 0 0 0
 * 1 0 0 0 0
 * 2 3 4 3
 *
 * YES
 */
public class p393 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, M;
    private static List<Edge> EDGES;

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        EDGES = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int v = inputProcessor.nextInt();
                if(v == 1) {
                    EDGES.add(new Edge(i, j));
                }
            }
        }
    }

    private static class Edge{
        private final int from;
        private final int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    private static void pro() {
        // 절렬 생략
        int[] parent = new int[N + 1];
        for(int i =1; i <= N; i++) parent[i] = i;

        for(Edge e : EDGES) {
            if(compareParent(e.from, e.to, parent)) {
                union(e.from, e.to, parent);
            }
        }


        String travel = inputProcessor.nextLine();
        String[] tokens = travel.split(" ");

        boolean check = true;
        for(int i = 0; i < tokens.length - 1; i++) {
            int v1 = Integer.parseInt(tokens[i]);
            int v2 = Integer.parseInt(tokens[i + 1]);
            if(parent[v1] != parent[v2]) {
                check = false;
                break;
            }
        }

        sb.append(check ? "YES" : "NO");
    }

    private static boolean compareParent(int from, int to, int[] parent) {
        return findParent(from, parent) != findParent(to, parent);
    }

    private static void union(int from, int to, int[] parent) {
        int a = findParent(from, parent);
        int b = findParent(to, parent);
        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int findParent(int n, int[] parent) {
        if(parent[n] == n) return n;
        return parent[n] = findParent(parent[n], parent);
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
