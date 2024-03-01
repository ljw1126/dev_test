package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 피보나치 수열 응용 문제
 * - 초기항 찾기, 좌석 개수당 경우의 수 구하기
 */
public class BOJ2302 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[] DP;

    static List<Integer> range = new ArrayList<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int start = 0;
        for(int i = 1; i <= M; i++) {
            int end = Integer.parseInt(br.readLine());
            range.add(end - start - 1);
            start = end;
        }
        range.add(N - start);
    }

    static void makeDP() {
        DP = new int[N + 1];
        DP[0] = 1;
        DP[1] = 1;

        for(int i = 2; i <= N; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
        }
    }

    static void pro() {
        int ans = 1;
        for(int r : range) ans *= DP[r];

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        makeDP();
        pro();
    }
}
