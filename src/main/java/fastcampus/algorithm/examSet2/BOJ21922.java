package fastcampus.algorithm.examSet2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 학부 연구생 민상(골드5)
 * https://www.acmicpc.net/problem/21922
 *
 * - 예제 입 출력 동일하게 나오는데 틀렸다함 .
 * - 바람이 물건에 부딪혔을 떄 방향이 바뀌는 기능을 정확히 구현해야함
 * - 바람의 현재 상태를 (i, j, 방향) 으로 표현
 * - 시뮬레이션 방문 배열을 visited[i][j][dir] 형태로 관리 -- 직접 생각한거는 다행
 *
 * - n * m 하더라도 400만이라 충분히 가능
 * - 에어컨 정보를 바로 찾아서 바람 초기화해서 처리
 * - 이떄 boolean[][] checked를 하나더 선언해서 마지막에 카운팅해서 결과값 도출 가능 // 또 어렵게 생각..
 */
public class BOJ21922 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[][] fields;
    static boolean[][][] visit;
    static boolean[][] checked;

    static int[][] dir = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        Queue<Wind> airCondition = new LinkedList<>();
        fields = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
                if(fields[i][j] == 9) {
                    airCondition.add(new Wind(i, j, -1));
                }
            }
        }

        visit = new boolean[N + 1][M + 1][4]; // 방향 정보도 필요
        checked = new boolean[N + 1][M + 1];

        Queue<Wind> windQue = new LinkedList<>();
        while(!airCondition.isEmpty()) {
            Wind cur = airCondition.poll();

            for(int i = 0; i < 4; i++) { // 현재 에어컨 위치에서 4가지 방향
                int dx = cur.x + dir[i][0];
                int dy = cur.y + dir[i][1];

                if(dx < 1 || dy < 1 || dx > N || dy > M) continue;
                if(visit[dx][dy][i]) continue;

                visit[dx][dy][i] = true;
                windQue.add(new Wind(dx, dy, i));
                checked[dx][dy] = true;
            }

            checked[cur.x][cur.y] = true;
        }

        while(!windQue.isEmpty()) {
            Wind cur = windQue.poll();

            // 현재 위치의 물건과, 이전 바람 방향에 따라 다음 방향이 바뀜
            int nextDir = getNextDir(cur.dir, fields[cur.x][cur.y]);

            int dx = cur.x + dir[nextDir][0];
            int dy = cur.y + dir[nextDir][1];

            if(dx < 1 || dy < 1 || dx > N || dy > M) continue;
            if(visit[dx][dy][nextDir]) continue;

            visit[dx][dy][nextDir] = true;
            windQue.add(new Wind(dx, dy, nextDir));
            checked[dx][dy] = true;
        }

        int answer = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(checked[i][j]) answer += 1;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static class Wind {
        int x;
        int y;
        int dir;

        public Wind(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int getNextDir(int curDir, int stuff) {
        int result = curDir;
        if(stuff == 1) {
            if(curDir == 1) result = 3;
            if(curDir == 3) result = 1;
        } else if(stuff == 2) {
            if(curDir == 0) result = 2;
            if(curDir == 2) result = 0;
        } else if(stuff == 3) {
            if(curDir == 0) result = 1;
            else if(curDir == 1) result = 0;
            else if(curDir == 2) result = 3;
            else if(curDir == 3) result = 2;
        } else if(stuff == 4) {
            if(curDir == 0) result = 3;
            else if(curDir == 1) result = 2;
            else if(curDir == 2) result = 1;
            else if(curDir == 3) result = 0;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
