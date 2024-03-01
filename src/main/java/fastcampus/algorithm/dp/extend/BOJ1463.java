package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 1로 만들기 (실버3) https://www.acmicpc.net/problem/1463
 *
 * 다시 풀어보기
 * 시간복잡도 O(N)
 */
public class BOJ1463 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new int[N + 1];
    }

    static void pro() {
        for(int i = 2; i <= N; i++) {
            DP[i] = DP[i - 1] + 1;

            if(i % 2 == 0) DP[i] = Math.min(DP[i], DP[i/2] + 1);

            if(i % 3 == 0) DP[i] = Math.min(DP[i], DP[i/3] + 1);
        }

        System.out.println(DP[N]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
