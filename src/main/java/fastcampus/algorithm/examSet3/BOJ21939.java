package fastcampus.algorithm.examSet3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 문제 추천 시스템 Version1(골드4)
 * https://www.acmicpc.net/problem/21939
 *
 * - 직접 풀이 못함
 * - TreeSet , HashMap 자료 구조 활용해 야했음
 * - remove 할때 iterator 사용해서 시간초과 발생
 * - HashMap 자료구조를 사용해서 no, level 정보 저장하고 삭제시 사용하여 시간 단축
 */
public class BOJ21939 {
    static StringBuilder sb = new StringBuilder();

    static class Problem implements Comparable<Problem> {
        int no;
        int level;

        public Problem(int no, int level) {
            this.no = no;
            this.level = level;
        }

        @Override
        public int compareTo(Problem problem) {
            if(level != problem.level) return level - problem.level;
            return no - problem.no;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Problem problem = (Problem) o;
            return no == problem.no && level == problem.level;
        }

        @Override
        public int hashCode() {
            return Objects.hash(no, level);
        }
    }

    static void process() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Map<Integer, Integer> problemMap = new TreeMap<>();
        TreeSet<Problem> problemTreeSet = new TreeSet<>();
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int no = Integer.parseInt(st.nextToken()); // P : 문제 번호
            int level = Integer.parseInt(st.nextToken()); // L : 난이도

            problemTreeSet.add(new Problem(no, level));
            problemMap.put(no, level);
        }

        int M = Integer.parseInt(br.readLine());
        while(M > 0) {
            M -= 1;
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if("add".equals(cmd)) {
                int no = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());

                problemTreeSet.add(new Problem(no, level));
                problemMap.put(no, level);
            } else if("recommend".equals(cmd)) {
                int x = Integer.parseInt(st.nextToken());

                if(x == 1) {
                    sb.append(problemTreeSet.last().no);
                } else { // -1
                    sb.append(problemTreeSet.first().no);
                }

                sb.append("\n");
            } else if("solved".equals(cmd)) {
                int no = Integer.parseInt(st.nextToken());
                int level = problemMap.get(no);

                problemTreeSet.remove(new Problem(no, level));
                problemMap.remove(no);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        process();
    }
}
