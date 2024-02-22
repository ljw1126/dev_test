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
 * 작업(골드4)
 * https://www.acmicpc.net/problem/2056
 *
 * - 최대치 10^6
 * - 위상 정렬 기본 문제
 */
public class BOJ2056 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N;
    static List<Integer>[] ADJ;
    static int[] TIME, IN_DEGREE;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        bfs();
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();
        int[] tDone = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
                tDone[i] = TIME[i];
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();

            for(int next : ADJ[cur]) {
                IN_DEGREE[next] -= 1;

                if(IN_DEGREE[next] == 0) que.add(next);

                tDone[next] = Math.max(tDone[next], tDone[cur] + TIME[next]);
            }
        }

        // 결과 출력
        int result = 0;
        for(int i = 1; i <= N; i++) {
            if(result < tDone[i]) result = tDone[i];
        }
        sb.append(result);
    }

    private static void input() {
        N = inputProcessor.nextInt();

        TIME = new int[N + 1];
        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        IN_DEGREE = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            int time = inputProcessor.nextInt();
            TIME[i] = time; // i번 작업에 걸리는 시간

            int relatedNodeCount = inputProcessor.nextInt(); // 선행 작업 수
            for(int j = relatedNodeCount; j > 0; j--) {
                int prev = inputProcessor.nextInt(); // 선행되야 할 작업

                ADJ[prev].add(i);
                IN_DEGREE[i] += 1; // 현재 노드의 선행 작업 개수
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
