package fastcampus.red.stream;

import fastcampus.red.stream.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class Ch8_10ParallelTest {
    private List<User> getUserList() {
        User user1 = User.builder()
                .id(101)
                .name("Aaul")
                .isVerified(true)
                .emailAddress("alice@fastcampus.co.kr")
                .friendUserIds(Arrays.asList(201, 202, 203, 204, 205, 206))
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

        User user4 = User.builder()
                .id(104)
                .name("Bob")
                .isVerified(true)
                .emailAddress("bob@fastcampus.co.kr")
                .build();

        User user5 = User.builder()
                .id(105)
                .name("John")
                .isVerified(false)
                .emailAddress("john@fastcampus.co.kr")
                .friendUserIds(Arrays.asList(204,205,206))
                .build();

        User user6 = User.builder()
                .id(106)
                .name("May")
                .isVerified(false)
                .emailAddress("may@fastcampus.co.kr")
                .build();

        return Arrays.asList(user1, user2, user3, user4, user5, user6);
    }

    @DisplayName("병렬 스트림이 순서는 섞이지만, 처리 속도는 직렬 스트림보다 빠르다")
    @Test
    void compareSequentialAndParallel() {
        List<User> userList = getUserList();

        long startTime = System.currentTimeMillis();

        userList.stream().filter(user -> !user.isVerified())
                .forEach(System.out::println);

        long endTime = System.currentTimeMillis();
        long result1 = (endTime - startTime);

        startTime = System.currentTimeMillis();

        userList.stream().parallel().filter(user -> !user.isVerified())
                .forEach(System.out::println);

        endTime = System.currentTimeMillis();
        long result2 = (endTime - startTime);

        System.out.println(result1 + "ms");
        System.out.println(result2 + "ms");

        assertThat(result1).isGreaterThan(result2);
    }
}
