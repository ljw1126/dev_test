package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소(골드4) https://www.acmicpc.net/submit/14502/60390534
 *
 * S사 기출 유형
 *
 * 벽 세우기 64C3 = 41K
 * 바이러스 전파 후 카운팅 N^2 ( 2 <= N <= 8)
 *
 * 총 시간 복잡도 O(64C3 * 64) = 약 260만
 *
 * 절차
 * 1) 빈칸에 벽을 3개 세우는 조합을 찾는다
 * 2) 바이러스를 전파(방문)
 * 3) 바이러스가 전파(방문)하지 않은 빈칸(0) 영역 카운팅하여 최대값을 찾는다
 */
public class BOJ14502 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, B, RESULT;
    static int[][] A, BLANK;

    static int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static boolean[][] VISIT;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()); // 공백 기준 분리해줌
            }
        }

        BLANK = new int[N*M][2];
        VISIT = new boolean[N][M];
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>(); // 테크닉으로 행,열을 push 해서 사용

        // 모든 바이러스가 시작점으로 가능하니까, 전부 큐에 삽입
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                VISIT[i][j] = false;
                if(A[i][j] == 2) {
                    que.add(i);
                    que.add(j);
                    VISIT[i][j] = true;
                }
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int k = 0; k < 4; k++) {
                int nx = x + DIRECTIONS[k][0], ny = y + DIRECTIONS[k][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;;
                if(A[nx][ny] != 0) continue;
                if(VISIT[nx][ny]) continue;

                VISIT[nx][ny] = true; // 바이러스 전염 가능한 경우
                que.add(nx);
                que.add(ny);
            }
        }

        // 탐색이 종료된 시점이니, 안전 영역의 넓이를 갱신하고 정답 갱신
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(A[i][j] == 0 && !VISIT[i][j]) cnt += 1;
            }
        }

        RESULT = Math.max(RESULT, cnt);
    }

    // idx 번째 빈칸에 벽을 세울지 말지 결정해야 하고, 이전까지 selecetedCnt 만큼 벽을 세웠다는 의미
    static void dfs(int idx, int selectedCnt) {
        if(selectedCnt == 3) { // 3개의 벽을 모두 세운 상태
            bfs();
            return;
        }

        if(idx >= B) return; // 더 이상 세울 수 있는 벽이 없는 상태 (실수 가능 포인트*, > 할 경우 예제 입력1에서 33이 나옴)

        // idx에 세워보는 경우
        A[BLANK[idx][0]][BLANK[idx][1]] = 1;
        dfs(idx + 1, selectedCnt + 1);

        // idx를 세우지 않는 경우
        A[BLANK[idx][0]][BLANK[idx][1]] = 0;
        dfs(idx + 1, selectedCnt);
    }

    static void pro() {
        // 모든 벽의 위치를 먼저 모음 (초기 B = 0)
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (A[i][j] == 0) {
                    BLANK[B][0] = i;
                    BLANK[B][1] = j;
                    B++;
                }
            }
        }

        //벽을 3개 세우는 모든 방법 확인
        dfs(0, 0);
        System.out.println(RESULT);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    /* 직접 풀이

    static int N, M, RESULT;

    static int[][] MAP;

    static boolean[][] VISIT;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        VISIT = new boolean[N][M];
        MAP = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();

        // 바이러스 찾음
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                VISIT[i][j] = false;
                if(MAP[i][j] == 2) {
                    que.add(i);
                    que.add(j);
                    VISIT[i][j] = true;
                }
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIRECTION[i][0];
                int ny = y + DIRECTION[i][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(VISIT[nx][ny]) continue;
                if(MAP[nx][ny] == 0) { // 빈칸인 경우
                    que.add(nx);
                    que.add(ny);
                    VISIT[nx][ny] = true;
                }
            }
        }

        // 안전 영역 카운팅
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!VISIT[i][j] && MAP[i][j] == 0) cnt += 1;
            }
        }

        RESULT = Math.max(RESULT, cnt);
    }

    static void dfs(int selectedCount) {
        if(selectedCount == 3) {
            bfs();
            return;
        }

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(MAP[i][j] == 0) {
                    MAP[i][j] = 1;
                    dfs(selectedCount + 1);
                    MAP[i][j] = 0;
                }
            }
        }
    }


    static void pro() {
        dfs(0);

        System.out.println(RESULT);
    }
    */
}
