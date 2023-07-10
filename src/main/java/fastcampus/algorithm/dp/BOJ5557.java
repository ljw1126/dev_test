package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 1학년(골드5) https://www.acmicpc.net/problem/5557
 *
 * 2차원 배열을 사용
 * 상향식 O(N)
 * 하향식 O(2^n) , Memorization 기법 활용
 *
 * 최대치는 long
 *
 * top-down 하향식, 재귀 풀이 ( 마치 리프 노드의 값을 다 더해주는 느낌, 트리의 DP 풀이 방식)
 * https://steady-coding.tistory.com/171
 */
public class BOJ5557 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] A;

    static long[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        DP = new long[101][21]; // depth, sum
    }

    static void executeBottomUp() {
        DP[1][A[1]] = 1;

        for(int i = 2; i <= (N - 1); i++) { // depth
            for(int v = 0; v <= 20; v++) { // sum
                if(DP[i - 1][v] > 0) {
                    int v1 = v + A[i];
                    int v2 = v - A[i];

                    if(v1 <= 20) DP[i][v1] += DP[i - 1][v];
                    if(v2 >= 0) DP[i][v2] += DP[i - 1][v];
                }
            }
        }

        System.out.println(DP[N-1][A[N]]);
    }

    static long executeTopDown(int depth, int value) {
        if(value < 0 || value > 20) return 0L;
        if(depth == N - 1) {
            return value == A[N] ? 1L : 0L;
        }

        if(DP[depth][value] != -1) return DP[depth][value];

        return DP[depth][value] = executeTopDown(depth + 1, value + A[depth + 1])
                + executeTopDown(depth + 1, value - A[depth + 1]);
    }

    public static void main(String[] args) throws Exception {
        input();
        //for(long[] _dp : DP) Arrays.fill(_dp, -1);
        //System.out.println(executeTopDown(1, A[1]));

        executeBottomUp();
    }
}
