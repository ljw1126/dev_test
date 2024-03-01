package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 정수삼각형(실버1) https://www.acmicpc.net/problem/1932
 *
 * 최대값 n = 500, 범위 9999(10000)이라 할 때 5 * 10^6 Integer 범위 충분
 */
public class BOJ1932 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] RECT;

    static Integer[][] DP;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        RECT = new int[N][N];
        DP = new Integer[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < i + 1; j++) {
                int value = Integer.parseInt(st.nextToken());
                RECT[i][j] = value;
            }
        }

        for(int i = 0; i < N; i++) {
            DP[N - 1][i] = RECT[N - 1][i];
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int depth, int idx) {
        if(depth == N - 1) return DP[N - 1][idx];

        if(DP[depth][idx] == null) {
            DP[depth][idx] = Math.max(dfs(depth + 1, idx), dfs(depth + 1, idx + 1)) + RECT[depth][idx];
        }

        return DP[depth][idx];
    }


    public static void main(String[] args) throws Exception {
        input();
    }
}
