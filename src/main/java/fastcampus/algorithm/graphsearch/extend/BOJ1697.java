package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 숨바꼭질(실버1) https://www.acmicpc.net/problem/1697
 *
 * N : 수빈이 위치 ( 0 ~ 100,000 )
 * K : 동생의 위치 ( 0 ~ 100,000 )
 *
 * N = 100,000, K = 0 일때 -1을 100,000번하면 동생의 위치에 도착 가능
 * - 최대 100,000 이므로 Integer 연산 가능
 */
public class BOJ1697 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[] DISTANCE = new int[100001];

    static boolean[] VISIT = new boolean[100001];

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        br.close();
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(N);
        VISIT[N] = true;
        DISTANCE[N] = 0;

        while(!que.isEmpty()) {
            int x = que.poll();

            int y = x + 1;
            if(0 <= y && y <= 100000 && !VISIT[y]) {
                DISTANCE[y] = DISTANCE[x] + 1;
                VISIT[y] = true;
                que.add(y);
            }

            y = x - 1;
            if(0 <= y && y <= 100000 && !VISIT[y]) {
                DISTANCE[y] = DISTANCE[x] + 1;
                VISIT[y] = true;
                que.add(y);
            }

            y = x * 2;
            if(0 <= y && y <= 100000 && !VISIT[y]) {
                DISTANCE[y] = DISTANCE[x] + 1;
                VISIT[y] = true;
                que.add(y);
            }
        }
    }

    static void pro() {
        bfs();
        System.out.println(DISTANCE[M]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
