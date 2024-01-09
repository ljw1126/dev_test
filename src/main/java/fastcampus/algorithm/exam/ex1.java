package fastcampus.algorithm.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ex1 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int MIN_CNT, MAX_CNT;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        MIN_CNT = Integer.MAX_VALUE;
        MAX_CNT = 0;
    }

    static int getOddCnt(int x) {
        int result = 0;
        while(x > 0) {
            int digit = x % 10;
            if(digit % 2 == 1) result += 1;

            x /= 10;
        }

        return result;
    }

    static void rec(int num, int totalCount) {
        if (num <= 9) { // 한자리 수인 경우
            MIN_CNT = Math.min(MIN_CNT, totalCount);
            MAX_CNT = Math.max(MAX_CNT, totalCount);
            return;
        }

        if(num <= 99) { // 두자리 수
            int next = (num / 10) + (num % 10);
            rec(next, totalCount + getOddCnt(next));
        }

        // 세 자리 수 이상인 경우, 임의로 세 개 끊어서 처리
        String s = Integer.toString(num);
        for(int i = 0; i <= s.length() - 3; i++) { // 부호 실수
            for(int j = i + 1; j <= s.length() - 2; j++) {
                String x1 = s.substring(0, i + 1);
                String x2 = s.substring(i + 1, j + 1);
                String x3 = s.substring(j + 1, s.length());

                int next = Integer.parseInt(x1) + Integer.parseInt(x2) + Integer.parseInt(x3);
                rec(next, totalCount + getOddCnt(next));
            }
        }
    }

    static void pro() {
        rec(N, getOddCnt(N));

        sb.append(MIN_CNT).append(" ").append(MAX_CNT);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
