package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 호석이 두마리 치킨 (골드5)
 * https://www.acmicpc.net/problem/21278
 *
 * - 건물의 개수 N개 (최대 100개)있을때 2개를 뽑아
 * - 100C2 = 100 * 99 / 2! = 4950개
 * - 100C2 * ElogV = 4950 * 4950log100 (1억 안에 수행가능)
 * - 다익스트라 알고리즘 풀이 완료 // 플로이드 워셜로도 풀이가능하네
 *
 */
public class BOJ21278_2 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static List<Integer>[] adj;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물 수 (노드)
        M = Integer.parseInt(st.nextToken()); // 도로 수 (간선)

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // cost는 무조건 1
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }
    }

    static class Info implements Comparable<Info> {
        int idx;
        int cost;

        public Info(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info other) {
            return cost - other.cost;
        }
    }

    static int dijkstra(int a, int b) {
        Queue<Info> que = new PriorityQueue<>();
        que.add(new Info(a, 0));
        que.add(new Info(b, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[a] = 0;
        dist[b] = 0;

        while(!que.isEmpty()) {
            Info cur = que.poll();

            if(dist[cur.idx] < cur.cost) continue;

            for(int next : adj[cur.idx]) {
                if(dist[next] > dist[cur.idx] + 1) {
                    dist[next] = dist[cur.idx] + 1;
                    que.add(new Info(next, dist[next]));
                }
            }
        }

        int result = 0;
        for(int i = 1; i <= N; i++) {
            result = result + (dist[i] * 2);
        }

        return result;
    }

    static void pro() {
        int result = Integer.MAX_VALUE;
        int a = 0;
        int b = 0;
        for(int i = 1; i < N; i++) {
            for(int j = i + 1; j <= N; j++) {
                int total = dijkstra(i, j);
                if(result > total) {
                    a = i;
                    b = j;
                    result = total;
                }
            }
        }

        sb.append(a).append(" ").append(b).append(" ").append(result);
    }

    public static void main(String[] args) throws Exception {
        input();

        pro();

        System.out.println(sb);
    }
}
