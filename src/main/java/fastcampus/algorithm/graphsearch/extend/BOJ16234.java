package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 인구이동(골드5)
 *
 * - 국경선을 공유하는 두 나아의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 국경선을 하루 연다
 * - 국경선이 모두 열렸다면, 인구 이동을 시작한다.
 * - 국셩선이 열려 있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나를 오늘 하루 동안 연합이라고 한다.
 * - (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
 * - 연합을 해체하고, 모든 국셩선을 닫는다.
 *
 * 문제) 연합을 어떻게 다루고 처리 가능한가
 * - 연합 좌표를 담는거는 필요했음
 * - 연합인걸 확인했을때 카운팅, 합산 구하는 것도
 * - 그룹 번호가 셋팅이 되어 있다는 것은 이미 범위 조건을 만족하지 않는다는 의미
 */
public class BOJ16234 {
    static StringBuilder sb = new StringBuilder();

    static int N, L, R, DAYS;

    static int[][] COUNTRY, UNIONS;

    static boolean[][] visit;
    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visit = new boolean[N][N];
        UNIONS = new int[N][N];
        COUNTRY = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                COUNTRY[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    static class Union {
        int x;
        int y;
        public Union(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void initVisit() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                visit[i][j] = false;
                UNIONS[i][j] = -1;
            }
        }
    }

    static void bfs(int startX, int startY, int groupNo) {
        Queue<Integer> que = new LinkedList<>();
        que.add(startX);
        que.add(startY);

        UNIONS[startX][startY] = groupNo;
        List<Union> united = new ArrayList<>();
        united.add(new Union(startX, startY));
        int people = COUNTRY[startX][startY];
        int country = 1;

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visit[nx][ny]) continue;
                if(UNIONS[nx][ny] != -1) continue;

                int diff = Math.abs(COUNTRY[nx][ny] - COUNTRY[x][y]);
                if(L <= diff && diff <= R) {
                    visit[nx][ny] = true;
                    que.add(nx);
                    que.add(ny);

                    united.add(new Union(nx, ny));
                    country += 1;
                    people += COUNTRY[nx][ny];
                    UNIONS[nx][ny] = groupNo;
                }
            }
        }

        int avg = people / country;
        for(Union u : united) {
            COUNTRY[u.x][u.y] = avg;
        }
    }

    static void pro() {
        while(true) {
            initVisit();
            int groupNo = 0;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(UNIONS[i][j] == -1) {
                        bfs(i, j, groupNo);
                        groupNo += 1;
                    }
                }
            }

            if(groupNo == N * N) break;
            else DAYS += 1;
        }

        System.out.println(DAYS);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
