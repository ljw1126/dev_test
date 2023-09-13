package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 호석이 두마리 치킨 (골드5)
 * https://www.acmicpc.net/problem/21278
 *
 * - 플로이드 워셜 풀이 복습
 * - 치킨집 2개를 고르는 조합 : 100C2 = 4950
 * - 플로이드 워셜 시간 복잡도 : O(N^3)
 */
public class BOJ21278_3 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[][] dist;

    static int INF = 101;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물의 개수 (노드)
        M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선)

        // 각 노드에서 다른 노드로 가는 전체 비용을 플로이드 워셜로 구한다
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

        // 플로이드 워셜 수행
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    static void pro() {
        int ans = Integer.MAX_VALUE;
        int a = 0;
        int b = 0;

        for(int i = 1; i < N; i++) {
            for(int j = i + 1; j <= N; j++) {
                int total = 0;

                for(int k = 1; k <= N; k++) {
                    if(k == i || k == j) continue;

                    int minDist = Math.min(dist[k][i], dist[k][j]); // k 노드에서 i, j 치킨집 거리 중 최소값
                    total += minDist;
                }

                if(total < ans) {
                    ans = total;
                    a = i;
                    b = j;
                }
            }
        }

        sb.append(a).append(" ").append(b).append(" ").append(2 * ans);
    }

    public static void main(String[] args) throws Exception {
        input();

        pro();

        System.out.println(sb);
    }
}
