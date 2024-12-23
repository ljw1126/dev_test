package its.codingtest.graph;

import its.codingtest.Practice;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 백준 - 최종순위(골1)
 * https://www.acmicpc.net/problem/3665
 *
 * - 위상 정렬 문제
 * - 인접 행렬을 사용해서 간선 정보를 나타냄 (ex, 5번이 1위이면 [5][1 ~ 4] = true
 * - 순위는 1 ~ N위 정해져 있으므로, 반복문으로 수행
 * - 이때 반복문이 끝나지 않았는데, 큐가 0인 경우 indegree가 0인 노드가 없으므로 IMPOSSIBLE
 * - 그리고 큐에 두 개 이상 들어가는 경우 순위를 정할 수 없으므로 물음표(question mark) 출력
 * - 그외는 위상 정렬로 결과 출력
 */
public class p399 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        output();
    }

    private static int T, N, M;
    private static int[] IN_DEGREE, RANK;
    private static boolean[][] EDGES;

    private static void input() {
        T = inputProcessor.nextInt();

        while(T > 0) {
            N = inputProcessor.nextInt();
            EDGES = new boolean[N + 1][N + 1];

            IN_DEGREE = new int[N + 1];
            for(int i = 1; i <= N; i++) {
                int no = inputProcessor.nextInt();
                IN_DEGREE[no] = i - 1;

                for(int j = 1; j <= N; j++) {
                    if(j == no) continue;
                    if(!EDGES[j][no]) {
                        EDGES[no][j] = true;
                    }
                }
            }

            M = inputProcessor.nextInt(); // 올해 a가 b보다 순위가 높다
            for(int i = 1; i <= M; i++) {
                int a = inputProcessor.nextInt();
                int b = inputProcessor.nextInt();
                swap(a, b);
            }

            pro();

            T -= 1;
        }
    }

    private static void swap(int a, int b) {
        if(EDGES[a][b]) {
            EDGES[a][b] = false;
            EDGES[b][a] = true;
            IN_DEGREE[b] -= 1;
            IN_DEGREE[a] += 1;
        } else {
            EDGES[a][b] = true;
            EDGES[b][a] = false;
            IN_DEGREE[b] += 1;
            IN_DEGREE[a] -= 1;
        }
    }

    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
            }
        }

        int[] rank = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            if(que.isEmpty()) {
                sb.append("IMPOSSIBLE").append("\n");
                return;
            }

            if(que.size() == 2) {
                sb.append("?").append("\n");
                return;
            }

            int cur = que.poll();
            rank[i] = cur;

            for(int j = 1; j <= N; j++) {
                if(j == cur) continue;
                if(!EDGES[cur][j]) continue; // cur > j 간선이 존재하는가

                EDGES[cur][j] = false;
                if(--IN_DEGREE[j] == 0) {
                    que.add(j);
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            sb.append(rank[i]).append(" ");
        }
        sb.append("\n");
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
