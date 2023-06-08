package fastcampus.algorithm.graphsearch.extend;

import org.bouncycastle.jcajce.provider.symmetric.ARC4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 케빈 베이커의 6단계 법칙 https://www.acmicpc.net/problem/1389
 *
 * 유저 수 : N (정점의 개수, 2 <= N <= 100)
 * 친구 관계의 수 : M (간선의 개수, 1 <= M <= 5000)
 */
public class BOJ1389 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[] distance;
    static List<Integer>[] adj;


    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        distance = new int[N + 1];

        br.close();
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        for(int i = 0; i <= N ; i++) {
            distance[i] = -1;
        }
        distance[start] = 0;

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

    static int kevinBacon(int target) {
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            cnt += distance[i];
        }

        return cnt;
    }

    static void pro() {
        int minValue = Integer.MAX_VALUE;
        int result = -1;
        for(int i = 1; i <= N; i++) {
            bfs(i);
            int cnt = kevinBacon(i);
            if(minValue > cnt) {
                minValue = cnt;
                result = i;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
