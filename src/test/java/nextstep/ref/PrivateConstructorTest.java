package nextstep.ref;

import nextstep.ref.ex3.PrivateConstructorClass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.*;

public class PrivateConstructorTest {
    @DisplayName("private 생성자로 인스턴스 생성할 수 있다")
    @Test
    void instanceByPrivateConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<PrivateConstructorClass> declaredConstructor = PrivateConstructorClass.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        PrivateConstructorClass privateConstructorClass = declaredConstructor.newInstance();
        assertThat(privateConstructorClass).isNotNull();
    }
}
