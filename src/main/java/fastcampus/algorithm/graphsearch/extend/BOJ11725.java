package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 트리의 부모 찾기
 * https://www.acmicpc.net/problem/11725
 */
public class BOJ11725 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static List<Integer>[] adj;

    static int[] parent;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N -1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        for(int i = 1; i <= N; i++) Collections.sort(adj[i]);

        br.close();
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        parent[start] = 1;

        while(!que.isEmpty()) {
            int x = que.poll();

            for(int y : adj[x]) {
                if(parent[y] == 0) {
                    parent[y] = x;
                    que.add(y);
                }
            }
        }

    }

    static void pro() {
        bfs(1);

        for(int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.println(sb);
    }


    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    /*
        // DFS 풀이 가능
        static int[] parent;
        static void dfs(int node, int _parent) {
            parent[node] = _parent;

            for(int x : adj[node]) {
                if(parent[x] == 0) {
                    dfs(x, node);
                }
            }
        }

        static void pro() {
            dfs(1, 0);

            for(int i = 2; i <= N; i++) sb.append(parent[i]).append("\n");

            System.out.println(sb);
        }
     */
}
