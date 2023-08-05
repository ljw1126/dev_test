package util.stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

public class StreamCreateTest {

    @DisplayName("")
    @Test
    void emptyStream() {
        Stream<String> empty = Stream.empty();
        assertThat(empty).isEmpty();

        // java.lang.IllegalStateException: stream has already been operated upon or closed
        empty = Stream.empty();
        assertThat(empty.count()).isEqualTo(0);
    }

    class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @DisplayName("")
    @Test
    void of() {
        Stream<Integer> ints = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> integers = Stream.of(new Integer[] {6, 7, 8, 9, 10});

        Person[] people = {
                new Person("고길동", 30),
                new Person("마이콜", 20),
                new Person("고희동", 5)
        };

        Stream<Person> personStream = Stream.of(people);
    }

    @DisplayName("")
    @Test
    void streamByCollectionInterface() {
        // List
        List<Person> people = List.of(new Person("고길동", 30), new Person("마이콜", 20), new Person("고희동", 5));
        Stream<Person> personStream = people.stream();

        // Set
        Set<Integer> integerSet = IntStream.range(1, 11).boxed().collect(Collectors.toSet()); // 1 ~ 10
        Stream<Integer> integerStream = integerSet.stream();

        // Map
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("A", 1);
        testMap.put("B", 2);
        testMap.put("C", 3);

        Stream<String> keyStream = testMap.keySet().stream();
        Stream<Integer> valueStream = testMap.values().stream();
        Stream<Map.Entry<String, Integer>> entryStream = testMap.entrySet().stream();
    }

    @DisplayName("")
    @Test
    void arraysStream() {
        Stream<String> stringStream = Arrays.stream(new String[] {"가", "나", "다"});
        Stream<Integer> integerStream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5});
    }

    @DisplayName("")
    @Test
    void infiniteStreamByRandomClass() {
        // ints() 무한으로 생성하기 때문에 limit 제한 -> List<Integer> 자료형으로 바꾸려면 boxing 후에 collect 처리
        List<Integer> integerValues = new Random().ints().limit(5).boxed().collect(Collectors.toList());
        assertThat(integerValues).hasSize(5);
        System.out.println(integerValues);

        long[] longValues = new Random().longs(5).toArray();
        assertThat(longValues).hasSize(5);
        System.out.println(Arrays.toString(longValues));

        List<Double> doubleValues = new Random().doubles(10).boxed().collect(Collectors.toList());
        assertThat(doubleValues).hasSize(10);
        System.out.println(doubleValues);
    }

    @DisplayName("난수 10개 생성 후 String[]로 반환")
    @Test
    void randomStringNumberArray() {
        String[] strNumber = new Random().ints().limit(10).mapToObj(String::valueOf).toArray(String[]::new);
        assertThat(strNumber).isInstanceOf(String[].class);
    }

    @DisplayName("")
    @Test
    void streamIterate() {
        // infinite stream 이므로 수량 제한 필요
        int[] numbers = Stream.iterate(0, x -> x + 2).limit(5).mapToInt(v -> v).toArray();

        assertThat(numbers).hasSize(5);
        assertThat(numbers).isInstanceOf(int[].class);
        assertThat(numbers).containsExactly(0, 2, 4, 6, 8);
    }

    @DisplayName("")
    @Test
    void streamGenerate() {
        Random random = new Random();

        // nextInt(int n) : 0 ~ n 사이의 수 중 하나
        // generate 또한 infinite stream 이므로 수량 제한 필요
        int[] randomNumber = Stream.generate(() -> random.nextInt(100)).limit(5).mapToInt(v -> v).toArray();
        assertThat(randomNumber).hasSize(5);
    }

    @DisplayName("")
    @Test
    void listToPrimitiveStream() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        IntStream intStream = integers.stream().mapToInt(v -> v);

        List<Double> doubles = List.of(1.1, 2.2, 3.3, 4.4, 5.5);
        DoubleStream doubleStream = doubles.stream().mapToDouble(v -> v);

        List<Long> longs = Arrays.asList(11L, 22L, 33L, 44L);
        LongStream longStream = longs.stream().mapToLong(l -> l);
    }

    @DisplayName("")
    @Test
    void range() {
        // DoubleStream 에는 range 없음
        int[] numbers = IntStream.range(1, 5).toArray(); // from : inclusive, to : exclusive
        assertThat(numbers).hasSize(4);
        assertThat(numbers).containsExactly(1, 2, 3, 4);
    }

    @DisplayName("")
    @Test
    void rangeClosed() {
        // DoubleStream 에는 rangeClosed 없음
        long[] longs = LongStream.rangeClosed(10L, 15L).toArray();  // from : inclusive, to : inclusive
        assertThat(longs).hasSize(6);
        assertThat(longs).containsExactly(10L, 11L, 12L, 13L, 14L, 15L);
    }

    @DisplayName("")
    @Test
    void primitiveStreamToBoxed() {
        // boxed() 호출하여 primitive type -> wrapper class object type
        Stream<Integer> integerStream = IntStream.of(1, 2, 3, 4, 5).boxed();
        Stream<Long> longStream = LongStream.of(1, 2, 3, 4, 5).boxed();
        Stream<Double> doubleStream = DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5).boxed();
    }

    //https://howtodoinjava.com/java/stream/primitive-type-streams/
    //IntStream, LongStream, DoubleStream 모두 count(), min(), max(), sum(), average() 지원
    @DisplayName("")
    @Test
    void IntStreamMaxOrMin() {
        int maxValue = IntStream.of(10, 18, 20, 70, 4).max().getAsInt();
        assertThat(maxValue).isEqualTo(70);

        int minValue = IntStream.of(99, 23, 328, 9, 1).min().getAsInt();
        assertThat(minValue).isEqualTo(1);
    }

    @DisplayName("")
    @Test
    void IntStreamAverage() {
        double avg = IntStream.of(1, 2, 3, 4, 5).average().getAsDouble();
        assertThat(avg).isEqualTo(3.0);
    }

    @DisplayName("")
    @Test
    void IntStreamSum() {
        int sumValue = IntStream.range(1, 100).sum();
        assertThat(sumValue).isEqualTo(4950); // 100 * 99 / 2 = 4950
    }

    @DisplayName("")
    @Test
    void IntStreamCount() {
        long count = IntStream.rangeClosed(1, 100).count(); // count return long
        assertThat(count).isEqualTo(100L);
    }
}
