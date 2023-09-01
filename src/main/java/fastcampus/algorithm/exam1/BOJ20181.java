package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 꿈틀꿈틀 호석 애벌레 - 효율성
 * (골드2)
 *
 * N : 먹이의 개수, 최대 10만
 * K : 최소 만족도, 10^8
 * 각 먹이의 만족도는 0 ~ 10^8
 *
 * - 직접 풀지 못함
 * - dp, two pointer, brute force 방식 풀이 추가
 */
public class BOJ20181 {
    static StringBuilder sb = new StringBuilder();

    static int n, k;
    static int[] foods;

    static long ans;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        foods = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            foods[i] = Integer.parseInt(st.nextToken());
        }
    }

    static class Info {
        private int start;
        private int point;

        public Info(int start, int point) {
            this.start = start;
            this.point = point;
        }

        public int start() { return this.start; }
        public int point() { return this.point; }
    }

    static void dp() {
        List<Info>[] infos = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) infos[i] = new ArrayList<>();

        int sum = 0;
        for(int L = 1, R = 0; L <= n; L++) {
            while(sum < k && R + 1 <= n) {
                R += 1;
                sum += foods[R];
            }

            if(k <= sum) {
                infos[R].add(new Info(L, sum - k));
            }

            sum -= foods[L];
        }

        long[] dp = new long[n + 1]; // 타입 확인 잘하기
        for(int R = 1; R <= n; R++) {
            dp[R] = dp[R - 1];
            for(Info i : infos[R]) {
                dp[R] = Math.max(dp[R], dp[i.start() - 1] + i.point());
            }
        }

        System.out.println(dp[n]);
    }

    static void twoPointer() {
        long[] dp  = new long[n + 1];
        long maxPoint = 0;
        long sum = 0;
        for(int L = 1, R = 0; L <= n; L++) {
            // 최대 탈피 에너지
            maxPoint = Math.max(maxPoint, dp[L - 1]);

            while(sum < k && R + 1 <= n) {
                R += 1;
                sum += foods[R];
            }

            dp[R] = Math.max(dp[R], maxPoint + (sum - k));

            sum -= foods[L];
        }

        // 생략 주의
        long ans = 0L;
        for(long d : dp) ans = Math.max(ans, d);

        System.out.println(ans);
    }

    // brute force, 완전 탐색, 요소의 개수가 적은 20167번만 가능하고 범위가 클 경우 시간초과 발생
    static void dfs(int idx, long energy) {
        ans = Math.max(ans, energy);
        if(idx > n) return; // 종료 조건 없어 stack overflow 발생 확인

        long sum = 0;
        // 먹는 경우
        for(int r = idx; r <= n; r++) {
            sum += foods[r];
            if(k <= sum) {
                dfs(r + 1, energy + (sum - k));
                break;
            }
        }

        // 먹지 않는 경우
        dfs(idx + 1, energy);
    }

    static void pro() {
        //dp();

        //twoPointer();

        dfs(1, 0);
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
