package fastcampus.algorithm.exam3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 빌런호석(골드5)
 *
 * - 직접 풀이 못함(문제 이해 못함)
 * - 1 ~ N층이 있고, K 층이 주어질 때 LED변환(1 .. N , K) <= P 이하면 카운트 증가
 * - O(N * K * 6) = O (NK) , 1 ~ N 까지 K 자리수에 대해 6번씩 비교 후 카운팅
 */
public class BOJ22251 {
    static StringBuilder sb = new StringBuilder();

    static int N, K, P, X;

    // 0 ~ 9
    static int[][] display = {
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 0, 0, 1, 0},
            {1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1, 0},
            {1, 1, 0, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 1, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1},
    };

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 1 ~ N층
        K = Integer.parseInt(st.nextToken()); // 디스플레이 자리수
        P = Integer.parseInt(st.nextToken()); // 최대 P개 반전 시킬려 함
        X = Integer.parseInt(st.nextToken()); // 실제로 멈춘 X층
    }

    static int toggle(int a, int b) {
        int result = 0;
        for(int i = 0; i <= 6; i++) {
            if(display[a][i] != display[b][i]) result += 1;
        }

        return result;
    }

    static int diff(int a, int b) {
        int result = 0;

        for(int i = 1; i <= K; i++) { // K자리수
            result += toggle(a % 10, b % 10);
            a /= 10;
            b /= 10;
        }

        return result;
    }

    static void pro() {
        int ans = 0;

        for(int i = 1; i <= N; i++) { // 1 ~ N 까지 층이 있을때
            if(i == X) continue; // X층에 멈춰있기 때문에 continue
            if(diff(i, X) <= P) {
                System.out.println(i + "," + X);
                ans += 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
