package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 어두운 굴다리 (실버4) https://www.acmicpc.net/problem/17266
 *
 * 최소 높이 구하는 문제 -> 조건 만족시 R을 줄여줌
 */
public class BOJ17266 {

    static int N, M;
    static int[] X;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        X = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean isValidHeight(int H) {
        int point = 0;
        for(int x : X) {
            if(x - point <= H) {
                point = x + H;
            } else {
                return false;
            }
        }

        return point >= N;
    }
    static void pro() {
        int L = 0, R = N, ans = N;
        while(L <= R) {
            int mid = (L + R) / 2;

            if(isValidHeight(mid)) {
                R = mid - 1;
                ans = mid;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
