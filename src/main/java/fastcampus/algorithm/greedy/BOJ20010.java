package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 악덕 영주 혜유 (골드2)
 * https://www.acmicpc.net/problem/20010
 *
 * - 직접 풀이 못함(어려움)
 * - 마을 (N, 최대 1000), 설치 가능 교역로 수 (K, 최대 100만)
 * - 간선이 더 많으니 프림을.. ElogV
 * - 노드는 0번 부터 시작
 * - 최대치는 10억 (마을 1000개, 비용 100만)
 *
 * 임의 두 마을을 이동하는 최단 경로 중 비용이 가장 큰 경로의 비용 ?
 * 마을과 마을을 이동하는 비용이 가장 큰 경로의 비용은 ? (dfs, 그래프, 지름, diameter)
 * -> 트리의 지름을 구한다 (dfs 두 번 수행해서 구함)
 *
 * [절차]
 * 1) 크루스칼 알고리즘으로 최소 가중치와 MST 간선 정보를 구한다
 * 2) MST 간선 정보를 가지고 트리의 지름을 구한다
 * 2-1) 임의 노드에서 dfs 수행해서 제일 깊은 경우가 트리의 지름을 구성하는 한 점이다
 * 2-2) 구한 점에서 다시 dfs 수행하면 최대 길이(지름)이 구해진다.
 */
public class BOJ20010 {

    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int leaf, max;
    static List<Edge>[] adj;

    static int[] parent;
    static List<Node> nodeList;

    static boolean[] visit;

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        // 오름차순 정렬
        @Override
        public int compareTo(Node node) {
            return cost - node.cost;
        }
    }

    static class Edge implements Comparable<Edge> { // MST로 최소 연결된 간선 정보 저장
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int getIdx() {
            return idx;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return cost - edge.cost;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 마을 수 (노드)
        K = Integer.parseInt(st.nextToken()); // 설치 가능한 교역로의 수 (간선)

        nodeList = new ArrayList<>();
        adj = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodeList.add(new Node(from, to, cost));
        }

        visit = new boolean[N];
    }
    /* 프림을 사용할 경우 연결되는 간선의 정보를 저장할 수 없음.. 이전 노드 정보를 알수가 없으니 , 최소힙에서 꺼내지는건 간선 비용에 따른 최소값이지
    static int prime(int start) {
        Queue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(start, 0));

        Arrays.fill(visit, false);

        int result = 0; // 최대치 10억
        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            result += cur.cost;

            for(Edge next : adj[cur.idx]) {
                if(!visit[next.idx]) {
                    que.add(next);
                }
            }
        }

        return result;
    }
     */

    static int getParent(int node) {
        if(parent[node] == node) return node;

        return parent[node] = getParent(parent[node]);
    }

    static void union(int a, int b) {
        int _a = getParent(a);
        int _b = getParent(b);

        if(_a < _b) parent[_b] = _a;
        else parent[_a] = _b;
    }

    static boolean unionFind(int a, int b) {
        int _a = getParent(a);
        int _b = getParent(b);

        return _a == _b;
    }

    static int kruskal() {
        // 정렬
        Collections.sort(nodeList);

        // 초기화
        parent = new int[N];
        for(int i = 0; i < N; i++) parent[i] = i;

        // MST, 최소 가중치 구함
        // MST에서 최대 거리는 구하는 문제
        int result = 0;
        for(Node node : nodeList) {
            if(!unionFind(node.x, node.y)) {
                result += node.cost;
                union(node.x, node.y);

                // MST 구성하는 간선 정보 저장 (양방향)
                adj[node.x].add(new Edge(node.y, node.cost));
                adj[node.y].add(new Edge(node.x, node.cost));
            }
        }

        return result;
    }

    // Tree diameter (트리의 지름 구하기, dfs 2번)
    static void dfs(int node, int dist) {
        visit[node] = true;

        if(max < dist) {
            leaf = node;
            max = dist;
        }

        for(Edge next : adj[node]) { // 이때 adj는 최소 신장 트리 간선 정보
            if(!visit[next.idx]) {
                dfs(next.idx, dist + next.cost);
            }
        }
    }

    static void pro() {
        // 최소 비용 (MST)
        int min = kruskal();


        // 트리의 지름 구하기
        // 1. 임의 노드에서 dfs 수행시 구해진 가장 긴 경로로 연결된 노드는, 트리의 지름에 해당하는 두 노드 중 하나이다 (양방향)
        // 2. 구해진 노드로 한번더 dfs 수행하면 트리의 지름이 구해진다
        Arrays.fill(visit, false);
        max = Integer.MIN_VALUE;
        dfs(0, 0);

        Arrays.fill(visit, false);
        max = Integer.MIN_VALUE;
        dfs(leaf, 0);

        sb.append(min).append("\n");
        sb.append(max).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();

        pro();

        System.out.println(sb.toString());
    }

}
