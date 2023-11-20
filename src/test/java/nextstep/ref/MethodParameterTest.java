package nextstep.ref;

import nextstep.ref.ex6.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MethodParameterTest {
    @DisplayName("")
    @Test
    void whenGetConstructParams_thenOk() throws NoSuchMethodException {
        Class<? extends Person> clazz = Person.class;
        List<Parameter> parameters = Arrays.asList(clazz.getConstructor(String.class).getParameters());
        Optional<Parameter> parameter = parameters.stream().filter(Parameter::isNamePresent).findFirst();

        assertThat(parameter.get().getName()).isEqualTo("fullName");
    }

    @DisplayName("")
    @Test
    void whenGetMethodParams_thenOk() throws NoSuchMethodException {
        Class<Person> clazz = Person.class;
        List<Parameter> setFullNameParams = Arrays.asList(clazz.getMethod("setFullName", String.class).getParameters());
        Optional<Parameter> parameter = setFullNameParams.stream().filter(Parameter::isNamePresent).findFirst();

        assertThat(parameter.get().getName()).isEqualTo("fullName");
    }
}
