package util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class IteratorTest {
    @DisplayName("")
    @Test
    void forEachRemainingTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        list.iterator().forEachRemaining(System.out::println);
        list.iterator().forEachRemaining(a -> System.out.println(a * 10));
    }

    @DisplayName("Collection interface 구현체는 iterator 객체를 얻어서 요소 조회가 가능하다")
    @Test
    void listIteratorTest() {
        // given [1, 2, 3, 4, 5, 6, 7, 8, 9]
        List<Integer> list = IntStream.range(1, 10).boxed().collect(Collectors.toList());

        // when
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            int i = iterator.next();

            if(i % 2 == 0) iterator.remove();
        }

        // then
        assertThat(list).hasSize(5);
        assertThat(list).containsExactly(1, 3, 5, 7, 9);
    }

    @DisplayName("Collection interface 구현한 Set 또한 Iterator 동일하게 적용가능하다")
    @Test
    void setIteratorTest() {
        // given [1, 2, 3, 4, 5, 6, 7, 8, 9]
        Set<Integer> data = IntStream.range(1, 10).boxed().collect(Collectors.toCollection(TreeSet::new));

        // when
        Iterator<Integer> iterator = data.iterator();
        while(iterator.hasNext()) {
            int i = iterator.next();

            if(i % 2 == 1) iterator.remove(); // 홀수 제거
        }

        // then
        assertThat(data).hasSize(4);
        assertThat(data).contains(2, 4, 6, 8);
    }

    @DisplayName("")
    @Test
    void mapIteratorTestByKeySet() {
        // given
        Map<String, Integer> data = new HashMap<>();
        data.put("A", 1);
        data.put("B", 2);
        data.put("C", 3);
        data.put("D", 4);

        // when
        Iterator<Integer> iterator = data.values().iterator();
        while(iterator.hasNext()) {
            int next = iterator.next();

            if(next % 2 == 0) {
                iterator.remove();
            }
        }

        // then
        assertThat(data).hasSize(2);
        assertThat(data).containsEntry("A", 1).containsEntry("C", 3);
    }

    @DisplayName("")
    @Test
    void mapIteratorTestByEntrySet() {
        // given
        Map<String, Integer> data = new HashMap<>();
        data.put("A", 1);
        data.put("B", 2);
        data.put("C", 3);
        data.put("D", 4);

        // when
        Iterator<Map.Entry<String, Integer>> iterator = data.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();

            if(data.get(next.getKey()) % 2 == 1) {
                iterator.remove();
            }
        }

        // TODO. MAP 에서 key 존재 여부 검사
        // then
        assertThat(data).hasSize(2);
        assertThat(data).containsEntry("B", 2).containsEntry("D", 4);
    }

    @DisplayName("")
    @Test
    void mapIteratorTestByValues() {
        // given
        Map<String, Integer> data = new HashMap<>();
        data.put("A", 1);
        data.put("B", 2);
        data.put("C", 3);
        data.put("D", 4);

        // when
        Iterator<String> keyIterator = data.keySet().iterator();
        while(keyIterator.hasNext()) {
            String key = keyIterator.next();

            if(data.get(key) % 2 == 1) {
                keyIterator.remove();
            }
        }

        // then
        assertThat(data).hasSize(2);
        assertThat(data).containsEntry("B", 2).containsEntry("D", 4);
    }

    private final PrintStream printStream = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @DisplayName("(java 1.0)Enumeration Interface 를 사용하면 Legacy Collection class 읽기(READ Only)만 가능하다")    @Test
    void enumerationInterfaceTest() {
        // given
        System.setOut(new PrintStream(outputStreamCaptor));

        StringBuilder sb = new StringBuilder();
        Vector<Integer> v = new Vector<>();

        for(int i = 1; i <= 5; i++) v.add(i);

        // when
        Enumeration e = v.elements();
        while(e.hasMoreElements()) {
            int i = (Integer) e.nextElement();
            sb.append(i).append(" ");
        }

        // then
        System.out.println(sb);
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("1 2 3 4 5 5");


        System.setOut(printStream);
    }

}
