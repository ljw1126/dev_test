package its.codingtest.greedy;

import java.io.*;
import java.util.*;

/**
 * 프로그래머스 - 무지의 먹방 라이브(카카오2019)
 * https://school.programmers.co.kr/learn/courses/30/lessons/42891
 *
 * - 그리드 문제
 * - 정렬과 포인터 사용
 * - 사각형 넓이 구하는 아이디어가 필요했음
 */
public class p316 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int[] FOOD_TIMES = {
            4, 2, 3, 6, 7, 1, 5, 8
    };

    private static int K = 16;

    private static void input() {

    }

    private static class Food implements Comparable<Food> {
        private final int no;
        private final int time;

        public Food(int no, int time) {
            this.no = no;
            this.time = time;
        }

        // 시간, 번호 순으로 오름차순 정렬
        public int compareTo(Food o) {
            return this.time - o.time;
        }
    }

    private static void pro() {
        List<Food> foods = new LinkedList<>();
        for(int i = 0; i < FOOD_TIMES.length; i++) {
            foods.add(new Food(i + 1, FOOD_TIMES[i]));
        }

        Collections.sort(foods);

        int prev = 0;
        int i = 0;
        long _k = K;
        int w = foods.size();
        int answer = -1;
        for(Food f : foods) {
            long h = f.time - prev;

            if(h != 0) { // k == 0인데 음식이 남아 있는 상태에서, 범위내에 답이 존재함
                long area = h * w;
                if(area <= _k) {
                    _k -= area;
                    prev = f.time;
                } else {
                    long idx = _k % w; // 연산이 필요할거라 생각했는데, 직사각형 넓이 풀이는 생각치 못함
                    List<Food> group = foods.subList(i, FOOD_TIMES.length);
                    group.sort(Comparator.comparingInt(a -> a.no));

                    answer = group.get((int)idx).no;
                    break;
                }
            }

            i += 1;
            w -= 1;
        }

        sb.append(answer);
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
