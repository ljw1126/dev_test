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
 * 단, 매 초마다 번호가 낮은 종류의 바이러스부터 먼저 증식한다 (=정렬이 필요하다)
 */
public class BOJ18405 {
    static int N, K;
    static int S, X, Y;

    static int[][] TEST_MAP;

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        TEST_MAP = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                TEST_MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
    }

    static class Virus {
        int virusNumber;
        int x;
        int y;
        int t;

        public Virus(int virusNumber, int x, int y, int t) {
            this.virusNumber = virusNumber;
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    // multisource bfs 말고 우선 순위 큐를 사용해야 함
    static void bfs() {
        // 타이밍과 바이러스 번호 순으로
        Queue<Virus> que = new PriorityQueue<>((a, b) -> {
            if(a.t == b.t) {
                return a.virusNumber - b.virusNumber;
            }

            return a.t - b.t;
        });

        for(int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(TEST_MAP[i][j] != 0) {
                    que.add(new Virus(TEST_MAP[i][j], i, j, 0));
                }
            }
        }

        while(!que.isEmpty()) {
            Virus virus = que.poll();
            if(virus.t == S) break;

            for(int i = 0; i < 4; i++) {
                int nx = virus.x + DIRECTION[i][0];
                int ny = virus.y + DIRECTION[i][1];

                if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if(TEST_MAP[nx][ny] != 0) continue;

                TEST_MAP[nx][ny] = virus.virusNumber;
                que.add(new Virus(virus.virusNumber, nx, ny, virus.t + 1));
            }
        }
    }

    static void pro() {
        bfs();
        System.out.println(TEST_MAP[X][Y]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
