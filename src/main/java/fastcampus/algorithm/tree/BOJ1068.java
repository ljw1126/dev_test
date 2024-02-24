package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리(골드5) https://www.acmicpc.net/problem/1068
 *
 * - DFS
 * - 인접리스트 사용하여 시간복잡도 O(V + E)
 * - 작은 문제를 풀어서 큰 문제를 풀도록 함
 */
public class BOJ1068 {
    static StringBuilder sb = new StringBuilder();

    static int N, REMOVE_NODE, ROOT_NODE;

    static List<Integer>[] CHILD;

    static int[] LEAF;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        CHILD = new ArrayList[N];
        for(int i = 0; i < N; i++) CHILD[i] = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1) {
                ROOT_NODE = i;
                continue;
            }

            CHILD[parent].add(i);
        }

        st = new StringTokenizer(br.readLine());
        REMOVE_NODE = Integer.parseInt(st.nextToken());

        LEAF = new int[N];
        
        br.close();
    }

    static void dfs(int x) {
        if(CHILD[x].isEmpty()) {
            LEAF[x] = 1;
            return;
        }

        for(int y : CHILD[x]) {
            dfs(y);
            LEAF[x] += LEAF[y];
        }
    }

    static void pro() {
        for(int i = 0; i < N; i++) {
            if(CHILD[i].contains(REMOVE_NODE)) {
                CHILD[i].remove(CHILD[i].indexOf(REMOVE_NODE));
            }
        }

        if(ROOT_NODE != REMOVE_NODE) dfs(ROOT_NODE);

        System.out.println(LEAF[ROOT_NODE]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
