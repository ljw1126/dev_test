package fastcampus.algorithm.bruteforce;

import fastcampus.algorithm.MyReader;

/**
 * N과 M(2), 실버3
 * https://www.acmicpc.net/problem/15650
 *
 * 중복 없이 M개 뽑은 조합, 오름차순 나열
 *
 * 시간 복잡도 : O(nCr)
 * 공간 복잡도 : O(M)
 */
public class BOJ15650 {

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

    static void recFunc(int k, int start) {
        if(k == M + 1) {
            for(int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }

        for(int cand = start; cand <= N; cand++) {
            selected[k] = cand;
            recFunc(k + 1, cand + 1);
            selected[k] = 0;
        }
    }

    // 강의 해설
    static void recFunc2(int k) {
        if(k == M + 1) {
            for(int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }

        for(int cand = selected[k - 1] + 1; cand <= N; cand++) {
            selected[k] = cand;
            recFunc2(k + 1);
            selected[k] = 0;
        }
    }

    public static void main(String[] args) {
        input();

        recFunc(1, 1);
        System.out.println(sb.toString());
    }
}
