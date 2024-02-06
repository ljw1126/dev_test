package basic.data.structure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 좋은 단어 (실버4)
 */
public class BOJ3986 {

    public static class MyStack {
        String word;
        Deque<String> stack;
        public MyStack(String word) {
            this.word = word;
            this.stack = new ArrayDeque<>();
        }

        public void logic() {
            String[] tokens = this.word.split("");
            for(String token : tokens) {
                if(!isEmpty() && token.equals(peek())) {
                    pop();
                } else {
                    push(token);
                }
            }
        }

        public void push(String s) {
            this.stack.push(s);
        }


        public String pop() {
            return this.stack.pop();
        }

        public String peek() {
            return this.stack.peek();
        }

        public boolean isEmpty() {
            return this.stack.isEmpty();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        MyStack[] data = new MyStack[N];

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            data[i] = new MyStack(word);
        }

        int result = 0;
        for(MyStack myStack : data) {
            myStack.logic();
            if(myStack.isEmpty())
                result += 1;
        }

        System.out.println(result);
    }
}
