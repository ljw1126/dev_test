package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/1991
 * 초기화시 2차원 배열 선언 생각 못함
 */
public class BOJ1991 {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] ALPHABET;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }
    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();

        ALPHABET = new int[27][2];
        for(int i = 0; i <= N; i++) {
            Arrays.fill(ALPHABET[i], -1);
        }

        for(int i = 1; i <= N; i++) {
            char root = inputProcessor.next().charAt(0);
            char left = inputProcessor.next().charAt(0);
            char right = inputProcessor.next().charAt(0);

            if(left != '.') {
                ALPHABET[root - 'A'][0] = left - 'A';
            }

            if(right != '.') {
                ALPHABET[root - 'A'][1] = right - 'A';
            }
        }
    }

    private static void pro() {
        preOrder(0);
        sb.append("\n");

        inOrder(0);
        sb.append("\n");

        postOrder(0);
        sb.append("\n");
    }

    // 전위 순회 : 루트 - 왼쪽 - 오른쪽
    private static void preOrder(int node) {
        if(node == -1) return;

        sb.append((char)(node + 'A'));
        preOrder(ALPHABET[node][0]);
        preOrder(ALPHABET[node][1]);
    }

    // 중위 순회 : 왼쪽 - 루트 - 오른쪽
    private static void inOrder(int node) {
        if(node == -1) return;

        inOrder(ALPHABET[node][0]);
        sb.append((char)(node + 'A'));
        inOrder(ALPHABET[node][1]);
    }

    // 후위 순회 : 왼쪽 - 오른쪽 - 루트
    private static void postOrder(int node) {
        if(node == -1) return;

        postOrder(ALPHABET[node][0]);
        postOrder(ALPHABET[node][1]);
        sb.append((char)(node + 'A'));
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
