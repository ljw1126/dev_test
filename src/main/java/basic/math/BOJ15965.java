package basic.math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * K번째 소수 (실버2)
 * https://www.acmicpc.net/problem/15965
 *
 * - 범위를 실수
 * - 최대 500000번째 소수를 구해야 했음(최대치, 배열 크기, 천만 넘지 않음)
 *
 */
public class BOJ15965 {
    static StringBuilder sb = new StringBuilder();

    static int MAX = 7368791;

    static void process() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        boolean[] data = new boolean[MAX + 1];
        Arrays.fill(data, true);

        for(int i = 2; i <= MAX; i++) {
            if(!data[i]) continue;

            for(int j = i + i; j <= MAX; j += i) {
                data[j] = false;
            }
        }

        int cnt = 0;
        for(int i = 2; i <= MAX; i++) {
            if(data[i] && ++cnt == K) {
                sb.append(i);
                break;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void pro() {
        int[] data = new int[10000001];
        Arrays.fill(data, -1);
        for(int i = 2; i <= 10000000; i++) {
            if(data[i] == 0) continue;

            for(int j = i + i; j <= 10000000; j += i) {
                data[j] = 0;
            }
        }

        List<Integer> prime = new ArrayList<>();
        for(int i = 2; i <= 10000000; i++) {
            if(data[i] != 0) prime.add(i);
        }

        System.out.println(prime.get(500000)); // 7368791
    }

    private static boolean isPrime(int n) {
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }

        return true;
    }


    public static void main(String[] args) throws Exception {
        pro();
        //process();
    }
}
