package fastcampus.algorithm.bruteforce.personal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 리모컨(골5)
 * <p>
 * - 예외 케이스로 인해 질문 게시판 참고
 * - dfs 직접 풀이
 * - 단순히 100만번 * 6 계산하는 방법으로도 풀 수 있었음
 */
public class BOJ1107 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MAX_CHANNEL = 999_999;
    private static final int MAX_DIGIT = 6;

    private static int N, M, RESULT;
    private static boolean[] BROKEN;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = Integer.parseInt(inputProcessor.nextLine().trim()); // 이동하려고 하는 채널
        M = inputProcessor.nextInt(); // 고장난 버튼 수

        BROKEN = new boolean[10]; // 0 ~ 9;
        for (int i = 1; i <= M; i++) {
            int no = inputProcessor.nextInt();
            BROKEN[no] = true;
        }

        RESULT = Integer.MAX_VALUE;
    }

    //참고 : https://velog.io/@khg5087/%EB%B0%B1%EC%A4%80-1107%EB%B2%88-%EB%A6%AC%EB%AA%A8%EC%BB%A8-%EC%9E%90%EB%B0%94
    private static void pro() { // 메모리 : 60Mb, 시간 : 30ms ( 5배 메모리 절약)
        RESULT = Math.abs(N - 100);
        for (int i = 0; i <= MAX_CHANNEL; i++) {
            String channel = String.valueOf(i);

            boolean isPossible = true;
            for (int j = 0; j < channel.length(); j++) {
                if (BROKEN[channel.charAt(j) - '0']) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                RESULT = Math.min(RESULT, Math.abs(N - i) + channel.length());
            }
        }

        sb.append(RESULT);
    }

    private static void dfs() { // 메모리 : 300Mb, 시간 : 600ms
        if (N == 100) {
            RESULT = 0;
        } else if (M == 10) {
            RESULT = Math.abs(100 - N);
        } else {
            pushButton(0, "100");
        }
        sb.append(RESULT);
    }

    private static void pushButton(int cnt, String value) {
        if (cnt > MAX_DIGIT) return;

        int ch = Integer.parseInt(value);
        if (ch >= MAX_CHANNEL) return;

        // 현재 위치에서 +, - 이동하는 경우
        if (ch == N) {
            RESULT = Math.min(RESULT, cnt);
        } else if (ch > N) {
            RESULT = Math.min(RESULT, cnt + (ch - N));
        } else {
            RESULT = Math.min(RESULT, cnt + (N - ch));
        }

        for (int i = 0; i <= 9; i++) {
            if (BROKEN[i]) continue;

            String no = String.valueOf(i);
            String next = cnt == 0 ? no : value.concat(no);
            pushButton(cnt + 1, next);
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

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
