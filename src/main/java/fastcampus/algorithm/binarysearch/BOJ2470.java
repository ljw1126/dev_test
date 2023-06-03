package fastcampus.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 이진 탐색으로 찾은 idx 값 위치와 idx - 1 위치 값 중 범위를 만족하고, 0에 가까운 수라면 갱신
 * 범위 : left + 1 ~ N 사이에 위치해야 함
 *
 * result 초기값이 N + 1인 이유는 result , result - 1 범위로 해서 조건 만족하면 구하려고 하는듯 result -1일때는 범위안에 드니깐
 *
 *  1  2   3 4  5
 * -99 -2 -1 4 98
 *
 * Point. 이진탐색으로 찾으려는 index
 * result = A[left + 1 ... N] 에서 X = -A[left] 이상의 원소 중 가장 왼쪽 위치를 구함, 만약 그런게 없다면 -1
 * -2의 경우 +2 이상 원소중에 가장 왼쪽 위치를 구해야 하니 4를 찾게 됨
 */
public class BOJ2470 {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] A;

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) A[i] = Integer.parseInt(st.nextToken());
    }

    static int binarySearch(int[] arr, int L, int R, int target) {
        int result = R + 1;
        while(L <= R) {
            int mid = (L + R) / 2;
            if(target <= arr[mid]) {
                R = mid - 1;
                result = mid;
            } else {
                L = mid + 1;
            }
        }

        return result;
    }

    static void pro() {
        Arrays.sort(A, 1, N + 1);

        int bestValue = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        for(int left = 1; left <= N; left++) {
            int result = binarySearch(A, left + 1, N, -A[left]);

            // result <= N
            if(left + 1 <= result && result <= N && Math.abs(A[left] + A[result]) < bestValue) {
                v1 = A[left];
                v2 = A[result];
                bestValue = Math.abs(A[left] + A[result]);
            }

            // left < result - 1
            if(left + 1 <= result - 1 && result - 1 <= N && Math.abs(A[left] + A[result - 1]) < bestValue) {
                v1 = A[left];
                v2 = A[result - 1];
                bestValue = Math.abs(A[left] + A[result - 1]);
            }
        }

        sb.append(v1).append(" ").append(v2);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
