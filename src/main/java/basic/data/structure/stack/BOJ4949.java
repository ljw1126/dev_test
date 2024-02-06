package basic.data.structure.stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 균형잡힌 세상(실버4)
 * https://www.acmicpc.net/problem/4949
 */
public class BOJ4949 {
    private static final StringBuilder sb = new StringBuilder();
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String NEW_LINE = System.lineSeparator();
    private static final Character OPEN_BRACE = '[';
    private static final Character CLOSE_BRACE = ']';
    private static final Character OPEN_PARENTHESIS = '(';
    private static final Character CLOSE_PARENTHESIS = ')';
    private static final Map<Character, Character> COUPLE_MAP;

    static  {
        COUPLE_MAP = new HashMap<>();
        COUPLE_MAP.put(')', '(');
        COUPLE_MAP.put(']', '[');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = "";
        while(!(line = br.readLine()).equals(".")) {
            String text = line.replaceAll("[a-zA-Z]*", "");
            text = text.replaceAll(" ", "");

            //System.out.println(text);

            boolean result = validParentheses(text);
            sb.append(result ? YES : NO).append(NEW_LINE);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    /**
     * 경우의 수
     * ] 또는 ) 인데 스택이 비어있으면 false
     * 스택에 값 이있는데 비교시 [ ) 이거나 ( ] 인 경우 false
     */
    private static boolean validParentheses(String text) {
        Deque<Character> parenthesis = new ArrayDeque<>();

        for(int i = 0; i < text.length(); i++) {
            char token = text.charAt(i);
            if(token == '.')
                continue;

            if(token == OPEN_PARENTHESIS || token == OPEN_BRACE) {
                parenthesis.push(token);
            } else if(token == CLOSE_BRACE || token == CLOSE_PARENTHESIS){
                if(!parenthesis.isEmpty() && parenthesis.peek() == COUPLE_MAP.get(token)) {
                    parenthesis.pop();
                } else {
                    return false;
                }
            }
        }

        return parenthesis.isEmpty();
    }
}
