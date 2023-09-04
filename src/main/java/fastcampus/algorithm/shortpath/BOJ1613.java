package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 역사 (골드3)
 * https://www.acmicpc.net/problem/1613
 *
 * 직접 풀이함
 * - 단방향 그래프이고, 플로이드 워셜 수행해서 각 노드별로 갈 수 있는 최단 거리 구함
 * - (i, j) (j, i) INF 인 경우 둘다 갈 수 없다는 뜻이고
 * - 한쪽만 INF이고 순방향 (i, j) 가 양수인 경우 -1이고 i가 먼저 발생
 * - 한쪽만 INF이고 역방향 (j, i) 가 양수이면 1이다 i가 먼저 발생
 */
public class BOJ1613 {
    static StringBuilder sb = new StringBuilder();

    static int n, k, s;

    static int[][] floyd;

    static int INF = 160001;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 사건의 개수
        k = Integer.parseInt(st.nextToken()); // 전 후 관계 수

        floyd = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(floyd[i], INF);
            floyd[i][i] = 0;
        }

        for(int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            floyd[v1][v2] = 1;
        }

        pro(); // 플로이드 워셜 실행

        s = Integer.parseInt(br.readLine());
        while(s > 0) {
            s -= 1;

            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            int result = 0;
            if(floyd[v1][v2] != INF && floyd[v1][v2] != 0) result = -1;
            else if(floyd[v2][v1] != INF && floyd[v2][v1] != 0) result = 1;

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    static void pro() {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }

        for(int i = 1; i <= n; i++) System.out.println(Arrays.toString(floyd[i]));
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
