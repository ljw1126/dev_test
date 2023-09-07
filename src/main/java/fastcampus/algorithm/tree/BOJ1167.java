package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리의 지름(골드2)
 * https://www.acmicpc.net/problem/1167
 *
 * dfs 두번 실행해서 정점을 구한다
 * 1) 임의 정점에서 출발해서 가장 깊은 정점(leaf)가 지름 구성하는 한 노드이다
 * 2) 찾은 노드로 다시 dfs 실행하여 구한 최대 깊이가 트리의 지름이다
 */
public class BOJ1167 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int leaf, max; // leaf : 지름을 구성하는 노드, max : 지름 (=최대 가중치)
    static List<Edge>[] adj;

    static boolean[] visit;
    static class Edge {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 정점의 개수

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // 정점 / (연결 정점과 가중치).. / -1(끝)
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            int to;
            while((to = Integer.parseInt(st.nextToken())) != -1) {
                int cost = Integer.parseInt(st.nextToken());

                adj[from].add(new Edge(to, cost));
                adj[to].add(new Edge(from, cost));
            }
        }
    }

    static void dfs(int node, int dist) {
        visit[node] = true;
        if(max < dist) {
            max = dist;
            leaf = node;
        }

        for(Edge next : adj[node]) {
            if(!visit[next.idx]) dfs(next.idx, dist + next.cost);
        }
    }

    static void pro() {
        // 임의 노드에서 최대 깊이의 leaf 노드(=지름을 구성하는 노드 중 하나)를 구함
        visit = new boolean[N + 1];
        dfs(1, 0);

        // leaf 노드에서 최대 최대 가중치를 구함 (= 트리의 지름)
        Arrays.fill(visit, false);
        dfs(leaf, 0);

        System.out.println(max); // 트리의 최대 깊이 == 트리의 지름
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
