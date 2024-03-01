package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 내리막길 (골드3)
 * https://www.acmicpc.net/problem/1520
 *
 * - 직접 풀이함
 * - 그래프 탐색, DFS, 동적 프로그래밍
 * - dfs로 풀이시 시간초과 발생 4^500
 * - 높이는 int 범위내라 중요치 않음
 * - dp로 풀이하는 방법 생각해서 풀이하니 정답 맞춤
 * - 이미 갈 수 있다는 것을 확인했기 때문에, 중간에 겹치는 경우 바로 합산 됨
 *
 */
public class BOJ1520 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[][] fieldMap;

    static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    static int[][] dp;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fieldMap = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                fieldMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) Arrays.fill(dp[i], -1);
    }

    /*
    // dfs 풀이시 시간 초과
    static void dfs(int x, int y) {
        if(x == N && y == M) {
            RESULT += 1;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            // 범위를 벗어 낫는가
            if(dx < 1 || dy < 1 || dx > N || dy > M) continue;

            // 이전 높이보다 큰가
            if(fieldMap[x][y] <= fieldMap[dx][dy]) continue;

            // 방문한적 있는가
            if(visit[dx][dy]) continue;

            visit[dx][dy] = true;

            dfs(dx, dy);

            visit[dx][dy] = false;
        }
    }


    static void pro() {
        visit[1][1] = true;
        dfs(1, 1);

        System.out.println(RESULT);
    }
    */

    static int pro(int x, int y) {
        if(x == N && y == M) return 1;

        if(dp[x][y] != -1) return dp[x][y];

        int value = 0;
        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx < 1 || dy < 1 || dx > N || dy > M) continue;
            if(fieldMap[x][y] <= fieldMap[dx][dy]) continue;

            value += pro(dx, dy);
        }

        return dp[x][y] = value;
    }


    public static void main(String[] args) throws Exception {
        input();
        System.out.println(pro(1, 1));

        //for(int i = 1; i <= N; i++) System.out.println(Arrays.toString(dp[i]));
    }
}
