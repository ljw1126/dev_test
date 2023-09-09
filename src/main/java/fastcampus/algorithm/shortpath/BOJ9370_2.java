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
 *
 * 참고 - 도식화가 가능 하네
 * https://studyandwrite.tistory.com/454
 *
 * 풀이 방법1)
 * 두 케이스 중에 s -> d 최단 거리랑 같은게 있으면 경로 지나간거
 * s -> g -> h -> d
 * s -> h -> g -> d
 */
public class BOJ9370_2 {
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
            // 1. 초기화
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

                adj[a].add(new Edge(b, d));
                adj[b].add(new Edge(a, d));
            }

            // 2. 후보 노드 - 오름차순 정렬 수행
            int[] spot = new int[t];
            for(int i = 0; i < t; i++) spot[i] = Integer.parseInt(br.readLine());

            Arrays.sort(spot);

            // 3. 다익스트라 수행
            int[] distS = dijkstra(s); // 시작점 s 부터 다른 노드까지 거리
            int[] distG = dijkstra(g); // 시작점 g 부터 다른 노드까지 거리
            int[] distH = dijkstra(h); // 시작점 h 부터 다른 노드까지 거리

            // 4. s -> 후보 노드로 최단 거리 비용과 s -> g - h -> 후보노드 로 가는 비용이 같은 경우 정답이다
            for(int s : spot) {
                int total = distS[s];

                int s_to_g = distS[g];
                int g_to_h = distG[h];
                int h_to_d = distH[s];

                int s_to_h = distS[h];
                int h_to_g = distH[g];
                int g_to_d = distG[s];

                int value = Math.min(s_to_g + g_to_h + h_to_d, s_to_h + h_to_g + g_to_d);

                if(total == value) sb.append(s).append(" ");
            }

            sb.append("\n");
        }
    }

    static int[] dijkstra(int start) {
        // 시작 노드로 초기화
        Queue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(start, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, 20000000);
        dist[start] = 0;

        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(dist[cur.idx] > cur.cost) continue;

            for(Edge next : adj[cur.idx]) {
                if(dist[next.idx] >= dist[cur.idx] + next.cost) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    que.add(new Edge(next.idx, dist[next.idx]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb.toString());
    }
}
