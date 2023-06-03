package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나무 자르기 (실버2)
 * https://www.acmicpc.net/problem/2805
 *
 * 나무의 개수 N : 최대 100만 (10^6)
 * 필요한 나무 길이 M : 최대 20억 (2 * 10^9)
 * 높이 : 0 이상 10억이하
 *
 * 잘린 나무의 길이 합 <= 나무 높이의 총합
 * 100만 * 10억 = 10^15
 * 계산 과정 중에 변수 타입은 long**으로 해야 함 (int로 할 경우 overflow 발생 가능)
 *
 * O(N^2)으로 풀 경우 시간초과 발생 가능
 * O(NlogN)으로 풀 경우 2천만 정도 되므로 풀이 가능
 * 1. H 구해서 M을 만족하는가 O(N)
 * 2. H 구하는 것을 이진탐색으로 구하니 O(logN)
 * 고로 100만 * log20억 = 3100만 정도 됨
 *
 * 문제)
 * 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최대값을 구하시오.
 * => 어떤 높이 H로 잘랐을때, 원하는 길이 M만큼을 얻을 수 있는가? yes/no (매개변수 탐색, paramatric search 문제 풀이)
 *
 */
public class BOJ2805 {

    static int N, M;

    static int[] tree;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) tree[i] = Integer.parseInt(st.nextToken());
    }

    static boolean isValidHeight(int H) {
        long sum = 0L; // int 선언시 overflow 발생 가능
        for(int t : tree) {
            if(t > H) sum += (t - H);
        }

        return sum >= M;
    }

    static void pro() {
        int result = 0;
        int L = 0, R = 2000000000;
        while(L <= R) {
            int H = (L + R) / 2;

            if(isValidHeight(H)) {
                L = H + 1;
                result = Math.max(result, H);
            } else {
                R = H - 1;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
