package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 정복자(골드3)
 *
 * - MST
 * - 프림 알고리즘 풀이
 * - 한 턴 연결 할 때마다 간선의 비용이 T 만큼 증가하네 -> 연결한 edge 개수 구해서 하면 될 듯
 * - 최대치 10^4 * 10^4 에 매 턴마다 최대 10(t) 만큼 증가한다 했을 때 10^5 이니 10^8 + 10^5 정도로 생각하면 될듯
 * - 직접 풀이
 */
public class BOJ14950 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, t;

    static List<Edge>[] adj;

    static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return cost - edge.cost; // 오름차순
        }
    }
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시 개수(노드)
        M = Integer.parseInt(st.nextToken()); // 도로 개수(간선)
        t = Integer.parseInt(st.nextToken()); // 매턴 증가하는 도로 비용

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }
    }

    static void pro() {
        Queue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(1, 0)); // 처음 점거하고 있는 도시는 1번 주어짐

        boolean[] visit = new boolean[N + 1];

        int result = 0;
        int edgeCount = 0;
        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            if(cur.idx == 1) { // 시작 노드
                result += cur.cost;
            } else {
                edgeCount += 1;
                result += (cur.cost + (edgeCount - 1) * t);
            }

            for(Edge next : adj[cur.idx]) {
                if(visit[next.idx]) continue;

                que.add(next);
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
