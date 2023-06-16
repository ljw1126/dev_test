package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이진 검색 트리 (골드5) https://www.acmicpc.net/problem/5639
 *
 * 이진 검색 트리를 구현하고 재귀 함수로 탐색하여 후위 순회(왼쪽, 오른쪽, 부모 순) 결과 표출
 */
public class BOJ5639 {
    static StringBuilder sb = new StringBuilder();

    static Node ROOT;
    static class Node {
        int value;
        Node left, right;

        public Node(int _value) {
            this.value = _value;
        }

        void insert(int _value) {
            if(this.value > _value) { // 왼쪽
                if(this.left == null) this.left = new Node(_value);
                else this.left.insert(_value);
            } else {
                if(this.right == null) this.right = new Node(_value);
                else this.right.insert(_value);
            }
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = "";
        while (true) {
            input = br.readLine();
            if(input == null || "".equals(input)) break;

            st = new StringTokenizer(input);
            if(ROOT == null) {
                ROOT = new Node(Integer.parseInt(st.nextToken()));
            } else {
                ROOT.insert(Integer.parseInt(st.nextToken()));
            }
        }
    }

    static void postOrder(Node node) {
        if(node == null) return;

        postOrder(node.left); // 왼쪽
        postOrder(node.right); // 오른쪽
        sb.append(node.value).append("\n"); // 부모
    }
    static void pro() {
        postOrder(ROOT);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
