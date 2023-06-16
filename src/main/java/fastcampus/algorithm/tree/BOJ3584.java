package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 가장 가까운 공통 조상 (골드4) https://www.acmicpc.net/problem/3584
 *
 * - O(N * M) 시간 복잡도 안에만 풀리면 됨
 * - LCA 문제
 * - depth와 parent 배열을 만들고, 찾을 두 노드의 depth를 맞춘 후, parent가 동일할 때까지 찾음
 * - 그외 기본 문제 11437, 심화 11438
 */
public class BOJ3584 {
    static StringBuilder sb = new StringBuilder();

    static int T, N, A, B, ROOT_NODE;

    static int[] DEPTH, PARENT;

    static List<Integer>[] adj;

    static boolean[] VISIT;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        while(T > 0) {
            T -= 1;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            VISIT = new boolean[N + 1];
            DEPTH = new int[N + 1];
            PARENT = new int[N + 1];
            Arrays.fill(PARENT, -1);

            adj = new ArrayList[N + 1];
            for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

            for(int i = 1; i <= N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                PARENT[to] = from;

                adj[from].add(to);
                adj[to].add(from);
            }

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            pro();
        }
    }

    static void dfs(int x, int _depth) {
        VISIT[x] = true;

        for(int y : adj[x]) {
            if(VISIT[y]) continue;

            DEPTH[y] = _depth + 1;
            dfs(y, _depth + 1);
        }
    }

    static void lca() {
        int depthA = DEPTH[A];
        int depthB = DEPTH[B];

        while(depthA > depthB) {
            depthA -= 1;
            A = PARENT[A];
        }

        while(depthA < depthB) {
            depthB -= 1;
            B = PARENT[B];
        }

        while(A != B) {
            A = PARENT[A];
            B = PARENT[A];
        }

        sb.append(A).append("\n");
    }
    static void pro() {
        for(int i = 1; i <= N; i++) {
            if(PARENT[i] == -1) {
                ROOT_NODE = i;
                break;
            }
        }

        dfs(ROOT_NODE, 0);
        lca();
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
