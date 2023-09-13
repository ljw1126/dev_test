package basic.data.structure.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10828 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        MyStack myStack = new MyStack(N);
        while(N > 0) {
            N -= 1;

            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd) {
                case "push" : {
                    myStack.push(Integer.parseInt(st.nextToken()));
                    break;
                }
                case "pop" : {
                    sb.append(myStack.pop()).append("\n");
                    break;
                }
                case "size" : {
                    sb.append(myStack.size()).append("\n");
                    break;
                }
                case "empty" : {
                    sb.append(myStack.empty()).append("\n");
                    break;
                }
                case "top" : {
                    sb.append(myStack.top()).append("\n");
                    break;
                }
            }

        }
    }

    static class MyStack {
        private int[] data;
        private int size;

        public MyStack(int capacity) {
            data = new int[capacity];
            size = 0;
        }

        public void push(int value) {
            data[size] = value;
            size += 1;
        }

        public int pop() {
            if(this.empty() == 1) return -1;

            int result = data[size - 1];
            data[size - 1] = 0;
            size -= 1;
            return result;
        }

        public int top() {
            if(this.empty() == 1) return -1;

            return data[size - 1];
        }

        public int size() {
            return this.size;
        }

        public int empty() {
            return size == 0 ? 1 : 0;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb.toString());
    }
}
