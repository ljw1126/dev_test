package util.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamIntermediateOperationTest {

    //https://howtodoinjava.com/java8/java-stream-distinct-examples/
    class Person {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    @DisplayName("중복 제거")
    @Test
    void distinct() {
        // #1 Array
        int[] intStream = new int[] {1, 2, 3, 4, 1, 2, 3, 4};
        int[] result = Arrays.stream(intStream).distinct().toArray();

        assertThat(result).hasSize(4);

        // #2 List
        List<Person> people = List.of(
                new Person(1, "Ash"),
                new Person(3, "Cina"),
                new Person(2, "Brian"),
                new Person(3, "Cina"),
                new Person(1, "Ash")
        );

        List<Person> person = people.stream().distinct().collect(Collectors.toList());
        assertThat(person).hasSize(3);
        assertThat(person).extracting("name")
                .containsExactlyInAnyOrder("Ash", "Brian", "Cina");
    }

    class Student {
        String name;
        int score;

        int ban;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public Student(String name, int score, int ban) {
            this.name = name;
            this.score = score;
            this.ban = ban;
        }

        public boolean passed() {
            return this.score >= 70;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public int getBan() {
            return ban;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    ", ban=" + ban +
                    '}';
        }
    }

    @DisplayName("")
    @Test
    void filter() {
        List<Student> students = List.of(
            new Student("Atom", 69),
            new Student("Cherry", 85),
            new Student("Brian", 75),
            new Student("Eilish", 45)
        );

        List<Student> passed = students.stream()
                                        .filter(Student::passed)
                                        .collect(Collectors.toList());
        List<Student> notPass = students.stream()
                                        .filter(s -> !s.passed())
                                        .collect(Collectors.toList());

        assertThat(passed).hasSize(2);
        assertThat(passed).extracting("name")
                        .containsExactly("Cherry", "Brian");

        assertThat(notPass).hasSize(2);
        assertThat(notPass).extracting("name")
                    .containsExactly("Atom", "Eilish");


        // #2
        int[] numbers = IntStream.rangeClosed(1, 10).toArray();

        int[] odd = Arrays.stream(numbers).filter(v -> v % 2 == 1).toArray();
        int[] even = Arrays.stream(numbers).filter(v -> v % 2 == 0).toArray();

        assertThat(odd).hasSize(5);
        assertThat(odd).containsExactly(1, 3, 5, 7, 9);

        assertThat(even).hasSize(5);
        assertThat(even).containsExactly(2, 4, 6, 8, 10);
    }

    @DisplayName("")
    @Test
    void limit() {
        List<Integer> integers = IntStream.range(1, 100).limit(10).boxed().collect(Collectors.toList());;
        assertThat(integers).hasSize(10);
        assertThat(integers).contains(1, 2, 3, 4, 5, 6, 7, 8 , 9, 10);

        int[] random = new Random().ints().limit(5).toArray();
        assertThat(random).hasSize(5);
    }

    @DisplayName("")
    @Test
    void skip() {
        int[] odd = IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).skip(2).toArray();
        assertThat(odd).hasSize(3);
        assertThat(odd).contains(6, 8, 10);
    }

    @DisplayName("peek 는 중간 연산 과정에서 요소를 소비하지 않고 확인할 수 있다")
    @Test
    void peek() {
        Stream.of("one", "two", "three", "four")
                .map(String::length)
                .peek(i -> System.out.println("mapped value : " + i))
                .filter(l -> l > 3)
                .peek(i -> System.out.println("filtered value : " + i))
                .forEach(System.out::println);


        List<String> result = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(i -> System.out.println("filtered value : " + i))
                .map(String::toUpperCase)
                .peek(i -> System.out.println("mapped value : " + i))
                .collect(Collectors.toList());

        assertThat(result).hasSize(2);
        assertThat(result).contains("THREE", "FOUR");
    }

    @DisplayName("")
    @Test
    void sorted() {
       List<Integer> numbers = List.of(9, 4, 2, 1, 6);
       List<Integer> asc = numbers.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
       List<Integer> desc = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

       assertThat(asc).containsExactly(1, 2, 4, 6, 9);
       assertThat(desc).containsExactly(9, 6, 4, 2, 1);

       List<String> alphabet = List.of("A", "s", "w", "F", "e");
       alphabet.stream().sorted(String::compareTo).forEach(System.out::println); // A F e s w
       numbers.stream().sorted(Integer::compareTo).forEach(System.out::println); // 1 2 4 6 9

       // 대소문자 구분 안함
       alphabet.stream().sorted(String.CASE_INSENSITIVE_ORDER).forEach(System.out::println); // A e F s w
       alphabet.stream().sorted(String.CASE_INSENSITIVE_ORDER.reversed()).forEach(System.out::println); // w s F e A

       // String 길이로 정렬, T : String, U : int
       List<String> strings = List.of("one", "two", "three", "five", "six", "seven");
       strings.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);
       strings.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);

       strings.stream().sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
    }

    @DisplayName("숫자 배열을 조합하여 가장 큰 문자열 숫자 반환")
    @Test
    void sorted2() {
       int[] input1 = new int[] {6, 10, 2};
       String answer1 = IntStream.of(input1) // IntStream
                                .mapToObj(String::valueOf) // Stream<String>
                                .sorted((a, b) -> (b + a).compareTo(a + b))
                                .collect(Collectors.joining());
       assertThat(answer1).isEqualTo("6210");

       int[] input2 = new int[] {3, 30, 34, 5, 9}; // 9534330
       String answer2 = IntStream.of(input2)
                                 .mapToObj(String::valueOf)
                                 .sorted((a, b) -> (b + a).compareTo(a + b))
                                 .collect(Collectors.joining());
       assertThat(answer2).isEqualTo("9534330");
    }

    @DisplayName("")
    @Test
    void thenComparing() {
        List<Student> students = List.of(
            new Student("철수", 90, 1),
            new Student("수진", 89, 2),
            new Student("영희", 70, 3),
            new Student("훈이", 65, 2),
            new Student("맹구", 78, 2),
            new Student("짱구", 45, 1),
            new Student("유리", 65, 3)
        );

        // 반 오름차순 - 점수 내림차순 - 이름 오름차순 순으로 정렬했는데 이해가 안되네..
        List<Student> sorted = students.stream()
                                        .sorted(Comparator.comparingInt(Student::getBan).reversed()
                                        .thenComparingInt(Student::getScore).reversed()
                                        .thenComparing(Student::getName))
                                        .collect(Collectors.toList());

        for(Student s : sorted) System.out.println(s);

        assertThat(sorted).extracting("name")
                .containsExactly("철수", "짱구", "수진", "맹구", "훈이", "영희", "유리");
    }

    @DisplayName("파일 스트림 내에 파일명에서 확장자를 뽑아 대문자 변환 후 중복 제거")
    @Test
    void map() {
        Stream<File> fileStream = Stream.of(new File("ex1.java"),
                new File("ex1.txt"),
                new File("ex1.class"),
                new File("ex2.class"),
                new File("ex3.csv"),
                new File("ex4")
        );

        Stream<String> fileName = fileStream.map(File::getName);
        Stream<String> filteredFileName = fileName.filter(t -> t.indexOf('.') != -1);
        Stream<String> extractExt = filteredFileName.map(t -> t.substring(t.indexOf('.') + 1));
        Stream<String> toUpperCase = extractExt.map(String::toUpperCase);
        List<String> distinctList = toUpperCase.distinct().collect(Collectors.toList());

        assertThat(distinctList).containsExactly("JAVA", "TXT", "CLASS", "CSV");
    }

    // flatMap example https://mkyong.com/java8/java-8-flatmap-example/
    @DisplayName("")
    @Test
    void flatMap() {
       Stream<String[]> strArrStream = Stream.of(
               new String[] {"a", "aa", "aaa"},
               new String[] {"b", "bb", "bbb"},
               new String[] {"c"}
       );

       //Stream<Stream<String>> strStrStream = strArrStream.map(Arrays::stream);
       Stream<String> flatMapStream = strArrStream.flatMap(Arrays::stream);
       List<String> result = flatMapStream.collect(Collectors.toList());

       assertThat(result).hasSize(7);
    }

    @DisplayName("flatMap 활용하면 2차원 배열을 1차원 배열로 변환하여 처리 가능하다")
    @Test
    void flatMapExample1() {
        String[][] array = new String[][] {{"a", "b"}, {"c", "d"}, {"e", "f"}};

        String[] result = Stream.of(array)     // Stream<String[]>
                                .flatMap(Stream::of) // Stream<String>
                                .toArray(String[]::new);

        assertThat(result).hasSize(6);
        assertThat(result).containsExactly("a", "b", "c", "d", "e", "f");
    }

    @DisplayName("test.txt 라인 단위로 읽은 후 단어를 공백 기준으로 자르면 16개 단어가 나온다")
    @Test
    void flatMapExample2() throws Exception {
        String filePath = "/home/gitRepository/dev_test/src/test/java/util/stream/test.txt";
        Path path = Paths.get(filePath);

        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +"))); // 공백 기준
        long wordCount = words.count(); // 16

        assertThat(wordCount).isEqualTo(16);
    }

    @DisplayName("flatMapToInt 을 사용하면 2차원 int 배열을 1차원 IntStream 으로 처리 가능하다")
    @Test
    void flatMapExample3() {
        int[][] arrays = new int[][] {{1, 2,}, {3}, {4, 5}};
        Stream<int[]> streamArr = Stream.of(arrays);

        int[] result = streamArr.flatMapToInt(Arrays::stream) // IntStream
                                .toArray();

        assertThat(result).hasSize(5);
    }

    @DisplayName("")
    @Test
    void flatMapExample4() {
        long[][] array = new long[][] {{11L, 22L}, {33L}};
        Stream<long[]> longArrayStream = Stream.of(array);

        long[] result = longArrayStream.flatMapToLong(Arrays::stream).toArray();

        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(11L, 22L, 33L);
    }

}
