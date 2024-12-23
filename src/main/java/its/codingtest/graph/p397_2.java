package its.codingtest.graph;

import its.codingtest.Practice;

import java.io.*;
import java.util.*;

public class p397_2 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, M, MAX_COST;
    private static List<Node>[] ADJ;

    private static void input() {
        N = inputProcessor.nextInt(); // 집의 수 (노드)
        M = inputProcessor.nextInt(); // 도로의 수 (간선)

        ADJ = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int z = inputProcessor.nextInt();

            ADJ[x].add(new Node(y, z));
            ADJ[y].add(new Node(x, z));
            MAX_COST += z;
        }
    }

    private static class Node implements Comparable<Node> {
        private final int to;
        private final int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    private static void pro() {
        for(int i = 0; i < N; i++) {
            Collections.sort(ADJ[i]);
        }

        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(0, 0));

        int total = 0;
        boolean[] visited = new boolean[N];
        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(visited[cur.to]) continue;

            visited[cur.to] = true;
            total += cur.cost;

            for(Node next : ADJ[cur.to]) {
                if(visited[next.to]) continue;

                que.add(next);
            }
        }

        // 가로등을 비활성화하여 절약할 수 있는 최대 금액을 출력 = 전체 금액 - 최소 연결 비용
        sb.append(MAX_COST - total);
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
