package its.codingtest.implementation;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 프로그래머스 - 문자열 압축(카카오2020)
 * https://school.programmers.co.kr/learn/courses/30/lessons/60057
 *
 * 직접 풀이
 * - 재귀 방식 사용하여 중복 되는 문자 카운팅
 * - 남겨진 부분과 남져지지 않는 부분에 대한 인덱스 처리가 모호했음
 * - 길이가 1일때 처리하기 위해 half = (문자열 길이 / 2) + 1 해줌
 *
 * 다른 사람 풀이봤을때
 * - 재귀에서 문자열 압축 결과를 반환하는 형태로 풀이 가능했음
 * - pre, post 문자열 구함
 */
public class p323 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static String S;

    private static void input() {
        S = inputProcessor.nextLine().trim();
    }

    private static void pro() {
        int half = S.length() / 2 + 1; // 문자열 길이가 1인 경우 동작하려면 + 1 해줘야 함
        int result = Integer.MAX_VALUE;
        StringBuilder str = new StringBuilder();
        for(int len = 1; len <= half; len++) {
            int idx = 0;
            while(idx < (S.length() - len)) {
                String target = S.substring(idx, idx + len);
                int repeated = 1 + rec(target, idx + len, len); // 1은 자기 자신

                String compress = repeated == 1 ? target : (repeated + target);
                str.append(compress);

                // 남겨진 부분과 남겨진 부분이 없는 경우가 모호 했네
                idx += (repeated == 1 ? len : repeated * len);
            }

            if(idx < S.length()) {
                str.append(S.substring(idx));
            }

            result = Math.min(result, str.length());
            str.setLength(0);
        }

        sb.append(result);
    }

    private static int rec(String t, int start, int len) {
        if(start + len > S.length()) return 0;

        String next = S.substring(start, start + len);
        if(t.equals(next)) {
            return 1 + rec(t, start + len, len);
        }

        return 0;
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
