package fastcampus.algorithm.topological.extend;

import java.io.*;
import java.util.*;

/**
 * 선수과목(골5)
 *
 * - 위상정렬 기본 문제
 * - 일반 큐를 사용했기 때문에 while문은 O(N)만큼 반복되고 for문은 O(M)만큼 반복됨
 * - 따라서 시간 복잡도 O(N + M)
 */
public class BOJ14567 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N, M;
    static List<Integer>[] ADJ;
    static int[] IN_DEGREE, COUNT;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        IN_DEGREE = new int[N + 1];
        for(int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();

            ADJ[from].add(to);
            IN_DEGREE[to] += 1;
        }

        COUNT = new int[N + 1];
    }

    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
                COUNT[i] = 1;
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();

            for(int next : ADJ[cur]) {
                IN_DEGREE[next] -= 1;
                if(IN_DEGREE[next] == 0) {
                    que.add(next);
                }

                COUNT[next] = COUNT[cur] + 1;
            }
        }

        for(int i = 1; i <= N; i++) {
            sb.append(COUNT[i]).append(" ");
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
