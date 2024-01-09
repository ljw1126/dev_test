package fastcampus.algorithm.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20167 {
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] DATA;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        DATA = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) DATA[i] = Integer.parseInt(st.nextToken());
    }

    static void pro() {
        int ans = 0;

        int R;
        for(int L = 1; L <= N; L++) {
            R = L + 1;
            int eatingL = 0;
            int eatingR = 0;

            while(L <= N && eatingL < K) {
                eatingL += DATA[L];
                L += 1;
            }

            while(R <= N && eatingR < K) {
                eatingR += DATA[R];
                R += 1;
            }

            ans += (Math.max(eatingL, eatingR) - K);
            if(eatingL < eatingR) L = R - 1;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
