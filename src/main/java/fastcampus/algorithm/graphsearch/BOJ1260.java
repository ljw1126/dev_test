package fastcampus.algorithm.graphsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, V;
    static List<Integer>[] adj;

    static boolean[] visited;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        V = Integer.parseInt(st.nextToken()); // 시작점

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }

        for(int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }
    }

    static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(' ');

        for(int y : adj[start]) {
            if(visited[y]) continue;

            dfs(y);
        }
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;

        while(!que.isEmpty()) {
            int x = que.poll();
            sb.append(x).append(" ");

            for(int y : adj[x]) {
                if(visited[y]) continue;

                que.add(y);
                visited[y] = true;
            }
        }
    }

    static void pro() {
        visited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");
        for(int i = 1; i <= N; i++) visited[i] = false;
        bfs(V);

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
