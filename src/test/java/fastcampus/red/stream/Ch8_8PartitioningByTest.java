package fastcampus.red.stream;

import fastcampus.red.stream.model.User;
import fastcampus.red.stream.service.EmailService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

// 8-8 Collectors.partitioningBy() , 2분할
public class Ch8_8PartitioningByTest {

    @DisplayName("")
    @Test
    void partitioningBy() {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);

        Map<Boolean, List<Integer>> result = numbers.stream()
                .collect(Collectors.partitioningBy(v -> v % 2 == 1));

        assertThat(result.get(true)).hasSize(6); // 홀수
        assertThat(result.get(false)).hasSize(4); // 짝수
    }

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

        return Arrays.asList(user1, user2, user3);
    }

    @DisplayName("친구가 5명 초과하면 친구랑 놀고, 5명이하면 친구를 더 늘려라")
    @Test
    void partitioningByFriendSize() {
        List<User> userList = getUserList();

        Map<Boolean, List<User>> result = userList.stream().collect(Collectors.partitioningBy(u -> u.getFriendUserIds().size() > 5));

        assertThat(result.get(true)).hasSize(1); // 5명 초과한 경우
        assertThat(result.get(false)).hasSize(2); // 5명 이하인 경우

        // 아래와 같이 2분할하여 서비스 실행 가능
        EmailService emailService = new EmailService();
        result.get(true).forEach(emailService::sendPlayWithFriendsEmail);
        result.get(false).forEach(emailService::sendMakeMoreFriendsEmail);
    }
}
