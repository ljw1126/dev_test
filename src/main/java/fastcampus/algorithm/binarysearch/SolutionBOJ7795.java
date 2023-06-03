package fastcampus.algorithm.binarysearch;

import fastcampus.algorithm.MyReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 먹을 것인가 먹힐 것인가 https://www.acmicpc.net/problem/7795
 *
 */
public class SolutionBOJ7795 {
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();
    static int N, M;

    static int[] A, B;

    static void input() throws Exception{
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N + 1];
        B = new int[M + 1];
        for(int i = 1; i <= N; i++) A[i] = scan.nextInt();

        for(int i = 1; i <= M; i++) B[i] = scan.nextInt();
    }

    static int lowerBound(int[] B, int L, int R, int A) {
        int result = L - 1;

        while(L <= R) {
            int mid = (L + R) / 2;
            if(B[mid] < A) {
                L = mid + 1;
                result = mid;
            } else if(B[mid] >= A) {
                R = mid - 1;
            }
        }

        return result;
    }

    static void pro() {
        Arrays.sort(B, 1, M + 1);
        int ans = 0;
        for(int i = 1; i <= N; i++) {
            ans += lowerBound(B, 1, M, A[i]);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        int testCase = scan.nextInt();
        for(int t = 1; t <= testCase; t++) {
            input();
            pro();
        }
    }
}
