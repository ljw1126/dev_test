package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 계단수 (골드1)
 *
 * i자리에 j숫자로 끝나는 수에 대해 bitmasking 으로 사용여부 파악
 *
 */
public class BOJ1562 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int MOD = 1000000000;

    static int MASKING = 1 << 10;

    static long[][][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 비트 마스킹 기법 활용
        DP = new long[N + 1][10][MASKING]; // 1 << 10 == 10,000,000,000 (2진수, 10진수 1024) 이므로 0 ~ 1023
        for(int i = 1; i < 10; i++) {
            //DP[1][i][1 << i] = 1;
        }
    }

    static void pro() {
        for(int i = 2; i <= N; i++) { // N자리 수 중
            for(int j = 0; j <= 9; j++) { // 끝 수가 j로 끝나면서
                for(int b = 0; b < MASKING; b++) { // 1 << 10 == 1024 이므로 1023까지 순회 1 1111 1111
                    int bitMask = b | 1 << j; // 숫자 조합을 비트 마스킹으로 나타냄

                    if(j > 0) DP[i][j][bitMask] += DP[i - 1][j - 1][b];
                    if(j < 9) DP[i][j][bitMask] += DP[i - 1][j + 1][b];

                    DP[i][j][bitMask] %= MOD;
                }
            }
        }

        long result = 0;
        for(int i = 0; i <= 9; i++) {
            result += DP[N][i][(1 << 10) -1];
            result %= MOD; // 나머지 계산 필요한 문제는 결과값에서도 처리 필요 !
        }

        System.out.println(result);
    }

    static long executeByTopDown(int depth, int lastNumber, int bit) {
        // 초기항, 조건 처리
        if(depth == N) return bit == (MASKING) - 1 ? 1 : 0;

        // 값이 있는 경우 early return (memorization)
        if(DP[depth][lastNumber][bit] != -1) return DP[depth][lastNumber][bit];

        int cnt = 0;
        if(lastNumber > 0) cnt += executeByTopDown(depth + 1, lastNumber - 1, bit | (1 << (lastNumber - 1)));
        if(lastNumber < 9) cnt += executeByTopDown(depth + 1, lastNumber + 1, bit | (1 << (lastNumber + 1)));

        // 값이 없는 경우 재귀 호출
        return DP[depth][lastNumber][bit] = cnt % MOD;
    }


    static void proForTopDown() {
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                Arrays.fill(DP[i][j], -1);
            }
        }

        long result = 0;
        for(int i = 1; i <= 9; i++) { // 맨 앞자리에 0은 올 수 없으므로 1 ~ 9 탐색, 정답은 DP[1][9][512] = 1
            result += executeByTopDown(1, i, 1 << i);
            result %= MOD;
        }

        System.out.println(result);
    }


    public static void main(String[] args) throws Exception {
        input();

        //pro();

        proForTopDown();
    }
}
