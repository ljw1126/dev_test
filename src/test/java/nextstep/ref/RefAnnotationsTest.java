package nextstep.ref;

import nextstep.ref.ex7.ClassWithAnnotations;
import nextstep.ref.ex7.FirstAnnotation;
import nextstep.ref.ex7.SecondAnnotation;
import nextstep.ref.ex7.ThirdAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class RefAnnotationsTest {
    @DisplayName("Retention.RUNTIME 애노테이션은 2개이다")
    @Test
    void whenCallingGetDeclaredAnnotations_thenOnlyRuntimeAnnotationsAreAvailable() throws NoSuchFieldException {
        Class<ClassWithAnnotations> clazz = ClassWithAnnotations.class;
        Field classMemberField = clazz.getDeclaredField("classMember");
        Annotation[] annotations = classMemberField.getAnnotations();

        assertThat(annotations).hasSize(2);
    }

    @DisplayName("")
    @Test
    void whenCallingIsAnnotationPresent_thenOnlyRuntimeAnnotationsAreAvailable() throws NoSuchFieldException {
        Class<ClassWithAnnotations> clazz = ClassWithAnnotations.class;
        Field classMemberField = clazz.getDeclaredField("classMember");

        assertThat(classMemberField.isAnnotationPresent(FirstAnnotation.class)).isTrue();
        assertThat(classMemberField.isAnnotationPresent(SecondAnnotation.class)).isTrue();
        assertThat(classMemberField.isAnnotationPresent(ThirdAnnotation.class)).isFalse(); // Retention.SOURCE
    }
}
