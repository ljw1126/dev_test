package jupiter;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Calculator {
    PLUS("+") {
        public double apply(int x, int y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(int x, int y) {
            return x - y;
        }
    },
    MULTIPLY("*") {
        public double apply(int x, int y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(int x, int y) {
            return x / y;
        }
    };

    private final String symbol;

    Calculator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(int x, int y);

    private static final Map<String, Calculator> stringToEnum = Stream.of(values()).collect(Collectors.toMap(Calculator::toString, Function.identity()));

    public static Optional<Calculator> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }
}

