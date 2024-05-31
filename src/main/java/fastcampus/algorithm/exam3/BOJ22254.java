package fastcampus.algorithm.exam3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 공정 컨설턴트 호석(골드3)
 * https://www.acmicpc.net/problem/22254
 * <p>
 * - 직접 풀이
 * - 1 ~ N까지 이진 탐색을 해서, 최소 라인 수를 구한다.
 * - 우선순위큐를 사용해서 limit를 안 넘는 경우 mid를 갱신하고 최소값이기때문에 R을 줄인다
 * 주문이 최대 10만건이 있고, 남은 시간 10억, 각 주문별 10억개가 소비될 때, 최소 10만건이 있어야 생산 가능
 */
public class BOJ22254 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int N, X;
    private static int[] PRODUCTS;

    private static void input() {
        N = inputProcessor.nextInt(); // 주문의 개수
        X = inputProcessor.nextInt(); // 남은 시간

        PRODUCTS = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            PRODUCTS[i] = inputProcessor.nextInt(); // 선물별 공정시간
        }
    }

    // 기계를 n개 작업 했을때 X시간 내에 작업이 가능한가
    // 개수만큼 선물을 넣고, 돌린다 (오름차순) 넣고
    // 마지막에는 pop 을하는데 최종 시간이 K 이하이면 통과 아니면 실패
    private static void pro() {
        int L = 1;
        int R = N;
        int result = N;
        while (L <= R) {
            int mid = (L + R) / 2;

            if (isPossible(mid)) {
                R = mid - 1;
                result = mid;
            } else {
                L = mid + 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(int robot) {
        Queue<Integer> que = new PriorityQueue<>(); // 오름차순
        for (int i = 1; i <= robot; i++) {
            que.add(PRODUCTS[i]);
        }

        for (int i = robot + 1; i <= N; i++) {
            int cur = que.poll();

            if (cur + PRODUCTS[i] > X) return false;

            que.add(cur + PRODUCTS[i]);
        }

        int result = 0;
        while (!que.isEmpty()) {
            result = Math.max(result, que.poll());
        }

        return result <= X;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        private StringTokenizer st;
        private BufferedReader br;

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
