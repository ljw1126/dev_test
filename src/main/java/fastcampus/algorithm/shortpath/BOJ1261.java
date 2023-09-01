package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 알고 스팟 (골드4)
 * https://www.acmicpc.net/problem/1261
 *
 * 다익스트라 카테고리 분류 되어 있어서 품
 * N, M은 최대 100
 * O(N * M)
 *
 * (1,1) -> (N, M) 이동시 최소로 벽을 뿌순 경우
 */
public class BOJ1261 {
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int[][] board;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 가로 (열)
        n = Integer.parseInt(st.nextToken()); // 세로 (행)

        board = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            String line = br.readLine();
            for(int j = 1; j <= m; j++) {
                board[i][j] = line.charAt(j - 1) - '0'; // char to int
            }
        }
    }

    static class Node implements Comparable<Node> {
        int i;
        int j;
        int count;

        public Node(int i, int j, int count) {
            this.i = i;
            this.j = j;
            this.count = count;
        }

        @Override
        public int compareTo(Node other) {
            return count - other.count; // 오름차순
        }
    }

    static void dijkstra() {
        // 1, 1 -> n , m 도착
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(1, 1, 0));

        boolean[][] visit = new boolean[n + 1][m + 1];
        visit[1][1] = true;

        int result = Integer.MAX_VALUE;
        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(cur.i == n && cur.j == m) {
                result = Math.min(result, cur.count);
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int di = cur.i + dir[i][0];
                int dj = cur.j + dir[i][1];
                int count = cur.count; // 현재까지 부순 벽돌 수

                if(di < 1 || dj < 1 || di > n || dj > m) continue;
                if(visit[di][dj]) continue;
                if(board[di][dj] == 1) count += 1;

                visit[di][dj] = true;
                que.add(new Node(di, dj, count));
            }
        }

        System.out.println(result);
    }

    static void pro() {
        dijkstra();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
