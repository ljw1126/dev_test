package its.codingtest.graph;

import its.codingtest.Practice;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 어두운 길
 * - 직접 풀이
 * - 크루스칼 알고리즘 O(ElogE), 정렬 시간복잡도가 가장 큼
 *
 * 입력
 * 7 11
 * 0 1 7
 * 0 3 5
 * 1 2 8
 * 1 3 9
 * 1 4 7
 * 2 4 5
 * 3 4 15
 * 3 5 6
 * 4 5 8
 * 4 6 9
 * 5 6 11
 *
 * 출력
 * 51
 */
public class p397 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, M, MAX_COST;
    private static List<Edge> EDGES; // 간선 정보

    private static void input() {
        N = inputProcessor.nextInt(); // 집의 수 (노드)
        M = inputProcessor.nextInt(); // 도로의 수 (간선)

        EDGES = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int z = inputProcessor.nextInt();

            EDGES.add(new Edge(x, y, z));
            MAX_COST += z;
        }
    }

    private static class Edge implements Comparable<Edge>{
        private final int from;
        private final int to;
        private final int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        // 오름차순 정렬
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static void pro() {
        Collections.sort(EDGES);

        int[] parent = new int[N];
        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int sum = 0;
        for(Edge e : EDGES) {
            if(compareParent(e.from, e.to, parent)) { // 부모가 같지 않다면 집합에 포함시킨다. (부모가 같으면 사이클형성되므로 합치면 안된다)
                union(e.from, e.to, parent);
                sum += e.cost;
            }
        }

        // 가로등을 비활성화하여 절약할 수 있는 최대 금액을 출력 = 전체 금액 - 최소 연결 비용
        sb.append(MAX_COST - sum);
    }

    private static boolean compareParent(int from, int to, int[] parent) {
        int a = findParent(from, parent);
        int b = findParent(to, parent);
        return a != b;
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
