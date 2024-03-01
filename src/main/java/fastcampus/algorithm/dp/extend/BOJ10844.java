package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 쉬운 계단 수
 *
 * 시간 복잡도 O(N^2)
 */
public class BOJ10844 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] DP;

    static Long[][] _DP;

    static int MOD = 1000000000;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N + 1][10];
        _DP = new Long[N + 1][10];
        for(int i = 1; i <=9; i++) {
            DP[1][i] = 1;
            _DP[1][i] = 1L;
        }

        _DP[1][0] = 0L;
    }

    static void pro() {
        for(int i = 2; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                DP[i][j] = 0;
                if(j > 0) DP[i][j] += DP[i - 1][j - 1];
                if(j < 9) DP[i][j] += DP[i - 1][j + 1];

                DP[i][j] %= MOD;
            }
        }

        int result = 0;
        for(int v : DP[N]) {
            result += v;
            result %= MOD;
        }

        System.out.println(result);
    }

    static void proByTopDown() {
        long result = 0L;
        for(int i = 0; i <= 9; i++) {
            result += topDown(N, i);
            result %= MOD;
        }

        System.out.println(result);
    }

    static long topDown(int depth, int idx) {
        if(depth == 1){
            return _DP[depth][idx];
        }

        if(_DP[depth][idx] == null) {
            if (idx == 0) {
                _DP[depth][idx] = topDown(depth - 1, idx + 1);
            } else if (idx == 9) {
                _DP[depth][idx] = topDown(depth - 1, idx - 1);
            } else {
                _DP[depth][idx] = topDown(depth - 1, idx - 1) + topDown(depth - 1, idx + 1);
            }
        }

        return _DP[depth][idx] %= MOD;
    }

    public static void main(String[] args) throws Exception {
        input();
        //pro(); // 32, 61
        proByTopDown();
    }
}
