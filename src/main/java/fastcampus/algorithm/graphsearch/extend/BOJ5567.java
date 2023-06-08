package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5567 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[] distance;
    static List<Integer>[] adj;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //동기의 수 (상근은 1)
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];
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

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(1);

        for(int i = 0; i <= N; i++) distance[i] = -1;

        distance[1] = 0;

        while(!que.isEmpty()) {
            int x = que.poll();

            for(int y : adj[x]) {
                if(distance[y] == -1) {
                    distance[y] = distance[x] + 1;
                    que.add(y);
                }
            }
        }
    }

    static void pro() {
        bfs();
        //친구와 친구의 친구까지 초대하려고 하니 2이하만 카운팅
        int cnt = 0;
        for(int i = 2; i <= N; i++) {
            if(distance[i] == 1 || distance[i] == 2) cnt += 1;
        }

        System.out.println(cnt);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
