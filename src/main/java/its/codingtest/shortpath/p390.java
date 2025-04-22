package its.codingtest.shortpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 숨바꼭질 - USACO
 * - 최단거리 문제
 * - 1번 노드에서 다른 노드로 가는 모든 최단 경로를 구한다
 * - 가장 큰 최단 거리 정보와 부가 정보들을 구한다
 * - O(ElogV)
 * <p>
 * 입력
 * 6 7
 * 3 6
 * 4 3
 * 3 2
 * 1 3
 * 1 2
 * 2 4
 * 5 2
 * <p>
 * 출력
 * 4 2 3
 */
public class p390 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, M;
    private static List<Integer>[] ADJ;

    private static void input() {
        N = inputProcessor.nextInt(); // 노드 수 ( 1 ~ N )
        M = inputProcessor.nextInt(); // 양방향 통로 수 (간선)

        ADJ = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ[from].add(to);
            ADJ[to].add(from);
        }
    }

    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(1);
        que.add(0);

        int[] dist = new int[N + 1];
        Arrays.fill(dist, 1, N + 1, Integer.MAX_VALUE);
        dist[1] = 0;

        while (!que.isEmpty()) {
            int node = que.poll();
            int cost = que.poll();

            if (dist[node] < cost) continue;

            for (int next : ADJ[node]) {
                if (dist[next] > cost + 1) {
                    dist[next] = cost + 1;
                    que.add(next);
                    que.add(dist[next]);
                }
            }
        }

        /*
        - 숨어야 하는 헛간 번호 (가장 먼 거리의 번호, 여러 개면 가장 작은 번호 고름
        - 그 헛간 까지의 거리
        - 거리가 같은 헛간의 개수
         */

        int first = -1;
        int count = 0;
        int max = -1;
        for (int i = 2; i <= N; i++) {
            if (max < dist[i]) {
                first = i;
                max = dist[i];
                count = 1;
            } else if (max == dist[i]) {
                count += 1;
            }
        }

        sb.append(first).append(" ")
                .append(max).append(" ")
                .append(count);
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
