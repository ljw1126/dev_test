package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 1, 2, 3 더하기 3(실버2) https://www.acmicpc.net/problem/15988
 *
 * 상향식, 하향식 연습하기 좋은 기본 문제
 */
public class BOJ15988 {
    static StringBuilder sb = new StringBuilder();

    static int T, N;

    static long[] DP;

    static int MOD = 1000000009;

    static void inputForBottomUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        while(T > 0) {
            T -= 1;
            N = Integer.parseInt(br.readLine());

            sb.append(DP[N]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void executeByBottomUp() {
        DP = new long[1000001]; // 최대치 long, 그냥 틀렸다 나오면 최대치 확인
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for(int i = 4; i <= 1000000; i++) {
            DP[i] = (DP[i - 1] + DP[i - 2] + DP[i - 3]) % MOD;
        }
    }


    static void inputForTopDown() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        DP = new long[1000001];
        Arrays.fill(DP, -1);

        DP[0] = 0;
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        while(T > 0) {
            T -= 1;
            N = Integer.parseInt(br.readLine());

            sb.append(executeByTopDown(N)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static long executeByTopDown(int idx) {
        if(DP[idx] != -1) return DP[idx];

        return DP[idx] = (executeByTopDown(idx - 1)
                + executeByTopDown(idx - 2)
                + executeByTopDown(idx -3)) % MOD;
    }



    public static void main(String[] args) throws Exception {
        //executeByBottomUp();
        //inputForBottomUp();

        inputForTopDown();
    }
}
