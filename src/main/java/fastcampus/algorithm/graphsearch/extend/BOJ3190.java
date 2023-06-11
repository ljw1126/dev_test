package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 뱀(골드4) https://www.acmicpc.net/problem/3190
 */
public class BOJ3190 {
    static int N, K, L, TIME; // N : 보드 크기, K : 사과의 개수 , L: 방향 변환 명령 횟수

    static int[][] GAME_MAP;

    static List<Cmd> CMD_LIST = new LinkedList<>();

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 초기 오른쪽

    static class Cmd {
        int timing;
        String direction;

        public Cmd(int timing, String direction) {
            this.timing = timing;
            this.direction = direction;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        GAME_MAP = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        // 빈 공간 : 0, 사과 위치 : 1, 뱀 : 2
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            GAME_MAP[x][y] = 1;
        }

        // 방향 전환 정보
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            CMD_LIST.add(new Cmd(t, c));
        }
    }

    static void bfs(int startX, int startY) {
        // 뱀의 정보를 가지고 있음
        Queue<Integer> que = new LinkedList<>();
        que.add(startX);
        que.add(startY);

        GAME_MAP[startX][startY] = 2;

        int pointX = startX, pointY = startY;
        int dirIdx = 0; // 0 ~ 3
        Cmd cmd = CMD_LIST.remove(0);
        while (!que.isEmpty()) {
            TIME += 1;
            pointX += DIRECTION[dirIdx][0];
            pointY += DIRECTION[dirIdx][1];

            // 벽에 부딪히는 경우
            if (pointX < 1 || pointY < 1 || pointX > N || pointY > N) break;
            // 자기 자신을 물었는가
            if (GAME_MAP[pointX][pointY] == 2) break;


            // 사과 없는 경우
            if (GAME_MAP[pointX][pointY] == 0) {
                int x = que.poll(), y = que.poll();
                GAME_MAP[x][y] = 0;
            }

            // 뱀의 머리 이동
            GAME_MAP[pointX][pointY] = 2;
            que.add(pointX);
            que.add(pointY);

            if (TIME == cmd.timing) {
                dirIdx = getDirectionIdx(dirIdx, cmd.direction);
                if (!CMD_LIST.isEmpty()) {
                    cmd = CMD_LIST.remove(0);
                }
            }
        }

        System.out.println(TIME);
    }

    static int getDirectionIdx(int current, String cmd) {
        int result;
        if ("D".equals(cmd)) {
            result = (current + 1) % 4;
        } else {
            result = current - 1;
            if (result < 0) result = 3;
        }

        return result;
    }

    static void pro() {
        bfs(1, 1);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}