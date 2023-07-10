package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 기타리스트(실버1) https://www.acmicpc.net/problem/1495
 *
 * 재귀 함수를 쓰거나 2차원 배열에 의미 부여해서 푸는데 상당히 논리적이다
 * 하향식 풀이(top-down), O(2^n) -> 메모리제이션으로 풀어야 통과
 * https://steady-coding.tistory.com/172
 *
 * 상향식 풀이(bottom-up)
 * https://steady-coding.tistory.com/172
 *
 * DP[i] = Math.max(DP[i - 1] + V[i], DP[i - 1] - V[i])
 * //이때 각 값은 0 <= 값 <= M
 */
public class BOJ1495 {
    static StringBuilder sb = new StringBuilder();

    static int N, S, M;

    static int[] VOLUME;

    static long[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 곡 수
        S = Integer.parseInt(st.nextToken()); // 시작 볼륨
        M = Integer.parseInt(st.nextToken()); // 최대 볼류

        VOLUME = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            VOLUME[i] = Integer.parseInt(st.nextToken());
        }

        DP = new long[N + 1][M + 1]; // [곡 순서][볼륨]
    }

    static void executeByBottomUp() {
        DP[0][S] = 1; //[시작][초기볼륨]

        for(int i = 1; i <= N; i++) { // 곡 순서
            for(int v = 0; v <= M; v++) { // 볼륨
                if(DP[i - 1][v] > 0) {
                    int v1 = v + VOLUME[i];
                    int v2 = v - VOLUME[i];

                    if(v1 <= M) DP[i][v1] += DP[i - 1][v];
                    if(v2 >= 0) DP[i][v2] += DP[i - 1][v];
                }
            }
        }

        int result = -1;
        for(int v = 0; v <= M; v++) {
            if(DP[N][v] > 0) {
                result = Math.max(result, v);
            }
        }

        System.out.println(result);
    }

    // 방문한 적이 없다 != 볼륨 조절 할 수 없다
    // https://www.acmicpc.net/board/view/2481
    static long executeByTopDown(int idx, int vol) {
        // 조건, 초기항 처리
        if(vol < 0 || vol > M) return -1;
        if(idx == N) return vol;

        // 값이 있을 경우 리턴
        if(DP[idx][vol] != -2) return DP[idx][vol];

        // 값이 없을 경우 재귀 호출
        return DP[idx][vol] = Math.max(
                executeByTopDown(idx + 1, vol + VOLUME[idx + 1]),
                executeByTopDown(idx + 1, vol - VOLUME[idx + 1])
        );
    }


    public static void main(String[] args) throws Exception {
        input();
        //executeByBottomUp();

        for(int i = 0; i <= N; i++) Arrays.fill(DP[i], -2);

        System.out.println(executeByTopDown(0, S));
    }
}
