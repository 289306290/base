package club.wujingjian.base.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DisplayNameGeneratorDemo {


    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_not_supported {

        @Test
        void if_it_is_zero() {
        }

        @DisplayName("A negative value for year is not supported by the leap year computation.")
        @ParameterizedTest(name = "For example, year {0} is not supported.")
        @ValueSource(ints = { -1, -4 })
        void if_it_is_negative(int year) {
        }

    }

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_a_leap_year {

        @Test
        @Disabled
        void if_it_is_divisible_by_4_but_not_by_100() {
            System.out.println("这个方法禁用了");
        }

        @ParameterizedTest(name = "Year {0} is a leap year.")
        @ValueSource(ints = { 2016, 2020, 2048 })
        void if_it_is_one_of_the_following_years(int year) {
        }

    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV2", matches = "staging-serverfff")
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
//    @EnabledIf("customCondition")
    void onlyOnStagingServer() {
        // ...
        System.out.println("发大水发");
        System.out.println(System.getenv("ENV2"));
        System.out.println(System.getProperty("os.arch"));
    }

    boolean customCondition(){
        return false;
    }
}
