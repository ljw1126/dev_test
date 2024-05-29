package fastcampus.algorithm.exam1;

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
 * 골목 대장 호석이 (골드5)
 * https://www.acmicpc.net/problem/20168
 * <p>
 * 변수 초기화 실수
 * - C가 long 타입
 * - 매개변수 탐색(파라메트릭 서치)시에도 변수 초기화
 * - Info 클래스 전달 받은 값 또한 long ..
 * - 다익스트라 초기화시 최대치 제대로 초기화 못해서 틀림 (32/43)
 */
public class BOJ20183 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M, A, B;
    private static long C;
    private static List<List<Node>> ADJ = new ArrayList<>();

    private static class Node implements Comparable<Node> {
        private int idx;
        private long cost;

        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if (this.cost < other.cost) return -1;
            else if (this.cost == other.cost) return 0;
            else return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 교차로 개수(노드)
        M = inputProcessor.nextInt(); // 골목 개수(간선)
        A = inputProcessor.nextInt(); // 시작 교차로
        B = inputProcessor.nextInt(); // 도착 교차로
        C = inputProcessor.nextLong(); // 가진 돈

        for (int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            long cost = inputProcessor.nextLong();

            ADJ.get(from).add(new Node(to, cost));
            ADJ.get(to).add(new Node(from, cost));
        }
    }

    // 수치심 mid만으로 C 금액을 가지고 갈 수 있는가??
    private static void pro() {
        int cost = -1;
        int L = 1;
        int R = 1000_000_000;

        while (L <= R) {
            int mid = (L + R) / 2;

            if (isPossible(mid)) {
                R = mid - 1;
                cost = mid;
            } else {
                L = mid + 1;
            }
        }

        sb.append(cost);
    }

    private static boolean isPossible(int limit) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(A, 0));

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[A] = 0L;

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (dist[cur.idx] < cur.cost) {
                continue;
            }
            if (cur.idx == B) {
                continue;
            }

            for (Node next : ADJ.get(cur.idx)) {
                if (next.cost > limit) continue;

                if (dist[next.idx] > dist[cur.idx] + next.cost) {
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    que.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        return dist[B] <= C;
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
