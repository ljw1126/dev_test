package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2670 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static double[] DATA, DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DATA = new double[N];
        for(int i = 0; i < N; i++) {
            DATA[i] = Double.parseDouble(br.readLine());
        }

        DP = new double[N];
    }

    static void pro() {
       DP[0] = DATA[0];
       for(int i = 1; i < N; i++) {
            DP[i] = Math.max(DATA[i], DP[i - 1] * DATA[i]);
       }

       System.out.printf("%.3f", Arrays.stream(DP).max().getAsDouble());
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
