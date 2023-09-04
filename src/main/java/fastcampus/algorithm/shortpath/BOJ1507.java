package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 궁금한 민호(골드2)
 * https://www.acmicpc.net/problem/1507
 *
 * 직접 풀이 못함
 * - 플로이드 워셜 문제
 * - 간선을 제거한다는 의미를 해석하지 못함
 * - dist[i][j] == dist[i][k] + dist[k][j] 인 경우 바로가나, k를 거쳐서 가나 동일한 비용이 드니
 * dist[i][j] 간선을 INF로 없애준 표시를 한다
 *
 * 참고. 기술블로그 https://steady-coding.tistory.com/105
 */
public class BOJ1507 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] floyd, arr;


    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        floyd = new int[N + 1][N + 1];
        arr = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                floyd[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = floyd[i][j];
            }
        }
    }

    // 플로이드 워셜은 (i, j) 로 바로 가거나 (i, k) + (k + j) k를 거쳐 가거나 최단 거리를 구하는 알고리즘인데
    // (i, j) == (i, k) + (k + j) 로 간다면 중복되는 간선이니 지워 버리는 문제였음
    static void pro() {
        boolean isValid = true;
        Loop :
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {

                    // i = 1, k = 1, j = 2 와 같이 하나의 간선 밖에 없는데
                    if(i == j || i == k || k == j) continue;

                    if(floyd[i][j] > floyd[i][k] + floyd[k][j]) { // 이미 모든 쌍의 도시의 최소 이동시간이 주어졌는데, 갱신되는 것은 모순이 발생하는 것
                        isValid = false;
                        break Loop;
                    }

                    if(floyd[i][j] == floyd[i][k] + floyd[k][j]) { // (1,2) = (1,1) + (1,2), 두개를 거쳐 가도 똑같기 때문에 바로 가는 간선을 없애준다
                        arr[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        int ans = 0;
        if(isValid) {
            boolean[][] checked = new boolean[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if (arr[i][j] != Integer.MAX_VALUE && !checked[i][j]) {
                        ans += arr[i][j];
                        checked[i][j] = checked[j][i] = true;
                    }
                }
            }
        }

        System.out.println(ans == 0 ? -1 : ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
