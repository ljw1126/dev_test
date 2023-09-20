package basic.math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 소수 구하기 (실버3)
 *
 * https://www.acmicpc.net/problem/1929
 *
 * - N 이 최대 100만이기 때문에 2 ~ N - 1까지 판별하게 될 경우 시간초과 발생가능
 * - 에라토스테네스의 체 알고리즘 사용하면 풀이 O(Nlog(logN))
 */
public class BOJ1929 {
    static StringBuilder sb = new StringBuilder();

    static void process() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // M이상
        int N = Integer.parseInt(st.nextToken()); // N이하

        int[] data = new int[N + 1];
        for(int i = 1; i <= N; i++) data[i] = i;

        for(int i = 2; i <= N; i++) {
            if(data[i] == 0) continue;

            for(int j = i + i; j <= N; j += i) {
                data[j] = 0;
            }
        }

        for(int i = M; i <= N; i++) {
            if(i == 1) continue;

            if(data[i] != 0) sb.append(i).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        process();
    }
}
