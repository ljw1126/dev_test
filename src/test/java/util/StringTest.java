package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {

    @DisplayName("substring 사용하면 from ~ (to - 1) 위치 까지 잘라서 새로운 String 문자열 반환한다")
    @Test
    void substringTest() {
        // given
        String text = "schoolOfRock";

        // when
        // then
        assertThat(text.substring(0)).isEqualTo(text);
        assertThat(text.substring(0, 6)).isEqualTo("school");
    }

    @DisplayName("length 를 사용하면 문자열의 길이를 반환한다")
    @Test
    void lengthTest() {
        // given
        String test = "hello world";

        // when
        int result = test.length();

        // then
        assertThat(result).isEqualTo(11);
    }


    @DisplayName("split 함수를 사용하면 정한 인자 값에 따라 문자열이 나눠져 String[] 로 리턴된다")
    @Test
    void splitTest() {
        // given
        String test = "hello world";

        // when
        String[] result = test.split(" ");

        // then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly("hello", "world");
        assertThat(Arrays.toString(result)).isEqualTo("[hello, world]");
    }


    @DisplayName("equals 는 String (Object)가 같은지 비교하여 boolean 형 결과를 리턴 한다")
    @Test
    void equalsTest() {
        // given
        String text = "testorg";

        // when
        String compare = "text";

        // then
        assertThat(text).isEqualTo("testorg");
        assertThat(text.equals(compare)).isFalse();
    }

    @DisplayName("charAt 원하는 인덱스 번호를 넣으면 문자열에서 해당 위치의 char 반환한다")
    @Test
    void charAtTest() {
        // given
        String text = "school";

        // when
        // then
        assertThat(text.charAt(0)).isEqualTo('s');
        assertThat(text.charAt(5)).isEqualTo('l');
    }

    @DisplayName("toCharArray 는 String 문자열을 char[] 로 변환하여 준다")
    @Test
    void toCharArrayTest() {
        // given
        String text = "testorg";

        // when
        char[] result = text.toCharArray();

        // then
        assertThat(result).hasSize(4);
        assertThat(result).containsExactly('t', 'e', 's', 't');
    }

    @DisplayName("toLowerCase  를 사용하면 문자열 전체가 소문자로 변환된다")
    @Test
    void toLowerCaseTest() {
        // given
        String text = "TEST";

        // when
        String result = "testorg";

        // then
        assertThat(text.toLowerCase()).isEqualTo(result);
    }

    @DisplayName("toUpperCase  를 사용하면 문자열 전체가 대문자로 변환된다")
    @Test
    void toUpperCaseTest() {
        // given
        String text = "testorg";

        // when
        String result = "TEST";

        // then
        assertThat(text.toUpperCase()).isEqualTo(result);
    }

    @DisplayName("trim 을 사용하면 문자열의 양쪽 끝 공백을 제거한다")
    @Test
    void trimTest() {
        // given
        String text = " Hello World ";

        // when
        String result = "Hello World";

        // then
        assertThat(text.trim()).isEqualTo(result);
    }

    @DisplayName("valueOf 사용하면 primitive type 인자를 전달받아 String 결과값을 리턴한다")
    @Test
    void valueOfTest() {
        // given
        int value = 1004;

        // when
        String result = "1004";

        // then
        assertThat(String.valueOf(value)).isEqualTo(result);
    }

    @DisplayName("contains 사용하면 인자값 포함 여부를 결과으로 boolean 형으로 반환한다")
    @Test
    void containsTest() {
        // given
        String text = "Let it be";

        // when
        // then
        assertThat(text.contains("a")).isFalse(); // 내부적으로 indexOf 호출하여 >= 0 이상이면 true 아니면 false
        assertThat(text.contains("L")).isTrue();
        assertThat(text.contains("b")).isTrue();
    }

    @DisplayName("compareTo 통해 문자열의 순서 비교를 할 수 있다 (이때 대소문자 구분함)")
    @Test
    void compareToTest() {
        // given , A - Z : 65 ~ 90 , a ~ z : 97 ~ 122
        String alphabet1 = "A";
        String alphabet2 = "b";
        String alphabet3 = "C";

        // when
        // then
        assertThat(alphabet1.compareTo(alphabet2)).isNegative(); // A < b , 음수값
        assertThat(alphabet3.compareTo(alphabet1)).isPositive(); // C > A , 양수값
    }

    @DisplayName("startWith 는 문자열의 시작이 해당 인자값인지 확인하여 boolean 결과를 리턴한다 (대소문자 구분)")
    @Test
    void startWithTest() {
        // given
        String text = "Beautiful";

        // when
        // then
        assertThat(text.startsWith("Be")).isTrue();
        assertThat(text.startsWith("b")).isFalse();
    }

    @DisplayName("endWith 는 문자열 끝이 해당 인자값이지 확인하여 boolean 결과를 리턴한다 (대소문자 구분)")
    @Test
    void endWithTest() {
        // given
        String text = "testorg";

        // when
        // then
        assertThat(text.endsWith("t")).isTrue();
        assertThat(text.endsWith("T")).isFalse();
    }

    @DisplayName("indexOf 는 문자열에서 찾으려는 문자가 있는 첫번째 위치를 반환하다 (없는 경우 -1) ")
    @Test
    void indexOfTest() {
        // given
        String text = "hello world";

        // when
        // then
        assertThat(text.indexOf("o")).isEqualTo(4);
        assertThat(text.indexOf("l")).isEqualTo(2);
        assertThat(text.indexOf("a")).isEqualTo(-1);
    }

    @DisplayName("lastIndexOf 는 문자열에서 찾으려는 문자가 여러개 존재할 때 마지막 위치를 반환한다(없으면 -1)")
    @Test
    void lastIndexOfTest() {
        // given
        String text = "hello world";

        // when
        // then
        assertThat(text.lastIndexOf("l")).isEqualTo(9);
        assertThat(text.lastIndexOf("o")).isEqualTo(7);
        assertThat(text.lastIndexOf("a")).isEqualTo(-1);
    }

    @DisplayName("replace 는 target 문자을 replcement 문자로 치환한다")
    @Test
    void replaceTest() {
        // given
        String text = "Hello";

        // when
        // then
        assertThat(text.replace("l", "L")).isEqualTo("HeLLo");
    }

    @DisplayName("replaceAll 은 정규표현식을 사용하여 문자를 치환한다")
    @Test
    void replaceAllTest() {
        // given
        String text = "홍길동 님이 입장하셨습니다";

        // when
        String result = "홍길동님이입장하셨습니다";

        // then
        assertThat(text.replaceAll("\\s", "")).isEqualTo(result); // white space 제거
    }

    @DisplayName("replaceFirst 는 정규표현식을 사용하여 찾은 첫번째 해당 문자만 치환한다")
    @Test
    void replaceFirstTest() {
        // given
        String text = "how+to+do+this";

        // when
        String result = "howto+do+this";

        // then
        assertThat(text.replaceFirst("\\+", "")).isEqualTo(result);
    }

    @DisplayName("matched 는 정규식을 사용하여 올바른 문자열인지 판단하여 boolean 형 결과 리턴한다")
    @Test
    void matchedTest() {
        // given
        String text = "fileName09.txt";

        // when
        // then
        assertThat(text.matches("^([a-zA-Z0-9])+\\.(txt)$")).isTrue();
    }

    @DisplayName("")
    @Test
    void format() {
        // given
        int a = 1;
        int b = 2;

        // when
        String result = "1 + 2 = 3";

        // then
        assertThat(String.format("%s + %s = %s", a, b, a + b)).isEqualTo(result);
    }

    @DisplayName("")
    @Test
    void concat() {
        // given
        String a = "Summer";
        String b = "Sonic";

        // when
        String result = "SummerSonic";

        // then
        assertThat(a.concat(b)).isEqualTo(result);
    }
}
