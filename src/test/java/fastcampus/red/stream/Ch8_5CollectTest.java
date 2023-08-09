package fastcampus.red.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// 8-5 collect
public class Ch8_5CollectTest {

    @DisplayName("toList() 는 기본 ArrayList 로 생성된다")
    @Test
    void toList() {
        List<Integer> list = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.toList());

        assertThat(list).hasSize(6);
        assertThat(list).isInstanceOf(ArrayList.class);
    }

    @DisplayName("toSet() 은 기본 HashSet 으로 생성된다")
    @Test
    void toSet() {
        Set<Integer> result = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.toSet());

        assertThat(result).hasSize(4);
        assertThat(result).isInstanceOf(HashSet.class);
    }

    @DisplayName("절대값으로 변환 후 List 에 담아 반환한다")
    @Test
    void mappingToList() {
        List<Integer> result = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.mapping(v -> Math.abs(v), Collectors.toList()));

        assertThat(result).hasSize(6);
        assertThat(result).containsExactly(3, 5, 3, 3, 4, 5);
    }

    @DisplayName("절대값으로 변환 후 Set 에 담아 반환한다")
    @Test
    void mappingToSet() {
        Set<Integer> result = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.mapping(v -> Math.abs(v), Collectors.toSet()));

        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(3, 4, 5);
    }

    @DisplayName("reducing 을 사용하여 스트림 합산 결과를 구할 수 있다")
    @Test
    void reducing() {
        int sum = Stream.of(3, 5, -3, 3, 4, 5).collect(Collectors.reducing(0, Integer::sum));

        assertThat(sum).isEqualTo(17);
    }
}
