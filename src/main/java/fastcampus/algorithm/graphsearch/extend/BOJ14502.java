package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
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
    /*
        0 : 빈칸, 1 : 벽, 2 : 바이러스
        1) 벽을 세운다 - 64C3
        2) 바이러스를 퍼뜨린다 - N^2
        3) 안전한 영역을 카운팅 한다(최대값 구함)
      */
    static StringBuilder sb = new StringBuilder();
    static int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] MATRIX;
    static boolean[][] VISIT;
    static int N, M, RESULT, BLANK_COUNT;
    static int MAX_WALL_COUNT = 3;

    static int[][] BLANKS;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        checkBlank();
        makeWall(0, 0);

        sb.append(RESULT);
    }

    private static void checkBlank() {
        BLANKS = new int[65][2];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(MATRIX[i][j] == 0) {
                    BLANKS[BLANK_COUNT][0] = i;
                    BLANKS[BLANK_COUNT][1] = j;
                    BLANK_COUNT += 1;
                }
            }
        }
    }

    private static void makeWall(int cnt, int idx) {
        if(cnt == MAX_WALL_COUNT) {
            spreadVirus();
            return;
        }

        if(idx >= BLANK_COUNT) return;

        // 벽을 세운다
        MATRIX[BLANKS[idx][0]][BLANKS[idx][1]] = 1;
        makeWall(cnt + 1, idx + 1);

        // 벽을 세우지 않는다
        MATRIX[BLANKS[idx][0]][BLANKS[idx][1]] = 0;
        makeWall(cnt, idx + 1);
    }

    private static void spreadVirus() {
        Deque<Integer> que = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                VISIT[i][j] = false;
                if(MATRIX[i][j] == 2) {
                    que.add(i);
                    que.add(j);
                    VISIT[i][j] = true;
                }
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIR[i][0];
                int ny = y + DIR[i][1];

                if(nx < 1 || ny < 1 || nx > N || ny > M) continue;
                if(VISIT[nx][ny]) continue;
                if(MATRIX[nx][ny] != 0) continue;

                que.add(nx);
                que.add(ny);
                VISIT[nx][ny] = true;
            }
        }

        int count = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(MATRIX[i][j] == 0 && !VISIT[i][j]) { // 바이러스가 방문하지 않은 경우
                    count += 1;
                }
            }
        }

        RESULT = Math.max(RESULT, count);
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        MATRIX = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                MATRIX[i][j] = inputProcessor.nextInt();
            }
        }

        VISIT = new boolean[N + 1][M + 1];
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
