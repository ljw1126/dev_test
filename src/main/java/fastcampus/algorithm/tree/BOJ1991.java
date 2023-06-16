package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 초기화시 2차원 배열 선언 생각 못함
 *
 */
public class BOJ1991 {

    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[][] TREE;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        TREE = new int[27][2];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = (st.nextToken().charAt(0) - 'A');
            for(int j = 0; j < 2; j++) {
                char child = st.nextToken().charAt(0);

                if(child != '.') TREE[parent][j] = (child - 'A');
                else TREE[parent][j] = -1;
            }
        }
    }

    static void preOrder(int x) {
        if(x == -1) return;
        sb.append((char)(x + 'A')); // 부모
        preOrder(TREE[x][0]); // 왼쪽
        preOrder(TREE[x][1]); // 오른쪽
    }

    static void inOrder(int x) {
        if(x == -1) return;

        inOrder(TREE[x][0]); // 왼쪽
        sb.append((char)(x + 'A')); // 부모
        inOrder(TREE[x][1]); // 오른쪽
    }

    static void postOrder(int x) {
        if(x == -1) return;

        postOrder(TREE[x][0]); // 왼쪽
        postOrder(TREE[x][1]); // 오른쪽
        sb.append((char)(x + 'A')); // 부모
    }

    static void pro() {
        int rootNode = 0; // A의 유니코드 0
        preOrder(rootNode);  // 전위 순회 (부모) (왼쪽) (오른쪽)
        sb.append("\n");

        inOrder(rootNode); // 중위 순회 (왼쪽)(부모)(오른쪽)
        sb.append("\n");

        postOrder(rootNode);

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
