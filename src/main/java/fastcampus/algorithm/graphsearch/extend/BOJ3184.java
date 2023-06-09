package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 양(실버1) https://www.acmicpc.net/problem/3184
 */
public class BOJ3184 {

    static StringBuilder sb = new StringBuilder();

    static int R, C, SHEEP, WOLF, _S, _W;

    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static boolean[][] visit;

    static String[] MAP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visit = new boolean[R][C];
        MAP = new String[R];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            MAP[i] = st.nextToken();
        }
    }

    static void bfs(int i, int j) {
        Queue<Integer> que = new LinkedList<>();
        que.add(i);
        que.add(j);
        visit[i][j] = true;

        int _wolf = 0, _sheep = 0;
        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();

            if(MAP[x].charAt(y) == 'v') _wolf += 1;
            if(MAP[x].charAt(y) == 'o') _sheep += 1;

            for(int k = 0; k < 4; k++) {
                int nx = x + direction[k][0];
                int ny = y + direction[k][1];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(visit[nx][ny]) continue;
                if(MAP[nx].charAt(ny) == '#') continue;

                que.add(nx);
                que.add(ny);
                visit[nx][ny] = true;
            }
        }

        if(_sheep == 0 && _wolf == 0) return;

        if(_sheep > _wolf) SHEEP += _sheep;
        else WOLF += _wolf;
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;
        if(MAP[x].charAt(y) == 'v') _W += 1;
        if(MAP[x].charAt(y) == 'o') _S += 1;

        for(int i = 0; i < 4; i++) {
            int nx = x + direction[i][0];
            int ny = y + direction[i][1];

            if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
            if(visit[nx][ny]) continue;
            if(MAP[nx].charAt(ny) == '#') continue;

            dfs(nx, ny);
        }
    }

    static void pro() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(!visit[i][j] && MAP[i].charAt(j) != '#') {
                    //bfs(i, j);

                    //전역변수로 해야 함
                    _W = 0;
                    _S = 0;
                    dfs(i, j);

                    if(_S > _W) SHEEP += _S;
                    else WOLF += _W;
                }
            }
        }

        sb.append(SHEEP).append(" ").append(WOLF);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
