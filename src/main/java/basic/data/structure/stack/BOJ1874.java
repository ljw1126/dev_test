package basic.data.structure.stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 스택 수열(실버2)
 * - 최대 100,000
 */
public class BOJ1874 {
    private static final String NEW_LINE = System.lineSeparator();
    private static final StringBuilder sb = new StringBuilder();
    private static final String PLUS_MARK = "+";
    private static final String MINUS_MARK = "-";
    private static final String NO = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> stack = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());

        int start = 1;
        for(int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());

            while(value >= start) {
                stack.push(start);
                start += 1;
                sb.append(PLUS_MARK).append(NEW_LINE);
            }

            if(stack.peek() == value) {
                stack.pop();
                sb.append(MINUS_MARK).append(NEW_LINE);
            } else {
                sb.delete(0, sb.length());
                sb.append(NO);
                break;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
