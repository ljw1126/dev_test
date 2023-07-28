package basic.function.ch4.functionalInterface;

import java.util.function.Supplier;

public class Ch4_Supplier {
    public static void main(String[] args) {
        Supplier<String> myStringSupplier = () -> "hell word";
        System.out.println(myStringSupplier.get()); // hell word

        // 함수가 1등 시민이 되었으므로, 매개변수로 인자로 전달 가능
        Supplier<Double> myRandomSupplier = () -> Math.random();
        printRandomDouble(myRandomSupplier, 5);
    }

    private static void printRandomDouble(Supplier<Double> supplier, int count) {
        for(int i = 1;  i <= count; i++) {
            System.out.println(supplier.get());
        }
    }
}
