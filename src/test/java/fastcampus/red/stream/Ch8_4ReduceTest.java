package fastcampus.red.stream;

import fastcampus.red.stream.model.Order;
import fastcampus.red.stream.model.OrderLine;
import fastcampus.red.stream.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 8-4. reduce
public class Ch8_4ReduceTest {

    @DisplayName("")
    @Test
    void sum() {
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        int sum = numbers.stream().reduce(0, Integer::sum); // (a, b) -> a + b;

        assertThat(sum).isEqualTo(1);
    }

    @DisplayName("")
    @Test
    void min() {
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        int min = numbers.stream().reduce(Integer.MAX_VALUE, Math::min); // (a, b) -> a < b ? a : b

        assertThat(min).isEqualTo(-5);
    }

    @DisplayName("")
    @Test
    void multiple() {
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        int result = numbers.stream().reduce(1, (a, b) -> a * b);

        assertThat(result).isEqualTo(120);
    }

    @DisplayName("")
    @Test
    void mappingAndReduce() {
        List<String> numberStrList = Arrays.asList("3", "2", "5", "4");
        int result = numberStrList.stream().map(Integer::parseInt).reduce(Integer::sum).get();

        assertThat(result).isEqualTo(14);

        // 위에 map().reduce() 방식을 좀 더 선호
        int result2 = numberStrList.stream().reduce(0, (number, str) -> number + Integer.parseInt(str), (a, b) -> a + b);
        assertThat(result2).isEqualTo(14);
    }

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

    @DisplayName("")
    @Test
    void friendSum() {
        List<User> users = getUserList();
        int sumOfFriend = users.stream().map(User::getFriendUserIds).map(List::size).reduce(0, Integer::sum);

        assertThat(sumOfFriend).isEqualTo(10);
    }

    private List<Order> getOrderList() {
        Order order1 = Order.builder()
                .id(1001L)
                .orderLines(Arrays.asList(
                        OrderLine.builder().amount(BigDecimal.valueOf(1000)).build(),
                        OrderLine.builder().amount(BigDecimal.valueOf(2000)).build()
                )).build();
        Order order2 = Order.builder()
                .id(1002L)
                .orderLines(Arrays.asList(
                        OrderLine.builder().amount(BigDecimal.valueOf(2000)).build(),
                        OrderLine.builder().amount(BigDecimal.valueOf(3000)).build()
                )).build();

        Order order3 = Order.builder()
                .id(1003L)
                .orderLines(Arrays.asList(
                        OrderLine.builder().amount(BigDecimal.valueOf(1000)).build(),
                        OrderLine.builder().amount(BigDecimal.valueOf(2000)).build()
                )).build();

        return Arrays.asList(order1, order2, order3);
    }

    @DisplayName("")
    @Test
    void sumOfAmount() {
        List<Order> orders = getOrderList();

        BigDecimal result = orders.stream()
                .map(Order::getOrderLines)
                .flatMap(List::stream)
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(result).isEqualTo(BigDecimal.valueOf(11000));
    }
}
