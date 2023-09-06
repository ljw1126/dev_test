package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 별자리 만들기(골드3)
 * https://www.acmicpc.net/problem/4386
 *
 * - 2차원 평면에 놓인 별들을 이어 별자리 만듦
 * - 피타고라스 정리로 거리 계산 (빗변)
 * - 직접 풀이 (이전에 풀었던 문제와 동일)
 */
public class BOJ4386 {
    static StringBuilder sb = new StringBuilder();

    static int n;

    static List<Edge>[] adj;

    static class Edge implements Comparable<Edge> {
        int idx;
        double cost;

        public Edge(int idx, double cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            if(cost < edge.cost) return -1;
            else if(cost == edge.cost) return 0;
            return 1;
        }
    }

    static class Point {
        int num;
        double x;
        double y;

        public Point(int num, double x, double y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    static double distance(Point p1, Point p2) {
        double dx = Math.pow(p1.x - p2.x, 2);
        double dy = Math.pow(p1.y - p2.y, 2);
        return Math.sqrt(dx + dy);
    }
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n + 1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            points[i] = new Point(i, x, y);
        }

        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            for(int j = i + 1; j <= n; j++) {
                double distance = distance(points[i], points[j]);

                adj[i].add(new Edge(j, distance));
                adj[j].add(new Edge(i, distance));
            }
        }
    }

    static void pro() {
        Queue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(1, 0.0)); // 시작 노드는 1

        boolean[] visit = new boolean[n + 1];

        double result = 0.0;
        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            result += cur.cost;

            for(Edge next : adj[cur.idx]) {
                if(visit[next.idx]) continue;

                que.add(next);
            }
        }

        System.out.println(String.format("%.2f", result));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
