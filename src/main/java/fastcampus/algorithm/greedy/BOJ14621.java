package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 나만 안되는 연애(골드3)
 * https://www.acmicpc.net/problem/14621
 *
 * - MST (최소 신장 트리 문제)
 * - 성별이 다른 경우에만 연결 (조건1 이해 못함)
 * - 프림 알고리즘 사용
 * - 최소힙으로 구현하여 시간 복잡도 O(ElogV)
 */
public class BOJ14621 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static List<Edge>[] adj;

    static boolean[] visit;

    static String[] school;

    static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        
        @Override 
        public int compareTo(Edge other) {
            return cost - other.cost;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 학교의 수
        M = Integer.parseInt(st.nextToken()); // 학교를 연결하는 도로의 개수 (간선)

        school = new String[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            school[i] = st.nextToken();
        }


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

        visit = new boolean[N + 1];
    }

    static void prime() {
        Queue<Edge> que = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            if("M".equals(school[i])) {
                que.add(new Edge(i, 0));
                break;
            }
        }

        int result = 0;
        int edgeCnt = 0;
        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            result += cur.cost;
            edgeCnt += 1;

            for(Edge e : adj[cur.idx]) {
                if(visit[e.idx]) continue;
                if(school[cur.idx].equals(school[e.idx])) continue;

                que.add(e);
            }
        }

        if(edgeCnt == N) sb.append(result);
        else sb.append("-1");

        System.out.println(sb);
    }

    static void pro() {
        prime();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
