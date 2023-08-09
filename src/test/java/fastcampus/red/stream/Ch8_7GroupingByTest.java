package fastcampus.red.stream;

import fastcampus.red.stream.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

// 8-7 groupingBy
// 그룹핑의 결과는 Map
public class Ch8_7GroupingByTest {

    @DisplayName("10으로 나눈 나머지별로 그룹화한다")
    @Test
    void groupingByMod() {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Integer, List<Integer>> result = numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 10)); // value : default List , result : default HashMap

        assertThat(result.get(1)).containsExactly(101);
        assertThat(result.get(2)).containsExactly(2, 402, 2312);
        assertThat(result.get(3)).containsExactly(13, 203, 203);
    }

    @DisplayName("10으로 나눈 나머지로 그룹화하여 Set 담는다")
    @Test
    void groupingByModAndSetValue() {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Integer, Set<Integer>> result = numbers.stream()
                .collect(Collectors.groupingBy(x -> x % 10, Collectors.toSet()));

        assertThat(result.get(1)).isInstanceOf(HashSet.class);
        assertThat(result.get(1)).containsExactly(101);
        assertThat(result.get(2)).containsExactly(2, 402, 2312);
        assertThat(result.get(3)).containsExactly(203, 13); // Set 순서 보장 x
    }

    @DisplayName("")
    @Test
    void groupingByNumberAndMappingValueList() {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Integer, List<String>> result = numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 10,
                                Collectors.mapping(n -> "unit digit is " + n, Collectors.toList())
                        )
                );

        assertThat(result.get(1)).hasSize(1);
        assertThat(result.get(2)).hasSize(3);
        assertThat(result.get(3)).hasSize(3);
    }

    private List<Order> getOrderList() {
        Order order1 = Order.builder()
                .id(1001L)
                .amount(BigDecimal.valueOf(2000))
                .status(Order.OrderStatus.CREATED)
                .build();

        Order order2 = Order.builder()
                .id(1002L)
                .amount(BigDecimal.valueOf(4000))
                .status(Order.OrderStatus.ERROR)
                .build();

        Order order3 = Order.builder()
                .id(1003L)
                .amount(BigDecimal.valueOf(7000))
                .status(Order.OrderStatus.ERROR)
                .build();

        Order order4 = Order.builder()
                .id(1004L)
                .amount(BigDecimal.valueOf(7000))
                .status(Order.OrderStatus.PROCESSED)
                .build();

        return Arrays.asList(order1, order2, order3, order4);
    }

    @DisplayName("")
    @Test
    void groupingByOrderStatus() {
        List<Order> orderList = getOrderList();

        Map<Order.OrderStatus, List<Order>> result = orderList.stream().collect(Collectors.groupingBy(Order::getStatus));

        assertThat(result.get(Order.OrderStatus.CREATED)).hasSize(1);
        assertThat(result.get(Order.OrderStatus.ERROR)).hasSize(2);
        assertThat(result.get(Order.OrderStatus.PROCESSED)).hasSize(1);
    }

    @DisplayName("")
    @Test
    void groupingByOrderStatusAndTotalValue() {
        List<Order> orderList = getOrderList();

        Map<Order.OrderStatus, BigDecimal> result = orderList.stream().collect(
                Collectors.groupingBy(
                        Order::getStatus,
                        Collectors.mapping(Order::getAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                )
        );

        assertThat(result.get(Order.OrderStatus.CREATED)).isEqualTo(BigDecimal.valueOf(2000));
        assertThat(result.get(Order.OrderStatus.ERROR)).isEqualTo(BigDecimal.valueOf(11000));
        assertThat(result.get(Order.OrderStatus.PROCESSED)).isEqualTo(BigDecimal.valueOf(7000));
    }
}
