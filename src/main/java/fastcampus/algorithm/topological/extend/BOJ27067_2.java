package fastcampus.algorithm.topological.extend;

import java.io.*;
import java.util.*;

/**
 * 데이터 순서 복원(골드2)
 * https://www.acmicpc.net/problem/27067
 * 
 * 가중치로 구하는 방식 참고 하여 풀이
 * - 번호별 인덱스를 구해서 가중치를 구한다
 * - 이때 항상 변형된 데이터는 원래의 위치보다 앞으로 가므로,  가장 가중치가 낮은 값이 변경되었을 가능성이 크다
 * - 그래서 가중치는 구할 때 3개의 값중 최소값을 빼준다
 */
public class BOJ27067_2 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static List<Integer>[] DATA;
    static int N;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        DATA = new ArrayList[4];
        for(int i = 1; i <= 3; i++) {
            DATA[i] = new ArrayList<>();
        }

        for(int i = 1; i <= 3; i++) {
            for(int j = 1; j <= N; j++) {
                DATA[i].add(inputProcessor.nextInt());
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int no;
        int weight;

        public Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight; // 오름차순
        }
    }

    // 변경된 데이터는 원래의 위치보다 앞으로 가있기 때문에 가장 가중치가 낮은 값이 변경되어있을 확률이 크다
    // 그래서 최소값을 빼준다
    private static void pro() {
        Queue<Node> que = new PriorityQueue<>();

        for(int i = 1; i <= N; i++) {
            int value = DATA[1].get(i - 1);
            int aw = i;
            int bw = DATA[2].indexOf(value) + 1;
            int cw = DATA[3].indexOf(value) + 1;
            int weight = aw + bw + cw - Math.min(aw, Math.min(bw, cw));

            que.add(new Node(value, weight));
        }

        while(!que.isEmpty()) {
            Node node = que.poll();
            sb.append(node.no).append(" ");
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
