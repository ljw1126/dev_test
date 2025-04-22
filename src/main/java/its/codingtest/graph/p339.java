package its.codingtest.graph;

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
 * 특정 거리의 도시 찾기 (실2)
 */
public class p339 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, M, K, X;
    private static List<Integer>[] ADJ;

    private static void input() {
        N = inputProcessor.nextInt(); // 도시의 개수 (노드)
        M = inputProcessor.nextInt(); // 도로의 개수 (간선)
        K = inputProcessor.nextInt(); // 거리 정보
        X = inputProcessor.nextInt(); // 출발 도시

        ADJ = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            ADJ[from].add(to);
        }
    }

    private static void pro() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(X);

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[X] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : ADJ[cur]) {
                if (dist[next] != -1) continue;

                dist[next] = dist[cur] + 1;
                queue.add(next);
            }
        }

        boolean check = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                sb.append(i).append("\n");
                check = true;
            }
        }

        if (!check) {
            sb.setLength(0);
            sb.append(-1);
        }
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
