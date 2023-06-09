package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 경로 찾기 https://www.acmicpc.net/problem/11403
 *
 * i,j = 1이라는 건 i -> j 로 가는 간선이 존재한다는 뜻 (단방향)
 * 그래서 1 ~ N 까지 순차 조회하면서 각 노드별로 갈 수 있는 간선을 출력하면 됨
 */
public class BOJ11403 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static List<Integer>[] adj;

    static int[] visit;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if(value == 1) {
                    adj[i].add(j);
                }
            }
        }
    }

    static void initVisit() {
        if(visit == null) {
            visit = new int[N + 1];
        } else {
            for(int i = 1; i <= N; i++) {
                visit[i] = 0;
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        initVisit(); // start 초기화는 필요없음

        while(!que.isEmpty()) {
            int x = que.poll();

            for(int y : adj[x]) {
                if(visit[y] == 0) {
                    visit[y] = 1;
                    que.add(y);
                }
            }
        }

        for(int i = 1; i <= N; i++) sb.append(visit[i]).append(' ');

    }

    static void pro() {
        for(int i = 1; i <= N; i++) {
            bfs(i);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    /*
    // DFS 풀이 할 경우
    static int[] distance;
    static void dfs(int x, int value) {
        distance[x] = value;

        for(int y : adj[x]) {
            if(distance[y] == 0) {
                dfs(y, value + 1);
            }
        }
    }

    static void pro() {
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                distance[j] = 0;
            }

            dfs(i, 0);

            for(int k = 1; k <= N; k++) {
                if(distance[k] > 0) sb.append(1).append(" ");
                else sb.append(0).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
     */
}
