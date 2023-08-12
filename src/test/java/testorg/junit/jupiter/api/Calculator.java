package testorg.junit.jupiter.api;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Calculator {
    PLUS("+") {
        double apply(int x, int y) {
            return x + y;
        }
    },
    MINUS("-") {
        double apply(int x, int y) {
            return x - y;
        }
    },
    MULTIPLY("*") {
        double apply(int x, int y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        double apply(int x, int y) {
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

    abstract double apply(int x, int y);

    private static final Map<String, Calculator> stringToEnum = Stream.of(values()).collect(Collectors.toMap(Calculator::toString, Function.identity()));

    public static Optional<Calculator> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }
}

