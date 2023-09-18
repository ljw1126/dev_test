package fastcampus.algorithm.examSet1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ21919 {
    static StringBuilder sb = new StringBuilder();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int A = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] data = new int[A + 1];
        for(int i = 1; i <= A; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        // 소수를 뽑아 낸다 (*없으면 -1 출력)
        // 유클리드 호제법을 활용하여 최소 공배수를 구한다
        // 최소 공배수 = A * B / gcd(A, B)    -- gcd 최대 공약수
        long ans = 1; // 1을 생각하지 못함
        for(int i = 1; i <= A; i++) {
            long a = data[i];
            if(isPrimeNumber(a)) {
                ans = lcm(ans, a);
            }
        }

        if(ans == 1) sb.append(-1);
        else sb.append(ans);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean isPrimeNumber(long n) {
        // 2부터 n의 제곱근까지 확인하면서 나누어 떨어지면 소수가 아니다
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }

    // 최소 공배수(LCM)
    static long lcm(long a, long b) {
        //return a * b / gcd(a, b);

        // 오버 플로우 방지
        return a / gcd(a, b) * b;
    }

    // 최소 공약수(GCD)
    static long gcd(long a, long b) {
        if(b == 0L) return a;

        return gcd(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
