package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *운동 (골드4)
 *https://www.acmicpc.net/problem/1956
 *
 * 직접품
 * - 플로이드 워셜 문제
 * - 각 노드별로 사이클 확인하여 최소 경로 구하는 거는 => (i,i)에 최소값을 확인하면 됨
 * - 이때 최대값을 Integer.MAX_VALUE 주면 부호 비트가 바껴서 음수가 나오므로 최대치 수정 필요
 */
public class BOJ1956 {
    static StringBuilder sb = new StringBuilder();

    static int V, E;

    static int[][] dist;

    static int INF = 4000001;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 노드
        E = Integer.parseInt(st.nextToken()); // 간선

        dist = new int[V + 1][V + 1];
        for(int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], INF);
        }

        for(int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[from][to] = cost;
        }
    }

    static void pro() {
       for(int k = 1; k <= V; k++) {
            for(int i = 1; i <= V; i++) {
                for(int j = 1; j <= V; j++) {
                    if(i == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
       }

       // 사이클 형성 되는 (2,2), (3,3) 값이 갱신되어 있음
       int ans = INF;
       for(int i = 1; i <= V; i++) {
           System.out.println(Arrays.toString(dist[i]));
           ans = Math.min(ans, dist[i][i]);
       }

        System.out.println(ans == INF ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
