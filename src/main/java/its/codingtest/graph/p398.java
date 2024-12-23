package its.codingtest.graph;

import java.io.*;
import java.util.*;

/*
    행성 터널
    - 크루스칼 알고리즘 사용
    - O(ElogE)
    - 행성만 주어질 때 간선을 직접 구하는게 포인트
    - 이때 이중 반복문으로 구할 경우 시간초과, 메모리 초과
    - 각 축 마다 오름차순 정렬해서 간선 구하는 방법으로 할 경우 통과 가능
    - 간선의 개수는 = 3N (3은 x, y, z 축)
 */
public class p398 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;
    private static Plant[] PLANTS;
    private static List<Edge> EDGES;

    private static void input() {
        N = inputProcessor.nextInt();
        PLANTS = new Plant[N];

        for(int i = 0; i < N; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int z = inputProcessor.nextInt();

            PLANTS[i] = new Plant(i, x, y, z);
        }

        // 간선의 개수 = 3N
        // 이중 반복문으로 간선 구할 경우 N* (N - 1) / 2개가 되버려서, 시간초과, 메모리 초과 발생 가능
        // 각 x좌표마다 오륾 차순 정렬해서 간선을 구한다 (y, z 마찬가지)
        EDGES = new ArrayList<>();
        Arrays.sort(PLANTS, Comparator.comparingInt(p -> p.x));
        for(int i = 0; i < N - 1; i++) {
            Plant a = PLANTS[i];
            Plant b = PLANTS[i + 1];
            EDGES.add(new Edge(a.idx, b.idx, Math.abs(a.x - b.x)));
        }

        Arrays.sort(PLANTS, Comparator.comparingInt(p -> p.y));
        for(int i = 0; i < N - 1; i++) {
            Plant a = PLANTS[i];
            Plant b = PLANTS[i + 1];
            EDGES.add(new Edge(a.idx, b.idx, Math.abs(a.y - b.y)));
        }

        Arrays.sort(PLANTS, Comparator.comparingInt(p -> p.z));
        for(int i = 0; i < N - 1; i++) {
            Plant a = PLANTS[i];
            Plant b = PLANTS[i + 1];
            EDGES.add(new Edge(a.idx, b.idx, Math.abs(a.z - b.z)));
        }
    }

    private static class Plant {
        private final int idx;
        private final int x;
        private final int y;
        private final int z;

        public Plant(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
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

        int result = 0;
        for(Edge e : EDGES) {
            if(compareParent(e.from, e.to, parent)) {
                union(e.from, e.to, parent);
                result += e.cost;
            }
        }

        sb.append(result);
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

