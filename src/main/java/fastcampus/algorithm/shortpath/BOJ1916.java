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
 * 최소비용 구하기(골드5)
 *
 * - 직접 풀이
 * - O(ElogV)
 * - 다익스트라 , 단방향 그래프
 * - 최대치 10^8 = int 범위내
 * - 방문 배열이 필요없었네
 */
public class BOJ1916 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, M, A, B;
    static List<List<Node>> ADJ;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost; // 오름차순 정렬
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        ADJ = new ArrayList<>();
        for(int i = 0; i <= N; i++) { // 시작 조심
            ADJ.add(new ArrayList<>());
        }

        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            ADJ.get(from).add(new Node(to, cost));
        }

        A = inputProcessor.nextInt(); // 시작점
        B = inputProcessor.nextInt(); // 도착지점
    }

    private static void pro() {
        dijkstra(A, B);
    }

    private static void dijkstra(int a, int b) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(a, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[a] = 0;

        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(dist[cur.idx] < cur.cost) continue;

            for(Node next : ADJ.get(cur.idx)) {
                if(dist[cur.idx] + next.cost >= dist[next.idx]) continue;

                dist[next.idx] = dist[cur.idx] + next.cost;
                que.add(new Node(next.idx, dist[next.idx]));
            }
        }

        // 결과 출력
        sb.append(dist[b]);
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
                st = new StringTokenizer(nextLine());
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
