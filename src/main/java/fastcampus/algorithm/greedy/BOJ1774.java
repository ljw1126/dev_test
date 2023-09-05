package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *우주신과의 교감(골드3)
 * https://www.acmicpc.net/problem/1774
 *
 *
 * - 직접 풀지 못함
 * - 피타고라스 정리의 빗변 계산식이 거리 계산식으로 쓰일 줄 생각못함
 * - 초기화에서도 .. Point[1] Point[2] 에 대한 거리 계산 후 Info{1,2, distance}로 저장 생각도 못함
 * - 2차원 좌표라서 착각했는데, 각 좌표가 노드1, 2, 3, 4라고 생각하고 풀면 되는 거였네
 *
 * 참고
 * https://steady-coding.tistory.com/120
 */
public class BOJ1774 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static List<Node> info;

    static int[] parent;

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

    static class Node implements Comparable<Node> {
        int from;
        int to;

        double cost;

        public Node(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if(cost < other.cost) return -1;
            else if(cost == other.cost) return 0;
            return 1;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;


        // 2차원 좌표를 각각 노드로 보고 정의하면 되었음 ..
        Point[] points = new Point[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            double from = Double.parseDouble(st.nextToken());
            double to = Double.parseDouble(st.nextToken());

            points[i] = new Point(i, from, to);
        }

        // 간선간의 거리 계산 d = 루트((x1 - x2)^2 + (y1 - y2)^2)
        // 2차원 좌표 line 하나를 노드로 보기때문에, 거리 계산은 피타고라스 빗변 계산 공식으로 처리하면 됨
        info = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                Point p1 = points[i];
                Point p2 = points[j];

                double distance = calDistance(p1.x, p1.y, p2.x, p2.y);

                info.add(new Node(p1.num, p2.num, distance));
            }
        }

        // 이미 연결된 노드에 대한 처리
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(!unionFind(from, to)) {
                union(from, to);
            }
        }
    }

    static double calDistance(double x1, double y1, double x2, double y2) { // 피타고라스 정리의 빗변
        double v1 = Math.pow((x1 - x2), 2);
        double v2 = Math.pow((y1 - y2), 2);
        return Math.sqrt(v1 + v2);
    }

    static int getParent(int node) {
        if(parent[node] == node) return node; // 재귀 종료 조건

        return parent[node] = getParent(parent[node]);
    }

    static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    static boolean unionFind(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        return parentA == parentB;
    }

    static void pro() {
        Collections.sort(info);

        double result = 0.0;
        for(Node node : info) {
            if(!unionFind(node.from, node.to)) {
                union(node.from, node.to);
                result += node.cost;
            }
        }

        System.out.println(String.format("%.2f", result));

        //for(Node node : info) System.out.println(node);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
