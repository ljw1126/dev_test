package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21773 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int T, N;

    private static class Node implements Comparable<Node> {
        private int id;
        private int time;
        private int priority;

        public Node(int id, int time, int priority) {
            this.id = id;
            this.time = time;
            this.priority = priority;
        }

        public void decreaseTime() {
            this.time -= 1;
            this.priority -= 1;
        }

        public boolean isEnd() {
            return this.time == 0;
        }

        @Override
        public int compareTo(Node o) {
            if (this.priority != o.priority) return o.priority - this.priority;

            return this.id - o.id;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        T = inputProcessor.nextInt();
        N = inputProcessor.nextInt();
    }

    private static void pro() {
        Queue<Node> que = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            int a = inputProcessor.nextInt(); // id
            int b = inputProcessor.nextInt(); // 필요 시간
            int c = inputProcessor.nextInt(); // 초기 우선 순위

            que.add(new Node(a, b, c));
        }

        while (T > 0) {
            T -= 1;
            Node cur = que.poll();
            sb.append(cur.id).append("\n");

            cur.decreaseTime(); // 실행 시간 감소

            if (!cur.isEnd()) {
                que.add(cur);
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
            while (st == null || !st.hasMoreElements()) {
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
