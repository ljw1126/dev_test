package fastcampus.algorithm.test3;

import java.io.*;
import java.util.*;

/**
 * 정보 상인 호석(골5)
 * https://www.acmicpc.net/problem/22252
 *
 */
public class BOJ22252 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int ONE = 1;
    static int TWO = 2;
    static int Q;
    static long SUM; // 최대치 실수
    static Map<String, Queue<Integer>> GORILLA = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();

        while(Q > 0) {
            pro();
            Q -= 1;
        }

        sb.append(SUM);

        output();
    }

    private static void pro() {
        int cmd = inputProcessor.nextInt();
        String name = inputProcessor.next();
        int count = inputProcessor.nextInt();

        if(!GORILLA.containsKey(name)) {
            GORILLA.put(name, new PriorityQueue<>((a, b) -> b - a));
        }

        Queue<Integer> que = GORILLA.get(name);
        if(cmd == ONE) {
            for(int i = 1; i <= count; i++) {
                int value = inputProcessor.nextInt();
                que.add(value);
            }
        } else if(cmd == TWO) {
            for(int i = 1; i <= count; i++) {
                if(que.isEmpty()) break;
                SUM += que.poll();
            }
        }
    }

    private static void input() {
        Q = inputProcessor.nextInt();
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
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
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
