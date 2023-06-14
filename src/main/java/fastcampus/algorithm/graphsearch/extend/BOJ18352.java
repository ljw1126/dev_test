package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * X로부터 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 K인 모든 도시의 번호를 한줄에 하나씩 오름차순 정렬
 */
public class BOJ18352 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, K, X;

    static int[] DIST;

    static List<Integer>[] adj;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 개수 (=정점)
        M = Integer.parseInt(st.nextToken()); // 도로의 개수 (=간선)
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        X = Integer.parseInt(st.nextToken()); // 시작 번호

        DIST = new int[N + 1];

        adj =  new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // 단반향 그래프
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
        }

        for(int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }

        br.close();
    }

    static void bfs(int start) {
        Arrays.fill(DIST, Integer.MAX_VALUE);

        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        DIST[start] = 0;

        while(!que.isEmpty()) {
            int x = que.poll();

            for(int y : adj[x]) {
                if(DIST[y] <= DIST[x] + 1) continue;

                DIST[y] = DIST[x] + 1;
                que.add(y);
            }
        }
    }


    static void pro() {
        bfs(X);
        boolean find = false;
        for(int i = 1; i <= N; i++) {
            if(DIST[i] == K) {
                find = true;
                sb.append(i).append("\n");
            }
        }

        if(!find) System.out.println(-1);
        else System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
