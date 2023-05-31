package fastcampus.algorithm.bruteforce.extend;

import fastcampus.algorithm.MyReader;
/**
 * 부분 수열의 합
 * https://www.acmicpc.net/problem/1182
 *
 * 최대값 : 20 * 100만 = 2000만이므로 int로 처리 가능
 * 시간 복잡도 : O(2^20)
 *
 * 진 부분 집합
 */
public class BOJ1182 {

    static int N, S, result;
    static int[] nums;


    static void input() {
        MyReader scan = new MyReader();
        N = scan.nextInt();
        S = scan.nextInt();

        nums = new int[N + 1];

        for(int i = 1; i <= N; i++) nums[i] = scan.nextInt();
    }

    static void recFunc(int k, int value) {
        if(k == N + 1) {
            if(S == value) result += 1;
            return;
        }

        // include
        recFunc(k + 1, value + nums[k]);
        // Not Include
        recFunc(k + 1, value);
    }


    public static void main(String[] args) {
        input();

        recFunc(1, 0);
        if(S == 0) { // 공집합인 경우
            result -= 1;
        }
        System.out.println(result);
    }
}