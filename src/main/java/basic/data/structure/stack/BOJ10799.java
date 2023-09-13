package basic.data.structure.stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * 쇠막대기(실버2)
 * https://www.acmicpc.net/problem/10799
 *
 * () : 레이저
 * ( ... ) 쇠막대기
 */
public class BOJ10799 {
    static StringBuilder sb = new StringBuilder();

    static String input;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine(); // 입력

        // 이전 막대가 "(" 가 아니고, 쇠막대기의 "("가 존재할 경우
        // () : 레이저
        // 레이저 1 쏘면 쇠막대 +3개, 레이저 2 쏘면 쇠막대 +3개, 닫으면서 쇠막대 2개 남음
        // 신규 쇠막대 1추가, 레이저 쏴서 +3추가, 닫기 -1 해서 쇠막대 2개, +1
        // 레이저 +2 , 종료하면서 + 1, + 1

        Stack<String> stack = new Stack();
        String[] parentheses = input.split("");

        int result = 0;
        String prev = "";
        for(String p : parentheses) {
            if("(".equals(p)) {
                stack.push(p);
                prev = p;
            } else { // 레이저이거나 닫는 괄호인 경우
                stack.pop();

                if("(".equals(prev)) result += stack.size();
                else result += 1;

                prev = p;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
