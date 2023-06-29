package club.wujingjian.base.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.CountDownLatch;

import static java.time.Duration.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class TestSimple {

    @Test
    void addition(){
        System.out.println("进入测试方法");
        assertEquals(2, 1 + 1);
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("c"));
        fail("test should have been aborted");
    }

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }

    @ParameterizedTest(name = "Year {0} is a leap year.")
    @ValueSource(ints = { 2016, 2020, 2048 })
    void if_it_is_one_of_the_following_years(int year) {
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        assertTimeout(ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }
    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = assertTimeout(ofSeconds(2), TestSimple::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    private static String greeting() throws InterruptedException {
        Thread.sleep(1000 * 1);
        return "Hello, World!";
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

    @Test
    void assertWithHamcrestMatcher() {
        assertThat(3, is(equalTo(3 +1 -1)));
    }


    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    System.out.println("这里只有满足条件才进来");
                    assertEquals(2, 4/1);
                });

        // perform these assertions in all environments
        assertEquals(42, 6*7);
    }

}
