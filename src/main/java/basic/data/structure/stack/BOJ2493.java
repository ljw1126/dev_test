package basic.data.structure.stack;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 탑(골드5)
 * https://www.acmicpc.net/problem/2493
 *
 * 직접풀이 - 한시간
 *
 * 예제 입력
 * 5
 * 6 9 5 7 4
 * 
 * 예제 결과
 * 0 0 2 2 4
 *
 * 반례 입력
 * 6
 * 9 4 6 3 7 8
 *
 * 반례 출력
 * 0 1 1 3 1 1
 *
 */
public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String data = br.readLine();
        StringTokenizer st = new StringTokenizer(data);

        int[] result = new int[N];
        Deque<Node> stack = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            if(i == 0) {
                stack.push(new Node(i + 1, height));
                continue;
            }

            while(!stack.isEmpty()) {
                Node peek = stack.peek();
                if(peek.height > height) {
                    result[i] = peek.idx;
                    break;
                }

                stack.pop();
            }

            stack.push(new Node(i + 1, height));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new OutputView().print(result));
        bw.flush();
        bw.close();
    }

    public static class OutputView {
        public OutputView() {}

        public String print(int[] result) {
            StringBuilder sb = new StringBuilder();
            for(int value : result) {
                sb.append(value).append(" ");
            }
            return sb.toString();
        }
    }

    public static class Node {
        int idx;
        int height;

        public Node(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
}
