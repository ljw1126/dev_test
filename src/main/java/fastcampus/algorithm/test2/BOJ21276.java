package fastcampus.algorithm.test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *  계보 복원가 호석(골3)
 *  https://www.acmicpc.net/problem/21276
 *
 *  - 직접 풀이
 *  - 해답이 더 깔끔해서 고침
 *  - 조상의 0명인 경우 root 트리를 구하면서 child 자식 정보를 채운다
 *    - 조상이 있다 => 현재 노드의 depth를 구할 수 있다
 *    - i노드의 조상이 있다는 것은 조상 노드 j의 자식 중 하나라는 뜻이다
 *    - 이때 부모 - 자식 관계의 depth 차이는 1이다
 *  - 시간 복잡도는 O(N + M), 정렬은 신경쓰지 않는 듯
 *
 */
public class BOJ21276 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();
    static String BLANK = " ";

    static int N, M;

    static String[] NAMES;
    static List<Integer>[] ANCESTOR;
    static List<String>[] CHILD;
    static Map<String, Integer> NAME_IDX_MAP = new HashMap<>();


    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        List<String> root = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(ANCESTOR[i].isEmpty()) {
                root.add(NAMES[i]);
            }

            for(int j : ANCESTOR[i]) {
                int depthI = ANCESTOR[i].size(); // i에 조상이 있으면, i를 누군가의 자식이라는 의미
                int depthJ = ANCESTOR[j].size();

                if(depthJ == depthI - 1) {
                    CHILD[j].add(NAMES[i]); // 부모 j, 자식 i
                }
            }
        }

        Collections.sort(root);
        sb.append(root.size()).append(NEW_LINE);

        for(String name : root) sb.append(name).append(BLANK);

        sb.append(NEW_LINE);

        Arrays.sort(NAMES, 1, N +1);
        for(int i = 1; i <= N; i++) {
            List<String> childs = CHILD[NAME_IDX_MAP.get(NAMES[i])];
            Collections.sort(childs);

            sb.append(NAMES[i]).append(BLANK);
            sb.append(childs.size()).append(BLANK);

            for(String child : childs) {
                sb.append(child).append(BLANK);
            }

            sb.append(NEW_LINE);
        }
    }

    private static void input() {
        N = inputProcessor.nextInt();
        NAMES = new String[N + 1];
        for(int i = 1; i <= N; i++) {
            String name= inputProcessor.next();
            NAME_IDX_MAP.put(name, i);
            NAMES[i] = name;
        }

        ANCESTOR = new ArrayList[N + 1];
        CHILD = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            ANCESTOR[i] = new ArrayList<>();
            CHILD[i] = new ArrayList<>();
        }


        M = inputProcessor.nextInt();
        for(int i = 1; i <= M; i++) {
            String x = inputProcessor.next(); // 자손
            String y = inputProcessor.next(); // 조상

            // 부모 - 자식
            ANCESTOR[NAME_IDX_MAP.get(x)].add(NAME_IDX_MAP.get(y));
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
