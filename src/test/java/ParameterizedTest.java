import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.EnumSource.Mode.*;

// https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
public class ParameterizedTest {

    @org.junit.jupiter.params.ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument) {
        assertThat(argument).isBetween(1, 3);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
        assertThat(unit).isNotNull();
    }

    @DisplayName("지정한 ENUM 파라미터만 테스트 가능하다")
    @org.junit.jupiter.params.ParameterizedTest
    @EnumSource(names = {"DAYS", "HOURS"}) // default INCLUDE mode
    void testWithEnumSourceInclude(ChronoUnit unit) {
        EnumSet<ChronoUnit> units = EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS);

        assertThat(units.contains(unit)).isTrue();
    }

    @DisplayName("EXCLUDE 모드로 파라미터 제외 할 수 있다")
    @org.junit.jupiter.params.ParameterizedTest
    @EnumSource(mode = EXCLUDE, names = {"ERAS", "FOREVER"})
    void testWithEnumSourceExclude(ChronoUnit unit) {
        EnumSet<ChronoUnit> units = EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER);

        assertThat(units.contains(unit)).isFalse();
    }

    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String arg) {
        assertThat(arg).isNotNull();
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    @DisplayName("MethodSource를 지정하지 않으면 테스트 함수 명과 동일한 factory method를 호출한다")
    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource
    void testWithDefaultLocalMethodSource(String arg) {
        assertThat(arg).isNotNull();
    }

    static Stream<String> testWithDefaultLocalMethodSource() {
        return Stream.of("apple", "banana");
    }

    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertThat(str.length()).isEqualTo(5);
        assertThat(num).isBetween(1, 2);
        assertThat(list).hasSize(2);
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.arguments("apple", 1, Arrays.asList("a", "b")),
                Arguments.arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }
}
