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
 * i번째 포도주를 마시는 경우 2가지, 마시지 않는 경우 1가지 있음
 *
 * top-down 재귀 방식으로 풀이 가능
 * https://st-lab.tistory.com/135
 */
public class BOJ2156 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] DP, DATA;


    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = Integer.parseInt(br.readLine());
        }

        DP = new int[N + 1];
    }

    /**
     * 1. A[N] + A[N - 1] + DP[N - 3]
     * 2. A[N] + DP[N - 2]
     * 3. DP[N - 1] (마시지 않는 경우)
     */
    static void executeBottomUp() {
        DP[1] = DATA[1];

        if(N >= 2) {
            DP[2] = DATA[1] + DATA[2];
        }

        for(int i = 3; i <= N; i++) {
            DP[i] = Math.max(Math.max(DATA[i - 1] + DP[i - 3], DP[i - 2]) + DATA[i], DP[i - 1]);
        }

        System.out.println(DP[N]);
    }

    static int executeTopDown(int idx) {
        if(idx == 0 || idx == 1) return DP[idx];
        if(idx == 2) return DP[2] = DATA[1] + DATA[2];

        if(DP[idx] != -1) return DP[idx];

        return DP[idx] = Math.max(
                Math.max(DATA[idx -1] + executeTopDown(idx - 3), executeTopDown(idx - 2)) + DATA[idx],
                executeTopDown(idx - 1)
        );
    }

    public static void main(String[] args) throws Exception {
        input();
        //executeBottomUp();

        Arrays.fill(DP, -1);
        DP[0] = 0;
        DP[1] = DATA[1];

        System.out.println(executeTopDown(N));
    }
}
