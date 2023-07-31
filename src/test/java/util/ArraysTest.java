package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArraysTest {

    @DisplayName("Arrays.equals 테스트")
    @Test
    void equalsTest() {
        // given
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {4, 5, 6};

        // when
        // then
        assertThat(Arrays.equals(arr1, arr2)).isTrue();
        assertThat(Arrays.equals(arr2, arr3)).isFalse();
    }

    @DisplayName("Arrays.deepEquals 을 사용하면 2차원 배열 이상의 배열을 비교하여 ")
    @Test
    void deepEqualsTest() {
        // given
        int[][] arr1 = new int[][]{{1, 2}, {3, 4}};
        int[][] arr2 = new int[][]{{1, 2}, {3, 4}};

        String[][] arr3 = new String[][]{{"A", "B"}, {"C", "D"}};
        String[][] arr4 = new String[][]{{"E", "F"}, {"G", "H"}};

        // when
        // then
        assertThat(Arrays.deepEquals(arr1, arr2)).isTrue();
        assertThat(Arrays.deepEquals(arr3, arr4)).isFalse();
    }

    @DisplayName("Arrays.toString 은 1차원 배열을 인자로 받아 String 문자열 형태로 반환한다")
    @Test
    void toStringTest() {
        // given
        int[] arr1 = new int[]{1, 2, 3};
        String[] arr2 = new String[]{"A", "B", "C"};

        // when

        // then
        assertThat(Arrays.toString(arr1)).isEqualTo("[1, 2, 3]");
        assertThat(Arrays.toString(arr2)).isEqualTo("[A, B, C]");
    }

    @DisplayName("Arrays.deepToString 은 2차원/다차원 배열 인자 받아 String 문자열로 변환하여 출력한다")
    @Test
    void deepToStringTest() {
        // given
        int[][] arr1 = new int[][]{{1, 2}, {3, 4}};
        String[][] arr2 = new String[][]{{"A", "B"}, {"C", "D"}};
        String[][][] arr3 = new String[][][]{{{"X"}, {"Y"}, {"Z"}}};

        // when
        // then
        assertThat(Arrays.deepToString(arr1)).isEqualTo("[[1, 2], [3, 4]]");
        assertThat(Arrays.deepToString(arr2)).isEqualTo("[[A, B], [C, D]]");
        assertThat(Arrays.deepToString(arr3)).isEqualTo("[[[X], [Y], [Z]]]");
    }

    @DisplayName("")
    @Test
    void copyOfTest() {
        // given
        int[] nums = new int[]{1, 2, 3, 4, 5};

        // when
        int[] copyOfNum1 = Arrays.copyOf(nums, 3); // [1, 2, 3]
        int[] copyOfNum2 = Arrays.copyOf(nums, nums.length); // [1, 2, 3, 4, 5]

        // then
        assertThat(copyOfNum1).hasSize(3);
        assertThat(copyOfNum1).containsExactly(1, 2, 3);

        assertThat(copyOfNum2).hasSize(nums.length);
        assertThat(copyOfNum2).containsExactly(1, 2, 3, 4, 5);

        assertThat(Arrays.equals(nums, copyOfNum2)).isTrue();
    }

    @DisplayName("")
    @Test
    void copyOfRangeTest() {
        // given
        int[] arr = {0, 1, 2, 3, 4, 5};

        // when
        int[] copy1 = Arrays.copyOfRange(arr, 1, 3); // [1, 2]
        int[] copy2 = Arrays.copyOfRange(arr, 0, arr.length); // [0, 1, 2, 3, 4, 5]

        // then
        assertThat(copy1).hasSize(2);
        assertThat(copy1).containsExactly(1, 2);

        assertThat(copy2).hasSize(6);
        assertThat(copy2).containsExactly(0, 1, 2, 3, 4, 5);

        assertThat(Arrays.equals(arr, copy2)).isTrue();
    }

    static class Student {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


    @DisplayName("")
    @Test
    void copyOfRangeWithClassTypeTest() {
        // given
        Student[] students = { new Student("홍길동", 15), new Student("이순신", 20) };

        // when
        Object[] copy = Arrays.copyOfRange(students, 0, 1, Object[].class);

        // then
        assertThat(copy).hasSize(1);
        assertThat(copy).extracting("name").containsExactly("홍길동");
    }

    @DisplayName("")
    @Test
    void sortTest() {
        // given
        int[] intArray = new int[]{5, 2, 1, 3, 4};
        String[] strings = new String[]{"z", "a", "y", "x"};
        Integer[] integers = Arrays.stream(intArray).boxed().toArray(Integer[]::new);

        // when
        Arrays.sort(intArray); // 기본 오름 차순 정렬, DualPivotQuicksort
        Arrays.sort(strings);
        Arrays.sort(integers, Collections.reverseOrder()); // 내림 차순 정렬 위해서는 Wrapper 클래스 변환 후 Comparator 정의

        // then
        assertThat(intArray).containsExactly(1, 2, 3, 4, 5);
        assertThat(strings).containsExactly("a", "x", "y", "z");
        assertThat(integers).containsExactly(5, 4, 3, 2, 1);
    }

    @DisplayName("Arrays.fill 을 사용하면 배열 요소를 원하는 값으로 초기화 할 수 있다")
    @Test
    void fillTest() {
        // given
        int[] dist = new int[5];
        boolean[] visit = new boolean[5];

        // when
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visit, 0, 4, true);

        // then
        assertThat(dist).containsOnly(Integer.MAX_VALUE);
        assertThat(visit).containsExactly(true, true, true, true, false);
    }

    @DisplayName("Arrays.setAll 은 배열 인덱스를 인자로 전달 받아, 연산 처리 결과값을 해당 인덱스에 반환한다")
    @Test
    void setAllTest() {
        // given
        int[] data = new int[5];

        // when
        Arrays.setAll(data, (a) -> a * 10);

        // then
        assertThat(data).containsExactly(0, 10, 20, 30, 40);
    }

    @DisplayName("binarySearch 는 탐색가 존재할 경우 인덱스를 반환하고, 없을 경우 -1을 반환한다")
    @Test
    void binarySearchTest() {
        // given
        char[] chars = {'x', 'a', 'z'};
        int[] ints = {6, 7, 4, 1, 5};
        float[] floats = {10.2f, 15.1f, 2.2f, 3.5f};
        double[] doubles = {10.0, 20.0, 15.0, 22.0 ,35.0};

        // 정렬 필수
        Arrays.sort(chars);
        Arrays.sort(ints);
        Arrays.sort(floats);
        Arrays.sort(doubles);

        // when
        // then
        assertThat(Arrays.binarySearch(chars, 'a')).isEqualTo(0);
        assertThat(Arrays.binarySearch(ints, 7)).isEqualTo(4);
        assertThat(Arrays.binarySearch(floats, 2.2f)).isEqualTo(0);
        assertThat(Arrays.binarySearch(doubles, 15.0)).isEqualTo(1);
    }

    @DisplayName("Arrays.asList 가변 인자로 전달받은 값으로 ArrayList 를 생성하여 반환한다")
    @Test
    void asList() {
        // given
        List<Integer> list = Arrays.asList(4, 5, 7, 3); // java.util.Arrays.ArrayList, immutable

        // when
        // then
        assertThat(list).hasSize(4); // true
        assertThatThrownBy(() -> list.remove(0)).isInstanceOf(UnsupportedOperationException.class);
    }
}
