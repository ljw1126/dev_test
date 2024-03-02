package fastcampus.algorithm.test2;

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
 * 호석이 두마리 치킨
 *
 * 시간복잡도
 * BFS O(NM) + 조합 O(N^3)
 */
public class BOJ21278 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String BLANK = " ";

    static int N, M;
    static List<Integer>[] ADJ;
    static int[][] DIST;

    public static void main(String[] args) throws IOException {
        //input();

        floydWarshall();
        pro();

        output();
    }

    private static void floydWarshall() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();


        DIST = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(DIST[i], 101); // 최대치 조심
            DIST[i][i] = 0;
        }

        for(int i = 1; i <= M; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            DIST[a][b] = 1;
            DIST[b][a] = 1;
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    DIST[i][j] = Math.min(DIST[i][j], DIST[i][k] + DIST[k][j]);
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(DIST[i]));
        }
    }

    private static void pro() {
        // i에서 다른 노드까지 가는 최단 거리
        /*
        DIST = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            bfs(i);
        }
         */

        int n1 = 0;
        int n2 = 0;
        int time = Integer.MAX_VALUE;

        // i, j를 치킨집으로 세울 때
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;

                int sum = 0;
                for(int k = 1; k <= N; k++) {
                    if(i == k || j == k) continue;

                    sum += Math.min(DIST[i][k], DIST[j][k]); // k가 i나 j 치킨집 중 가장 거리
                }

                if(sum < time) {
                    n1 = i;
                    n2 = j;
                    time = sum;
                }
            }
        }

        sb.append(n1).append(BLANK).append(n2).append(BLANK).append(time * 2);
    }

    private static void bfs(int start) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(start);

        Arrays.fill(DIST[start], Integer.MAX_VALUE);
        DIST[start][start] = 0;

        while(!que.isEmpty()) {
            int cur = que.poll();

            for(int next : ADJ[cur]) {
                if(DIST[start][next] != Integer.MAX_VALUE) continue;
                if(DIST[start][next] > DIST[start][cur] + 1) {
                    DIST[start][next] = DIST[start][cur] + 1;
                    que.add(next);
                }
            }
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();

            ADJ[a].add(b);
            ADJ[b].add(a);
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
