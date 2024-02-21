package fastcampus.algorithm.tree.extend;

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
 * 노드 사이의 거리(골드5)
 * https://www.acmicpc.net/problem/1240
 *
 * - DFS/BFS 둘다 풀이 가능
 */
public class BOJ1240 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, M;
    static List<Node>[] ADJ;
    static boolean[] VISIT; // DFS
    static int[] DIST; // DFS
    private static class Node {
        int to;
        int cost;
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }
    private static void input() {
        N = inputProcessor.nextInt(); // 노드의 개수
        M = inputProcessor.nextInt(); // 알고 싶은 노드 쌍의 개수 (query)

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N - 1; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            ADJ[from].add(new Node(to, cost));
            ADJ[to].add(new Node(from, cost));
        }
    }

    private static void pro() {
        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            //sb.append(bfs(from, to)).append("\n");
            VISIT = new boolean[N + 1];
            DIST = new int[N + 1];
            dfs(from);
            sb.append(DIST[to]).append("\n");
        }
    }

    private static int bfs(int from, int to) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(from);

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[from] = 0;

        while(!que.isEmpty()) {
            int node = que.poll();

            for(Node child : ADJ[node]) {
                if(dist[child.to] != -1) continue;

                dist[child.to] = dist[node] + child.cost;
                que.add(child.to);
            }
        }

        return dist[to];
    }

    private static void dfs(int node) {
        VISIT[node] = true;

        for(Node child : ADJ[node]) {
            if(VISIT[child.to]) continue;

            DIST[child.to] = DIST[node] + child.cost;
            dfs(child.to);
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
