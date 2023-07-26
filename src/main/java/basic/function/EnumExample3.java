package basic.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Operation {
    PLUS("+") { double apply(double x, double y) { return x + y; } },
    MINUS("-") { double apply(double x, double y) { return x - y; } },
    TIMES("*") { double apply(double x, double y) { return x * y; } },
    DIVIDE("/") { double apply(double x, double y) { return x / y; } };


    private final String symbol;

    private Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    abstract double apply(double x, double y);

    private static final Map<String, Operation> stringToEnum = Stream.of(values()).collect(Collectors.toMap(Objects::toString, e -> e));

    // 지정한 문자열에 해당하는 Operation 을 반환한다 (p216)
    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }
}
public class EnumExample3 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        double x = Double.parseDouble(br.readLine());
        double y = Double.parseDouble(br.readLine());

        for(Operation op : Operation.values()) {
            sb.append(String.format("%f %s %f = %f%n", x, op, y, op.apply(x, y)));
        }

        System.out.println(sb);
    }
}
