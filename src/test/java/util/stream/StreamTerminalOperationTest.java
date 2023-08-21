package util.stream;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamTerminalOperationTest {

    @DisplayName("병렬 스트림에서 forEachOrdered 는 순서를 보장한다 (forEach는 순서 보장하지 않음)")
    @Test
    void forEachOrdered() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(toList());

        list.stream().parallel().forEach(System.out::println); // 8 5 6 4 9 2 1 10 7 3
        list.stream().parallel().forEachOrdered(System.out::println); // 1 2 3 4 5 6 7 8 9 10
    }

    @DisplayName("count 통해 요소 수를 구한다")
    @Test
    void count() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(toList());

        long count = list.stream().count();

        assertThat(count).isEqualTo(10);
    }

    @DisplayName("toArray 통해 요소를 담은 배열을 생성할 수 있다")
    @Test
    void toArray() {
        List<String> list = List.of("A", "B", "C", "D", "E");
        String[] result = list.stream().toArray(String[]::new);

        assertThat(result).isInstanceOf(String[].class);
    }

    @DisplayName("최대값 찾기")
    @Test
    void max() {
        List<Integer> list = IntStream.rangeClosed(1, 100).boxed().collect(toList());
        int max = list.stream().max(comparing(v -> v)).orElse(-1);
        assertThat(max).isEqualTo(100);

        // Integer -> IntStream
        int max2 = list.stream().mapToInt(v -> v).max().orElse(-1);
    }

    @DisplayName("최소값 찾기")
    @Test
    void min() {
        List<Integer> list = IntStream.rangeClosed(1, 100).boxed().collect(toList());
        int min = list.stream().min(comparing(v -> v)).orElse(-1);
        assertThat(min).isEqualTo(1);

        // Integer -> IntStream
        int min2 = list.stream().mapToInt(v -> v).min().orElse(-1);
    }

    @DisplayName("직렬 스트림에서 findAny 는 첫번째 요소를 반환한다")
    @Test
    void findAnyInSequential() {
        List<String> list = Arrays.asList("A", "B", "C", "D");

        Optional<String> result  = list.stream().findAny();

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo("A");
    }

    @DisplayName("병렬 스트림에서 findAny 는 임의 요소를 반환한다")
    @Test
    void findAnyInParallel() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> result = list.stream()
                                        .parallel()
                                        .filter(n -> n < 4).findAny();

        assertThat(result.get()).isIn(1, 2, 3);
    }

    @DisplayName("직렬 스트림에서 findFirst 는 첫번째 요소를 반환한다")
    @Test
    void findFirstSequential() {
        List<String> list = Arrays.asList("A", "B", "C", "D");

        Optional<String> result  = list.stream().findFirst();

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo("A");
    }

    @DisplayName("병렬 스트림에서도 findFirst 는 첫번째 요소를 반환한다")
    @Test
    void findFirstParallel() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> result = list.stream().parallel().findFirst();

        assertThat(result.get()).isEqualTo(1);
    }

    @DisplayName("모든 요소가 조건을 만족하면 true 반환한다")
    @Test
    void allMatch() {
        List<Integer> integers = IntStream.rangeClosed(1, 10).boxed().collect(toList());

        boolean result = integers.stream().allMatch(v -> v <= 10);
        boolean result2 = integers.stream().allMatch(v -> v % 2 == 0);

        assertThat(result).isTrue();
        assertThat(result2).isFalse();
    }

    @DisplayName("조건을 하나라도 만족하는 요소가 있으면 true 반환한다")
    @Test
    void anyMatch() {
        int[] ints = IntStream.of(1, 3, 5, 7, 10).toArray();
        boolean result = Arrays.stream(ints).anyMatch(v -> v % 2  == 0);

        assertThat(result).isTrue();
    }

    @DisplayName("모두 조건을 만족하지 않을 경우 true 리턴한다")
    @Test
    void noneMatch() {
        int[] ints = IntStream.of(2, 4, 6, 8, 10).toArray();

        boolean result = Arrays.stream(ints).noneMatch(v -> v % 2 == 1);
        assertThat(result).isTrue();
    }

    @DisplayName("")
    @Test
    void reduce() {
       int[] numbers = IntStream.rangeClosed(1, 10).toArray();

       int count = Arrays.stream(numbers).reduce(0, (a, b) -> a + 1); // 10
       int sum = Arrays.stream(numbers).reduce(0, (a, b) -> a + b); // 55
       int max = Arrays.stream(numbers).reduce(Integer.MIN_VALUE, Math::max); // (a, b) -> a < b ? a : b
       int min = Arrays.stream(numbers).reduce(Integer.MAX_VALUE, Math::min);

       assertThat(count).isEqualTo(10);
       assertThat(sum).isEqualTo(55);
       assertThat(max).isEqualTo(10);
       assertThat(min).isEqualTo(1);
    }

    @DisplayName("")
    @Test
    void collect() {
        int[] number = IntStream.rangeClosed(1, 10).toArray();

        List<Integer> result1 = Arrays.stream(number).boxed().collect(toList());
        Set<Integer> result2 = Arrays.stream(number).boxed().collect(toSet());
        Map<String, Integer> result3 = Arrays.stream(number).boxed().collect(toMap(String::valueOf, v -> v));

        List<Integer> result4 = Arrays.stream(number).boxed().collect(toCollection(LinkedList::new));
        Set<Integer> result5 = Arrays.stream(number).boxed().collect(toCollection(TreeSet::new));
    }

    @DisplayName("")
    @Test
    void collectorsCalculateMethod() {
        long count = IntStream.of(1, 2, 3).boxed().collect(counting()); // count() 대체 가능, Stream<T> IntStream ..
        assertThat(count).isEqualTo(3);

        int sum = IntStream.rangeClosed(1, 10).boxed().collect(summingInt(v -> v)); // mapToInt().sum() 대체 가능
        assertThat(sum).isEqualTo(55);

        double avg = IntStream.rangeClosed(1, 3).boxed().collect(averagingInt(v -> v - 1)); // (0 + 1 + 2) / 3
        assertThat(avg).isEqualTo(1);
    }

    class Box {
        int weight;
        String colorName;

        public Box(int weight, String colorName) {
            this.weight = weight;
            this.colorName = colorName;
        }

        public int getWeight() {
            return weight;
        }

        public String getColorName() {
            return colorName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Box box = (Box) o;
            return weight == box.weight && Objects.equals(colorName, box.colorName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, colorName);
        }
    }

    @DisplayName("")
    @Test
    void maxByAndMinBy() {
        List<Box> boxes = List.of(new Box(8, "green"),
                                  new Box(3, "blue"),
                                  new Box(10, "yellow"));

        //boxes.stream().max(Comparator.comparingInt(Box::getWeight)); 동일
        Box maxResult = boxes.stream().collect(maxBy(Comparator.comparingInt(Box::getWeight))).orElse(null);
        Box minResult = boxes.stream().collect(minBy(Comparator.comparingInt(o -> o.getColorName().length()))).orElse(null);

        assertThat(maxResult).isEqualTo(new Box(10, "yellow"));
        assertThat(minResult).isEqualTo(new Box(3, "blue"));
    }

    @DisplayName("")
    @Test
    void joining() {
        String[] strings = new String[] {"a", "b", "c", "d", "e"};

        String result = Arrays.stream(strings).collect(Collectors.joining()); // "abcde"
        String result2 = Arrays.stream(strings).collect(Collectors.joining("|"));  // "a|b|c|d|e"
        String result3 = Arrays.stream(strings).collect(Collectors.joining(", ", "[", "]")); // "[a, b, c, d, e]"

        assertThat(result).isEqualTo("abcde");
        assertThat(result2).isEqualTo("a|b|c|d|e");
        assertThat(result3).isEqualTo("[a, b, c, d, e]");
    }

    class Student {
        private String name;
        private String city;
        private double avgGrade;
        private int age;

        public Student(String name, String city, double avgGrade, int age) {
            this.name = name;
            this.city = city;
            this.avgGrade = avgGrade;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        public double getAvgGrade() {
            return avgGrade;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", city='" + city + '\'' +
                    ", avgGrade=" + avgGrade +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Double.compare(student.avgGrade, avgGrade) == 0 && age == student.age && Objects.equals(name, student.name) && Objects.equals(city, student.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, city, avgGrade, age);
        }
    }

    private List<Student> getStudent() {
        return List.of(
                new Student("John Smith", "Miami", 7.38, 19),
                new Student("Mike Miles", "New York", 8.4, 21),
                new Student("Michael Peterson", "New York", 7.5, 20),
                new Student("James Robertson", "Miami", 9.1, 20),
                new Student("Joe Murray", "New York", 7.9, 19),
                new Student("Kyle Miller", "Miami", 9.83, 20)
        );
    }

    @DisplayName("Collectors.reducing() 은 파라미터 따라 결과 타입(Optional<T>, T, U)이 달라진다")
    @Test
    void reducing() {
        List<Student> students = getStudent();

        // #1. city 그룹화한 후 그룹별 최대 avgGrade 가진 Student 구하기
        // T타입 입력을 받아 Optional<T> 반환
        Map<String, Optional<Student>> cityAndMaxAvgGrade = students.stream().collect(
                Collectors.groupingBy(Student::getCity, Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Student::getAvgGrade))))
        );

        assertThat(cityAndMaxAvgGrade)
                .containsEntry("New York", Optional.of(new Student("Mike Miles", "New York", 8.4, 21)))
                .containsEntry("Miami", Optional.of(new Student("Kyle Miller", "Miami", 9.83, 20)));


        // #2. identity 정의하여 #1과 동일한 결과 얻기
        // identity 정의했기 때문에 T 타입 입력 시 T타입(객체) 반환
        Student identity = new Student("", "", 0, 0);
        Map<String, Student> cityAndMaxAvgGrade2 = students.stream().collect(
                Collectors.groupingBy(Student::getCity, Collectors.reducing(identity, BinaryOperator.maxBy(Comparator.comparing(Student::getAvgGrade))))
        );
        assertThat(cityAndMaxAvgGrade2)
                .containsEntry("New York", new Student("Mike Miles", "New York", 8.4, 21))
                .containsEntry("Miami", new Student("Kyle Miller", "Miami", 9.83, 20));


        // #3. identity, mapper 정의하여 결과 얻기
        // T타입 입력받아 U 타입 결과 반환
        // map().reduce() 와 동일
        double largestAvgGrade = students.stream().collect(Collectors.reducing(0.0, Student::getAvgGrade, BinaryOperator.maxBy(Comparator.comparingDouble(v -> v))));
        assertThat(largestAvgGrade).isEqualTo(9.83);


        // #4. city 그룹화 후 최대 avgGrade 값 구하기
        Map<String, Double> cityAndLargestAvgGrade = students.stream().collect(
                Collectors.groupingBy(Student::getCity, Collectors.reducing(0.0, Student::getAvgGrade, BinaryOperator.maxBy(Comparator.comparingDouble(v -> v))))
        );
        assertThat(cityAndLargestAvgGrade)
                .containsEntry("New York", 8.4)
                .containsEntry("Miami", 9.83);
    }

    @DisplayName("Collectors.partitioningBy 는 조건에 따라 2분할 처리한다")
    @Test
    void partitioningBy() {
        // #1. 문자열 길이가 4 초과하는 경우와 아닌 경우로 2분할
        List<String> names = Arrays.asList("John", "Jane", "Michael", "Anna", "James");
        Map<Boolean, List<String>> partitioningByNameLength = names.stream()
                .collect(Collectors.partitioningBy(v -> v.length() > 4));

        assertThat(partitioningByNameLength.get(true).size()).isEqualTo(2); // [Michael, James]
        assertThat(partitioningByNameLength.get(false).size()).isEqualTo(3); // [John, Jane, Anna]

        // #2. 문자열 길이가 4 초과하는 경우와 아닌 경우로 2분할하고, downstream 처리
        Map<Boolean, List<String>> partitioningByNameLengthToUpperCase = names.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 4, Collectors.mapping(String::toUpperCase, toList())));

        assertThat(partitioningByNameLengthToUpperCase.get(true)).containsExactly("MICHAEL", "JAMES");
        assertThat(partitioningByNameLengthToUpperCase.get(false)).containsExactly("JOHN", "JANE", "ANNA");

        // #3. 학생명 길이가 8 초과하고 avgGrade > 8.0 인 경우와 아닌 경우로 2분할
        List<Student> students = getStudent();
        Map<Boolean, List<Student>> nameAndAvgGrade = students.stream()
                .collect(Collectors.partitioningBy(student -> student.getName().length() > 8 && student.getAvgGrade() > 8.0));

        assertThat(nameAndAvgGrade.get(true).size()).isEqualTo(3);

        // #4. 학생 avgGrade > 8.0 인 경우와 아닌 경우를 2분할 하고, 카운팅
        Map<Boolean, Long> avgGradeCounting = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getAvgGrade() > 8.0, Collectors.counting()));

        assertThat(avgGradeCounting.get(true)).isEqualTo(3L);
        assertThat(avgGradeCounting.get(false)).isEqualTo(3L);
    }

    @DisplayName("")
    @Test
    void groupingBy() {
        List<Student> students = getStudent();

        // City 그룹별, Student
        Map<String, List<Student>> groupingByCityName = students.stream()
                .collect(Collectors.groupingBy(Student::getCity));

        assertThat(groupingByCityName.get("New York").size()).isEqualTo(3);
        assertThat(groupingByCityName.get("Miami").size()).isEqualTo(3);

        // #2. City 그룹별, Student::getName
        Map<String, List<String>> cityAndNameList = students.stream()
                .collect(Collectors.groupingBy(Student::getCity, Collectors.mapping(Student::getName, toList())));

        assertThat(cityAndNameList.get("New York")).containsExactly("Mike Miles", "Michael Peterson", "Joe Murray");
        assertThat(cityAndNameList.get("Miami")).containsExactly("John Smith", "James Robertson", "Kyle Miller");

        // #3. age 그룹별, counting
        Map<Integer, Long> ageAndCountMap = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));

        assertThat(ageAndCountMap.get(19)).isEqualTo(2);
        assertThat(ageAndCountMap.get(20)).isEqualTo(3);
        assertThat(ageAndCountMap.get(21)).isEqualTo(1);

        // #4. groupingBy 는 default HashMap::new 인데 TreeMap::new로 생성자 변경
        Map<String, List<String>> namesByCity = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getCity,
                        TreeMap::new,
                        Collectors.mapping(Student::getName, toList()))
                );

        assertThat(namesByCity).isInstanceOf(TreeMap.class);
    }
}
