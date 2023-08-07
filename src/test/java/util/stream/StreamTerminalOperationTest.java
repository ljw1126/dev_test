package util.stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class StreamTerminalOperationTest {

    @DisplayName("병렬 스트림에서 forEachOrdered 는 순서를 보장한다 (forEach는 순서 보장하지 않음)")
    @Test
    void forEachOrdered() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        list.stream().parallel().forEach(System.out::println); // 8 5 6 4 9 2 1 10 7 3
        list.stream().parallel().forEachOrdered(System.out::println); // 1 2 3 4 5 6 7 8 9 10
    }

    @DisplayName("")
    @Test
    void count() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        long count = list.stream().count();

        assertThat(count).isEqualTo(10);
    }

    @DisplayName("")
    @Test
    void toArray() {
        List<String> list = List.of("A", "B", "C", "D", "E");
        String[] result = list.stream().toArray(String[]::new);

        assertThat(result).isInstanceOf(String[].class);
    }
}
