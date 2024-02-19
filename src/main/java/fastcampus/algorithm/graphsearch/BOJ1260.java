package fastcampus.algorithm.graphsearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * DFS와 BFS (실버2)
 * https://www.acmicpc.net/problem/1260
 *
 * - 기본문제
 * - 시간 복잡도 : O(V + E)
 */
public class BOJ1260 {
    static StringBuilder sb = new StringBuilder();
    static int N, M, V;
    static boolean[] VISIT;
    static List<Integer>[] ADJ;


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        VISIT = new boolean[N + 1];

        dfs(V);

        sb.append("\n");
        for(int i = 1; i <= N; i++) {
            VISIT[i] = false;
        }

        bfs(V);
    }

    private static void bfs(int node) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(node);
        VISIT[node] = true;

        while(!que.isEmpty()) {
            int cur = que.poll();
            sb.append(cur).append(" ");

            for(int next : ADJ[cur]) {
                if(VISIT[next]) continue;

                que.add(next);
                VISIT[next] = true;
            }
        }
    }

    private static void dfs(int node) {
        VISIT[node] = true;
        sb.append(node).append(" ");

        for(int next : ADJ[node]) {
            if(VISIT[next]) continue;

            dfs(next);
        }
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt(); // 정점의 개수
        M = inputProcessor.nextInt(); // 간선의 개수
        V = inputProcessor.nextInt(); // 탐색을 시작할 정점의 번호

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        // 양 방향
        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ[from].add(to);
            ADJ[to].add(from);
        }

        // 정점 번호가 작은거 부터 방문하도록 오름차순 정렬 : O(NlogN)
        for(int i = 1; i <= N; i++) {
            Collections.sort(ADJ[i]);
        }
    }

    private static void output() throws IOException {
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
