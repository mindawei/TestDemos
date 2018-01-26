package junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.RepetitionInfo;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import org.junit.jupiter.api.Tag;

/**
 * 一个标准的测试
 * 所有的核心注解都位于junit-jupiter-api模块的 org.junit.jupiter.api 包中。
 * 网址： http://sjyuan.cc/junit5/user-guide-cn/#32-%E6%A0%87%E5%87%86%E6%B5%8B%E8%AF%95%E7%B1%BB
 */

/***
 * DisplayName : 测试类和测试方法可以声明自定义的显示名称 –
 * 空格、特殊字符甚至是emojis表情 – 都可以显示在测试运行器和测试报告中。
 */
@DisplayName("一个包含常用测试注解的类")
class Junit5Demo {

    /**
     *  BeforeAll 表示使用了该注解的方法应该在当前类中所有
     *  使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory
     *  注解的方法之前执行；类似于JUnit 4的 @BeforeClass。
     *  这样的方法会被继承（除非它们被隐藏 或覆盖），
     *  并且它必须是 static方法（除非"per-class" 测试实例生命周期 被使用）。
     */
    @BeforeAll
     static void initAll() {
        System.out.println("---测试类开始---");
    }

    /**
     * BeforeEach 表示使用了该注解的方法应该在当前类中每一个
     * 使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之前 执行；
     * 类似于JUnit 4的 @Before。这样的方法会被继承，除非它们被覆盖。
     */
    @BeforeEach
    void init() {
        System.out.println("---测试函数开始---");
    }

//    @Test
//    @DisplayName("╯°□°）╯")
//    void failingTest() {
//       fail("a failing test");
//    }

    /**
     * Test 表示该方法是一个测试方法。
     * 与JUnit 4的@Test注解不同的是，它没有声明任何属性，
     * 因为JUnit Jupiter中的测试扩展是基于它们自己的专用注解来完成的。
     * 这样的方法会被继承，除非它们被覆盖。
     */
    @Test
    /**
     * Disabled 用于禁用一个测试类或测试方法；类似于JUnit 4的@Ignore。该注解不能被继承。
     */
    @Disabled("禁用此测试函数：skippedTest")
    void skippedTest() {
        // not executed
        System.out.println("未禁用此测试函数：skippedTest");
    }

    /**
     * AfterEach 表示使用了该注解的方法应该在当前类中每一个
     * 使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之后执行；
     * 类似于JUnit 4的 @After。这样的方法会被继承，除非它们被覆盖。
     */
    @AfterEach
    void tearDown() {
        System.out.println("---测试函数结束---\n");
    }

    /**
     *  AfterAll 表示使用了该注解的方法应该在当前类中
     * 所有使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之后执行；
     * 类似于JUnit 4的 @AfterClass。这样的方法会被继承（除非它们被隐藏 或覆盖），
     * 并且它必须是 static方法（除非"per-class" 测试实例生命周期 被使用）。
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println("---测试类结束---");
    }

    /// >> 断言部分

    class Person{
        String firstName;
        String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    public static String greeting(){
        return "hello world!";
    }

    /**
     * JUnit Jupiter附带了很多JUnit 4 就已经存在的断言方法，
     * 并增加了一些适合与Java8 Lambda一起使用的断言。
     * 所有的JUnit Jupiter断言都是 org.junit.jupiter.Assertions 类中static方法。
     */
    @Test
    @DisplayName("断言使用例子")
    void assertionsDemo() {

        assertEquals(2, 2);
        assertEquals(4, 4, "The optional assertion message is now the last parameter.");
        assertTrue(2 == 2, () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");

        // 在断言组中，所有的断言都会被执行，其中任何一个断言的失败都会导致整个组的失败。
        Person person = new Person("John", "Doe");
        assertAll("person",
                () -> assertEquals("John", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );

        // 异常抛出信息断言
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());

        // 超时测试1，输出超时时间？
        // The following assertion succeeds.
        assertTimeout(ofMillis(10), () -> {
            // Thread.sleep(100);
        });

        // 超时测试2，不输出超时时间
        assertTimeoutPreemptively(ofMillis(100), () -> {
            // Thread.sleep(100);
        });

        // 超时测试，并对返回的结果进行测试
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);

        // 超时测试，并对指定函数的返回的结果进行测试
        String actualGreeting = assertTimeout(ofMinutes(2), Junit5Demo::greeting);
        assertEquals("hello world!", actualGreeting);

        // 断言块
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("n"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );

        //  第三方断言库
        // JUnit团队推荐使用第三方断言类库，例如：AssertJ、Hamcrest、Truth 等等。
        // 因此，开发人员可以自由使用他们选择的断言类库.
        // 举个例子，匹配器 和流式调用的API组合起来使用可以让断言更加具有描述性和可读性。
        // 然而，JUnit Jupiter的 org.junit.jupiter.Assertions 类
        // 没有提供一个类似于JUnit 4的org.junit.Assert类中 assertThat() 方法，
        // 该方法接受一个Hamcrest Matcher。
        // 所以，我们鼓励开发人员使用由第三方断言库提供的匹配器的内置支持。
        System.out.println("断言使用例子结束");
    }

    /**
     * Junit4 假设和断言的区别
     * 网址：http://mwhgjava.iteye.com/blog/778372
     *
     * 断言：Assert 用于测试用例中，如果断言失败，用例即结束。
     * 假设：Assume 用于在准备环境时判断环境是否符合要求，包括测试套的@BeforeClass，测试类的@BeforeClass,测试类的实例化，测试类的@Before。
     *
     * 如果假设失败，假设所处初始化代码方法立即结束，更深级别的后续工作也被忽略，相关测试用例被忽略，
     * 但与假设同级别的收尾工作还要继续执行。例如：如果在测试类的@BeforeClass中假设失败，
     * 该类的实例化及子级别将被忽略，@AfterClass会继续执行。
     */

    /**
     * JUnit Jupiter附带了JUnit 4中所提供的假设方法的一个子集，
     * 并增加了一些适合与Java 8 lambda一起使用的假设方法。
     * 所有的JUnit Jupiter假设都是 org.junit.jupiter.Assumptions 类中的静态方法。
     */
    @Test
    @DisplayName("假设使用例子")
    void AssumptionsDemo(){

        // 通过以下例子，假设可以用来根据生产环境判断相关测试是否要执行

        assumeTrue("CI".equals("C"+"I"));
        assumeTrue(System.getenv("ENV")==null,
                () -> "Aborting test: not on developer workstation");

        // 在指定环境中判断 2 equals 2
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, 2);
                });

        // 在所有环境中判断 string equals string
        // perform these assertions in all environments
        assertEquals("string", "string");

    }

    /**
     * 测试类和测试方法可以被@Tag注解标记。那些标记可以在后面被用来过滤 测试发现和执行。
     *
     * 标记不能为null或空。
     * trimmed 的标记不能包含空格。
     * trimmed 的标记不能包含IOS字符。
     * trimmed 的标记不能包含以下保留字符。
     *      ,, (, ), &, |, !
     * 上述的”trimmed”指的是两端的空格字符被去除掉。
     */
    @Test
    @Tag("taxes")
    void testingTaxCalculation() {

    }

    static int round = 0;
    @RepeatedTest(3)
    void repeatedTest() {
        System.out.println("重复测试，第 "+(++round)+" 轮测试。");
    }

    @RepeatedTest(5)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "Repeat! 1/1");
    }

    // 更多例子请查阅文档： http://sjyuan.cc/junit5/user-guide-cn/#55-%E5%8F%82%E6%95%B0%E8%A7%A3%E6%9E%90
    // 包括：参数化测试、 测试模板、 动态测试等。

}