package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 꿈틀꿈틀 호석 애벌레
 *
 * - 실버 1
 * - DP와 투 포인터 (그외 재귀 함수를 활용한 완전탐색으로도 풀이 가능)
 *
 * DP와 투포인터로 풀 경우
 * - 시간 복잡도 O(N)
 *
 * 완전 탐색으로 풀 경우 (BOJ20181 참고)
 * - 시간 복잡도 O(N!)
 *
 * - 골드 2의 경우 범위가 크게 증가하기 때문에 int 대신 long 타입을 사용해야 함
 * - 손으로 그려보자
 */
public class BOJ20167 {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static long K;
    static int[] foods;

    static PrintWriter pw = new PrintWriter(System.out);

    static class Info {
        private int start;
        private long point;

        public Info(int start, long point) {
            this.start = start;
            this.point = point;
        }

        public int start() {
            return this.start;
        }

        public long point() {
            return this.point;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 먹이 개수
        K = Long.parseLong(st.nextToken()); // 최소 만족도

        foods = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            foods[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() throws Exception {
        List<Info>[] infos = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) infos[i] = new ArrayList<>();

        long acc = 0;
        for(int L = 1, R = 0; L <= N; L++) { // 초기화 실수 가능
            while(acc < K && R + 1 <= N) {
                R += 1;
                acc += foods[R];
            }

            if(K <= acc) { // 기준치 만족도 이상일 경우
                infos[R].add(new Info(L, acc - K));
            }

            acc -= foods[L];
        }

        long[] dp = new long[N + 1];
        for(int R = 1; R <= N; R++) { // isEmpty일대 continue 빼니 다 맞음
            dp[R] = dp[R - 1]; // 이걸 빼먹음..
            for(Info info : infos[R]) {
                dp[R] = Math.max(dp[R], dp[info.start() - 1] + info.point());
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(dp[N] + "");
        bw.flush();
        bw.close();
    }

    // 투포인터로 누적합 구하는 느낌
    static void twoPointer() {
        long[] _dp = new long[N + 1];

        int R = 0;
        long maxleft = 0;
        int sum = 0;
        for(int L = 1; L <= N; L++) {
            // 이전까지 얻을 수 있는 최대 탈피 에너지
            maxleft = Math.max(maxleft, _dp[L - 1]);

            while(sum < K && R + 1 <= N) {
                R += 1;
                sum += foods[R];
            }

            if(sum >= K) {
                _dp[R] = Math.max(_dp[R], maxleft + (sum - K));
            }

            sum -= foods[L];
        }

        // 요거 때문에 틀림
        long ans = 0;
        for(int i = 1; i<= N; i++) ans = Math.max(ans, _dp[i]);

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        //pro();
        twoPointer();
    }
}
