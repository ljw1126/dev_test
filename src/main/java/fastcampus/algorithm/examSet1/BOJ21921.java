package fastcampus.algorithm.examSet1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 블로그(실버3)
 * https://www.acmicpc.net/problem/21921
 *
 * - 재풀이
 * - 슬라이디 ㅇ윈도우 기법
 */
public class BOJ21921 {
    static StringBuilder sb = new StringBuilder();

    static int N, X;

    static int[] data;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 지난 일 수
        X = Integer.parseInt(st.nextToken()); // X일 슬라이딩 윈도우 크기

        data = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) data[i] = Integer.parseInt(st.nextToken());

        int ans = 0;
        int cnt = 0;
        int sum = 0;

        for(int L = 1, R = 0; L <= N; L++) {
            while(R + 1 <= N && R - L + 1 != X) {
                R += 1;
                sum += data[R];
            }

            if(ans < sum) {
                ans = sum;
                cnt = 1;
            } else if(ans == sum) {
                cnt += 1;
            }

            sum -= data[L];
        }

        if(ans == 0) sb.append("SAD");
        else {
            sb.append(ans).append("\n");
            sb.append(cnt);
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
