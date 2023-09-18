package fastcampus.algorithm.examSet1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 서로서 평균(실버4)
 * https://www.acmicpc.net/problem/21920
 *
 * - 서로소란? 1과 자기 자신으로만 나눠지는 수
 * - 유클리드 호제법 사용은 맞았는데 최대치에서 틀림
 */
public class BOJ21920 {
    static StringBuilder sb = new StringBuilder();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        long[] input = new long[N + 1];
        for(int i = 1; i <= N; i++) {
            input[i] = Long.parseLong(st.nextToken());
        }

        int A = Integer.parseInt(br.readLine());

        // A가 4일 때 5와 7이 서로소이네
        // 유클리드 호제법을 사용하여 최대 공약수가 1인 경우, 공통이 1과 자기 자신이므로 서로소다
        int cnt = 0;
        long sum = 0; // 최대치 실수..
        for(int i = 1; i <= N; i++) {
            if(gcd(A, input[i]) == 1) {
                cnt += 1;
                sum += input[i];
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.format("%.6f", (double) sum/cnt));
        bw.flush();
        bw.close();
    }

    static boolean isPrimeNumber(int n, int v) {
        for(int i = 2; i <= Math.sqrt(v); i++) {
            if(n % i == 0) return false;
        }

        return true;
    }


    static long gcd(int a, int b) {
        if(b == 0) return a;

        return gcd(b, a % b);
    }

    static long gcd(long x, long y) {
        while(y != 0) {
            long temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
