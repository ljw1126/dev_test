package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * i * j = a
 * i -> j 비용시 1 이다
 *
 * - a ~ z : 1 ~ 26
 * - A ~ Z : 27 ~ 52
 * 알파벳 치환을 비용으로 치환하는게 어렵네..
 *
 * -- 직접 풀이
 * -- 시작 노드를 다르게 하니 비용도 차이가 나는 것을 확인함
 * -- 가지고 있는 총 랜선에서 제일 작은 비용을 빼주고 그걸 불우 이웃 돕기 하면 되는것으로 생각함
 */
public class BOJ1414 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static List<Edge>[] adj;

    static class Edge implements Comparable<Edge> {
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return cost - other.cost; // 내림차순
        }

    }

    private static int convertAlphabet(char alphabet) {
        if(Character.isLowerCase(alphabet)) return alphabet - 'a' + 1;
        else if(Character.isUpperCase(alphabet)) return alphabet - 'A' + 27;
        return 0;
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        int totalLen = 0;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] line = st.nextToken().toCharArray();
            for(int j = 1; j <= N; j++) {
                if(line[j - 1] == '0') continue;

                int cost = convertAlphabet(line[j - 1]);
                adj[i].add(new Edge(j, cost));
                adj[j].add(new Edge(i, cost));

                totalLen += cost;
            }
        }

        // 시작 노드를 i 로 노드를 모두 연결하는 최소 비용 찾는다
        int result = Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++) {
            result = Math.min(result, pro(i));
        }

        // 최소 비용을 제외한 나머지 랜선을 기부한다
        if(result == -1) sb.append("-1");
        else sb.append(totalLen - result);

        System.out.println(sb);
    }

    static int pro(int start) {
        Queue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(start, 0));

        boolean[] visit = new boolean[N + 1];

        int result = 0;
        int edgeCnt = 0;
        while(!que.isEmpty()) {
            Edge cur = que.poll();

            if(visit[cur.idx]) continue;

            visit[cur.idx] = true;
            result += cur.cost;
            edgeCnt += 1;

            for(Edge next : adj[cur.idx]) {
                if(visit[next.idx]) continue;

                que.add(next);
            }
        }

        return edgeCnt == N ? result : -1;
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
