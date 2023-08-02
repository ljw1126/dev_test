package util;

import basic.function.ch5.Car;
import basic.function.ch5.Sedan;
import basic.function.ch5.Suv;
import basic.function.ch5.Van;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 1. ClassName::staticMethodName 클래스 static method
 * 2. objectName::instanceMethodName 인스턴스화한 객체의 instance method (new 생성자 선언후 사용)
 * 3. ClassName::instanceMethodName 특정 타입의 임의 객체가 instance method (String, Integer..)
 * 4. className::new 클래스 생성자
 *
 * - 메서드 자체를 지정하기 때문에 괄호를 쓰지 않는다
 * - :: 표기
 */
public class MethodReferenceTest {

    public static <T> T mergeThing(T a, T b, BiFunction<T, T, T> merger) {
        return merger.apply(a, b);
    }

    public static String appendStrings(String a, String b) {
        return a + b;
    }

    public String appendString(String a, String b) {
        return a + b;
    }

    @DisplayName("")
    @Test
    void methodReferenceTest() {
        String t1 = "Hello";
        String t2 = "World!";
        String result = "HelloWorld!";

        // 1. Calling the method mergeThings with a lambda expression
        String lambdaResult = MethodReferenceTest.mergeThing(t1, t2, (a, b) -> a + b);
        assertThat(lambdaResult).isEqualTo(result); // Ok

        // 2. Reference to a static method
        String staticMethodResult = MethodReferenceTest.mergeThing(t1, t2, MethodReferenceTest::appendStrings);
        assertThat(staticMethodResult).isEqualTo(result); // Ok

        // 3. Reference to an instance method of a particular object
        MethodReferenceTest obj = new MethodReferenceTest();
        String instanceMethodResult = MethodReferenceTest.mergeThing(t1, t2, obj::appendString);
        assertThat(instanceMethodResult).isEqualTo(result); // Ok

        // 4. Reference to an instance method of an arbitrary object of a
        // particular type
        String particularTypeResult = MethodReferenceTest.mergeThing(t1, t2, String::concat); // t1.concat(t2)
        assertThat(particularTypeResult).isEqualTo(result); // Ok
    }


    @DisplayName("")
    @Test
    void staticMethodReference() {
        Function<String, Integer> strToInt = Integer::parseInt;
        assertThat(strToInt.apply("5")).isEqualTo(5);
    }

    @DisplayName("")
    @Test
    void objectInstanceMethodReference() {
       String str = "hello";
       Predicate<String> equalsToHello = str::equals;
       assertThat(equalsToHello.test("world")).isFalse();
    }

    public static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(x, y);
    }

    public static int multiply(int x, int y) {
        return x * y;
    }

    public int subtract(int x, int y) { // instance method
        return x - y;
    }

    public void myMethod() {
        System.out.println(calculate(10, 3, this::subtract));
    }

    @DisplayName("")
    @Test
    void methodReference() {
        assertThat(calculate(5, 4, (x, y) -> x + y)).isEqualTo(9);
        assertThat(calculate(5, 4, MethodReferenceTest::multiply)).isEqualTo(20);

        MethodReferenceTest methodReferenceTest = new MethodReferenceTest();
        assertThat(calculate(5, 4, methodReferenceTest::subtract)).isEqualTo(1);

        methodReferenceTest.myMethod();
    }

    @DisplayName("")
    @Test
    void classNameMethodReference() {
        Function<String, Integer> strLength = String::length;
        assertThat(strLength.apply("hello world")).isEqualTo(11); // 길이 11 출력

        BiPredicate<String, String> strEquals = String::equals;
        assertThat(strEquals.test("hello", "world")).isFalse();
        assertThat(strEquals.test("hello", "hello")).isTrue(); // 첫번째 인자 v1의 equals 호출해서 인자로 v2 넘김
    }

    class User {
        String name;
        int id;

        public User(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) { // 동등성
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, id);
        }
    }

    public static void printUserField(List<User> userList, Function<User, Object> function) {
        for(User user : userList) {
            System.out.println(function.apply(user));
        }
    }

    @DisplayName("")
    @Test
    void printUserNameByMethodReference() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("홍길동", 1));
        userList.add(new User("고길동", 2));
        userList.add(new User("도치", 3));

        printUserField(userList, User::getName);
    }

    @DisplayName("")
    @Test
    void constructorMethodReference() {
        User user = new User("고길동", 30);

        BiFunction<String, Integer, User> userBiFunction = User::new;
        User clone = userBiFunction.apply("고길동", 30);

        assertThat(user.equals(clone)).isTrue(); // equals override
    }

    @DisplayName("Map 키 별로 생성자 참조를 넣어두고 호출하여 사용할 수 있다")
    @Test
    void constructorMethodReferenceMap() {
        String[][] inputs = new String[][] {
                {"sedan", "Sonata", "Hyundai"},
                {"van", "Sienna", "Toyota"},
                {"sedan", "Model S", "Tesla"},
                {"suv", "Sorento", "KIA"}
        };

        Map<String, BiFunction<String, String, Car>> constructorMap = new HashMap<>();
        constructorMap.put("sedan", Sedan::new);
        constructorMap.put("van", Van::new);
        constructorMap.put("suv", Suv::new);

        List<Car> cars = new ArrayList<>();
        for(String[] input : inputs) {
            cars.add(constructorMap.get(input[0]).apply(input[1], input[2]));
        }

        assertThat(cars).hasSize(4);

        cars.forEach(Car::drive);
    }

    class Bicycle {
        String brand;
        public Bicycle(String brand) { this.brand = brand; }
    }

    @DisplayName("")
    @Test
    void constructReferenceMethod2() {
        List<String> bikeBrand = Arrays.asList("Giant", "Scott", "Trek", "GT");
        Bicycle[] bicycles = bikeBrand.stream().map(Bicycle::new).toArray(Bicycle[]::new);

        assertThat(bicycles).hasSize(4);
    }

    @DisplayName("")
    @Test
    void instanceMethodReference() { // 이미 선언된 인스턴스에 메서드 호출 (기준이 생성 인스턴스고 인자를 전달받음)
        List<Integer> numbers =  Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);
        //numbers.stream().sorted((a, b) -> a.compareTo(b));
        List<Integer> sorted = numbers.stream().sorted(Integer::compareTo).collect(Collectors.toList());

        assertThat(sorted).containsExactly(2, 3, 5, 9, 18, 24, 40, 50);
    }

    @DisplayName("")
    @Test
    void instanceMethodOfAnArbitraryObjectOfParticularType() {
        String[] strings = { "Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda" };

        Arrays.sort(strings, String::compareToIgnoreCase);
        assertThat(strings)
                .containsExactly("Barbara", "James", "John", "Linda", "Mary", "Michael", "Patricia", "Robert");
    }
}
