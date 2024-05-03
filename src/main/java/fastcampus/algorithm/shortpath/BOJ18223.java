package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 골4. 민준이와 마산 그리고 건우
 * https://www.acmicpc.net/problem/18223
 * <p>
 * 1 -> P, P -> V 로 가는 최소 비용 합과 1 -> V 로 가는 최소 비용 합이 같으면 구해진거고
 * 틀리면 안 구해진거
 * <p>
 * 첫 풀이는 prev 노드를 두고 역추적 해서 구함
 */
public class BOJ18223 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int START = 1;
    private static final int MAX_DIST = 50000001;

    private static int V, E, P;
    private static List<List<Node>> ADJ;


    private static class Node implements Comparable<Node> {
        private int idx;
        private int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        V = inputProcessor.nextInt(); // 정점의 개수
        E = inputProcessor.nextInt(); // 간선의 개수
        P = inputProcessor.nextInt(); // 건우가 위치한 정점

        ADJ = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= E; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            ADJ.get(from).add(new Node(to, cost));
            ADJ.get(to).add(new Node(from, cost));
        }
    }

    // 1 -> P  + P -> V == 1 -> V 면 구해진거
    private static void pro() {
        int[] result1 = dijkstra(START, V);
        int oneToP = result1[P];
        int oneToV = result1[V];

        int[] result2 = dijkstra(P, V);
        int pToV = result2[V];

        if (oneToP + pToV == oneToV) sb.append("SAVE HIM");
        else sb.append("GOOD BYE");
    }

    private static int[] dijkstra(int start, int end) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(start, 0));

        int[] dist = new int[V + 1];
        Arrays.fill(dist, MAX_DIST);
        dist[start] = 0;

        boolean result = false;
        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (dist[cur.idx] < cur.cost) continue;

            for (Node next : ADJ.get(cur.idx)) {
                if (dist[cur.idx] + next.cost >= dist[next.idx]) continue;

                dist[next.idx] = dist[cur.idx] + next.cost;
                que.add(new Node(next.idx, dist[next.idx]));
            }
        }

        return dist;
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
