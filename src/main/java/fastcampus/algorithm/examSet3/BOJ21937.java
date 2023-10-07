package fastcampus.algorithm.examSet3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 작업(실버1)
 * https://www.acmicpc.net/problem/21937
 *
 * - dfs 풀이시 리프 노드를 샌다면 최악의 경우 99999 호출하고, 이진 트리면 log(100000)호출
 * - 직접 풀이함
 * - 결국 화살표 방향을 반대로 하는 아이디어가 핵심 (BFS) 또한 마찬가지
 */
public class BOJ21937 {
    static StringBuilder sb = new StringBuilder();

    static List<Integer>[] adj;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 작업할 개수 N (노드)
        int M = Integer.parseInt(st.nextToken()); // 작업 순서 정보 개수 (간선)

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[to].add(from);
        }

        int X = Integer.parseInt(br.readLine()); // 반드시 끝내야 하는 작업 X

        boolean[] visited = new boolean[N + 1];
        int[] leaf = new int[N + 1];
        Arrays.fill(leaf, 1);
        leaf[X] = 0;
        dfs(X, visited, leaf);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(leaf[X]));
        bw.flush();
        bw.close();
    }

    static void dfs(int node, boolean[] visited, int[] leaf) {
        visited[node] = true;
        if(adj[node].isEmpty()) {
            return;
        }

        for(int next : adj[node]) {
            if(visited[next]) continue;

            dfs(next, visited, leaf);
            leaf[node] += leaf[next];
        }
    }

    public static void main(String[] args) throws Exception {
        input();
    }

}
