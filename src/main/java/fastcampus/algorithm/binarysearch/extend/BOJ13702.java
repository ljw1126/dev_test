package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 이상한 술집(실버3) https://www.acmicpc.net/problem/13702
 *
 * 최대값을 찾으려면
 * 조건 성립시 L = mid + 1; 해야함 (최소값은 반대)
 *
 * long 타입으로 안해서 다 틀림;;
 *
 * 240216
 * L, R 범위 틀림 ..
 *
 * 반례
 * 1 1
 * 2100000000
 *
 * 답 2100000000
 */
public class BOJ13702 {

    // 주전자 개수 (N, 최대 1만), 친구 수 (K, 최대 100만)
    // 주전자 개수 최대 1만이고, 친구가 1만일때(N<=K) 막걸리가 각각 21억 ml씩 들어 있다면, 21만 ml씩 나눠 먹을 수 있다
    // 막걸리 용량 x ml를 햇을때, K명 친구에게 모두 나눠줄 수 있는가? (이때 최대가 되는 ml를 구해라)
    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static long[] DRINK;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    public static void pro() {
        long L = 0L;
        long R = Integer.MAX_VALUE;
        long result = 0L;
        while(L <= R) {
            long mid = (L + R) / 2;

            if(isPossible(mid)) {
                result = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(long limit) {
        int count = 0;
        for(int i = 1; i <= N; i++) {
            count += (DRINK[i] / limit);
        }

        return count >= K;
    }


    public static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        K = inputProcessor.nextInt();

        DRINK = new long[N + 1];
        for(int i = 1; i <= N; i++) {
            DRINK[i] = inputProcessor.nextLong();
        }
    }

    public static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
