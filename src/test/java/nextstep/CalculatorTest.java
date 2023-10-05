package nextstep;

import org.junit.jupiter.api.*;

class CalculatorTest {
    private Calculator cal;

    @DisplayName("Before to BeforeEach")
    @BeforeEach
    public void init() {
        cal = new Calculator();
        System.out.println("BeforeEach");
    }

    @DisplayName(value = "add 테스트")
    @Test
    public void add() {
        Assertions.assertEquals(3, cal.add(1, 2));
        System.out.println("add");
    }

    @DisplayName(value = "subtract 테스트")
    @Test
    public void subtract() {
        Assertions.assertEquals(3, cal.subtract(6, 3));
        System.out.println("subtract");
    }

    @DisplayName("After to AfterEach")
    @AfterEach
    public void teardown() {
        System.out.println("teardown");
    }

}