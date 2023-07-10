package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 1,2,3 더하기 5(실버2) https://www.acmicpc.net/problem/15990
 *
 * 앞에 숫자에서 1,2,3중 뭐로 끝났는지 알아야 하니 2차원 배열 사용
 *
 * N - 1 에서 1을 붙일 수 있는 건 2,3인 경우
 * N - 2 에서 2를 붙일 수 있는 건 1,3인 경우
 * N - 3 에서 3을 붙일 수 있는 건 1,2인 경우
 */
public class BOJ15990 {
    static StringBuilder sb = new StringBuilder();

    static int T, N;

    static int MOD = 1000000009;

    static long[][] DP;

    static void inputForBottomUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            N = Integer.parseInt(br.readLine());

            long result = 0L;
            for(int i = 1; i <= 3; i++) {
                result += DP[N][i];
                result %= MOD;
            }

            sb.append(result).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void executeByBottomUp() {
        DP = new long[100001][4];
        DP[1][1] = 1;
        DP[2][2] = 1;
        DP[3][1] = 1;
        DP[3][2] = 1;
        DP[3][3] = 1;

        for(int i = 4; i <= 100000; i++) {
            DP[i][1] = (DP[i - 1][2] + DP[i - 1][3]) % MOD;
            DP[i][2] = (DP[i - 2][1] + DP[i - 2][3]) % MOD;
            DP[i][3] = (DP[i - 3][1] + DP[i - 3][2]) % MOD;
        }
    }

    static void inputForTopDown() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        DP = new long[100001][4];
        for(int i = 1; i <= 100000; i++) Arrays.fill(DP[i], -1);

        DP[0][1] = 0;
        DP[0][2] = 0;
        DP[0][3] = 0;

        DP[1][1] = 1;
        DP[1][2] = 0;
        DP[1][3] = 0;

        DP[2][1] = 0;
        DP[2][2] = 1;
        DP[2][3] = 0;

        DP[3][1] = 1;
        DP[3][2] = 1;
        DP[3][3] = 1;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            N = Integer.parseInt(br.readLine());

            long result = 0L;
            for(int i = 1; i <= 3; i++) {
                result += executeByTopDown(N, i);
                result %= MOD;
            }

            sb.append(result).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static long executeByTopDown(int depth, int usage) {
        if(depth == 1 || depth == 2 || depth == 3) return DP[depth][usage];

        if(DP[depth][usage] != -1) return DP[depth][usage];

        long value = 0;
        if(usage == 1) {
            value += executeByTopDown(depth - 1, 2);
            value += executeByTopDown(depth - 1, 3);
        } else if(usage == 2) {
            value += executeByTopDown(depth - 2, 1);
            value += executeByTopDown(depth - 2, 3);
        } else if(usage == 3) {
            value += executeByTopDown(depth - 3, 1);
            value += executeByTopDown(depth - 3, 2);
        }

        return DP[depth][usage] = value % MOD;
    }

    public static void main(String[] args) throws Exception {
        //executeByBottomUp();
        //inputForBottomUp();

        inputForTopDown();
    }
}
