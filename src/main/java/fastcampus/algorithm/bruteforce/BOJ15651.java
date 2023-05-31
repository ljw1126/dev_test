package fastcampus.algorithm.bruteforce;

import fastcampus.algorithm.MyReader;

/**
 * N과 M(3), 실버3
 * https://www.acmicpc.net/problem/15651
 *
 * 시간복잡도 : O(N^M)
 * 공간복잡도 : O(M)
 */
public class BOJ15651 {

    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        MyReader scan = new MyReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M + 1];
    }

    static void recFunc(int k) {
        if(k == M + 1) {
            for(int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }

        for(int cand = 1; cand <= N; cand++) {
            selected[k] = cand;
            recFunc(k + 1);
            selected[k] = 0;
        }
    }

    public static void main(String[] args) {
        input();

        // 1번째
        recFunc(1);
        System.out.println(sb.toString());
    }
}
