package io.github.mat3e.hello;

import io.github.mat3e.hello.HelloService;
import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {
    private static final String WELCOME = "Hello";
    private static final String FALLBACK_ID_WELCOME = "Hola";
    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallback() throws Exception {
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SystemUnderTest = new HelloService(mockRepository);

        // when
        var result = SystemUnderTest.prepareGreeting(null, "-1");

        // then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SystemUnderTest = new HelloService(mockRepository);
        String name = "test";

        // when
        var result = SystemUnderTest.prepareGreeting(name, "-1");

        // then
        assertEquals(WELCOME + " " +  name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        var mockRepository = fallbackIdLangRepository();

        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, null);

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() {
        var mockRepository = fallbackIdLangRepository();

        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, "abc");

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        var mockRepository = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };

        var SUT = new HelloService(mockRepository);

        var result = SUT.prepareGreeting(null, "-1");

        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackIdLangRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

}
