package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 가장 긴 바이토닉 부분 수열(골드4) https://www.acmicpc.net/problem/11054
 *
 * O(N) 시간 복잡도 소요
 */
public class BOJ11054 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] A, REVERSED_A, LIS, LDS;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        REVERSED_A = new int[N];
        LIS = new int[N];
        LDS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());

            A[i] = value;
            REVERSED_A[N - i - 1] = value;
        }

        //System.out.println(Arrays.toString(A));
        //System.out.println(Arrays.toString(REVERSED_A));
    }

    static void pro() {
        Arrays.fill(LIS, 1);
        Arrays.fill(LDS, 1);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(A[i] > A[j]) LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                if(REVERSED_A[i] > REVERSED_A[j]) LDS[i] = Math.max(LDS[i], LDS[j] + 1);
            }
        }

        //System.out.println(Arrays.toString(LIS));
        //System.out.println(Arrays.toString(LDS));

        int ans = 0;
        for(int i = 0; i < N; i++) {
            int value = LIS[i] + LDS[N - 1 - i] - 1; // 실수
            ans = Math.max(ans, value);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
