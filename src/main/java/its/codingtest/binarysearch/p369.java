package its.codingtest.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 - 공유기 설치(골4)
 * https://www.acmicpc.net/problem/2110
 *
 * - 다 풀었는데, 설치하는 공유기 개수가 c개 이상 조건식을 실수함
 * - 정렬 : O(NlogN), 이진탐색 : O(logN), 그리드 : O(N)
 * - 거리 d를 두고 공유기를 설치했을때 "c개 이상" 설치가능한가 ? 거리 d의 최대값을 구하라
 *  - d = 4를 하게 될 경우 공유기 2개밖에 설치하지 못함
 *  - d = 2를 하게 될 경우 공유기 3개 설치 가능
 *  - d = 3을 하게 될 경우 공유기 3개 설치 가능
 */
public class p369 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, C;
    private static int[] DATA;

    private static void input() {
        N = inputProcessor.nextInt(); // 집의 개수
        C = inputProcessor.nextInt();

        DATA = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            DATA[i] = inputProcessor.nextInt();
        }
    }

    private static void pro() {
        Arrays.sort(DATA, 1, N + 1);

        int result = 0;
        int L = 1;
        int R = DATA[N] + 1;

        // 거리를 h로 했을대 C개 공유기를 설치 할 수 있는가
        while(L <= R) {
            int d = (L + R) / 2;

            if(isPossible(d, C)) {
                result = d;
                L = d + 1;
            } else {
                R = d - 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(int d, int c) {
        int count = 1;
        int prev = DATA[1];
        for(int i = 2; i <= N; i++) {
            int cur = DATA[i];
            int diff = cur - prev;

            if(diff < d) continue;

            count += 1;
            prev = cur;
        }

        // c개 이상의 공유기를 설치할 수 있는 거리 중 최대 d를 구해야함
        return count >= c;
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
