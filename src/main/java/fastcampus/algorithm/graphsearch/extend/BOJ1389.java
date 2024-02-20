package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 케빈 베이커의 6단계 법칙 https://www.acmicpc.net/problem/1389
 *
 * 유저 수 : N (정점의 개수, 2 <= N <= 100)
 * 친구 관계의 수 : M (간선의 개수, 1 <= M <= 5000)
 */
public class BOJ1389 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static List<Integer>[] ADJ;
    static int[] DIST;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        int min = Integer.MAX_VALUE;
        int result = N;
        for(int i = 1; i <= N; i++) {
            int kevin = bfs(i);
            if(min > kevin) { // 가장 작은 케빈 베이컨을 가지는 인덱스
                min = kevin;
                result = i;
            }
        }

        sb.append(result);
    }

    private static int bfs(int start) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(start);

        Arrays.fill(DIST, -1);
        DIST[start] = 0;

        while(!que.isEmpty()) {
            int cur = que.poll();

            for(int next : ADJ[cur]) {
                if(DIST[next] != -1) continue;

                DIST[next] = DIST[cur] + 1;
                que.add(next);
            }
        }

        int kevin = 0;
        for(int i = 1; i <= N; i++) {
            kevin += DIST[i];
        }

        return kevin;
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            ADJ[from].add(to);
            ADJ[to].add(from);
        }

        DIST = new int[N + 1];
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
