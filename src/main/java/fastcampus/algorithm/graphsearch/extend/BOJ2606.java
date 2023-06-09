package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  바이러스 (실버3) https://www.acmicpc.net/problem/2606
 */
public class BOJ2606 {

    static StringBuilder sb = new StringBuilder();

    static int N, LINE;

    static List<Integer>[] adj;

    static boolean[] visit;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        LINE = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= LINE; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        br.close();
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;

        while(!que.isEmpty()) {
            int x = que.poll();

            for(int y : adj[x]) {
                if(visit[y]) continue;

                visit[y] = true;
                que.add(y);
            }
        }
    }

    static void dfs(int x) {
        visit[x] = true;

        for(int y : adj[x]) {
            if(visit[y]) continue;

            dfs(y);
        }
    }

    static void pro() {
        //bfs(1);
        dfs(1);

        int cnt = 0;
        for(int i = 2; i <= N; i++) if(visit[i]) cnt += 1;

        System.out.println(cnt);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
