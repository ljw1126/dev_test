package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HashMapTest {

    Map<String, Integer> map = new HashMap<>();

    @BeforeEach
    void setUp() {
        map.put("A", 65);
        map.put("B", 66);
        map.put("C", 67);
        map.put("D", 68);
        map.put("Z", 90);
    }

    @DisplayName("key가 존재하지 않을 때만 value를 넣는다")
    @Test
    void putIfAbsent() { // absent : 없는
        //given
        //when
        map.putIfAbsent("A", 0);
        map.putIfAbsent("X", 88);

        //then
        assertThat(map.get("A")).isEqualTo(65);
        assertThat(map.get("X")).isEqualTo(88);

        assertThat(map.putIfAbsent("Y", 89)).isNull(); // key 값이 존재하지 않는 경우
        assertThat(map.putIfAbsent("Y", 999)).isEqualTo(89); // key 값이 존재하는 경우

        assertThat(map).containsEntry("A", 65)
                       .containsEntry("B", 66)
                       .containsEntry("C", 67)
                       .containsEntry("D", 68)
                       .containsEntry("X", 88)
                       .containsEntry("Z", 90);
    }

    @DisplayName("key 에 mapping 되는 value를 가져오거나, 없으면 defaultValue를 반환")
    @Test
    void getOrDefault() {
        //given
        int value1 = 0;
        int value2 = 90;

        //when
        int result1 = map.getOrDefault("X", 0);
        int result2 = map.getOrDefault("Z", 0);

        //then
        assertThat(result1).isEqualTo(value1);
        assertThat(result2).isEqualTo(value2);
    }

    @DisplayName("key에 해당하는 노드의 존재 여부를 확인한다")
    @Test
    void containsKey() {
        //given when then
        assertThat(map.containsKey("A")).isTrue();
        assertThat(map.containsKey("Y")).isFalse();
    }

    @DisplayName("인자로 전달한 value의 존재여부를 확인하다")
    @Test
    void containsValue() {
        //given when then
        assertThat(map.containsValue(65)).isTrue();
        assertThat(map.containsValue(999)).isFalse();
    }

    @DisplayName("")
    @Test
    void entrySet() {
        //given
        Set<String> result = Set.of("A", "B", "C", "D", "Z");

        //when
        Set<String> keySet = map.keySet();

        //then
        assertThat(keySet).isEqualTo(result);
    }

    @DisplayName("")
    @Test
    void values() {
        //given when
        Collection<Integer> values = map.values();

        //then
        assertThat(values).containsExactly(65, 66, 67, 68, 90);
    }

    @DisplayName("")
    @Test
    void keySet() {
        //given when
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

        for(Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @DisplayName("key가 없으면 BiFunction 결과 추가하고, 있으면 BiFunction 결과로 갱신한다")
    @Test
    void compute() {
        assertThatThrownBy(() -> map.compute("NPE", null)).isInstanceOf(NullPointerException.class);

        assertThat(map.compute("A", (key, value) -> value + 10)).isEqualTo(75);
        assertThat(map.compute("NPE", (key, value) -> 9999)).isEqualTo(9999);
    }

    @DisplayName("key 존재하면 value를 반환하고, 없으면 함수 결과 value와 key를 저장ㅎ낟 ")
    @Test
    void computeIfAbsent() {
        assertThatThrownBy(() -> map.computeIfAbsent("NPE", null))
                .isInstanceOf(NullPointerException.class);

        assertThat(map.computeIfAbsent("A", key -> 999999)).isEqualTo(65);
        assertThat(map.computeIfAbsent("X", key -> 88)).isEqualTo(88);
    }

    @DisplayName("key가 존재하면 함수 실행 결과값 갱신후 결과값 반환하고, 없으면 null 반환")
    @Test
    void computeIfPresent() {
        assertThatThrownBy(() -> map.computeIfPresent("NPE", null))
                .isInstanceOf(NullPointerException.class);

        assertThat(map.computeIfPresent("X", (key, value) -> 88)).isNull();
        assertThat(map.computeIfPresent("A", (key, value) -> value + 10)).isEqualTo(75);
    }

    @DisplayName("")
    @Test
    void merge() {
        assertThatThrownBy(() -> map.merge("NPE", null, (oldValue, putValue) -> oldValue + putValue))
                .isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> map.merge("NPE", 0, null))
                .isInstanceOf(NullPointerException.class);

        assertThat(map.merge("A", 1, (oldValue, putValue) -> oldValue * 10)).isEqualTo(650);
        assertThat(map.merge("Y", 89, (oldValue, putValue) -> putValue * 10)).isEqualTo(89);
        assertThat(map.merge("Y", 89, (oldValue, putValue) -> null)).isNull();
    }



}
