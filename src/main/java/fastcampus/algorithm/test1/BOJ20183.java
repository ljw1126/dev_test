package fastcampus.algorithm.test1;

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
 *
 * 시간 복잡도
 * - 수치심 이분 탐색 * 다익스트라(ElogV)
 * - log10^14 * (50만log10만)
 */
public class BOJ20183 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static long INF = 100000000000001L;
    static int N, M, A, B;
    static long C;
    static List<Node>[] ADJ;

    private static class Node implements Comparable<Node> {
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        // 값이 틀려서 시간초과 발생
        // 오름차순일때 값이 1이라는건 정렬 필요하다는 뜻 (1 : true, -1 : false)
        @Override
        public int compareTo(Node o) {
            if(this.cost < o.cost) {
                return -1;
            } else if(this.cost > o.cost) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        int L = 1;
        int R = 1000000000;

        int result = -1;
        while(L <= R) {
            int mid = (L + R) / 2; // 수치심

            if(isPossible(mid)) {
                R = mid - 1;
                result = mid;
            } else {
                L = mid + 1;
            }
        }

        sb.append(result);
    }

    private static boolean isPossible(int limit) {
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(A, 0));

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[A] = 0L;

        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(dist[cur.to] < cur.cost) continue;

            for(Node next : ADJ[cur.to]) {
                if(next.cost > limit) continue; // 수치심이 limit 넘는다면 볼필요가 없지

                if(dist[next.to] > dist[cur.to] + next.cost) {
                    dist[next.to] = dist[cur.to] + next.cost;
                    que.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist[B] <= C;
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 교차로 개수(노드)
        M = inputProcessor.nextInt(); // 골목 개수 (간선)
        A = inputProcessor.nextInt(); // 시작 교차로
        B = inputProcessor.nextInt(); // 도착 교차로
        C = inputProcessor.nextLong(); // 가진 돈

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            ADJ[from].add(new Node(to, cost));
            ADJ[to].add(new Node(from, cost));
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
