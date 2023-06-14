package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 적록색약(골드5) https://www.acmicpc.net/problem/10026
 */
public class BOJ10026 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static String[][] FIELD;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static boolean[][] VISIT;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        FIELD = new String[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String[] str = st.nextToken().split("");
            for(int j = 0; j < N; j++) {
                FIELD[i][j] = str[j];
            }
        }

        VISIT = new boolean[N][N];
    }

    static void dfs(int x, int y, String color) {
        VISIT[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + DIRECTION[i][0];
            int ny = y + DIRECTION[i][1];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(VISIT[nx][ny]) continue;
            if(!color.equals(FIELD[nx][ny])) continue;

            dfs(nx, ny, FIELD[nx][ny]);
        }
    }

    static void initVisitAndReverseRedToGreen() {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                VISIT[i][j] = false;
                if(FIELD[i][j].equals("R")) {
                    FIELD[i][j] = "G";
                }
            }
        }
    }

    static void pro() {
        //적록색약이 아닌 경우
        int groupCnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!VISIT[i][j]) {
                    groupCnt += 1;
                    dfs(i, j, FIELD[i][j]);
                }
            }
        }

        sb.append(groupCnt).append(" ");

        initVisitAndReverseRedToGreen();

        groupCnt = 0;
        //적록 색약인 경우
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!VISIT[i][j]) {
                    groupCnt += 1;
                    dfs(i, j, FIELD[i][j]);
                }
            }
        }

        sb.append(groupCnt);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
