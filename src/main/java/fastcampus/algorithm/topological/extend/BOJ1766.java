package fastcampus.algorithm.topological.extend;

import java.io.*;
import java.util.*;

/**
 * 문제집(골2)
 * https://www.acmicpc.net/problem/1766
 *
 * - 직접 풀이
 * - O(NlogN + E)
 * - 조건3에 따라 낮은 번호가 더 쉬운 문제이니 4, 3이 있으면 3이 먼저 풀이 되야 하여 우선순위 큐 사용
 * - while 문은 NlogN 만큼 add,poll 반복되고, 반복문은 M만큼 반복된다
 */
public class BOJ1766 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N, M;
    static List<Integer>[] ADJ;
    static int[] IN_DEGREE;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 문제 수
        M = inputProcessor.nextInt(); // 간선 정보

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
    }

    private static void pro() {
        Queue<Integer> que = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();
            sb.append(cur).append(" "); //결과 기록

            for(int next : ADJ[cur]) {
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
