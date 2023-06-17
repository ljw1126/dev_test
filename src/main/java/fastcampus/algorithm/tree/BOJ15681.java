package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리와 쿼리(골드5) https://www.acmicpc.net/problem/15681
 *
 * 설명까지 있는 좋은 문제
 * - 트리 만드는 방법, 서브 트리 구하는 방법
 */
public class BOJ15681 {
    static StringBuilder sb = new StringBuilder();

    static int N, R, Q;

    static List<Integer>[] adj;

    static List<Integer> query = new ArrayList<>();

    static int[] subtree, parent;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 트리의 정점 수
        R = Integer.parseInt(st.nextToken()); // 루트 번호
        Q = Integer.parseInt(st.nextToken()); // 쿼리 수

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        for(int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            query.add(Integer.parseInt(st.nextToken()));
        }

        subtree = new int[N + 1];
        Arrays.fill(subtree, 1);
        parent = new int[N + 1];
    }

    static void makeTree(int x, int prev) {
        parent[x] = prev;
        for(int y : adj[x]) {
            if(y == prev) continue;

            makeTree(y, x);
        }
    }

    static void dfs(int x, int par) {
        subtree[x] = 1;

        for(int y : adj[x]) {
            if(y == par) continue;

            parent[y] = x;
            dfs(y, x);
            subtree[x] += subtree[y];
        }
    }

    static void pro() {
        dfs(R, -1);

        for(int q : query) sb.append(subtree[q]).append("\n");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
