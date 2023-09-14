package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 폰 호석만 (실버2)
 * https://www.acmicpc.net/problem/21275
 *
 * - 직접 풀이 못함
 * Xa = Yb = X10     // X와 Y가 주어질 때 a와 b를 무슨 진법으로 해야 X가 같은 값이 나오냐
 * 주의
 * 1) 변환시 2^63 (long) 넘어가지 않는가?
 * 2) 등장하는 숫자가 진법(base)으로 변환 가능한가?
 */
public class BOJ21275 {
    static StringBuilder sb = new StringBuilder();

    static String[] data = new String[2];

    static long MAX_VALUE = Long.MAX_VALUE;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        data[0] = st.nextToken();
        data[1] = st.nextToken();
    }

    static int convert(char x) {
        if('0' <= x && x <= '9') return x - '0';

        return x - 'a' + 10;
    }

    static long convertTo(String str, int base) {
        long result = 0L;
        for(char c : str.toCharArray()) {
            if(convert(c) >= base) return -1;

            // Long의 MAX 넘어갈 경우 Overflow가 발생하는데 이를 비교 위해 이항 시킴
            // 좌변이 넘어갈 경우 overflow 발생하니 아래와 같이 이항을 통해 처리
            if(result > (MAX_VALUE - convert(c)) / base) return - 1;

            result *= base;
            result += convert(c);

            // result = result * base + convert(c);
        }

        return result;
    }

    static void pro() {
        long ans = -1;
        long ansA = 0;
        long ansB = 0;
        for(int i = 2; i <= 36; i++) {
            for(int j = 2; j <= 36; j++) {
                if(i == j) continue;

                long v1 = convertTo(data[0], i); // data[0]을 i진법으로 변환
                if(v1 == -1) continue;

                long v2 = convertTo(data[1], j); // data[1]을 j진접으로 변환
                if(v2 == -1) continue;

                if(v1 != v2) continue;

                if(ans == -1) {
                    ans = v1;
                    ansA = i;
                    ansB = j;
                } else {
                    sb.append("Multiple");
                    return;
                }
            }
        }

        if(ans == -1) sb.append("Impossible");
        else sb.append(ans).append(" ").append(ansA).append(" ").append(ansB);
    }

    public static void main(String[] args) throws Exception {
        input();

        pro();

        System.out.println(sb.toString());
    }
}
