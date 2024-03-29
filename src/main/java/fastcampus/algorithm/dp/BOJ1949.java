package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 우수마을(골드2) https://www.acmicpc.net/problem/1949
 *
 * rooted tree 를 알고 시작했는나
 * 루트 노드는 무엇을 골라야 하고, 초기화는 어떻게 해야 하고
 * 조합은 또 어떻게 해야하는지 전혀 알수 없었다
 * ==> DFS 알아도 방법이 떠오르지 않음
 *
 * (240228)
 * - DP[i][0] 점화식 틀림
 * - 그리고 dfs 돌면서 DP[i][1] = CITIZEN[i]로 초기화 생각 못함
 */
public class BOJ1949 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int ROOT = 1;
    static int N;
    static List<Integer>[] ADJ;
    static int[] CITIZEN;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        CITIZEN = new int[N + 1]; // 주민 수
        for(int i = 1; i <= N; i++) {
            CITIZEN[i] = inputProcessor.nextInt();
        }

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N - 1; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            ADJ[a].add(b);
            ADJ[b].add(a);
        }
    }

    private static void pro() {
        DP = new int[N + 1][2]; // 초기화는 리프 노드에서

        dfs(ROOT, - 1);

        sb.append(Math.max(DP[ROOT][0], DP[ROOT][1]));
    }

    private static void dfs(int node, int prev) {
        DP[node][1] = CITIZEN[node];

        for(int child : ADJ[node]) {
            if(child == prev) continue;

            dfs(child, node);

            DP[node][0] += Math.max(DP[child][0], DP[child][1]);
            DP[node][1] += DP[child][0];
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
