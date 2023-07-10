package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * int로 해서 틀렸음 => long으로 해야함
 *
 * 문제 힌트) MOD로 나머지를 구하거나, 아무 말 없는 경우
 * 최대치가 int로 하면 "틀렸습니다"만 출력되서 시간 소비하게 됨
 *
 * 피보나치 47번째 항이 29억이 넘는다는 거만 참고로 기억하자
 */
public class BOJ2193 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static long[] DP = new long[91];

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    static void executeBottomUp() {
        DP[0] = 0;
        DP[1] = 1;

        for(int i = 2; i <= N; i++) {
            DP[i] = DP[i - 1] + DP[i -2];
        }

        System.out.println(DP[N]);
    }

    static long executeTopDown(int idx) {
        if(idx == 0 || idx == 1) return DP[idx];

        if(DP[idx] != -1) return DP[idx];

        return DP[idx] = executeTopDown(idx - 2) + executeTopDown(idx - 1);
    }

    public static void main(String[] args) throws Exception {
        input();
        //executeBottomUp();

        Arrays.fill(DP, -1);
        DP[0] = 0L;
        DP[1] = 1L;
        System.out.println(executeTopDown(N));
    }
}
