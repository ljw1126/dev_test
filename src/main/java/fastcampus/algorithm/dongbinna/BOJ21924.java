package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21924 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;
    private static long TOTAL;
    private static List<Edge> EDGES;
    private static int[] PARENT;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        EDGES = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();
            int c = inputProcessor.nextInt(); // 비용

            EDGES.add(new Edge(a, b, c));
            TOTAL += c;
        }

        PARENT = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            PARENT[i] = i;
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static void pro() {
        
    }

    private static void kruskal() {
        Collections.sort(EDGES);

        int cnt = 0;
        long acc = 0L;
        for (Edge edge : EDGES) {
            if (unionFind(edge.from, edge.to)) {
                union(edge.from, edge.to);
                cnt += 1;
                acc += edge.cost;
            }
        }

        if (cnt == N - 1) { // 트리의 간선은 N - 1개
            sb.append(TOTAL - acc);
        } else {
            sb.append(-1);
        }
    }

    private static boolean unionFind(int from, int to) {
        int a = findParent(from);
        int b = findParent(to);
        return a != b;
    }

    private static void union(int from, int to) {
        int a = findParent(from);
        int b = findParent(to);

        if (a > b) {
            PARENT[a] = b;
        } else {
            PARENT[b] = a;
        }
    }

    private static int findParent(int idx) {
        if (PARENT[idx] == idx) return idx;

        PARENT[idx] = findParent(PARENT[idx]);
        return PARENT[idx];
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
