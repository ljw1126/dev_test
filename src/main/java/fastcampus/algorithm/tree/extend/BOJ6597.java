package fastcampus.algorithm.tree.extend;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * - 처음 중위 순회의 루트 인덱스를 빠르게 찾기 위해 Map 자료 구조 사용
 * - 근데 알파벳이 26개 밖에 안되니 거의 상수시간에 풀 수 있으므로 반복문 사용하도록 변경
 */
public class BOJ6597 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();

    static int[] PRE_ORDER, IN_ORDER;


    public static void main(String[] args) throws IOException {
        while(true) {
            String input = inputProcessor.nextLine();
            if(input == null || "".equals(input)) break;

            input(input.trim());
            pro();
            sb.append(NEW_LINE);
        }

        output();
    }

    private static void input(String data) {
        String[] orders = data.split(" ");
        String preorder = orders[0];
        String inorder = orders[1];

        PRE_ORDER = new int[preorder.length()];
        IN_ORDER = new int[inorder.length()];

        for(int i = 0; i < preorder.length(); i++) {
            PRE_ORDER[i] = preorder.charAt(i) - 'A';
        }

        for(int i = 0; i < inorder.length(); i++) {
            IN_ORDER[i] = inorder.charAt(i) - 'A';
        }

    }

    private static void divide(int preS, int preE, int inS, int inE) {
        // 종료 조건
        if(preE < 0 || inE < 0 || preE < preS || inE < inS) return;

        int root = PRE_ORDER[preS];
        int rootIdx = find(root, inS, inE);

        int len = rootIdx - inS; // 중위순회에서 왼쪽 서브트리의 길이 구함

        // 왼쪽 서브 트리
        divide(preS + 1, preS + len, inS, rootIdx - 1);

        // 오른쪽 서브 트리
        divide(preS + len  + 1, preE, rootIdx + 1, inE);

        sb.append((char)(root + 'A'));
    }

    private static int find(int root, int inS, int inE) {
        for(int i = inS; i <= inE; i++) {
            if(IN_ORDER[i] == root) {
                return i;
            }
        }

        return -1;
    }

    private static void pro() {
        int len = PRE_ORDER.length - 1;
        divide(0, len, 0, len);
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
