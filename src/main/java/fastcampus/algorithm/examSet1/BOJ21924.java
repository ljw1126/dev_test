package fastcampus.algorithm.examSet1;

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
 * - MST
 * - 크루스칼로 풀었는데 프림으로 풀어보기
 * - 최대치 long 주의
 *
 */
public class BOJ21924 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static long TOTAL;

    static List<Node>[] adj;

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return cost - node.cost;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물의 개수 (노드)
        M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선)

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to, cost));
            adj[to].add(new Node(from, cost));

            TOTAL += cost; // 총 비용
        }
    }

    static void prime() {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(1, 0));

        boolean[] visit = new boolean[N + 1];

        long ans = 0L;
        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            ans += cur.cost;

            for(Node next : adj[cur.idx]) {
                if(!visit[next.idx]) {
                    que.add(next);
                }
            }
        }


        boolean valid = true;
        for(int i = 1; i <= N; i++) {
            if(!visit[i]) {
                valid = false;
                break;
            }
        }

        if(valid) sb.append(TOTAL - ans);
        else sb.append("-1");

        System.out.println(sb.toString());
    }

    static void pro() {
        prime();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
