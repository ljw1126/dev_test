package basic.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OperationTest {

    static <T extends Enum<T> & CommonOperationMethod> void test(Class<T> classType, double x, double y) {
        for(CommonOperationMethod op : classType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    @DisplayName("")
    @Test
    void basicOperation() {
        double x = 3;
        double y = 4;

        test(BasicOperation.class, x, y);
        test(ExtendedOperation.class, x, y);
    }

    @DisplayName("")
    @Test
    void extendedOperation() {
        double x = 3;
        double y = 4;

        test(ExtendedOperation.class, x, y);
    }
}
