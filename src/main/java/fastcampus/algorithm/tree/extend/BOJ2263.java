package fastcampus.algorithm.tree.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 트리의 순회(골1)
 * https://www.acmicpc.net/problem/2263
 *
 * - 직접 풀이 못함
 * - 성질과 분할 정복으로 풀이하는 문제였음 (큰문제를 풀기 위해 작은 문제로 나눔)
 * - postorder는 제일 마지막 인덱스에 항상 루트값이 존재함
 * - 루트값을 알 수 있기 때문에 inorder에서 루트 인덱스를 조회한다
 * - 루트 인덱스를 기준으로 왼쪽/오른쪽 서브 트리를 나눈다
 * - 작은 문제로 나누면서 preorder를 구한다
 *
 * - preorder : 루트 - 왼쪽 - 오른쪽
 * - inorder : 왼쪽 - 루트 - 오른쪽
 * - postorder : 왼쪽 - 오른쪽 - 루트
 */
public class BOJ2263 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String BLANK = " ";
    static int N;
    static int[] IN_ORDER, POST_ORDER, INORDER_IDX; // 중위 순회(왼쪽-루트-오른쪽), 후위 순회(왼쪽-오른쪽-루트*)

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        IN_ORDER = new int[N + 1];
        POST_ORDER = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            IN_ORDER[i] = inputProcessor.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            POST_ORDER[i] = inputProcessor.nextInt();
        }

        INORDER_IDX = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            INORDER_IDX[IN_ORDER[i]] = i;
        }
    }

    private static void pro() {
        divide(1, N, 1, N); // 분할 정복
    }

    private static void divide(int inS, int inE, int poS, int poE) {
        if(inE < 1 || inE < inS || poE < 1 || poE < poS) return;

        int rootValue = POST_ORDER[poE];
        int rootIndex = INORDER_IDX[rootValue];

        sb.append(rootValue).append(BLANK);

        int len = rootIndex - inS; // left 길이

        // 왼쪽 서브 트리
        divide(inS, rootIndex - 1, poS, poS + len - 1);

        // 오른쪽 서브 트리
        divide(rootIndex + 1, inE, poS + len, poE - 1);
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
