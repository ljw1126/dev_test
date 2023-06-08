package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18404 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, KNIGHT_X, KNIGHT_Y;

    static int[][] target, distance;

    static int[][] direction = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        KNIGHT_X = Integer.parseInt(st.nextToken());
        KNIGHT_Y = Integer.parseInt(st.nextToken());

        target = new int[M][2]; // 나이트가 잡아야 하는 말들의 위치
        distance = new int[N + 1][N + 1];
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            target[i][0] = x;
            target[i][1] = y;
        }
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(KNIGHT_X);
        que.add(KNIGHT_Y);

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                distance[i][j] = -1;
            }
        }

        distance[KNIGHT_X][KNIGHT_Y] = 0;

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            for(int i = 0; i < 8; i++) {
                int nx = x + direction[i][0];
                int ny = y + direction[i][1];

                if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if(distance[nx][ny] != -1) continue;

                que.add(nx);
                que.add(ny);
                distance[nx][ny] = distance[x][y] + 1;
            }
        }
    }

    static void pro() {
        bfs();
        for(int[] t : target) {
            sb.append(distance[t[0]][t[1]]).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
