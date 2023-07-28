package basic.function.ch4.functionalInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ch4_Comparator {

    static class Student {
        int id;
        String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2, "홍길동"));
        students.add(new Student(1, "이순신"));
        students.add(new Student(3, "김철수"));
        students.add(new Student(4, "최영희"));

        Comparator<Student> sortByIdDesc = (s1, s2) -> s2.id - s1.id;  // 내림차순
        Collections.sort(students, sortByIdDesc);
        System.out.println(students);

        Comparator<Student> sortByNameDesc = (s1, s2) -> s2.name.compareTo(s1.name); // 내림차순
        Collections.sort(students, sortByNameDesc);
        System.out.println(students);
    }
}
