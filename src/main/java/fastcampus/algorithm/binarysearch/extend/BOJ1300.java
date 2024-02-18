package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * K번째 수(골드2) https://www.acmicpc.net/problem/1300
 *
 * n * n 2차원 배열에 숫자를 1차원 배열도 나열했을 때 k 번재 숫자 구하는 문제
 *
 * n 이 최대 10^5 이고 이걸 1차원 배열로 옮기면 10억이 되서 메모리 초과 및 시간 초과 발생가능
 * 참고. https://steady-coding.tistory.com/20
 */
public class BOJ1300 {

    // mid 일 때, 임의 수 S보다 작은 숫자가 mid - 1개를 만족하는가 (이대 최소가 되는 mid값은)
    static StringBuilder sb = new StringBuilder();
    static int N, K;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    public static void pro() {
        int L = 1;
        int R = 1000000000;
        int result = 0;
        while(L <= R) {
            int mid = (L + R) / 2;

            if(isValid(mid)) {
                result = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        sb.append(result);
    }

    private static boolean isValid(int mid) {
        int count = 0;
        for(int i = 1; i <= N; i++) {
            count += Math.min(N, (mid / i));
        }

        return count >= K;
    }


    public static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        K = inputProcessor.nextInt();
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
