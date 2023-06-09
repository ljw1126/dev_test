package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 단지 번호 붙이기 (실버1) https://www.acmicpc.net/problem/2667
 *
 * 오름차순 정렬 후 출력
 */
public class BOJ2667 {
    static StringBuilder sb = new StringBuilder();

    static int N, CNT;

    static String[][] adj; // String 1차원 배열에 담고 adj[dx].charAt(dy)

    static boolean[][] visited;

    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        adj = new String[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String[] texts = st.nextToken().split("");
            for(int j = 0; j < N; j++) {
                adj[i][j] = texts[j];
            }
        }

        visited = new boolean[N][N];
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        CNT += 1;

        for(int k = 0; k < 4; k++) {
            int dx = x + directions[k][0];
            int dy = y + directions[k][1];

            if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
            if(visited[dx][dy]) continue;
            if(Objects.equals(adj[dx][dy] , "0")) continue;

            dfs(dx, dy);
        }
    }

    static void bfs(int i, int j) {
        Queue<Integer> que = new LinkedList<>();
        que.add(i);
        que.add(j);
        visited[i][j] = true;

        while(!que.isEmpty()) {
            int x = que.poll(), y = que.poll();
            CNT += 1;

            for(int k = 0; k < 4; k++) {
                int dx = x + directions[k][0];
                int dy = y + directions[k][1];

                if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
                if(visited[dx][dy]) continue;
                if(Objects.equals(adj[dx][dy] , "0")) continue;

                visited[dx][dy] = true;
                que.add(dx);
                que.add(dy);
            }
        }
    }


    static void pro() {
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(Objects.equals(adj[i][j], "1") && !visited[i][j]) {
                    CNT = 0;
                    //dfs(i, j);
                    bfs(i, j);
                    result.add(CNT);
                }
            }
        }

        Collections.sort(result);

        sb.append(result.size()).append("\n");
        for(int i : result) sb.append(i).append("\n");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
