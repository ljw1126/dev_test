package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 용돈관리 (실버2) https://www.acmicpc.net/problem/6236
 *
 * L 의 범위가 틀려서 시간 소요
 * https://blog.potados.com/ps/boj-6236-money/
 */
public class BOJ6236 {

    static int N, M, MIN;
    static int[] A;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 일정 N일 동안 사용할 금액 정함
        M = Integer.parseInt(st.nextToken()); // M번만 통장에서 돈을 빼서 쓰기로 함

        A = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            MIN = Math.max(MIN, A[i]); // 요걸 지정하지 않아서.. K가 아주 작아서 어떤날에 써양하는 금액보다 작으면, 그날에 돈을 쓸 수 없다. 그러므로 K 시작 범위는 주어진 날 별 필요한 돈 중 max값 취함
        }
    }

    static boolean isValid(int limit) {
        int wallet = limit;
        int cnt = 1;
        for(int a : A) {
            if(wallet - a < 0) {
                cnt += 1;
                wallet = limit;
            }

            wallet -= a;
        }

        return cnt <= M;
    }

    static void pro() {
        // 금액 K 한도를 정했을 떄 N일 동안 M번 K원 만큼 뽑으며 지낼 수 있는가
        int K = 0;
        int L = MIN, R = (int)1e9;
        while(L <= R) {
            int limit = (L + R) / 2;

            if(isValid(limit)) {
                R = limit - 1;
                K = limit;
            } else {
                L = limit + 1;
            }
        }

        System.out.println(K);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
