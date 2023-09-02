package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 키순서(골드4)
 * https://www.acmicpc.net/problem/2458
 */
public class BOJ2458 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static boolean[][] visit;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1][N + 1];
        for(int i = 1; i <= N; i++) visit[i][i] = true;

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            visit[from][to] = true;
        }
    }

    static void pro() {
        // 나보다 큰 녀석 찾아서 갱신
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(visit[i][k] && visit[k][j]) visit[i][j] = true;
                }
            }
        }

        int ans = 0;
        for(int i = 1; i <= N; i++) {
            int result = 0;
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                if(visit[i][j] || visit[j][i]) result += 1; // visit[i][j] : 나보다 큰 경우, visit[j][i] : 나보다 작은 경우
            }

            if(result == N - 1) ans += 1;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
