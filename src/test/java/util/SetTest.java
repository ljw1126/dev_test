package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// HashSet의 경우 내부적으로 Map을 사용하고 있고, Map의 key가 중복을 허용하지 않는 점을 이용해서 Key에 저장하네
public class SetTest {

    private Set<Integer> setA;
    private Set<Integer> setB;

    private static Set<Integer> setOf(Integer... values) {
        return new HashSet<>(Arrays.asList(values));
    }

    @BeforeEach
    void setUp() {
        setA = setOf(1, 2, 3, 4);
        setB = setOf(2, 4, 6, 8);
    }

    @DisplayName("두 집합의 교집합(Intersection)을 구한다")
    @Test
    void retainAll() {
        //given
        Set<Integer> givenData = setOf(2, 4);

        //when
        setA.retainAll(setB);

        //then
        assertThat(setA).hasSize(2).isEqualTo(givenData);
        assertThat(setA).containsExactly(2, 4);
    }

    @DisplayName("두 집합의 합집합(Union)을 구한다")
    @Test
    void addAll() {
        //given
        Set<Integer> givenData = setOf(1, 2, 3, 4, 6, 8);

        //when
        setA.addAll(setB);

        //then
        assertThat(setA).hasSize(6).isEqualTo(givenData);
        assertThat(setA).containsExactly(1, 2, 3, 4, 6, 8);
    }

    @DisplayName("집합 A 기준으로 집합 B에 대한 차집합을 구한다")
    @Test
    void removeAll() {
        //given
        Set<Integer> givenData = setOf(1, 3);

        //when
        setA.removeAll(setB);

        //then
        assertThat(setA).hasSize(2).isEqualTo(givenData);
        assertThat(setA).containsExactly(1, 3);
    }

    @DisplayName("stream filter 로 Set의 교집합을 구한다")
    @Test
    void intersectionByStream() {
        //given
        Set<Integer> givenData = setOf(2, 4);

        //when
        Set<Integer> result = setA.stream().filter(setB::contains).collect(Collectors.toSet());

        //then
        assertThat(result).isEqualTo(givenData);
    }

    @DisplayName("Stream concat 으로 Set의 합집합을 구한다")
    @Test
    void unionByStream() {
        //given
        Set<Integer> givenData = setOf(1, 2, 3, 4, 6, 8);

        //when
        Set<Integer> result = Stream.concat(setA.stream(), setB.stream()).collect(Collectors.toSet());

        //then
        assertThat(result).hasSize(6).isEqualTo(givenData);
    }

    @DisplayName("stream filter 로 Set의 차집합을 구한다")
    @Test
    void relativeComplementByStream() {
        //given
        Set<Integer> givenData = setOf(1, 3);

        //when
        Set<Integer> result = setA.stream().filter(a -> !setB.contains(a)).collect(Collectors.toSet());

        //then
        assertThat(result).hasSize(2).isEqualTo(givenData);
    }

}
