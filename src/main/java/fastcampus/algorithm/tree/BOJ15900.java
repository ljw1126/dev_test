package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15900 {

    static StringBuilder sb = new StringBuilder();

    static int N, LEAF;

    static List<Integer>[] adj;

    static boolean[] VISIT;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        VISIT = new boolean[N + 1];
    }

    static void dfs(int x, int depth) {
        VISIT[x] = true;
        for(int y : adj[x]) {
            if(VISIT[y]) continue;
            dfs(y,depth + 1);
        }

        // 단말 노드 : 사이즈가 1이고, 부모 노드에 대한 정보만 있을 때
        if(x != 1 && adj[x].size() == 1) {
            LEAF += depth;
        }
    }
    static void pro() {
        dfs(1,  0);
        System.out.println(LEAF % 2 == 1 ? "YES" : "NO");
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}
