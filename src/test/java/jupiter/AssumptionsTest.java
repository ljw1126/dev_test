package jupiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionsTest {

    @DisplayName("true인 경우 테스트 통과, false 인 경우 test ignore 된다")
    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
    }

    @DisplayName("true인 경우 테스트 통과, false 인 경우 test ignore 된다")
    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
    }

    @DisplayName("true인 경우 Executable 실행하고, false 인 경우 해당 테스트를 건너 다음 테스트를 실행한다")
    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, Calculator.DIVIDE.apply(4, 2));
                });

        assertEquals(42, Calculator.MULTIPLY.apply(6, 7));
    }
}
