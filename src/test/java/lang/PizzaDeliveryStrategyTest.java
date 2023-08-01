package lang;

import basic.function.Pizza;
import basic.function.PizzaDeliveryStrategy;
import basic.function.PizzaStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class PizzaDeliveryStrategyTest {

    // System.out.println() 테스트 참고. https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream printStream = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(printStream);
    }

    @DisplayName("EXPRESS 전략의 경우 deliver 메서드 호출시 'Pizza will be delivered in express mode' 메세지를 출력합니다")
    @Test
    void EXPRESS_TYPE_DELIVER_MSG_TEST() {
        // given
        PizzaDeliveryStrategy givenType = PizzaDeliveryStrategy.EXPRESS;

        // when
        // then
        givenType.deliver(new Pizza(PizzaStatus.ORDERED));
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Pizza will be delivered in express mode");
    }

    @DisplayName("NORMAL 전략의 경우 deliver 메서드 호출시 'Pizza will be delivered in normal mode' 메세지를 출력합니다")
    @Test
    void NORMAL_TYPE_DELIVER_MSG_TEST() {
        // given
        PizzaDeliveryStrategy givenType = PizzaDeliveryStrategy.NORMAL;

        // when
        // then
        givenType.deliver(new Pizza(PizzaStatus.ORDERED));
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Pizza will be delivered in normal mode");
    }
}