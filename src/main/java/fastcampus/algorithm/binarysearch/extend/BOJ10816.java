package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 숫자 카드2(실버4) https://www.acmicpc.net/problem/10816
 *
 * N, M이 각각 50만개이기때문에 O(N^2)으로 문제 풀이시 시간초과 발생가능 => 이진탐색으로 풀이
 *
 * HashMap 사용해도 풀이 가능하지 않나 싶음
 */
public class BOJ10816 {

    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] own, card;

    static int lowerBound(int[] arr, int L, int R, int target) {
        while(L < R) {
            int mid = (L + R) / 2;
            if(target <= arr[mid]) {
               R = mid;
            } else {
               L = mid + 1;
            }
        }

        return R;
    }

    static int upperBound(int[] arr, int L, int R, int target) {
        while(L < R) {
            int mid = (L + R) / 2;
            if(target < arr[mid]) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        return R;
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        own = new int[N + 1]; // 보유 카드
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) own[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        card = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= M; i++) card[i] = Integer.parseInt(st.nextToken());
    }

    static void pro() {
        Arrays.sort(own, 1, N + 1);

        for(int i = 1; i <= M; i++) {
            int lowerBound = lowerBound(own, 1, N + 1, card[i]);
            int upperBound = upperBound(own, 1, N + 1, card[i]);
            int cnt = upperBound - lowerBound;
            sb.append(cnt).append(" ");
        }

        System.out.println(sb.toString());
    }

    static void proByHashMap() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Map<Integer, Integer> countMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            countMap.put(num, countMap.getOrDefault(num , 0) + 1);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i =1; i <= M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(countMap.getOrDefault(num, 0)).append(" ");
        }

        System.out.println(sb);
    }


    public static void main(String[] args) throws Exception {
        input();
        pro();

        //proByHashMap();
    }
}
