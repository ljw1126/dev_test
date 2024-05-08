package util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeSetTest {

    private TreeSet<Integer> numberSet;
    private TreeSet<Integer> reverseNumberSet;

    @BeforeEach
    void setUp() {
        numberSet = new TreeSet<>();
        numberSet.addAll(IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList()));

        reverseNumberSet = new TreeSet<>(Comparator.reverseOrder());
        reverseNumberSet.addAll(IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList()));
    }

    @DisplayName("검색에 최적화 되어 있는 TreeSet은 내부적은 TreeMap 사용한다")
    @Test
    void treeSet() {
        assertThat(reverseNumberSet).hasSize(10);
        assertThat(reverseNumberSet).containsExactly(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
    }

    @DisplayName("null 추가할 경우 NPE가 발생한다")
    @Test
    void nullDisable() {
        Assertions.assertThatNullPointerException().isThrownBy(() -> numberSet.add(null));
    }

    @DisplayName("")
    @Test
    void remove() {
        Iterator<Integer> iterator = reverseNumberSet.iterator();
        while (iterator.hasNext()) {
            int v = iterator.next();
            if (v % 2 == 0) iterator.remove();
        }

        assertThat(reverseNumberSet).hasSize(5)
                .containsExactly(9, 7, 5, 3, 1);
    }

    @DisplayName("")
    @Test
    void clear() {
        reverseNumberSet.clear();

        assertThat(reverseNumberSet).hasSize(0);
    }

    @DisplayName("정렬된 순서에서 첫번째 요소 반환한다")
    @Test
    void first() {
        assertThat(numberSet.first()).isEqualTo(1);
        assertThat(numberSet).hasSize(10);
    }

    @DisplayName("정렬된 순서에서 마지막 요소 반환한다")
    @Test
    void last() {
        assertThat(numberSet.last()).isEqualTo(10);
        assertThat(numberSet).hasSize(10);
    }

    @DisplayName("지정된 값보다 큰 값(초과)을 가진 객체 중 제일 가까운 값의 객체 반환(인자값 미포함, 없으면 null)")
    @Test
    void higher() {
        int result = numberSet.higher(5);
        assertThat(result).isEqualTo(6);
        assertThat(numberSet).hasSize(10);
    }

    @Test
    void higherByReverseOrder() {
        int result = reverseNumberSet.higher(5);
        assertThat(result).isEqualTo(4);
        assertThat(reverseNumberSet).hasSize(10);
    }

    @DisplayName("지정된 값보다 작은 값(미만)을 가진 객체 중 제일 가까운 값의 객체 반환(인자값 미포함, 없으면 null)")
    @Test
    void lower() {
        int result = numberSet.lower(5);
        assertThat(result).isEqualTo(4);
        assertThat(numberSet).hasSize(10);
    }

    @Test
    void lowerByReverseOrder() {
        int result = reverseNumberSet.lower(5);
        assertThat(result).isEqualTo(6);
        assertThat(reverseNumberSet).hasSize(10);
    }

    @DisplayName("지정된 값 이상의 값 중 가장 작은 값(인자값 포함)")
    @Test
    void ceiling() {
        numberSet.add(12); // 1 ~ 10, 12

        int result = numberSet.ceiling(11); // 인자값 12이면 12 반환
        assertThat(result).isEqualTo(12);
        assertThat(numberSet).hasSize(11);
    }

    @DisplayName("지정된 값 이하의 값 중 가장 큰 값(인자값 포함)")
    @Test
    void floor() {
        numberSet.add(12); // 1 ~ 10, 12

        int result = numberSet.floor(11); // 인자값 12이면 12 반환
        assertThat(result).isEqualTo(10);
        assertThat(numberSet).hasSize(11);
    }

    @DisplayName("from ~ to 범위 검색 결과를 반환(to : exclusive)")
    @Test
    void subSet() {
        SortedSet<Integer> subSet = numberSet.subSet(3, 6);

        assertThat(subSet).hasSize(3)
                .containsExactly(3, 4, 5);
        assertThat(numberSet).hasSize(10);
    }

    @DisplayName("주어진 값 보다 작은 값의 요소들을 반환한다(to: exclusive)")
    @Test
    void headSet() {
        SortedSet<Integer> headSet = numberSet.headSet(5);

        assertThat(headSet).hasSize(4)
                .containsExactly(1, 2, 3, 4);
        assertThat(numberSet).hasSize(10);
    }

    @DisplayName("주어진 값보다 큰 요소들을 반환한다(from: inclusive)")
    @Test
    void tailSet() {
        SortedSet<Integer> tailSet = numberSet.tailSet(5);

        assertThat(tailSet).hasSize(6)
                .containsExactly(5, 6, 7, 8, 9, 10);
        assertThat(numberSet).hasSize(10);
    }

    @DisplayName("첫번째 요소 반환 후 삭제한다")
    @Test
    void pollFirst() {
        int result1 = numberSet.pollFirst();
        int result2 = reverseNumberSet.pollFirst();

        assertThat(result1).isEqualTo(1);
        assertThat(numberSet).hasSize(9);

        assertThat(result2).isEqualTo(10);
        assertThat(reverseNumberSet).hasSize(9);
    }

    @DisplayName("마지막 요소 반환 후 삭제한다")
    @Test
    void pollLast() {
        int result1 = numberSet.pollLast();
        int result2 = reverseNumberSet.pollLast();

        assertThat(result1).isEqualTo(10);
        assertThat(numberSet).hasSize(9);

        assertThat(result2).isEqualTo(1);
        assertThat(reverseNumberSet).hasSize(9);
    }
}
