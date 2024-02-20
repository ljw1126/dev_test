package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 *
 * R행 C열 (최대 50 이하)
 *
 * 비어 있는 곳 '.'
 * 물이 차있는 지역 '*'
 * 돌 'X'
 * 비버의 굴 'D'
 * 고슴도치 위치 'S'
 *
 * 매분 마다 고슴도치 이동, 물도 비어있는 칸으로 확장됨
 * - 물과 고슴도치는 돌을 통과할 수 없다.
 * - 고슴도치는 물이 차있는 지역으로 이동 불가
 * - 물도 비버의 소굴로 이동불가
 * - (중요) 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찾을 예정인 칸에 고슴도치는 이동할 수 없다
 *
 *
 * 물이 차는 시간 구할 경우 : O(N^2)
 * 고슴도치 이동 구할 경우 : O(N^2 * 4)
 *
 * 시간 복잡도 : O(N^2 + N^2) = O(5000) 정도 연산 수행
 */
public class BOJ3055 {
    /**
     . : 비어 있는 곳, * : 물이 차있는 곳, 돌 : X
     */
    static StringBuilder sb = new StringBuilder();
    static int MAX_VALUE = 2501;
    static int[][] DIR = {{1, 0}, {-1, 0}, {0,  1}, {0, -1}};
    static int R, C;
    static int[][] DIST_WATER, DIST_HEDGEHOG;
    static String[] MATRIX;


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        calculateWater();
        runHedgeHog();

        Loop:
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(MATRIX[i].charAt(j) == 'D') {
                    sb.append(DIST_HEDGEHOG[i][j] == MAX_VALUE ? "KAKTUS" : DIST_HEDGEHOG[i][j]);
                    break Loop;
                }
            }
        }
    }

    private static void runHedgeHog() {
        Deque<Integer> que = new ArrayDeque<>();

        Loop:
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(MATRIX[i].charAt(j) == 'S') {
                    que.add(i);
                    que.add(j);
                    DIST_HEDGEHOG[i][j] = 0;
                    break Loop;
                }
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIR[i][0];
                int ny = y + DIR[i][1];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(MATRIX[nx].charAt(ny) == 'X') continue; // 바위
                if(DIST_HEDGEHOG[nx][ny] != MAX_VALUE) continue; // 방문 여부
                if(DIST_WATER[nx][ny] <= DIST_HEDGEHOG[x][y] + 1) continue;

                DIST_HEDGEHOG[nx][ny] = DIST_HEDGEHOG[x][y] + 1;
                que.add(nx);
                que.add(ny);
            }
        }
    }

    private static void calculateWater() {
        Deque<Integer> que = new ArrayDeque<>();

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(MATRIX[i].charAt(j) == '*') {
                    que.add(i);
                    que.add(j);
                    DIST_WATER[i][j] = 0;
                }
            }
        }

        while(!que.isEmpty()) {
            int x = que.poll();
            int y = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = x + DIR[i][0];
                int ny = y + DIR[i][1];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(MATRIX[nx].charAt(ny) != '.') continue;

                if(DIST_WATER[nx][ny] > DIST_WATER[x][y] + 1) {
                    DIST_WATER[nx][ny] = DIST_WATER[x][y] + 1;
                    que.add(nx);
                    que.add(ny);
                }
            }
        }
    }


    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        R = inputProcessor.nextInt();
        C = inputProcessor.nextInt();

        MATRIX = new String[R];
        for(int i = 0; i < R; i++) {
            MATRIX[i] = inputProcessor.nextLine();
        }

        DIST_WATER = new int[R][C];
        DIST_HEDGEHOG = new int[R][C];
        for(int i = 0; i < R; i++) {
            Arrays.fill(DIST_WATER[i], MAX_VALUE);
            Arrays.fill(DIST_HEDGEHOG[i], MAX_VALUE);
        }
    }
    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
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
