package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 기타리스트(실버1) https://www.acmicpc.net/problem/1495
 *
 * 재귀 함수를 쓰거나 2차원 배열에 의미 부여해서 푸는데 상당히 논리적이다
 * 하향식 풀이(top-down), O(2^n) -> 메모리제이션으로 풀어야 통과
 * https://steady-coding.tistory.com/172
 *
 * 상향식 풀이(bottom-up)
 * https://steady-coding.tistory.com/172
 *
 * DP[i] = Math.max(DP[i - 1] + V[i], DP[i - 1] - V[i])
 * //이때 각 값은 0 <= 값 <= M
 */
public class BOJ1495 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, S, M;
    static int[] V; // 볼륨 리스트
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        S = inputProcessor.nextInt(); // 시작 볼륨
        M = inputProcessor.nextInt(); // 최대 볼륨

        V = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            V[i] = inputProcessor.nextInt();
        }

        // 초기화
        DP = new int[N + 1][M + 1];
        DP[0][S] = 1;
    }

    private static void pro() {
        bottomUp();
    }

    private static void bottomUp() {
        for(int i = 1; i <= N; i++) {
            for(int v = 0; v <= M; v++) {
                if(DP[i - 1][v] > 0) {
                    int v1 = v + V[i];
                    int v2 = v - V[i];

                    if(valid(v1)) DP[i][v1] = 1;
                    if(valid(v2)) DP[i][v2] = 1;
                }
            }
        }

        int result = -1;
        for(int i = 0; i <= M; i++) {
            if(DP[N][i] > 0 && i > result) {
                result = i;
            }
        }

        sb.append(result);
    }

    private static boolean valid(int value) {
        return 0 <= value && value <= M;
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

    // 방문한 적이 없다 != 볼륨 조절 할 수 없다
    // https://www.acmicpc.net/board/view/2481
    /*
    static long executeByTopDown(int idx, int vol) {
        // 조건, 초기항 처리
        if(vol < 0 || vol > M) return -1;
        if(idx == N) return vol;

        // 값이 있을 경우 리턴
        if(DP[idx][vol] != -2) return DP[idx][vol];

        // 값이 없을 경우 재귀 호출
        return DP[idx][vol] = Math.max(
                executeByTopDown(idx + 1, vol + VOLUME[idx + 1]),
                executeByTopDown(idx + 1, vol - VOLUME[idx + 1])
        );
    }


    public static void main(String[] args) throws Exception {
        input();
        //executeByBottomUp();

        for(int i = 0; i <= N; i++) Arrays.fill(DP[i], -2);

        System.out.println(executeByTopDown(0, S));
    }
     */
}
