package basic.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PizzaStatusTest {

    @DisplayName("ORDERED 타입의 배송시간은 5이다")
    @Test
    void checkTimeToDeliveryOfOrderedType() {
        // given
        PizzaStatus givenType = PizzaStatus.ORDERED;

        // when
        // then
        assertThat(givenType.getTimeToDelivery()).isEqualTo(5);
    }

    @DisplayName("READY 타입의 배송시간은 2이다")
    @Test
    void checkTimeToDeliveryOfReadyType() {
        // given
        PizzaStatus givenType = PizzaStatus.READY;

        // when
        // then
        assertThat(givenType.getTimeToDelivery()).isEqualTo(2);
    }

    @DisplayName("DELIVERED 타입의 배송시간은 0이다")
    @Test
    void checkTimeToDeliveryOfDeliveryType() {
        // given
        PizzaStatus givenType = PizzaStatus.DELIVERED;

        // when
        // then
        assertThat(givenType.getTimeToDelivery()).isEqualTo(0);
    }


    @DisplayName("ORDER 타입은 isOrdered() 리턴값이 true 이고, 그 이외는 false 이다")
    @Test
    void ordered_type_is_true_only_isOrdered() {
        // given
        PizzaStatus givenType = PizzaStatus.ORDERED;

        // when
        // then
        assertThat(givenType.isOrdered()).isTrue();
        assertThat(givenType.isReady()).isFalse();
        assertThat(givenType.isDelivered()).isFalse();
    }

    @DisplayName("READY 타입은 isReady() 리턴값이 true 이고, 그 이외는 false 이다")
    @Test
    void ready_type_is_true_only_isReady() {
        // given
        PizzaStatus givenType = PizzaStatus.READY;

        // when
        // then
        assertThat(givenType.isReady()).isTrue();
        assertThat(givenType.isOrdered()).isFalse();
        assertThat(givenType.isDelivered()).isFalse();
    }

    @DisplayName("DELIVERED 타입은 isDelivered() 리턴값이 true 이고, 그 이외는 false 이다")
    @Test
    void delivered_type_is_true_only_isDelivered() {
        // given
        PizzaStatus givenType = PizzaStatus.DELIVERED;

        // when
        // then
        assertThat(givenType.isDelivered()).isTrue();
        assertThat(givenType.isOrdered()).isFalse();
        assertThat(givenType.isReady()).isFalse();
    }
}