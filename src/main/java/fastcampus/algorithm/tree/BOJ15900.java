package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * DFS, 인접리스트로 풀이시 O(V + E) 만큼의 시간 소요
 * O(V + E) = O(500,000 + 499,999)
 */
public class BOJ15900 {

    static int N, ANS;
    static List<Integer>[] tree;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) tree[i] = new ArrayList();

        for(int i = 1; i <= (N - 1); i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree[from].add(to);
            tree[to].add(from);
        }

        br.close();
    }

    static void dfs(int node, int parent, int depth) {
        if(node != 1 && tree[node].size() == 1 && tree[node].get(0) == parent) {
            ANS += depth;
            return;
        }

        for(int child : tree[node]) {
            if(child == parent) continue;

            dfs(child, node,depth + 1);
        }
    }

    static void pro() {
        dfs(1, -1,  0);

        System.out.println(ANS % 2 == 1 ? "Yes" : "No");
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
}
