package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 가운데에서 만나기 (골4)
 * https://www.acmicpc.net/problem/21940
 * <p>
 * 플로이드 워셜 : O(N^3)
 * 최대치 int 범위내 (N * T)
 * <p>
 * 친구들의 왕복시간 중 "최대"가 "최소"가 되는 도시 X를 선택한다
 * -> 최대가 최소가 된다는게
 * -> i번 도시로 친구들이 모두 이동할 때 최대값을 구하고
 * -> 그 최대값이 최소가 되는 i번 도시를 구함
 */
public class BOJ21940 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MAX_DIST = 200001;
    private static int N, M, K;
    private static int[][] DIST;
    private static List<Integer> PERSON = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 도시의 개수(노드)
        M = inputProcessor.nextInt(); // 도로의 개수(간선)

        DIST = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(DIST[i], MAX_DIST);
            DIST[i][i] = 0;
        }

        for (int i = 1; i <= M; i++) {
            int from = inputProcessor.nextInt();
            int to = inputProcessor.nextInt();
            int cost = inputProcessor.nextInt();

            DIST[from][to] = cost;
        }

        K = inputProcessor.nextInt(); // 총 인원
        for (int i = 1; i <= K; i++) {
            PERSON.add(inputProcessor.nextInt());
        }
    }

    private static void pro() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    DIST[i][j] = Math.min(DIST[i][j], DIST[i][k] + DIST[k][j]);
                }
            }
        }

        int min = MAX_DIST;
        List<Integer> cities = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int dist = 0;
            for (int friend : PERSON) {
                if (i == friend) continue;

                dist = Math.max(dist, DIST[friend][i] + DIST[i][friend]);
            }

            if (dist < min) { // 최대가 최소가 되는
                cities.clear();

                min = dist;
                cities.add(i);
            } else if (dist == min) {
                cities.add(i);
            }
        }

        Collections.sort(cities);
        cities.forEach(v -> sb.append(v).append(" "));
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
