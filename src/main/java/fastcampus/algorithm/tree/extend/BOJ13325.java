package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13325 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int K, N, RESULT;
    private static int[] DIST;
    private static int[] DP;

    private static void input() {
        K = inputProcessor.nextInt(); // depth
        // 2^(k+1) - 1;
        N = (int) Math.pow(2, K + 1) - 1;

        DIST = new int[N];
        DP = new int[N];
        for (int i = 1; i < N; i++) {
            int dist = inputProcessor.nextInt();

            DIST[i] = dist;
            DP[i] = dist;
        }
    }


    private static void pro() {
        //bottomUp();
        topDown(0);
        sb.append(RESULT);
        System.out.println(Arrays.toString(DP));
    }

    //https://www.acmicpc.net/problem/13325
    // 직접 풀이 못함
    private static int topDown(int node) {
        if (node * 2 + 1 >= N) { // 리프 노드 부터 합산
            RESULT += DIST[node];
            return DIST[node];
        }

        int parent = node * 2;
        int left = topDown(parent + 1);
        int right = topDown(parent + 2);

        // 자식 노드의 차이만큼 더해줘야 수평이 맞음
        RESULT += (DP[node] + Math.abs(left - right));
        return DP[node] + Math.max(left, right); // 현재 노드의 까지의 평균 가중치, 서브트리인 경우
    }

    private static void bottomUp() {
        for (int i = N - 1; i >= 0; i--) {
            int left = i * 2 + 1;
            int right = left + 1;
            if (right >= N) continue;

            int max = DP[i];
            max = Math.max(max + DP[left], max + DP[right]); // 5


            if (max > DP[left]) {
                int value = max - DP[i] - DP[left];
                DP[left] += value;
                DIST[left] += value;
            }

            if (max > DP[right]) {
                int value = max - DP[i] - DP[right];
                DIST[right] += value;
                DP[right] += value;
            }

            DP[i] = max;
        }

        int result = 0;
        for (int i = 0; i <= N - 1; i++) {
            result += DIST[i];
        }

        sb.append(result);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
