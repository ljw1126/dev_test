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
 * 미확인 도착지 (골드2)
 * https://www.acmicpc.net/problem/9370
 *

 */
public class BOJ9370 {
    static StringBuilder sb = new StringBuilder();

    static int T;
    static int n, m, t;
    static int s, g, h;

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
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 교차로 (노드)
            m = Integer.parseInt(st.nextToken()); // 도로(간선)
            t = Integer.parseInt(st.nextToken()); // 목적지 후보 개수

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 출발 노드
            g = Integer.parseInt(st.nextToken()); // 경유 노드 1
            h = Integer.parseInt(st.nextToken()); // 경유 노드 2

            adj = new ArrayList[n + 1];
            for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for(int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()); // 비용

                d *= 2;
                if((a == g && b == h) || (a == h && b == g)) { // 지나 쳐야 하는 간선의 경우 가중치를 홀수로 만듦
                    d -= 1;
                }

                adj[a].add(new Edge(b, d));
                adj[b].add(new Edge(a, d));
            }

            // 후보 노드
            int[] spot = new int[t];
            for(int i = 0; i < t; i++) spot[i] = Integer.parseInt(br.readLine());

            Arrays.sort(spot);

            dijkstra(s, spot);
        }
    }

    static void dijkstra(int start, int[] spot) {
        Queue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(start, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, 20000000);
        dist[start] = 0;

        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(dist[cur.idx] > cur.cost) continue;

            for(Edge next : adj[cur.idx]) {
                if(dist[next.idx] > dist[cur.idx] + next.cost) { // = 문제였다.. 메모리 초과 발생
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    que.add(new Edge(next.idx, dist[next.idx]));
                }
            }
        }

        for(int i : spot) {
            if(dist[i] % 2 == 1) sb.append(i).append(" ");
        }
        sb.append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb.toString());
    }
}
