package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 기타레슨(실버1) https://www.acmicpc.net/problem/2343
 *
 * L, R 범위 설정 때문에 시간 소모
 * L(min) 의 경우 한 강의의 최대 길이이다.
 * 한 블루레이에는 한 강의 이상 들어가야하기 때문에 아무리 블루레이의 용량을 줄여도 강의 길이 만큼 확보되어야 한다.
 * R(max) 는 모든 강의 길이의 합
 *
 * https://january-diary.tistory.com/entry/BOJ-2343-%EA%B8%B0%ED%83%80-%EB%A0%88%EC%8A%A8-JAVA
 */
public class BOJ2343 {
    static int N, M, L, R;
    static int[] A;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            R += A[i];
            L = Math.max(L, A[i]);
        }
    }

    static boolean isValidVideoSize(int[] arr, int size) {
        int cnt = 0; // 1로 하게 될 경우 마지막 조건문은 삭제 가능(=하나에 전체 강의 담는 경우)
        int videoLength = 0;
        for(int a : arr) {
            if(videoLength + a > size) {
                cnt += 1;
                videoLength = 0;
            }

            videoLength += a;
        }

        if (videoLength > 0) cnt += 1;

        return cnt <= M;
    }

    static void pro() {
        int result = 0;
        while(L <= R) {
            int size = (L + R) / 2;
            if(isValidVideoSize(A, size)) {
                R = size - 1;
                result = size;
            } else {
                L = size + 1;
            }
        }

        System.out.println(result);
    }


    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
