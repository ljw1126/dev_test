package orgs.junit.jupiter.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;

import static java.time.Duration.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    private final Person person = new Person("Jane", "Doe");

    @DisplayName("expected와 actual 값이 같은지 확인한다")
    @Test
    void standardAssertions() {
        double result = Calculator.PLUS.apply(1, 2);

        assertEquals(3, result);
    }

    @DisplayName("값이 다른 지 확인, 같을 경우 에러")
    @Test
    void assertNotEquals() {
        int value = 5;
        Assertions.assertNotEquals(0, value, "the result cannot be 0");
    }

    @DisplayName("")
    @Test
    void groupedAssertions() {
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    @DisplayName("코드 블록안에 assertion fails 발생하면 같은 블록에 있는 subsequent code는 skip된다")
    @Test
    void dependentAssertions() {
        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    Assertions.assertNotNull(firstName);

                    assertAll("first name",
                            () -> Assertions.assertTrue(firstName.startsWith("J")),
                            () -> Assertions.assertTrue(firstName.endsWith("e"))
                    );
                },
                () -> {
                    String lastName = person.getLastName();
                    Assertions.assertNotNull(lastName);

                    assertAll("last name",
                            () -> Assertions.assertTrue(lastName.startsWith("D")),
                            () -> Assertions.assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    @DisplayName("예외 확인")
    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class,
                () -> Calculator.DIVIDE.apply(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        assertTimeout(ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMillis(10), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsTest::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }

    @DisplayName("assertTimeout은 시간 넘어가도 메서드 종료 후 결과 반환하고, assertTimeoutPreemptively는 시간 초과시 바로 종료하여 결과 리턴 한다")
    @Test
    void timeoutExceedTest() {
        assertAll(
                () -> assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(100)),
                () -> assertTimeoutPreemptively(Duration.ofMillis(10), () -> Thread.sleep(100))
        );
    }

    private static String greeting() {
        return "Hello, World!";
    }

    @DisplayName("배열의 값이 같은지 확인한다")
    @Test
    void assertArrayEquals() {
        char[] expected = {'J', 'u', 'p', 'i', 't', 'e', 'r'};
        char[] actual = "Jupiter".toCharArray();

        Assertions.assertArrayEquals(expected, actual, "Arrays should be equal");
    }

    @DisplayName("조건이 참이면 통과, 거짓이면 에러 메시지 출력")
    @Test
    void assertTrue() {
        Assertions.assertTrue(5 > 4, "5 is greater than 4");
        Assertions.assertTrue(null == null, "null is equal to null");

        BooleanSupplier condition = () -> 10 > 1;
        Assertions.assertTrue(condition);
    }

    @DisplayName("조건이 거짓이면 통과, 참이면 에러 메시지 출력")
    @Test
    void assertFalse() {
        BooleanSupplier condition = () -> 5 > 6;
        Assertions.assertFalse(condition, "5 is not greater than 6");
    }

    @DisplayName("")
    @Test
    void assertNotNull() {
        Object dog = new Object();

        Assertions.assertNotNull(dog, () -> "The dog should not be null");
    }

    @DisplayName("")
    @Test
    void assertNull() {
        Object cat = null;

        Assertions.assertNull(cat, () -> "The cat should be null");
    }

    @DisplayName("객체 참조 주소가 동일(==)한지 확인한다")
    @Test
    void assertSame() {
        String language = "Java";
        Optional<String> optional = Optional.of(language);

        Assertions.assertSame(language, optional.get());
    }

    @DisplayName("타입이 달라도 동일한 순서로 내용만 일치하는지 확인")
    @Test
    void assertIterableEquals() {
        Iterable<String> al = new ArrayList<>(Arrays.asList("Java", "Junit", "Test"));
        Iterable<String> ll = new LinkedList<>(Arrays.asList("Java", "Junit", "Test"));

        Assertions.assertIterableEquals(al, ll);
    }

    @DisplayName("같은 List 인스턴스인지 비교하고, 아닌 경우 인덱스별 값을 String.matcher() 로 검사한다")
    @Test
    void assertLinesMatch() {
        List<String> expected = Arrays.asList("Java", "\\d+", "JUnit");
        List<String> actual = Arrays.asList("Java", "11", "JUnit");

        Assertions.assertLinesMatch(expected, actual);
    }

    @DisplayName("fail은 개발이 완료되지 않은 테스트를 표시하는데 유용할 수 있다")
    @Test
    void fail() {
        // Test not completed
        Assertions.fail("FAIL - test not completed");
    }
}


