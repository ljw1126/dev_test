package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 호석이 두마리 치킨
 * https://www.acmicpc.net/problem/21278
 *
 * 플로이드 워셜 알고리즘으로 각 노드별로 다른 정점까지 가는 최단 경로를 구한다
 * O(N^3) -> BFS로 노드 하나하나씩 다 구하는 것도 방법임 O(V + E)
 *
 * 최대 노드가 100개 일 때 2개의 치킨집 조합의 수
 * 100C2 = 100 * 99 / 2! = 9900 / 2 = 4950
 */
public class BOJ21278 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[][] dist;

    static int INF = 100;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물 개수 (노드)
        M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선), 가중치 1

        dist = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            dist[from][to] = 1;
            dist[to][from] = 1;
        }

        // int 범위를 초과하니 플로이드 워셜이 이상하게 나옴 (bfs로 풀이가능, 또 어렵게 생각함)
        for(int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    static void pro() {
        // 초기화 때문에 틀려서 시간 소비 (9/19)
        int A = 0;
        int B = 0;
        int ans = Integer.MAX_VALUE;
        for(int i =1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                int total = 0;

                for(int k = 1; k <= N; k++) {
                    int minHour = Math.min(dist[k][i], dist[k][j]);
                    total += minHour;
                }

                if(total < ans) { // 여기서도 미스
                    ans = total;
                    A = i;
                    B = j;
                }
            }
        }

        sb.append(A).append(" ").append(B).append(" ").append(ans * 2);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
