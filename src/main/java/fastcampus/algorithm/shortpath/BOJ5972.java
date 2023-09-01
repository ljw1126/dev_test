package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 택배 배송(골드5)
 * https://www.acmicpc.net/problem/5972
 *
 * C_i : 가중치 (소에게 여물을 줘야하는)
 * A_i, B_i : 소들이 잇는 길
 *
 * 1 -> N까지 가는 최소 비용
 *
 * N : 50000개 (노드)
 * M : 50000개 (간선)
 * O(ElogV)
 */
public class BOJ5972 {
    static StringBuilder sb = new StringBuilder();

    static List<Info>[] adj;

    static int n, m;

    static class Info implements Comparable<Info> {
        int idx;
        int cost;

        public Info(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info info) {
            return cost - info.cost; // 오름차순
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Info(to, cost));
            adj[to].add(new Info(from, cost));
        }
    }

    // 1 -> N 까지 최소 비용 구한다
    static void dijkstra() {
        Queue<Info> que = new PriorityQueue<>();
        que.add(new Info(1, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        int result = Integer.MAX_VALUE;
        while(!que.isEmpty()) {
            Info cur = que.poll();

            if(cur.idx == n) {
                result = Math.min(result, cur.cost);
                continue;
            }

            if(dist[cur.idx] < cur.cost) continue;

            for(Info next : adj[cur.idx]) {
                if(dist[next.idx] > dist[cur.idx] + next.cost) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    que.add(new Info(next.idx, dist[next.idx]));
                }
            }
        }

        System.out.println(result);
    }

    static void pro() {
        dijkstra();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
