package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 용돈관리 (실버2) https://www.acmicpc.net/problem/6236
 *
 * L 의 범위가 틀려서 시간 소요
 * https://blog.potados.com/ps/boj-6236-money/
 *
 * 최대치
 * 10만일 동안 1번 인출 가능하고, 매일 만원씩 필요한 경우 10^9 필요 (int 범위내)
 * 반례 1
 * 2 2
 * 500
 * 501
 *
 * 답 501
 *
 * 반례 2
 * 5 5
 * 1
 * 1
 * 1
 * 1
 * 100
 *
 * 답 100
 */
public class BOJ6236 {

    static StringBuilder sb = new StringBuilder();
    static int N, M, MIN, MAX;
    static int[] SCHEDULE;

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 일수
        M = inputProcessor.nextInt(); // 최대 인출 횟수

        SCHEDULE = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            SCHEDULE[i] = inputProcessor.nextInt();
            MIN = Math.max(MIN, SCHEDULE[i]);
            MAX += SCHEDULE[i];
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void pro() {
        int L = MIN;
        int R = MAX;
        int result = 0;
        while(L <= R) {
            int k = (L + R) / 2;

            if(valid(k)) {
                result = k;
                R = k - 1;
            } else {
                L = k + 1;
            }
        }

        sb.append(result);
    }

    private static boolean valid(int limit) {
        int cnt = 1;
        int wallet = limit;

        for(int i = 1; i <= N; i++) {
            if(wallet - SCHEDULE[i] < 0) {
                wallet = limit; // 남은 금액을 통장에 넣고 다시 K원을 인출 한다
                cnt += 1;
            }

            wallet -= SCHEDULE[i];
        }

        return cnt <= M;
    }

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
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
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
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
