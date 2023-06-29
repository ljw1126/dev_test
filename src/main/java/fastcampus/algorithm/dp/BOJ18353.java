package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 병사 배치하기 (실버2)
 * https://www.acmicpc.net/problem/18353
 *
 * - N 최대치 2,000일때 O(N^2) 풀이 가능
 *
 * 참고
 * https://mgyo.tistory.com/689
 */
public class BOJ18353 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] DATA, DP;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        DP = new int[N];
        DATA = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            DATA[i] = Integer.parseInt(st.nextToken());
        }

        // reverse (Collections의 경우 Wrapper 클래스 사용)
        for(int i = 0; i < (N / 2); i++) {
            int temp = DATA[i];
            int swapIdx = DATA.length - i - 1;
            DATA[i] = DATA[swapIdx];
            DATA[swapIdx] = temp;
        }
    }

    static void pro() {
        Arrays.fill(DP, 1);

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(DATA[i] > DATA[j]) DP[i] = Math.max(DP[i], DP[j] + 1);
            }
        }

        int lisMaxCount = Arrays.stream(DP).max().getAsInt();
        System.out.println(N - lisMaxCount);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
