package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * - 매개변수 탐색 + bfs 풀이시 중량 최대값을 구하기 위해서는 조건 만족시 L을 증가해야함
 * - 직접 풀지 못함
 */
public class BOJ1939 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int A, B;

    static List<Node>[] adj;

    static int INF;

    static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to, cost));
            adj[to].add(new Node(from, cost));

            INF = Math.max(INF, cost);
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }

    static boolean bfs(long limit) {
        Queue<Integer> que = new LinkedList<>();
        que.add(A);

        boolean[] visit = new boolean[N + 1];
        visit[A] = true;

        while(!que.isEmpty()) {
            int cur = que.poll();

            if(cur == B) return true;

            for(Node next : adj[cur]) {
                if(limit > next.cost) continue;
                if(visit[next.idx]) continue;

                que.add(next.idx);
                visit[next.idx] = true;
            }
        }

        return false;
    }

    static void pro() {
        int L = 1;
        int R = INF;
        int ans = R;

        while(L <= R) {
            int mid = (L + R) / 2;

            // 최대값을 찾아야 하니 L을 증가
            if(bfs(mid)) {
               ans = mid;
               L = mid + 1;
            } else {
               R = mid - 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
