package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리(골드4) https://www.acmicpc.net/problem/4803
 *
 * case 별 시간복잡도 O(N)
 * - 재귀 함수 사용하여 visit, 부모 노드 번호로 하여 사이클 판별
 * - 정점에서 연결된 간선이 이미 방문한 적있고, 현 정점의 부모 노드와 간선 연결 정점이 다른 경우 사이클이다
 */
public class BOJ4803 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, CASE_NUMBER;

    static List<Integer>[] adj;

    static boolean[] visit;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        CASE_NUMBER = 1;
        String str = "";
        while(!(str = br.readLine()).equals("0 0")) {
            st = new StringTokenizer(str);

            N = Integer.parseInt(st.nextToken()); // 정점 개수
            M = Integer.parseInt(st.nextToken()); // 간선 개수

            adj = new ArrayList[N + 1];
            for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

            for(int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adj[from].add(to);
                adj[to].add(from);
            }

            for(int i = 1; i <= N; i++) {
                Collections.sort(adj[i]);
            }

            visit = new boolean[N + 1];
            pro();

            CASE_NUMBER += 1;
        }
    }

    static void addResult(int caseNumber, int treeCount) {
        if(treeCount > 1) {
            sb.append(String.format("Case %d: A forest of %d trees.", caseNumber, treeCount)).append("\n");
        } else if(treeCount == 1) {
            sb.append(String.format("Case %d: There is one tree.", caseNumber)).append("\n");
        } else {
            sb.append(String.format("Case %d: No trees.", caseNumber)).append("\n");
        }
    }

    // 방문한적이 있고 부모 노드가 다른 경우 사이클
    static boolean isCycle(int x, int prev) {
        visit[x] = true;
        for(int y : adj[x]) {
            if(!visit[y]) {
                if(isCycle(y, x)) return true;
            } else if(y != prev) {
                return true;
            }
        }

        return false;
    }

    static void pro() {
        int treeCount = 0;
        for(int i = 1; i <= N; i++) {
            if(!visit[i] && !isCycle(i, 0)) treeCount += 1;
        }

        addResult(CASE_NUMBER, treeCount);
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
