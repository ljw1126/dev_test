package basic.function.ch4.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class Ch4_BiConsumer {
    public static void main(String[] args) {
        BiConsumer<Integer, Double> myBiConsumer =
                (index, value) -> System.out.printf("Processing index %s, value %f%n", index, value);

        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3, 4.4);
        process(doubles, myBiConsumer);
    }

    public static <T> void process(List<T> input, BiConsumer<Integer, T> biConsumer) {
        for(int i = 0; i < input.size(); i++) {
            biConsumer.accept(i, input.get(i));
        }
    }
}
