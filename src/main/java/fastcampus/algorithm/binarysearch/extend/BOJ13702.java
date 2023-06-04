package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이상한 술집(실버3) https://www.acmicpc.net/problem/13702
 *
 * 최대값을 찾으려면
 * 조건 성립시 L = mid + 1; 해야함 (최소값은 반대)
 *
 * long타입으로 안해서 다 틀림;;
 */
public class BOJ13702 {

    static int N, K;
    static long[] drink;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        drink = new long[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            drink[i] = Long.parseLong(st.nextToken());
        }
    }

    static boolean isValid(int ml) {
        long cnt = 0;
        for(long d : drink) {
            cnt += (d / ml);
        }

        return cnt >= K;
    }

    static void pro() {
        long ans = 0;
        long L = 0, R = Integer.MAX_VALUE;
        while(L <= R) {
            long mid = (L + R) / 2;

            if(isValid((int)mid)) { // 최대값을 찾을 경우
                L = mid + 1;
                ans = mid;
            } else {
                R = mid - 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
