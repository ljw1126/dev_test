package nextstep.ref;

import nextstep.ref.ex2.LongArrayUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

public class InvokePrivateMethod {
    @DisplayName("")
    @Test
    void invokePrivateMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long[] arr = new long[] {1L, 2L, 3L, 4L, 5L};

        Method indexOf = LongArrayUtil.class.getDeclaredMethod("indexOf", long[].class, long.class, int.class, int.class);
        indexOf.setAccessible(true);

        assertThat(indexOf.invoke(LongArrayUtil.class, arr, 5L, 0, arr.length))
                .isEqualTo(4);
    }

    @DisplayName("")
    @Test
    void noSuchMethodException() {
        assertThatThrownBy(() -> LongArrayUtil.class.getDeclaredMethod("indexOf", long[].class))
                .isInstanceOf(NoSuchMethodException.class);
    }

    @DisplayName("")
    @Test
    void reflectionTestUtils() {
        long[] arr = new long[] {1L, 2L, 3L, 4L, 5L};
        int value = ReflectionTestUtils.invokeMethod(LongArrayUtil.class, "indexOf", arr, 1L, 0, arr.length);
        assertThat(value).isZero();
    }
}
