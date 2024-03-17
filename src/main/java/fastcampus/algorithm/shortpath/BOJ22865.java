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
 * 가장 먼곳 (골드4)
 * https://www.acmicpc.net/problem/22865
 *
 * - 질문 게시판 찾아보고 풀이
 * - N * NlogN하니 시간 초과
 */
public class BOJ22865 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, M;
    static List<Integer> FRIEND = new ArrayList<>();
    static List<List<Node>> ADJ = new ArrayList<>();
    static int[][] DIST;

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
        public int compareTo(Node o) {
            return this.cost - o.cost; // 오름차순
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        for(int i = 1; i <= 3; i++) {
            FRIEND.add(inputProcessor.nextInt());
        }

        for(int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        M = inputProcessor.nextInt();
        for(int i = 1; i <= M; i++) {
            int D = inputProcessor.nextInt();
            int E = inputProcessor.nextInt();
            int L = inputProcessor.nextInt();

            ADJ.get(D).add(new Node(E, L));
            ADJ.get(E).add(new Node(D, L));
        }

    }

    private static void pro() {
        DIST = new int[3][N + 1];
        for(int i = 0; i < FRIEND.size(); i++) {
            dijkstra(FRIEND.get(i), i);
        }

        int result = 0;
        int min = 0;
        for(int i = 1; i <= N; i++) {
            if(FRIEND.contains(i)) continue;

            int v1 = DIST[0][i];
            int v2 = DIST[1][i];
            int v3 = DIST[2][i];

            int minDist = Math.min(v1, Math.min(v2, v3));

            if(min < minDist) {
                result = i;
                min = minDist;
            }
        }

        sb.append(result);
    }

    // start에서 A,B,C 친구집까지 거리중 가장 가까운 거리를 구함
    // 그리고 가까운 거리값중 가장 먼곳(큰값) 위치가 정답
    private static void dijkstra(int start, int idx) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(start, -1));

        Arrays.fill(DIST[idx], Integer.MAX_VALUE);
        DIST[idx][start] = 0;

        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(DIST[idx][cur.idx] < cur.cost) continue;

            for(Node next : ADJ.get(cur.idx)) {
                if(DIST[idx][next.idx] <= DIST[idx][cur.idx] + next.cost) continue;

                DIST[idx][next.idx] = DIST[idx][cur.idx] + next.cost;
                que.add(new Node(next.idx, DIST[idx][next.idx]));
            }
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
