package fastcampus.algorithm.binarysearch.extend;

import java.io.*;
import java.util.StringTokenizer;


/**
 * 기타레슨(실버1) https://www.acmicpc.net/problem/2343
 *
 * L, R 범위 설정 때문에 시간 소모
 * L(min) 의 경우 한 강의의 최대 길이이다.
 * 한 블루레이에는 한 강의 이상 들어가야하기 때문에 아무리 블루레이의 용량을 줄여도 강의 길이 만큼 확보되어야 한다.
 * R(max) 는 모든 강의 길이의 합
 *
 * https://january-diary.tistory.com/entry/BOJ-2343-%EA%B8%B0%ED%83%80-%EB%A0%88%EC%8A%A8-JAVA
 */
public class BOJ2343 {
    // 강의 길이 총합 10^9
    // 최소 강의 길이(L) : 최대값
    // 최대 강의 길이(R) : 모든 강의 길이 합
    // 블루레이 크기(limit)를 정했을 때, 모든 강의를 블루레이 M개에 담을 수 있는가? (이때 최소값 구함)
    static StringBuilder sb = new StringBuilder();
    static int N, M, MIN, TOTAL;
    static int[] VIDEO;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    public static void pro() {
        int L = MIN;
        int R = TOTAL;
        int result = 0;
        while(L <= R) {
            int mid = (L + R) / 2;

            if(isPossible(mid)) {
                result = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(int limit) {
        int count = 0;
        int sum = 0;
        for(int i = 1; i <= N; i++) {
            if(sum + VIDEO[i] > limit) {
                count += 1;
                sum = 0;
            }

            sum += VIDEO[i];
        }

        if(sum > 0) count += 1;

        return count <= M; // 블루레이 M개에 다 담을 수 있는가
    }

    public static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 강의의 수
        M = inputProcessor.nextInt(); // 블루레이 개수

        VIDEO = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            VIDEO[i] = inputProcessor.nextInt();
            MIN = Math.max(MIN, VIDEO[i]);
            TOTAL += VIDEO[i];
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
