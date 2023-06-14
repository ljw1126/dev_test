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
 * 이분그래프(골드4) https://www.acmicpc.net/problem/1707
 *
 * - 양방향 그래프 처리 안하면 틀림
 */
public class BOJ1707 {
    static StringBuilder sb = new StringBuilder();

    static int K, V, E;

    static List<Integer>[] adj;

    static int[] COLOR;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); // 테스트 케이스
        COLOR = new int[20001];
        while(K > 0) {
            K -= 1;

            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 정점
            E = Integer.parseInt(st.nextToken()); // 간선

            for(int i = 1; i <= V; i++) COLOR[i] = -1;

            adj = new ArrayList[V + 1];
            for(int i = 1; i <= V; i++) adj[i] = new ArrayList<>();

            for(int i = 1; i <= E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adj[from].add(to);
                adj[to].add(from);
            }

            for(int i = 1; i <= V; i++) Collections.sort(adj[i]);

            pro();
        }

        br.close();
    }

    static boolean bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        COLOR[start] = 0;

        boolean result = true;
        while(!que.isEmpty()) {
            int x = que.poll();
            if(!checkBipartite(x)) {
                result = false;
                break;
            }

            for(int y : adj[x]) {
                if(COLOR[y] == -1) {
                    COLOR[y] = (COLOR[x] + 1) % 2; // 1인 경우 : blue, 0인 경우 red;
                    que.add(y);
                }
            }
        }

        return result;
    }

    static boolean checkBipartite(int node) {
        for(int n : adj[node]) {
            if(COLOR[n] == COLOR[node]) return false;
        }

        return true;
    }

    static void pro() {
        boolean isBipartite = true;
        for(int i = 1; i <= V; i++) {
            if(COLOR[i] == -1 && !bfs(i)) {
                isBipartite = false;
                break;
            }
        }

        String result = isBipartite ? "YES" : "NO";
        sb.append(result).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
