package fastcampus.algorithm.examSet2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 도시건설(골드4)
 * https://www.acmicpc.net/problem/21924
 *
 * - 최소신장트리 문제
 */
public class BOJ21924 {
    static StringBuilder sb = new StringBuilder();

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return  cost - node.cost; // 오름차순
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 건물의 개수 (노드)
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선)

        List<Node>[] adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        long totalCost = 0L;
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to, cost));
            adj[to].add(new Node(from, cost));

            totalCost += cost;
        }

        boolean[] visited = new boolean[N + 1];
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(1, 0));

        long mstCost = 0L;
        int visitCnt = 0;
        while(!que.isEmpty()){
            Node cur = que.poll();

            if(visited[cur.idx]) continue;

            visited[cur.idx] = true;
            mstCost += cur.cost;
            visitCnt += 1;

            for(Node next : adj[cur.idx]) {
                if(!visited[next.idx]) que.add(next);
            }
        }

        if(visitCnt != N) System.out.println(-1);
        else System.out.println(totalCost - mstCost);
    }

    static void pro() {

    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
