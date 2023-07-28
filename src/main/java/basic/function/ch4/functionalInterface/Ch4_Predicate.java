package basic.function.ch4.functionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Ch4_Predicate {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = x -> x > 0;

        List<Integer> inputs = Arrays.asList(10, -5, 4, -2, 0, 3);

        System.out.println(filter(inputs, isPositive)); // [10, 4, 3]
        System.out.println(filter(inputs, isPositive.negate())); // [-5, -2, 0]
        System.out.println(filter(inputs, isPositive.or(x -> x == 0))); // [10, 4, 0, 3]
        System.out.println(filter(inputs, isPositive.and(x -> x % 2 == 0))); // [10, 4]
    }

    public static <T> List<T> filter(List<T> inputs, Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for(T input : inputs) {
            if(condition.test(input)) result.add(input);
        }

        return result;
    }
}
