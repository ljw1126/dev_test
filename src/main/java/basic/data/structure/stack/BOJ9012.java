package basic.data.structure.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 괄호 (실버4)
 * https://www.acmicpc.net/problem/9012
 */
public class BOJ9012 {
    static StringBuilder sb = new StringBuilder();

    static int T;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while(T > 0) {
            T -= 1;

            Stack<String> stack = new Stack<>();
            String line = br.readLine();
            String[] parentheses = line.split("");

            boolean isValid = true;
            for(String p : parentheses) {
                if("(".equals(p)) {
                    stack.push(p);
                    continue;
                }

                if(stack.isEmpty()) {
                    isValid = false;
                    break;
                }

                stack.pop();
            }

            // "NO"인 경우
            // 여는 괄호가 닫는 괄호보다 더 많을 때
            // 여는 괄호가 없는데 pop을 할 때 empty stack exception 발생
            if(isValid && stack.size() == 0) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
