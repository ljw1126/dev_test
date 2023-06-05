package fastcampus.algorithm.twopoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 두용액 https://www.acmicpc.net/problem/2470
 *
 * 정렬 O(NlogN) 소요
 * 투포인터 로직 O(N) 소요
 *
 * 고로 시간 복잡도 : O(NlogN)
 */
public class BOJ2470 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] liquid;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        liquid = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        Arrays.sort(liquid);

        int L = 0, R = N - 1, bestValue = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        while(L < R) {
            int sum = liquid[L] + liquid[R];
            if(bestValue > Math.abs(sum)) {
                bestValue = Math.abs(sum);
                v1 = liquid[L];
                v2 = liquid[R];
            }

            if(sum > 0) R -= 1;
            else L += 1;
        }

        sb.append(v1).append(" ").append(v2);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
