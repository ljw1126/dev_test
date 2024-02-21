package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 회사 문화 (골드4) https://www.acmicpc.net/problem/14267
 *
 * - 심플한 방식을 생각하지 못함
 * - 같은 직원에게 여러번 칭찬 할 수 있다.
 * - 시간복잡도 : O(V + E)
 */
public class BOJ14267 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, M, ROOT;
    static List<Integer>[] ADJ;
    static int[] SCORE;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }
    private static void input() {
        N = inputProcessor.nextInt(); // 회사의 직원 수 (노드, 1번부터)
        M = inputProcessor.nextInt(); // 칭찬의 횟수 (쿼리)

        SCORE = new int[N + 1];

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            int parent = inputProcessor.nextInt();
            if(parent == -1) {
                ROOT = i;
                continue;
            }

            ADJ[parent].add(i);
        }

        for(int i = 1; i <= M; i++) {
            int u = inputProcessor.nextInt();
            int v = inputProcessor.nextInt();

            SCORE[u] += v;
        }
    }

    private static void pro() {
        dfs(ROOT);

        for(int i = 1; i <= N; i++) {
            sb.append(SCORE[i]).append(" ");
        }
    }

    private static void dfs(int node) {
        for(int child : ADJ[node]) {
            SCORE[child] += SCORE[node];
            dfs(child);
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
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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
