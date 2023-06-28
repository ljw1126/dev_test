package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 포도주 시식(실버1) https://www.acmicpc.net/problem/2156
 *
 * - 직절 풀지 못함
 * 시간복잡도 O(N)
 *
 * i번째 포도주를 마시는 경우 2가지와 마시지 않는 경우 1가지 있음
 *
 * top-down 재귀 방식으로 풀이 가능
 * https://st-lab.tistory.com/135
 */
public class BOJ2156 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] DRINKS, DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DRINKS = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            int value = Integer.parseInt(br.readLine());
            DRINKS[i] = value;
        }
    }

    static void pro() {
       DP = new int[N + 1];
       DP[1] = DRINKS[1];

       for(int i = 2; i <= N; i++) {
           if(i == 2) {
               DP[i] = DRINKS[1] + DRINKS[2];
               continue;
           }

           // 실수. 마시지 않는 경우도 비교 필요
           DP[i] = Math.max(Math.max(DRINKS[i] + DRINKS[i - 1] + DP[i -3], DRINKS[i] + DP[i - 2]), DP[i - 1]);
       }

        System.out.println(DP[N]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
