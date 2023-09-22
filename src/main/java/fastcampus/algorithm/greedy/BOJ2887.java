package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 행성터널(플5)
 * https://www.acmicpc.net/problem/2887
 *
 * 크루스칼, 프림 알고리즘
 * - n이 최대 100000만 일때
 * - 간선을 계산할 경우 100,000^2 = 100억개 (러프하게, 중복 제외시 더 적지만)
 * - int 2개 , long 1개니깐  = 16byte
 * - 100억 * 16 byte = 10^10 * 16 = 160Gb -- 메모리 초과 발생
 */
public class BOJ2887 {
    static StringBuilder sb = new StringBuilder();

    static List<Edge> edges = new ArrayList<>();

    static int N;
    static int[] parent;

    static class Plant {
        int x;
        int y;
        int z;

        public Plant(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        long cost;

        public Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            if(cost < other.cost) return -1;
            else if(cost == other.cost) return 0;
            return 1;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        Plant[] plants = new Plant[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            plants[i] = new Plant(x, y, z);
        }

        Arrays.sort(plants, 1, N + 1, (a, b) -> a.x - b.x); // toIndex : exclusive
        for(int i = 1; i < N; i++) {
            long cost = Math.abs(plants[i].x - plants[i + 1].x);

            edges.add(new Edge(i, i +1, cost));
        }

        Arrays.sort(plants, 1, N + 1, (a, b) -> a.y - b.y);
        for(int i = 1; i < N; i++) {
            long cost = Math.abs(plants[i].y - plants[i + 1].y);

            edges.add(new Edge(i, i + 1, cost));
        }

        Arrays.sort(plants, 1, N + 1, (a, b) -> a.z - b.z);
        for(int i = 1; i < N; i++) {
            long cost = Math.abs(plants[i].z - plants[i + 1].z);

            edges.add(new Edge(i, i +1, cost));
        }

    }

    static int getParent(int idx) {
        if(parent[idx] == idx) return idx;

        return parent[idx] = getParent(parent[idx]);
    }

    static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    static boolean unionFind(int a, int b) {
        return getParent(a) == getParent(b);
    }

    static void kruskal() {
        Collections.sort(edges);

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        long cost = 0L;
        for(Edge edge : edges) {
            if(!unionFind(edge.from, edge.to)) {
                union(edge.from, edge.to);
                cost += edge.cost;
            }
        }

        System.out.println(cost);

        //System.out.println(edges);
    }

    public static void main(String[] args) throws Exception {
        input();
        kruskal();
    }
}
