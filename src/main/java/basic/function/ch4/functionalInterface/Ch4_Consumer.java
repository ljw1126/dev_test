package basic.function.ch4.functionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Ch4_Consumer {
    public static void main(String[] args) {
        Consumer<String> myStringConsumer = System.out::println;
        myStringConsumer.accept("hello world!");

        List<Integer> integers = Arrays.asList(1, 2, 3);
        Consumer<Integer> myIntegerProcess = x -> System.out.printf("Process Integer 1 : %s%n", x);
        process(integers, myIntegerProcess);

        List<Integer> integers2 = Arrays.asList(4, 5, 6);
        Consumer<Integer> myIntegerProcess2 = x -> System.out.printf("Process Integer 2 : %s%n", x);
        process(integers2, myIntegerProcess2);

        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        Consumer<Double> myDoubleProcess = x -> System.out.printf("Process Double : %f%n", x);
        process(doubles, myDoubleProcess);
    }

    public static <T> void process(List<T> inputs, Consumer<T> consumer) {
        for(T input : inputs) {
            consumer.accept(input);
        }
    }
}
