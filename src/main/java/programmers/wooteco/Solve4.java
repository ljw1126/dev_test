package programmers.wooteco;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 문제4. 네트워크 (MST, 그리디)
 * <p>
 * *최대치 계산
 * n = 10만개이고, network가 1개만 주어질 때 선을 99,999개 연결해야 한다.
 * 이때 cost가 최대치 10만인 경우 >> 10만 cost * 99,999개 = 약 100억이 구해지므로 long을 써야함
 */
public class Solve4 {

    private static final Data data1 = new Data(6, new int[][]{{1, 2}, {3, 5}, {4, 2}, {5, 6}}, new int[][]{{3, 2, 10}}, 10);
    private static final Data data2 = new Data(4, new int[][]{{1, 2}}, new int[][]{{2, 3, 10}, {3, 1, 12}}, -1);

    public static void main(String[] args) throws IOException {
        pro(data1);
    }

    private static void pro(Data data) throws IOException {
        int n = data.n; // 노드 수
        int[][] network = data.network;
        int[][] repair = data.repair;
        int result = data.answer; // 답

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int[] net : network) {
            edges.add(new Edge(net[0], net[1], 0));
        }

        for (int[] r : repair) {
            edges.add(new Edge(r[0], r[1], r[2]));
        }

        Collections.sort(edges);

        long actual = kruskal(edges, n, parent);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(actual));
        bw.flush();
        bw.close();
    }

    private static long kruskal(List<Edge> edges, int n, int[] parent) {
        int cost = 0;
        int cnt = 0;
        for (Edge e : edges) {
            if (!unionFind(e.from, e.to, parent)) {
                union(e.from, e.to, parent);
                cnt += 1;
                cost += e.dist;
            }
        }

        if (n - 1 == cnt) {
            return cost;
        }

        return -1;
    }

    private static boolean unionFind(int from, int to, int[] parent) {
        int parentA = findParent(from, parent);
        int parentB = findParent(to, parent);

        return parentA == parentB;
    }

    private static int findParent(int i, int[] parent) {
        if (parent[i] != i) {
            return findParent(parent[i], parent);
        }

        return i;
    }

    private static void union(int from, int to, int[] parent) {
        int parentA = findParent(from, parent);
        int parentB = findParent(to, parent);

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    private static class Data {
        private final int n;
        private final int[][] network;
        private final int[][] repair;
        private final int answer;

        public Data(int n, int[][] network, int[][] repair, int answer) {
            this.n = n;
            this.network = network;
            this.repair = repair;
            this.answer = answer;
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            // 오름차순
            return dist - o.dist;
        }
    }
}
