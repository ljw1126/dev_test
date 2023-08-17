package orgs.junit.jupiter.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class BasicAnnotationTest {

    @BeforeAll
    static void initAll() {
        System.out.println("@BeforeAll : initAll()");
    }

    @BeforeEach
    void init() {
        System.out.println("@BeforeEach : init()");
    }

    @Test
    void succeedingTest() {
        System.out.println("succeedingTest");
    }

    @Test
    void failingTest() {
        System.out.println("failingTest");
        fail("a filing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not expected
    }

    @Test
    void abortedTest() {
        System.out.println("abortedTest");
        Assumptions.assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach : tearDown()");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("@AfterAll : tearDownAll()");
    }
}
