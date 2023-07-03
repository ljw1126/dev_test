package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1,2,3 더하기 (실버3) https://www.acmicpc.net/problem/9095
 *
 * 가짜 문제 정의 -> 가짜문제로 풀 수 있는가 ? -> 초기항 정의 -> 점화식 
 */
public class BOJ9095 {

    static StringBuilder sb = new StringBuilder();

    static int T;

    static int[] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        preProcess();

        while(T > 0) {
            T -= 1;

            int N = Integer.parseInt(br.readLine());
            sb.append(DP[N]).append("\n");
        }
    }

    static void preProcess() {
        DP = new int[12]; // 11이하 이므로
        DP[0] = 0;
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for(int i = 4; i <= 11; i++) {
            DP[i] = DP[i - 1] + DP[i - 2] + DP[i -3];
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
