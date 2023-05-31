package fastcampus.algorithm.bruteforce;

import fastcampus.algorithm.MyReader;

/**
 * N과 M(1), 실버3
 * https://www.acmicpc.net/problem/15649
 *
 * 시간 복잡도 : O(nPm) = 최대 8!/ 0! = 40,320
 * 공간 복잡도 : O(M)
 */
public class BOJ15649 {

    static int N, M;
    static int[] selected;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        MyReader scan = new MyReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M + 1];
        visited = new boolean[N + 1];
    }

    static void recFunc(int k) {
        if(k == M + 1) {
            for(int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }

        for(int cand = 1; cand <= N; cand++) {
            if(visited[cand]) continue;

            selected[k] = cand;
            visited[cand] = true;
            recFunc(k + 1);
            selected[k] = 0;
            visited[cand] = false;
        }
    }

    public static void main(String[] args) {
        input();

        // 1번째
        recFunc(1);
        System.out.println(sb.toString());
    }
}
