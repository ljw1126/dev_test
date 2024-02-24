package fastcampus.algorithm.tree.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 트리(골드2)
 * https://www.acmicpc.net/problem/4256
 *
 * - 전위/중위 순회가 주어질때 후위 순회 구하라
 * - 분할 정복 문제
 * - 전위 순회의 경우 항상 맨앞에 루트 노드가 존재함
 * - 중위 순회는 왼쪽-루트-오른쪽, 루트를 기준으로 왼쪽/오른쪽 서브 트리가 나눠짐
 * - 이에 따라 루트 인덱스구하면 길이로 왼쪽/오른쪽 서브트리를 각각 분할할 수 있다
 */
public class BOJ4256 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String BLANK = " ";
    static String NEW_LINE = System.lineSeparator();
    static int T;
    static int N;
    static int[] PRE_ORDER, IN_ORDER, INORDER_INDEX;

    public static void main(String[] args) throws IOException {
        T = inputProcessor.nextInt();
        while(T > 0) {
            input();
            pro();
            sb.append(NEW_LINE);
            T -= 1;
        }

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        PRE_ORDER = new int[N + 1];
        IN_ORDER = new int[N + 1];
        INORDER_INDEX = new int[N + 1];

        // 전위 순회
        for(int i = 1; i <= N; i++) {
            PRE_ORDER[i] = inputProcessor.nextInt();
        }

        // 중위 순회
        for(int i = 1; i <= N; i++) {
            IN_ORDER[i] = inputProcessor.nextInt();
        }

        // 중위 순회에서 루트를 기준으로 왼쪽/오른쪽 서브트리 분할 하기 위해 기록
        for(int i = 1; i <= N; i++) {
            INORDER_INDEX[IN_ORDER[i]] = i;
        }
    }

    private static void pro() {
        divide(1, N, 1, N);
    }

    private static void divide(int preS, int preE, int inS, int inE) {
        // 종료 조건
        if(preE < 1 || preE < preS || inE < 1 || inE < inS) return;

        int root = PRE_ORDER[preS];
        int rootIdx = INORDER_INDEX[root];

        int len = rootIdx - inS;

        // 왼쪽 서브 트리
        divide(preS + 1, preS + len, inS, rootIdx - 1);

        // 오른쪽 서브 트리
        divide(preS + len + 1, preE, rootIdx + 1, inE);

        sb.append(root).append(BLANK);
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
