package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OptionalTest {

    Logger LOG = LoggerFactory.getLogger(OptionalTest.class);

    @DisplayName("null 타입을 비교시 인자 위치에 따라 NPE 가 발생한다")
    @Test
    void npeCheck() {
        // given
        String person1 = null;
        String person2 = "tony";

        // when
        // then
        assertThatThrownBy(() -> person1.equals(person2)).isInstanceOf(NullPointerException.class);
        assertThat(person2.equals(person1)).isFalse();
    }

    @DisplayName("of 에 객체 값을 인자로 전달하면 Optional 객체가 생성된다")
    @Test
    void of() {
        // given
        String given = "testorg";
        Optional<String> boxed = Optional.of(given);

        // when
        // then
        assertThat(boxed.isEmpty()).isFalse();
        assertThat(boxed.isPresent()).isTrue();
        assertThat(boxed.get()).isEqualTo(given);
        assertThat(boxed).isInstanceOf(Optional.class);
        assertThat(boxed).isNotEqualTo(Optional.empty());
    }

    @DisplayName("of에 null 값을 넣으면 NPE가 발생한다")
    @Test
    void ofThrowsNPE() {
        // given
        // when
        // then
        assertThatThrownBy(() -> Optional.of(null)).isInstanceOf(NullPointerException.class);
    }

    @DisplayName("Optional 객체에 value 값이 null 인지 아닌지 boolean 형 결과값을 리턴한다")
    @Test
    void isPresent() {
        Optional<String> boxed = Optional.of("testorg");
        Optional<String> empty = Optional.empty();

        assertThat(boxed.isPresent()).isTrue();
        assertThat(empty.isPresent()).isFalse();
    }

    @DisplayName("Optional 값이 있다면 값을 가져오고, null 인 경우 NoSuchElementException 발생한다")
    @Test
    void get() {
        // given
        Optional<String> boxed = Optional.of("testorg");
        Optional<String> empty = Optional.empty();

        // when
        // then
        assertThat(boxed.get()).isEqualTo("testorg");
        assertThatThrownBy(empty::get).isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("")
    @Test
    void orElse() {
        // given
        Optional<String> value = Optional.of("abc");
        Optional<String> empty = Optional.empty();

        // when
        String str1 = value.orElse("empty");
        String str2 = empty.orElse("empty");

        // then
        assertThat(str1).isEqualTo("abc");
        assertThat(str2).isEqualTo("empty");
    }

    @DisplayName("")
    @Test
    void orElseGet() {
        // given
        String defaultText = "default";
        Optional<String> text = Optional.of("testorg");
        Optional<String> empty = Optional.empty();

        // when
        // then
        assertThat(text.orElseGet(String::new)).isEqualTo("testorg");
        assertThat(empty.orElseGet(() -> defaultText)).isEqualTo(defaultText);
    }

    @DisplayName("Optional 값이 있는 경우 값을, null 인 경우 전달받은 예외를 발생시킨다")
    @Test
    void orElseThrow() {
        // given
        Optional<String> given = Optional.of("testorg");
        Optional<String> empty = Optional.empty();

        // when
        // then
        assertThat(given.orElseThrow(IllegalArgumentException::new)).isEqualTo("testorg");
        assertThatThrownBy(() -> empty.orElseThrow(() -> new RuntimeException("no data")))
                .isInstanceOf(RuntimeException.class).hasMessage("no data");
    }

    public String getTextName() {
        final String text = new String("text");
        LOG.info("getTextName -- start");
        return text;
    }

    @DisplayName("")
    @Test
    void compareOrElseAndOrElseGet() {
        Optional<String> empty = Optional.empty();
        String name1 = empty.orElse(getTextName());
        String name2 = Optional.of("baeldung").orElse(getTextName());
        String name3 = Optional.of("baeldung").orElseGet(this::getTextName);

        assertThat(name1).isEqualTo("text");
        assertThat(name2).isEqualTo("baeldung"); // orElse의 경우 함수가 먼저 실행되고, 값이 null 이 아니면 값을 리턴하게 됨
        assertThat(name3).isEqualTo("baeldung"); // orElseGet의 경우 지연 로딩이 되서 값이 null인 경우에만 람다식/함수 실행
    }

    @DisplayName("")
    @Test
    void optionalPrimitiveType() {
        // given
        OptionalInt intValue = OptionalInt.empty();
        OptionalLong longValue = OptionalLong.empty();
        OptionalDouble doubleValue = OptionalDouble.empty();

        // when
        // then
        assertThat(intValue.isPresent()).isFalse();
        assertThat(longValue.isPresent()).isFalse();
        assertThat(doubleValue.isPresent()).isFalse();
    }

    @DisplayName("값이 존재하면, 인자 함수를 실행하고, 없으면 아무일도 일어나지 않는다")
    @Test
    void ifPresent() {
        // given
        Optional<String> given = Optional.ofNullable("홍길동");
        Optional<String> empty = Optional.empty();

        given.ifPresent(System.out::println); // 홍길동
        empty.ifPresent(System.out::println);
    }

    @DisplayName("")
    @Test
    void map() {
        // given
        List<String> companyNames = Arrays.asList("oracle", "microsoft", "apple", "samsung");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        String name = "baeldung";
        Optional<String> nameOptional = Optional.of(name);

        // when
        int result1 = listOptional.map(List::size).orElse(0);
        int result2 = nameOptional.map(String::length).orElse(0);

        // then
        assertThat(result1).isEqualTo(4);
        assertThat(result2).isEqualTo(8);
    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Optional<String> getName() {
            return Optional.of(name);
        }

        public OptionalInt getAge() {
            return OptionalInt.of(age);
        }
    }

    @DisplayName("")
    @Test
    void flatMap() {
        // given
        Person person = new Person("고길동", 30);
        Optional<Person> given = Optional.of(person);

        // when
        String name = given.flatMap(Person::getName).orElse("이름없음");

        // then
        assertThat(name).isEqualTo("고길동");
    }

}
