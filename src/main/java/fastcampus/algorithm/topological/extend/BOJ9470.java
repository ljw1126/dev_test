package fastcampus.algorithm.topological.extend;

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
 *
 * - 직접 풀이 못함
 * - 핵심은 IN_DEGREE[next] == 0이 되었을 때
 * - 최대값의 카운팅이 2이상이면 order + 1 해주는거 였다
 */
public class BOJ9470 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String RESULT_FORMAT = "%d %d\n";
    static int T, K, M, P;
    static int[] IN_DEGREE;
    static List<Integer>[] ADJ;

    static Node[] NODES;

    public static void main(String[] args) throws IOException {
        T = inputProcessor.nextInt();
        for(int i = 1; i <= T; i++) {
            input();

            pro();

            sb.append(String.format(RESULT_FORMAT, K, NODES[M].order));
        }

        output();
    }

    private static class Node {
        int order;
        int count;

        public Node(int order, int count) {
            this.order = order;
            this.count = count;
        }

        public boolean isSame(Node node) {
            return this.order == node.order;
        }

        public boolean greatThan(Node node) {
            return this.order > node.order;
        }

        public void countUp() {
            this.count += 1;
        }
    }

    private static void pro() {
        Deque<Integer> que = new ArrayDeque<>();

        for(int i = 1; i <= M; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
                NODES[i] = new Node(1, 0);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();

            for(int next : ADJ[cur]) {
                IN_DEGREE[next] -= 1;

                if(NODES[next] == null || NODES[cur].greatThan(NODES[next])) {
                    NODES[next] = new Node(NODES[cur].order, 1);
                } else if(NODES[cur].isSame(NODES[next])) {
                    NODES[next].countUp();
                }

                if(IN_DEGREE[next] == 0) {
                    if(NODES[next].count >= 2) {
                        NODES[next] = new Node(NODES[next].order + 1, 1);
                    }

                    que.add(next);
                }
            }
        }
    }
    private static void input() {
        K = inputProcessor.nextInt(); // 테스트 케이스 번호
        M = inputProcessor.nextInt(); // 노드의 수(바다와 만나는 노드, 결과값)
        P = inputProcessor.nextInt(); // 간선의 수

        ADJ = new ArrayList[M + 1];
        for(int i = 1; i <= M; i++) {
            ADJ[i] = new ArrayList<>();
        }

        IN_DEGREE = new int[M + 1];
        for(int i = 1; i <= P; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            ADJ[a].add(b);
            IN_DEGREE[b] += 1;
        }

        NODES = new Node[M + 1];
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
