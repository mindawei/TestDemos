package mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 文档参考地址：https://static.javadoc.io/org.mockito/mockito-core/2.13.0/org/mockito/Mockito.html
 * 版本：2.13.0
 */
public class MockitoDemo {

    @Test
    @DisplayName("Mockito 使用例子")
    public void testUseDemo() {

        // 可以创建类或者接口的实现
        LinkedList mockedList = mock(LinkedList.class);

        // >> stubbing，打桩，用自己的实现进行替换
        when(mockedList.get(0)).thenReturn("first"); // 可以返回一个值
        when(mockedList.get(1)).thenThrow(new RuntimeException()); // 可以抛出一个异常
        when(mockedList.get(eq(1000))). // 参数匹配： anyInt()、 anyString()、 eq("??") 等
                thenReturn("element");
        when(mockedList.add(argThat(s -> ((String) s).length() > 5)))  //  自定义匹配函数，可使用Java 8 Lambdas
                .thenReturn(true);
        doThrow(new RuntimeException()).when(mockedList).clear(); // void 方法抛出异常

        // 打桩结果出测试
        assertEquals("first", mockedList.get(0));
        assertEquals(null, mockedList.get(999)); // 因为 get(999) 没有打桩
        assertEquals("element", mockedList.get(1000));
        assertEquals(false, mockedList.add("12345"));
        assertEquals(true, mockedList.add("123456"));
        verify(mockedList).add(argThat(s -> ((String) s).length() > 5)); // 验证是否执行过


        // >> 验证次数
        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        // 验证调用次数
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");
        verify(mockedList, never()).add("never happened");    // 0次
        verify(mockedList, atLeastOnce()).add("three times"); // 至少1次
        verify(mockedList, atLeast(2)).add("three times");// 至少2次
        verify(mockedList, atMost(5)).add("three times"); // 至多5次

        /**
         06 验证添加顺序 InOrder
         07 确认方法没有被调用过 verifyZeroInteractions
         08 找到重复调用 verifyNoMoreInteractions
         09 简化 mock 创建 @Mock ，但是需要在测试的地方添加 MockitoAnnotations.initMocks(testClass);
         10 连续调用打桩  thenReturn、thenThrow 连接，或者thenReturn 中多个参数
         11 回调函数打桩 thenAnswer
         12 类似的几个void作用函数 doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod()
         13 对真实对象进行监控（代理并植入自己的代码） spy
         14 修改没有打桩的函数的默认返回值 mock(xx.class, new YourOwnAnswer()); （Since 1.7）
         15 捕捉参数，为进一步的断言提供支持 ArgumentCaptor （Since 1.8）
         16 真正的部分打桩 spy() （Since 1.8）
         17 重置打桩 reset() （Since 1.8.0）
         18 故障诊断和验证框架使用 (Since 1.8.0)
         19 行为驱动开发的别名  //given //when //then  (Since 1.8.0)
         20 序列化打桩 (Since 1.8.1)
         21 一些新的注解: @Captor, @Spy, @InjectMocks （Since 1.8.3）
         22 提供超时机制的verify (Since 1.8.5)
         ...
         更多请查看：https://static.javadoc.io/org.mockito/mockito-core/2.13.0/org/mockito/Mockito.html#defaultreturn
         */

    }

}
