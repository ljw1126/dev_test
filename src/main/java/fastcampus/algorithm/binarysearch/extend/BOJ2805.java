package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 나무 자르기 (실버2)
 * https://www.acmicpc.net/problem/2805
 *
 * 나무의 개수 N : 최대 100만 (10^6)
 * 필요한 나무 길이 M : 최대 20억 (2 * 10^9)
 * 높이 : 0 이상 10억이하
 *
 * 잘린 나무의 길이 합 <= 나무 높이의 총합
 * 100만 * 10억 = 10^15
 * 계산 과정 중에 변수 타입은 long**으로 해야 함 (int로 할 경우 overflow 발생 가능)
 *
 * O(N^2)으로 풀 경우 시간초과 발생 가능
 * O(NlogN)으로 풀 경우 2천만 정도 되므로 풀이 가능
 * 1. H 구해서 M을 만족하는가 O(N)
 * 2. H 구하는 것을 이진탐색으로 구하니 O(logN)
 * 고로 100만 * log20억 = 3100만 정도 됨
 *
 * 문제)
 * 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최대값을 구하시오.
 * => 어떤 높이 H로 잘랐을때, 원하는 길이 M만큼을 얻을 수 있는가? yes/no (매개변수 탐색, paramatric search 문제 풀이)
 *
 */
public class BOJ2805 {

    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] TREE;

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 나무의 수
        M = inputProcessor.nextInt(); // 필요한 나무의 길이

        TREE = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            TREE[i] = inputProcessor.nextInt();
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void pro() {
        int L = 0;
        int R = 2000000001;

        int result = -1;
        while(L <= R) {
            int mid = (L + R) / 2;

            if(validHeight(mid)) {
                result = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        sb.append(result);
    }

    private static boolean validHeight(int mid) {
        long sum = 0L;

        for(int i = 1; i <= N; i++) {
            if(TREE[i] > mid) {
                sum += (TREE[i] - mid);
            }
        }

        return sum >= M;
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
