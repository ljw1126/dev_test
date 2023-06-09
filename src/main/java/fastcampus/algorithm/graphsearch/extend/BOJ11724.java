package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연결 요소의 개수 https://www.acmicpc.net/problem/11724
 */
public class BOJ11724 {
    static int N, M;

    static List<Integer>[] adj;

    static boolean[] visit;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        M = Integer.parseInt(st.nextToken()); // 간선의 개수

        visit = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        for(int i = 1; i <= N; i++) Collections.sort(adj[i]);
    }

    static void dfs(int x) {
        visit[x] = true;

        for(int y : adj[x]) {
            if(visit[y]) continue;

            dfs(y);
        }
    }

    static void bfs(int x) {
        Queue<Integer> que = new LinkedList<>();
        que.add(x);
        visit[x] = true;

        while(!que.isEmpty()) {
            int i = que.poll();

            for(int j : adj[i]) {
                if(visit[j]) continue;

                visit[j] = true;
                que.add(j);
            }
        }
    }

    static void pro() {
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            if(!visit[i]) {
                cnt += 1;
                //dfs(i);
                bfs(i);
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
