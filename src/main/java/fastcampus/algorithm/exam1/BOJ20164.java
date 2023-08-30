package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
홀수 홀릭 호석 (골드5)
https://www.acmicpc.net/problem/20164

'완전 탐색 접근'을 통해서 모든 경우를 직접 하나 찾아야 함
9자리 수에 대해 재귀 함수 호출 처리
모든 경우의 수가 1억을 절대 넘지 않음 (감)

재귀 함수 조건
1. 종료 조건 -> x가 한 자리 수일 때
2. 아닌 경우 -> 분할 하여 탐색

실수*
- 3자리 이상인 경우, 3분할을 하는데 이때 수의 개수는 상관이 없었음

 */
public class BOJ20164 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int MIN_VALUE, MAX_VALUE;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        MIN_VALUE = Integer.MAX_VALUE;
        MAX_VALUE = Integer.MIN_VALUE;
    }

    static int countOdd(int num) {
        int result = 0;
        int value = num;
        while(value > 0) {
            int mod = value % 10;
            if(mod % 2 == 1) result += 1;

            value /= 10;
        }

        return result;
    }

    static int sum(String... numbers) {
        int result = 0;
        for(String n : numbers) {
            result += Integer.parseInt(n);
        }

        return result;
    }

    static void rec(int data, int oddCount) {
        // 숫자가 1자리 인 경우
        if(data <= 9) {
            oddCount += (data % 2 == 1 ? 1 : 0);
            MIN_VALUE = Math.min(MIN_VALUE, oddCount);
            MAX_VALUE = Math.max(MAX_VALUE, oddCount);
            return;
        }

        // 숫자가 2자리 인 경우
        if(data <= 99) {
            int v10 = data / 10;
            int v1 = data % 10;
            oddCount += (v10 % 2 == 1 ? 1 : 0);
            oddCount += (v1 % 2 == 1 ? 1 : 0);

            rec(v10 + v1, oddCount);
            return;
        }

        // 숫자가 3자리 이상인 경우, 임의 위치에서 끊어서 3개의 수로 분할
        if(data >= 100) {
            oddCount += countOdd(data);
            String num = String.valueOf(data);
            for(int i = 0; i < num.length() - 2; i++) {
                for(int j = i + 1; j < num.length() - 1; j++) {
                    String v1 = num.substring(0, i + 1);
                    String v2 = num.substring(i + 1, j + 1);
                    String v3 = num.substring(j + 1);

                    rec(sum(v1, v2, v3), oddCount);
                }
            }
        }
    }

    static void pro() {
        rec(N, 0);

        sb.append(MIN_VALUE).append(" ").append(MAX_VALUE);

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
