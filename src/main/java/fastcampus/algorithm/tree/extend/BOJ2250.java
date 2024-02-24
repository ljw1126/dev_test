package fastcampus.algorithm.tree.extend;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 트리의 높이와 너비(골2)
 * https://www.acmicpc.net/problem/2250
 *
 * - 직접 풀이
 * - 중위 순회 결과가 열의 번호와 동일하다는거 파악
 * - Dfs로 노드별 Depth 구함
 *
 * 중위 순회를 하면서 열번호를 갱신할 수 있는 방법이 없을까?? 따로구하는게 최선일까??
 * - inorder 함수를 돌면서 갱신하도록 함
 * - 정적 멤버 필드 선언시 멀티 스레드에서 안정적이지 못할 거라 생각
 */
public class BOJ2250 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N, ROOT, MAX_DEPTH;
    static int[] DEPTH;
    static int[][] BINARY_TREE;
    static int[][] COL;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();

        BINARY_TREE = new int[N + 1][2]; //[N][0 or 1] 0 : 왼쪽 자식, 1 : 오른쪽 자식
        for(int i = 1; i <= N; i++) {
            Arrays.fill(BINARY_TREE[i], -1);
        }

        int[] p = new int[N + 1]; // 부모 노드 기록
        Arrays.fill(p, -1);

        for(int i = 1; i <= N; i++) {
            int parent = inputProcessor.nextInt();
            int left = inputProcessor.nextInt();
            int right = inputProcessor.nextInt();

            if(left != -1) {
                BINARY_TREE[parent][0] = left;
                p[left] = parent;
            }
            if(right != -1) {
                BINARY_TREE[parent][1] = right;
                p[right] = parent;
            }
        }

        // 루트 노드 찾기
        for(int i = 1; i <= N; i++) {
            if(p[i] == -1) {
                ROOT = i;
                break;
            }
        }

        DEPTH = new int[N + 1];
    }


    private static void pro() {
        // 노드별 Depth 갱신
        dfs(ROOT, 1);

        //Depth별 최소값, 최대값을 기록하기 위한 자료구조
        //(0 : 최소값, 1 : 최대값), 노드가 일직선 생각했을때 Depth 최대는 N + 1
        COL = new int[MAX_DEPTH + 1][2];
        for(int i = 1; i <= MAX_DEPTH; i++) {
            COL[i][0] = N + 1; // 최소값을 구하기 위해 최대값 설정
        }

        inorder(ROOT, 0);

        result();
    }

    private static void result() {
        int level = 0;
        int width = 0;
        for(int i = 1; i <= MAX_DEPTH; i++) {
            int value = COL[i][1] - COL[i][0] + 1;
            if(width < value) {
                level = i;
                width = value;
            }
        }

        sb.append(level).append(" ").append(width);
    }

    // 중위 순회 : 왼쪽-루트-오른쪽
    private static int inorder(int node, int col) {
        if(node == -1) return col;

        col = inorder(BINARY_TREE[node][0], col);

        col += 1;
        int depth = DEPTH[node];
        COL[depth][0] = Math.min(COL[depth][0], col);
        COL[depth][1] = Math.max(COL[depth][1], col);

        //sb.append(node).append(" ");

        col = inorder(BINARY_TREE[node][1], col);

        return col;
    }

    private static void dfs(int node, int depth) {
        if(node == -1) return;

        DEPTH[node] = depth;
        MAX_DEPTH = Math.max(MAX_DEPTH, depth);

        int left = BINARY_TREE[node][0];
        dfs(left, depth + 1);

        int right = BINARY_TREE[node][1];
        dfs(right, depth + 1);
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
