package basic.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PizzaTest {

    @DisplayName("피자 상태가 READY 일때, 배송 가능 여부는 true 이다")
    @Test
    void givenPizzaOrder_whenReady_thenDeliverable() {
        // given
        Pizza givenPizza = new Pizza(PizzaStatus.READY);

        // when
        // then
        assertThat(givenPizza.isDeliverable()).isTrue();
    }


    @DisplayName("피자 주문이 3개 주어졌을 때, 주문 상태 값에 따라 배송되지 않은 피자 주문는 2개이다")
    @Test
    void getAllUndeliveredPizzas_test() {
        // given
        List<Pizza> pizzaList = List.of(new Pizza(PizzaStatus.READY), new Pizza(PizzaStatus.ORDERED), new Pizza(PizzaStatus.DELIVERED));

        // when
        List<Pizza> undeliveredPizza = Pizza.getAllUndeliveredPizzas(pizzaList);

        // then
        assertThat(undeliveredPizza).hasSize(2);
        assertThat(undeliveredPizza).extracting("pizzaStatus")
                .containsExactlyInAnyOrder(PizzaStatus.ORDERED, PizzaStatus.READY);
    }

    @DisplayName("피자 주문 리스트가 주어졌을 때, groupPizzaByStatus 메서드 호출하면 PizzaStatus 키값으로 피자 주문이 그룹핑된다")
    @Test
    void givenPizzaList_whenGroupByStatusCalled_thenCorrectlyGrouped() {
        // given
        Pizza pz1 = new Pizza(PizzaStatus.ORDERED);
        Pizza pz2 = new Pizza(PizzaStatus.ORDERED);
        Pizza pz3 = new Pizza(PizzaStatus.READY);
        Pizza pz4 = new Pizza(PizzaStatus.READY);
        Pizza pz5 = new Pizza(PizzaStatus.DELIVERED);

        List<Pizza> pizzaList = List.of(pz1, pz2, pz3, pz4, pz5);

        // when
        EnumMap<PizzaStatus, List<Pizza>> enumMap = Pizza.groupPizzaByStatus(pizzaList);

        // then
        assertThat(enumMap.get(PizzaStatus.ORDERED).size()).isEqualTo(2);
        assertThat(enumMap.get(PizzaStatus.READY).size()).isEqualTo(2);
        assertThat(enumMap.get(PizzaStatus.DELIVERED).size()).isEqualTo(1);
    }

    @DisplayName("피자가 READY 상태 일때 주문을 호출하면 주문 상태값이 DELIVERED 가 된다")
    @Test
    void deliver_method_test() {
        // given
        Pizza pizza = new Pizza(PizzaStatus.READY);

        // when
        pizza.deliver();

        // then
        assertThat(pizza.getPizzaStatus()).isEqualTo(PizzaStatus.DELIVERED);
    }
}