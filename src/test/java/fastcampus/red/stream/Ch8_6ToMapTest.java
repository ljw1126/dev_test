package fastcampus.red.stream;

import fastcampus.red.stream.model.Order;
import fastcampus.red.stream.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

// 8-6 Collectors.toMap
public class Ch8_6ToMapTest {

    private List<User> getUserList() {
        User user1 = User.builder()
                .id(101)
                .name("Aaul")
                .isVerified(true)
                .emailAddress("alice@fastcampus.co.kr")
                .friendUserIds(Arrays.asList(201, 202, 203, 204))
                .build();

        User user2 = User.builder()
                .id(102)
                .name("David")
                .isVerified(false)
                .emailAddress("bob@fastcampus.co.kr")
                .friendUserIds(Arrays.asList(204, 205, 206))
                .build();

        User user3 = User.builder()
                .id(103)
                .name("John")
                .isVerified(false)
                .emailAddress("charlie@fastcampus.co.kr")
                .friendUserIds(Arrays.asList(204, 205, 207))
                .build();

        return Arrays.asList(user1, user2, user3);
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
                .status(Order.OrderStatus.PROCESSED)
                .build();

        return Arrays.asList(order1, order2, order3);
    }


    @DisplayName("")
    @Test
    void toMap() {
        Map<Integer, String> result = Stream.of(3, 5, -4, 2, 6)
                .collect(Collectors.toMap(Function.identity(), x -> "Number " + x));

        assertThat(result)
                .containsEntry(3, "Number 3")
                .containsEntry(5, "Number 5")
                .containsEntry(-4, "Number -4")
                .containsEntry(2, "Number 2")
                .containsEntry(6, "Number 6");
    }

    @DisplayName("")
    @Test
    void toMapIdAndUser() {
        List<User> userList = getUserList();
        Map<Integer, User> result = userList.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        assertThat(result).hasSize(3);
    }

    @DisplayName("")
    @Test
    void toMapOrderIdAndStatus() {
        List<Order> orderList = getOrderList();

        Map<Long, Order.OrderStatus> result = orderList.stream()
                .collect(Collectors.toMap(Order::getId, Order::getStatus));

        assertThat(result).hasSize(3);
    }
}
