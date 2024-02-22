package fastcampus.algorithm.topological;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 줄세우기(골3)
 * https://www.acmicpc.net/problem/2252
 *
 * - 위상 정렬 문제
 * - indegree 카운팅 배열을 하나 선언해두고 사용하는 것을 생각 못함
 * - O(V + E)
 */
public class BOJ2252 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static List<Integer>[] ADJ;
    static int[] IN_DEGREE;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        IN_DEGREE = new int[N + 1];
        ADJ = new ArrayList[N + 1]; // indegree -> 들어가는 방향만 고려
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ[from].add(to);
            IN_DEGREE[to] += 1;
        }
    }

    private static void pro() {
        bfs();
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();

        // O(V)
        // 제일 앞에 정렬될 수 있는 정점 찾기 (=들어오는 간선이 없는 경우)
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
            }
        }

        // Queue 추가/삭제 : O(1)
        // while : O(E)
        while(!que.isEmpty()) {
            int node = que.poll();
            sb.append(node).append(" ");

            for(int next : ADJ[node]) {
                IN_DEGREE[next] -= 1;

                if(IN_DEGREE[next] == 0) {
                    que.add(next);
                }
            }
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
