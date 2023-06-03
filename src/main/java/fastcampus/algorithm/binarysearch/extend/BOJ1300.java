package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * K번째 수(골드2) https://www.acmicpc.net/problem/1300
 *
 * n * n 2차원 배열에 숫자를 1차원 배열도 나열했을 때 k 번재 숫자 구하는 문제
 *
 * n 이 최대 10^5 이고 이걸 1차원 배열로 옮기면 10억이 되서 메모리 초과 및 시간 초과 발생가능
 * 참고. https://steady-coding.tistory.com/20
 */
public class BOJ1300 {

    static int N, K;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
    }

    static void pro() {
        long result = 0L;
        long L = 1, R = K;
        while(L <= R) {
            long mid = (L + R) / 2;

            long totalCnt = 0;
            for(int i = 1; i <= N; i++) {
                totalCnt += Math.min(mid / i, N);
            }

            if(totalCnt >= K) {
                R = mid - 1;
                result = mid;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
