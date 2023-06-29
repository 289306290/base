package club.wujingjian.base;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Streams;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
public class JUnitTest {

    @BeforeAll
    @DisplayName("@BeforeAll所有方法执行之前,只运行一次")
    public static void beforeAll(){
      log.info("所有方法执行前执行一次");
    }

    @DisplayName("@BeforeEach注解每个方法执行前执行一次")
    @BeforeEach
    public void beforeEach(){
        log.info("每个方法执行前执行一次");
    }


    @ParameterizedTest
    @ValueSource(strings = {"aaa","bbb","ccc"})
    public void parameterTest(String str) {
        log.info("循环遍历参数列表,每个参数执行一遍这个方法"+ str);
    }

    @DisplayName("方法参数来源于别的方法")
    @ParameterizedTest
    @MethodSource("abc")
    public void parameterByOtherMethodTest(String str) {
        log.info("循环遍历参数列表,每个参数执行一遍这个方法"+ str);
    }

    public static Stream<String> abc() {
        return Stream.of("abc", "def", "lmn");
    }

    @Test
    @DisplayName("断言")
    public void assertEquals() {
        Assertions.assertEquals(3, 2 + 1);
    }

    @Test
    @DisplayName("RepeatedTest重复测试")
    @RepeatedTest(3)
    public void RepeatedTest() {
        Assertions.assertEquals(3, 2 + 1);
    }
}
