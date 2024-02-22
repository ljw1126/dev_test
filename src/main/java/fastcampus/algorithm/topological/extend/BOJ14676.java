package fastcampus.algorithm.topological.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 영우는 사기군?(골3)
 * https://www.acmicpc.net/problem/14676
 *
 * - 직접 풀이
 * - 위상 정렬
 */
public class BOJ14676 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, M, K;
    static List<Integer>[] ADJ;
    static int[] IN_DEGREE, COUNT;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    // 1 a : 영우가 a번 건물을 1개 건설함
    // 2 a : 영우의 a번 건물이 파괴됨
    private static void pro() {
        boolean cheat = false;
        for(int i = 1; i <= K; i++) {
            int cmd = inputProcessor.nextInt(); // 1 : 건설, 2 : 파괴
            int target = inputProcessor.nextInt();

            if(cmd == 1 && !build(target)) {
                cheat = true;
                break;
            }

            if(cmd == 2 && !destory(target)) {
                cheat = true;
                break;
            }
        }

        if(cheat) {
            sb.append("Lier!");
        } else {
            sb.append("King-God-Emperor");
        }
    }

    private static boolean destory(int target) {
        if(COUNT[target] < 1) return false;

        COUNT[target] -= 1;
        if(COUNT[target] == 0) {
            for (int next : ADJ[target]) {
                IN_DEGREE[next] += 1;
            }
        }

        return true;
    }

    private static boolean build(int target) {
        if(IN_DEGREE[target] != 0) return false;

        COUNT[target] += 1;
        if(COUNT[target] == 1) {
            for(int next : ADJ[target]) {
                IN_DEGREE[next] -= 1;
            }
        }

        return true;
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 건물 종류 개수(노드)
        M = inputProcessor.nextInt(); // 건물 사이 관계의 개수 (간선)
        K = inputProcessor.nextInt(); // 영우의 게임 정보

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
