package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * A -> B(실버2) https://www.acmicpc.net/problem/16953
 *
 * 거리 배열 생성시 '메모리 초과' 발생
 *
 * B -> A 방향으로 값을 구하는데
 * (실수) 2 로 나누어 떨어지지 않거나 , 끝자리가 1인 아닌 경우는 -1 출력
 */
public class BOJ16953 {
    static long A, B;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        br.close();
    }

    static void func() {
        boolean find = true;
        int cnt = 1;
        while(A != B) {
            if(B < A) {
                find = false;
                break;
            }

            String str = String.valueOf(B);
            if(str.charAt(str.length() - 1) != '1' && B % 2 != 0) {
                find = false;
                break;
            }


            if(B % 2 == 0) B /= 2;
            else B /= 10;

            cnt += 1;
        }

        System.out.println(!find ? -1 : cnt);
    }

    static void pro() {
        func();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
