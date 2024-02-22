package fastcampus.algorithm.topological.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *장난감 조힙(골드2)
 * https://www.acmicpc.net/problem/2637
 *
 * - 직접 풀이함
 * 각 라인이 노드 번호이고
 * - 작업시간 선행작업수 (선행작업 노드..) 이렇게 주어진다
 * - (중요)기본 부품이 예시가 1,2,3,4 이고, 직접 구해야 하는 듯함****
 * - 들어오는 노드가 없는게 기본 부품으로 풀이 (IN_DEGREE[i] == 0)
 *
 *
 * dp bottom-up 풀이 방식이 인상깊다
 * https://www.acmicpc.net/source/73129619
 *
 * 문제 제시된 것처럼 풀이한 좋은 코드
 * https://www.acmicpc.net/source/71486061
 */
public class BOJ2637 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String RESULT_FORMAT = "%d %d\n";
    static int N, M;
    static List<Node>[] ADJ;
    static int[] IN_DEGREE;
    static Material[] MATERIAL_ARR;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        bfs();

        Material material = MATERIAL_ARR[N]; // 완제품 = N
        Set<Integer> keys = material.keySet();
        for(int key : keys) {
            sb.append(String.format(RESULT_FORMAT, key, material.get(key)));
        }
    }

    private static void bfs() {
        Deque<Integer> que = new ArrayDeque<>();

        // 예시에 기본 부품이 1,2,3,4 라고 했을 뿐, 직접 구해야 함.. IN_DEGREE가 0을 기본 부품으로 선정
        Set<Integer> basic = new HashSet<>();
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
                basic.add(i);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();

            for(Node next : ADJ[cur]) {
                IN_DEGREE[next.to] -= 1;

                if(basic.contains(cur)) {
                    MATERIAL_ARR[next.to].supply(cur, next.amount);
                } else {
                    MATERIAL_ARR[next.to].supply(MATERIAL_ARR[cur], next.amount);
                }

                if(IN_DEGREE[next.to] == 0) {
                    que.add(next.to);
                }
            }
        }
    }

    private static class Material {
        Map<Integer, Integer> supply;

        public Material() {
            supply = new TreeMap<>(); // HashMap으로 하니 틀림
        }

        public Set<Integer> keySet() {
            return supply.keySet();
        }

        public int get(int key) {
            return supply.get(key);
        }

        public void supply(int no, int amount) {
            supply.put(no, supply.getOrDefault(no, 0) + amount);
        }

        public void supply(Material material, int amount) {
            Set<Integer> keys = material.keySet();
            for(int key : keys) {
                supply.put(key, supply.getOrDefault(key, 0) + material.get(key) * amount);
            }
        }
    }

    private static class Node {
        int to;
        int amount;
        public Node(int to, int amount) {
            this.to = to;
            this.amount = amount;
        }
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 완제품 번호
        M = inputProcessor.nextInt(); // 부품들간의 관계 (간선 정보)

        // 1 ~ N - 1까지 기본 부품(1~4) or 중간 부품 - 기본 부품에 대한 카운팅이 필요함
        ADJ = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ADJ[i] = new ArrayList<>();
        }

        IN_DEGREE = new int[N + 1];
        // x를 만들기 위해서 y가 k개 필요하다 -> y 가 k개 만들어져야 x를 만들 수 있다
        for(int i = 1; i <= M; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();
            int k = inputProcessor.nextInt();

            ADJ[y].add(new Node(x, k)); // x를 만들려면 y가 k개 필요하다, y는 x에 k개 공급해줘야 한다
            IN_DEGREE[x] += 1;
        }

        MATERIAL_ARR = new Material[N + 1];
        for(int i = 1; i <= N; i++) {
            MATERIAL_ARR[i] = new Material();
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
