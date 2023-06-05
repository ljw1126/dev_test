package fastcampus.algorithm.twopoint;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부분합(골드4) https://www.acmicpc.net/problem/1806
 *
 * 투 포인터 연산 O(N) 소모
 * 총 시간 복잡도 : O(N)
 */
public class BOJ1806 {
    static StringBuilder sb = new StringBuilder();

    static int N, S;
    static int[] NUMBERS;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        NUMBERS = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            NUMBERS[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        int L = 1, R = 1, ans = 100000;
        int sum = 0;

        while(L <= N) {
            while(R <= N && sum < S) {
                sum += NUMBERS[R];
                R += 1;
            }

            if(sum >= S) {
                ans = Math.min(ans, R - L);
            }

            sum -= NUMBERS[L];
            L += 1;
        }

        if(ans == 100000) ans = 0;

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
