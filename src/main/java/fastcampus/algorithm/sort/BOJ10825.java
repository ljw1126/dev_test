package fastcampus.algorithm.sort;

import fastcampus.algorithm.MyReader;

import java.util.Arrays;

/**
 * 국영수(실버4) https://www.acmicpc.net/problem/10825
 *
 * 단순 정렬 문제
 *
 * 자바에서 제공하는 정렬 알고리즘은 O(NlogN) 시간 복잡도 가짐
 * 최대값으로 구할 경우100,000 * log100,000 = 1,600,000
 * 따라서 1초안에 연산 가능
 */
public class BOJ10825 {

    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static Student[] students;
    static class Student implements Comparable<Student>{

        public String name;
        private int korean, english, math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Student other) {
            if(korean != other.korean) return other.korean - korean; // 내림 차순
            if(english != other.english) return english - other.english; // 오름차순
            if(math != other.math) return other.math - math; // 내림차순

            return name.compareTo(other.name);
        }
    }


    static void input() {
        N = scan.nextInt();

        students = new Student[N];
        for(int i = 0; i < N; i++) {
            String name = scan.next();
            int korean = scan.nextInt();
            int english = scan.nextInt();
            int math = scan.nextInt();

            students[i] = new Student(name, korean, english, math);
        }
    }

    static void pro() {
        Arrays.sort(students);

        for(Student s : students) {
            sb.append(s.name).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
