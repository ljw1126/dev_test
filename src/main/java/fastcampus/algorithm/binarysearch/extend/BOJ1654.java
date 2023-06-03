package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 랜선자르기(실버3) https://www.acmicpc.net/problem/1654
 *
 * 매개변수 탐색*
 * 길이가 H일때, K개의 랜선에서 N개의 랜선을 만들 수 있는가? 이떄 최대 길이 H인가?
 *
 * 주의)
 * 각 랜선의 길이가 최대 10억이라서 long 처리해야 됨
 */
public class BOJ1654 {

    static int K, N;

    static long[] lines;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lines = new long[K];
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i] = Long.parseLong(st.nextToken());
        }
    }

    static boolean isValidHeight(int H) {
        long total = 0;

        for(long l : lines) {
            long cnt = l / H;
            total += cnt;
        }

        return N <= total;
    }
    static void pro() {
        Arrays.sort(lines);

        long result = 0;
        long L = 0, R = Integer.MAX_VALUE;
        while(L <= R) {
            long height = (L + R) / 2;
            if(isValidHeight((int)height)) {
                L = height + 1;
                result = height;
            } else {
                R = height - 1;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
