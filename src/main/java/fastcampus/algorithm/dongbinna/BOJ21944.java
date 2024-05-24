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
 * 문제추천 시스템 v2 (골2)
 * https://www.acmicpc.net/problem/21944
 * <p>
 * TreeSet 자료구조 활용하는 문제
 * 직접 풀이
 * - group 굳이 VO에 정의할 필요가 없었음
 * - 내림차순, 오름차순 두 가지 Comprator 정의해서 재 풀이 했는데, 오름차순 정의 한번에 TreeSet 메소드 조합해서 풀이 가능
 * - first(), last(), ceiling(), floor()
 */
public class BOJ21944 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static Map<Integer, TreeSet<Problem>> GROUP_PROBLEM = new HashMap<>();
    private static Map<Integer, Integer[]> NO_PROBLEM = new HashMap<>();
    private static TreeSet<Problem> PROBLEMS = new TreeSet<>();
    private static int N, M;

    private static class Problem implements Comparable<Problem> {
        private int no;
        private int level;

        public Problem(int level) {
            this(0, level);
        }

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
            if (this.level != o.level) return this.level - o.level;

            return this.no - o.no;
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
            int g = inputProcessor.nextInt(); // 그룹

            Problem problem = new Problem(p, l);

            TreeSet<Problem> problems = findGroup(g);
            problems.add(problem); // 내림차순
            PROBLEMS.add(problem); // 오름차순
            NO_PROBLEM.put(p, new Integer[]{l, g});
        }
    }

    private static TreeSet<Problem> findGroup(int groupNo) {
        if (GROUP_PROBLEM.containsKey(groupNo)) return GROUP_PROBLEM.get(groupNo);

        TreeSet<Problem> newGroup = new TreeSet<>();
        GROUP_PROBLEM.put(groupNo, newGroup);
        return newGroup;
    }

    private static final String RECOMMEND = "recommend";
    private static final String RECOMMEND2 = "recommend2";
    private static final String RECOMMEND3 = "recommend3";
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
                int g = inputProcessor.nextInt();

                Problem newProblem = new Problem(p, l);

                TreeSet<Problem> problems = findGroup(g);
                problems.add(newProblem);

                PROBLEMS.add(newProblem);
                NO_PROBLEM.put(p, new Integer[]{l, g});
            } else if (RECOMMEND.equals(cmd)) {
                int g = inputProcessor.nextInt();
                int x = inputProcessor.nextInt();

                TreeSet<Problem> group = findGroup(g);
                if (x == 1) {
                    Problem problem = group.last();
                    sb.append(problem.no).append("\n");
                } else {
                    Problem problem = group.first();
                    sb.append(problem.no).append("\n");
                }
            } else if (RECOMMEND2.equals(cmd)) {
                int x = inputProcessor.nextInt();

                if (x == 1) {
                    Problem problem = PROBLEMS.last();
                    sb.append(problem.no).append("\n");
                } else {
                    Problem problem = PROBLEMS.first();
                    sb.append(problem.no).append("\n");
                }
            } else if (RECOMMEND3.equals(cmd)) {
                int x = inputProcessor.nextInt();
                int l = inputProcessor.nextInt();

                Problem target = new Problem(l);
                if (x == 1) {
                    Problem ceiling = PROBLEMS.ceiling(target);
                    sb.append(ceiling == null ? -1 : ceiling.no).append("\n");
                } else {
                    Problem floor = PROBLEMS.floor(target);
                    sb.append(floor == null ? -1 : floor.no).append("\n");
                }

            } else if (SOLVED.equals(cmd)) {
                int p = inputProcessor.nextInt();
                Integer[] info = NO_PROBLEM.remove(p);

                Problem target = new Problem(p, info[0]);
                PROBLEMS.remove(target);

                TreeSet<Problem> problems = GROUP_PROBLEM.get(info[1]);
                problems.remove(target);
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
