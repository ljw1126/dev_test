package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 구슬 찾기 (골드4)
 * https://www.acmicpc.net/problem/2617
 *
 * - 플로이드 워셜 응용 문제
 * - 직접 풀지 못함
 * - "중간이 될 수 없는 구슬"이 키워드 였음
 * - 고로 자기보다 크거나, 작은 구슬이 중간 개수 이상이면 절대 중간이 될 수 없는 구슬이다
 */
public class BOJ2617 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] floyd;

    static int INF = 100;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        floyd = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(floyd[i], INF);
            floyd[i][i] = 0;
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            floyd[to][from] = 1;

        }
    }

    static void pro() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }

        //for(int i = 1; i <= N; i++) System.out.println(Arrays.toString(floyd[i]));

        // 중간 개수를 구하고 크거나, 작은 구슬이 중간 개수 이상이면 절대 중간이 될 수 없다.
        int ans = 0;
        int limit = (N + 1) / 2;
        for(int i = 1; i <= N; i++) {
            int high = 0;
            int low = 0;
            for(int j = 1; j <= N; j++) {
                if(floyd[i][j] != INF && floyd[i][j] > 0) high += 1;
                else if(floyd[j][i] != INF && floyd[j][i] > 0) low += 1;
            }

            if(high >= limit || low >= limit) ans += 1;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
