package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 문제 추천 시스템 version1
 * https://www.acmicpc.net/problem/21939
 * <p>
 * 구현, 자료구조 문제
 * - TreeSet 구현체 사용
 * - ArrayList 사용시 정렬에서 문제 발생, 최악의 경우 O(nlogn) 시간 초과발생
 */
public class BOJ21939 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static Map<Integer, Problem> NO_PROBLEM = new HashMap<>();
    private static TreeSet<Problem> PROBLEMS = new TreeSet<>();
    private static int N, M;

    private static class Problem implements Comparable<Problem> {
        private int no;
        private int level;

        public Problem(int no, int level) {
            this.no = no;
            this.level = level;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Problem problem = (Problem) other;
            return no == problem.no && level == problem.level;
        }

        @Override
        public int hashCode() {
            return Objects.hash(no, level);
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level != o.level) return o.level - this.level;

            return o.no - this.no;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 추천 문제 개수
        for (int i = 1; i <= N; i++) {
            int p = inputProcessor.nextInt(); // 문제 번호
            int l = inputProcessor.nextInt(); // 난이도

            Problem problem = new Problem(p, l);
            PROBLEMS.add(problem);
            NO_PROBLEM.put(p, problem);
        }
    }

    private static final String RECOMMEND = "recommend";
    private static final String ADD = "add";
    private static final String SOLVED = "solved";


    private static void pro() {
        M = inputProcessor.nextInt();
        while (M > 0) {
            M -= 1;

            String cmd = inputProcessor.next();
            if (ADD.equals(cmd)) {
                int p = inputProcessor.nextInt();
                int l = inputProcessor.nextInt();

                Problem newProblem = new Problem(p, l);
                PROBLEMS.add(newProblem);
                NO_PROBLEM.put(p, newProblem);
            } else if (RECOMMEND.equals(cmd)) {
                int x = inputProcessor.nextInt();

                if (x == 1) {
                    Problem first = PROBLEMS.first();
                    sb.append(first.no).append("\n");
                } else if (x == -1) {
                    Problem last = PROBLEMS.last();
                    sb.append(last.no).append("\n");
                }

            } else if (SOLVED.equals(cmd)) {
                int p = inputProcessor.nextInt();
                Problem removed = NO_PROBLEM.remove(p);
                PROBLEMS.remove(removed);
            }
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
