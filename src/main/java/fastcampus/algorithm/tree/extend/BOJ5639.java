package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 이진 검색 트리 (골드5) https://www.acmicpc.net/problem/5639
 *
 * 이진 검색 트리를 구현하고 재귀 함수로 탐색하여 후위 순회(왼쪽, 오른쪽, 부모 순) 결과 표출
 */
public class BOJ5639 {
    static StringBuilder sb = new StringBuilder();
    static String NEW_LINE = System.lineSeparator();
    static Node ROOT;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }
    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();

        while(true) {
            String input = inputProcessor.nextLine();
            if(input == null || input.equals("")) {
                break;
            }

            int value = Integer.parseInt(input.trim());
            if(ROOT == null) {
                ROOT = new Node(value);
            } else {
                ROOT.add(value);
            }
        }
    }

    private static void pro() {
        postorder(ROOT);
    }

    private static void postorder(Node node) {
        if(node == null) return;

        postorder(node.left());
        postorder(node.right());
        sb.append(node.value()).append(NEW_LINE);
    }

    private static class Node {
        private Node left;
        private Node right;

        private int value;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public void add(int other) {
            if(other < value) {
                if(left == null) {
                    left = new Node(other);
                } else {
                    left.add(other);
                }
            } else {
                if(right == null) {
                    right = new Node(other);
                } else {
                    right.add(other);
                }
            }
        }

        public int value() {
            return value;
        }

        public Node left() {
            return left;
        }

        public Node right() {
            return right;
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
