package fastcampus.algorithm.test3;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 공정 컨설턴트 호석(골3)
 * https://www.acmicpc.net/problem/22254
 *
 * - 우선 순위 큐 사용하면 쉬운 문제
 * - 배열 사용하여 매턴 제일 적은 라인을 구하면 10^10 으로 시간 초과 발생
 */
public class BOJ22254 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static int N, X;
    static int[] GIFT;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        int L = 1;
        int R = 100000;
        int result = -1;
        while(L <= R) {
            int mid = (L + R) / 2;

            if(isPossible(mid)) {
                R = mid - 1;
                result = mid;
            } else {
                L = mid + 1;
            }
        }

        sb.append(result);
    }

    private static class Line implements Comparable<Line>{
        int i;
        int total;

        public Line(int i, int total) {
            this.i = i;
            this.total = total;
        }


        @Override
        public int compareTo(Line o) {
            if(total == o.total) return i - o.i; // 생산 라인 번호 순
            return total - o.total; // 시간 오름차순
        }
    }

    private static boolean isPossible(int count) { //라인 수
        Queue<Line> que = new PriorityQueue<>();
        for(int i = 1; i <= count; i++) {
            que.add(new Line(i, 0));
        }

        for(int i = 1; i <= N; i++) {
            int gift = GIFT[i];
            Line line = que.poll();

            if(line.total + gift > X) return false;

            line.total += gift;
            que.add(line);
        }

        return true;
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 선물 주문의 개수
        X = inputProcessor.nextInt(); // 제작 완료까지 남은 시간

        GIFT = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            GIFT[i] = inputProcessor.nextInt();
        }
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
