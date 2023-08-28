package jupiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class NestedOrderTest {

    @Nested
    @DisplayName("테스트 method 이름 순으로 정렬한다")
    @TestMethodOrder(MethodOrderer.MethodName.class)
    class OrderByMethodName {

        @DisplayName("testC")
        @Test
        void testC() {
            // ..
        }

        @DisplayName("testB")
        @Test
        void testB() {
            // ..
        }

        @DisplayName("testA")
        @Test
        void testA() {
            // ..
        }
    }

    @Nested
    @DisplayName("순서가 랜덤으로 테스트 메서드 실행된다")
    @TestMethodOrder(MethodOrderer.Random.class)
    class OrderByRandomName {

        @DisplayName("testC")
        @Test
        void testC() {
            // ..
        }

        @DisplayName("testB")
        @Test
        void testB() {
            // ..
        }

        @DisplayName("testA")
        @Test
        void testA() {
            // ..
        }
    }

    @Nested
    @DisplayName("@Order 메서드로 실행 순서를 결정한다")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class OrderTestMethod {

        @DisplayName("testC")
        @Test
        @Order(2)
        void testC() {
            // ..
        }

        @DisplayName("testB")
        @Test
        @Order(3)
        void testB() {
            // ..
        }

        @DisplayName("testA")
        @Test
        @Order(1)
        void testA() {
            // ..
        }
    }

    @Nested
    @DisplayName("DisplayName 기준으로 정렬한다")
    @TestMethodOrder(MethodOrderer.DisplayName.class)
    class OrderByDisplayName {

        @DisplayName("testC")
        @Test
        void testC() {
            // ..
        }

        @DisplayName("testB")
        @Test
        void testB() {
            // ..
        }

        @DisplayName("testA")
        @Test
        void testA() {
            // ..
        }
    }

}
