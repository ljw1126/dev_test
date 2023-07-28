package basic.function.ch3.lambda;

import java.util.function.Function;

public class Adder implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer t) {
        return 10 + t;
    }
}
