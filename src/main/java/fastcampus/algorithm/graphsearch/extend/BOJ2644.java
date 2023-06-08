package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 촌수 계산(실버2) https://www.acmicpc.net/problem/2644
 *
 */
public class BOJ2644 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, START, END;
    static int[] distance;

    static List<Integer>[] adj;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        distance = new int[N + 1];

        // 촌수 계산 대상
        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        for(int i = 1; i <= N; i++) distance[i] = -1;

        distance[start] = 0;

        while(!que.isEmpty()) {
            int x = que.poll();

            for(int y : adj[x]) {
                if(distance[y] == -1) {
                    que.add(y);
                    distance[y] = distance[x] + 1;
                }
            }
        }
    }
    static void pro() {
        bfs(START);
        System.out.println(distance[END]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
