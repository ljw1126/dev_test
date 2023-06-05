package nextstep;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(3, cal.add(1, 2));
        System.out.println("add");
    }

    @DisplayName(value = "subtract 테스트")
    @Test
    public void subtract() {
        assertEquals(3, cal.subtract(6, 3));
        System.out.println("subtract");
    }

    @DisplayName("After to AfterEach")
    @AfterEach
    public void teardown() {
        System.out.println("teardown");
    }

}