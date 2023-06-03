package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 예산(실버3) https://www.acmicpc.net/problem/2512
 *
 * 매개변수 탐색 문제*
 *
 * 상한액 money 를 정하고, 지방 예산 요청 처리시 국가예산 총액을 만족하는가? 이때 최대 상한액인가?
 */
public class BOJ2512 {
    static int N, M;
    static int[] A;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 총 예산
    }

    static boolean isValid(int limit) {
        int total = 0;
        for(int i : A) {
            total += Math.min(i, limit);
        }

        return total <= M;
    }

    static void pro() {
        Arrays.sort(A);

        int result = 0;
        int L = 0, R = A[N-1]; // 두번째 예시의 경우 지방 요청 금액이 예산보다 낮음
        while(L <= R) {
            int limit = (L + R) / 2;
            if(isValid(limit)) {
                L = limit + 1;
                result = limit;
            } else {
                R = limit - 1;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
