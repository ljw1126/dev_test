package its.codingtest.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 국영수 (실4)
 * https://www.acmicpc.net/problem/10825
 */
public class p359 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N;
    private static List<Student> STUDENTS;

    private static void input() {
        N = inputProcessor.nextInt();
        STUDENTS = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            String name = inputProcessor.next();
            int kor = inputProcessor.nextInt();
            int eng = inputProcessor.nextInt();
            int math = inputProcessor.nextInt();

            STUDENTS.add(new Student(name, kor, eng, math));
        }
    }

    private static void pro() {
        Collections.sort(STUDENTS);
        for (Student s : STUDENTS) {
            sb.append(s.name).append("\n");
        }
    }

    private static class Student implements Comparable<Student> {
        private final String name;
        private final int kor;
        private final int eng;
        private final int math;

        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            if (this.kor == o.kor && this.eng == o.eng && this.math == o.math) {
                return this.name.compareTo(o.name);
            } else if (this.kor == o.kor && this.eng == o.eng) {
                return o.math - this.math; // 수학 감소하는 순서로
            } else if (this.kor == o.kor) {
                return this.eng - o.eng; // 국어가 같으면 영어 점수가 증가하는 순서로
            }

            return Integer.compare(o.kor, this.kor); // 국어 점수가 감소하는 순서
        }
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
