package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 경쟁적 전염(골드5) https://www.acmicpc.net/problem/18405
 *
 * keyword
 * 단, 매 초마다 번호가 낮은 종류의 바이러스부터 먼저 증식한다
 * => 우선 순위 큐를 사용하여 번호가 낮은 바이러스 종류부터 조회함
 */
public class BOJ18405 {
    static int N, K, S, X, Y;

    static int[][] FIELD;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static class Virus {
        int x;
        int y;
        int num;

        int timing;

        public Virus(int x, int y, int num, int timing) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.timing = timing;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        FIELD = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                FIELD[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
    }

    static void bfs() {
        Queue<Virus> que = new PriorityQueue<>((a, b) -> {
            if(a.timing == b.timing) return a.num - b.num;
            else return a.timing - b.timing;
        });

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(FIELD[i][j] != 0) {
                    que.add(new Virus(i, j, FIELD[i][j], 0));
                }
            }
        }

        while(!que.isEmpty()) {
            Virus v = que.poll();
            if(v.timing == S) break;

            for(int i = 0; i < 4; i++) {
                int nx = v.x + DIRECTION[i][0];
                int ny = v.y + DIRECTION[i][1];

                if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if(FIELD[nx][ny] != 0) continue;

                FIELD[nx][ny] = v.num;
                que.add(new Virus(nx, ny, v.num, v.timing + 1));
            }
        }
    }

    static void pro() {
        bfs();

        System.out.println(FIELD[X][Y]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
