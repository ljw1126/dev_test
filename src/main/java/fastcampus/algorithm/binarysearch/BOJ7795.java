package fastcampus.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 먹을 것인가 먹힐 것인가 https://www.acmicpc.net/problem/7795
 *
 * 이진탐색
 * N = 20,000
 *
 * 시간
 * O(N^2) = 4억, 4초만에 연산은 되겠지만 시간초과 발생 가능
 * 이진탐색 사용할 경우 -> O(NlogM) // 이진탐색의 시간 복잡도 O(logN)
 *
 * 공간
 * O(40000)
 *
 * 풀이)
 * B를 정렬 후 이진탐색으로 처리
 */
public class BOJ7795 {

    static StringBuilder sb = new StringBuilder();
    static int T, N, M;

    static int[] A, B;

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            B = new int[M];

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < M; k++) {
                B[k] = Integer.parseInt(st.nextToken());
            }

            pro();
        }
    }

    static int binarySearch(int[] arr, int target, int left, int right) {
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static void pro() {
        Arrays.sort(B);

        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += binarySearch(B, A[i], 0, B.length);
        }

        sb.append(sum).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();

        System.out.println(sb.toString());
    }
}
