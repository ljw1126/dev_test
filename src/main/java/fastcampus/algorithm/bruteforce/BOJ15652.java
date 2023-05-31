package fastcampus.algorithm.bruteforce;

import fastcampus.algorithm.MyReader;

/**
 * N과 M(4), 실버3
 * https://www.acmicpc.net/problem/15652
 *
 * 중복허용하되, 비내림차순 문제
 *
 * 시간 : O(N^M) => 8^8 = 약 1677만
 * 공간 : O(M)
 */
public class BOJ15652 {

    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        MyReader scan = new MyReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M + 1];
    }

    // 내 풀이
    static void recFunc(int k, int start) {
        if(k == M + 1) {
            for(int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }

        for(int cand = start; cand <= N; cand++) {
            selected[k] = cand;
            recFunc(k + 1, cand);
            selected[k] = 0;
        }
    }

    // 강의 풀이
    static void recFunc2(int k) {
        if(k == M + 1) {
            for(int i = 1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }

        int start = selected[k - 1];
        if(start == 0) start = 1;
        for(int num = start; num <= N; num++) {
            selected[k] = num;
            recFunc2(k + 1);
            selected[k] = 0;
        }
    }

    public static void main(String[] args) {
        input();

        // 1번째
        recFunc(1, 1);
        System.out.println(sb.toString());
    }
}
