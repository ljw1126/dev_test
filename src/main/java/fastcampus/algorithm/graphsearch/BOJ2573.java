package fastcampus.algorithm.graphsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 빙상(골드4)
 * https://www.acmicpc.net/problem/2573
 *
 * - 직접 풀이 못함
 * - 조건을 제대로 이해하지 못했고, 빙산을 녹일 때 주변바다를 카운팅 해서 녹이는 것을 생각 못함
 * - 한턴이 다지나서 다 녹아버리는 반례의 경우 0이 출력되야한다
 * - 2그룹이상 나눠지는 경우 턴을 출력
 */
public class BOJ2573 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[][] board;

    static boolean[][] visit;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        board = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[N + 1][M + 1];
    }

    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static void melt() {
        Queue<Integer> que = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(board[i][j] != 0) {
                    que.add(i);
                    que.add(j);
                    visit[i][j] = true;
                }
            }
        }

        while(!que.isEmpty()) {
            int _x = que.poll();
            int _y = que.poll();

            int seaCnt = 0;

            for(int i = 0; i < 4; i++) {
                int dx = _x + dir[i][0];
                int dy = _y + dir[i][1];

                if(dx < 1 || dy < 1 || dx > N || dy > M) continue;
                if(visit[dx][dy]) continue;

                if(board[dx][dy] == 0) {
                    seaCnt += 1;
                }
            }

            if(board[_x][_y] - seaCnt < 0) {
                board[_x][_y] = 0;
            } else {
                board[_x][_y] -= seaCnt;
            }
        }
    }

    static void grouping(int x, int y) {
        visit[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx < 1 || dy < 1 || dx > N || dy > M) continue;
            if(visit[dx][dy]) continue;

            if(board[dx][dy] != 0) grouping(dx, dy);
        }
    }

    static int getGroupCnt() {
        initVisit();
        int groupCnt = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(!visit[i][j] && board[i][j] != 0) {
                    grouping(i, j);
                    groupCnt += 1;
                }
            }
        }

        return groupCnt;
    }

    static void pro() {
        int ans = 0;
        int cnt;

        // 빙하가 2개 이상 그룹으로 분리될 경우 종료
        while((cnt = getGroupCnt()) < 2) {
            if(cnt == 0) { // 빙하가 다 녹았을 경우 0을 출력
                ans = 0;
                break;
            }

            initVisit();
            melt();
            ans += 1;
        }

        System.out.println(ans);
    }

    static void initVisit() {
        if(visit == null) {
            visit = new boolean[N + 1][M + 1];
        } else {
            for(int i = 1; i <= N; i++) {
                Arrays.fill(visit[i], false);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
