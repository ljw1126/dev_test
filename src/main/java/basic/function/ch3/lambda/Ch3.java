package basic.function.ch3.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Ch3 {
    public static void main(String[] args) {
        // 1. 인터페이스 구현체 사용
        Function<Integer, Integer> myAdder = new Adder();
        System.out.println(myAdder.apply(5)); // 15

        // 2. 람다 표현식 사용
        Function<Integer, Integer> myAdderByAnonymous = (Integer a) -> {
            return a + 10;
        };
        System.out.println(myAdderByAnonymous.apply(5)); // 15

        // 3. 람다 표현식 (simply)
        Function<Integer, Integer> simpleMyAdder = a -> a + 10;
        System.out.println(simpleMyAdder.apply(5)); // 15

        // 4. BiFunction<T, U, R> - T, U : 매개변수 타입, R : 리턴 타입
        BiFunction<Integer, Integer, Integer> simpleAdder = (a, b) -> a + b;
        System.out.println(simpleAdder.apply(2, 8));

        // 5. TriFunction<T, U, V, R> - T, U, V : 매개변수 타입, R : 리턴 타입
        TriFunction<Integer, Integer, Integer, Integer> triAdder = (a, b, c) -> a + b + c;
        System.out.println(triAdder.apply(1,2, 3)); // 6
    }
}
