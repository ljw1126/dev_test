package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 물통 (골드5) https://www.acmicpc.net/problem/2251
 *
 * 그래프에 대한 언질이 없음
 *
 * 상태(정점), 물을 부어서 다른 상태로 이동하는 것을 "간선"으로 정의
 *
 * 200 * 200 * 200 = 8 * 10^6 = 800만 가지
 *
 * 정점 개수  8 * 10^6
 * 간선 개수 8 * 10^6 * 6 ( A => B,C 붙는 경우, B => A,C붙는 경우, C => A, C붙는 경우)
 *
 * 시간복잡도 = 8 * 10^6
 */
public class BOJ2251 {
    static StringBuilder sb = new StringBuilder();

    static int A, B, C;
    static boolean[][][] VISIT;

    static int[] LIMIT;
    private static class Bottle {
        int[] arr;

        public Bottle(int[] arr) {
            this.arr = arr;
        }

        public Bottle move(int from, int to, int[] limit) {
            int[] _arr = new int[3];
            for(int i = 0; i < 3; i++) {
                _arr[i] = this.arr[i];
            }

            if(_arr[from] + _arr[to] <= limit[to]) {
                _arr[to] += _arr[from];
                _arr[from] = 0;
            } else {
                _arr[from] -= (limit[to] - _arr[to]);
                _arr[to] = limit[to];
            }

            return new Bottle(_arr);
        }
    }


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    /**
     * 초기 세번째 물통(c)만 가득 차 있다
     * 첫번쨰 물통이 비어 있을 때, 세번째 물통에 담겨있는 물의 양을 구하라
     */
    private static void pro() {
        bfs();
    }

    private static void bfs() {
        Deque<Bottle> que = new ArrayDeque<>();
        que.add(new Bottle(new int[] {0, 0, C}));
        VISIT[0][0][C] = true;

        List<Integer> result = new LinkedList<>();
        result.add(C);

        while(!que.isEmpty()) {
            Bottle cur = que.poll();

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(i == j) continue;

                    Bottle next = cur.move(i, j, LIMIT);
                    if(VISIT[next.arr[0]][next.arr[1]][next.arr[2]]) continue;

                    que.add(next);
                    VISIT[next.arr[0]][next.arr[1]][next.arr[2]] = true;

                    if(next.arr[0] == 0) {
                        result.add(next.arr[2]);
                    }
                }
            }
        }

        Collections.sort(result);
        for(int v : result) {
            sb.append(v).append(" ");
        }
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        A = inputProcessor.nextInt();
        B = inputProcessor.nextInt();
        C = inputProcessor.nextInt();

        VISIT = new boolean[201][201][201];
        LIMIT = new int[]{A, B, C};
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static class InputProcessor {
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
