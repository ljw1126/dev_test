package mockito;

import dev.test.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MockAnnotationTest {

    @Mock
    UserRepository userRepository;


    @DisplayName("Mocktio.mock() 대신 @Mock Annotation 사용하여 Mock 객체 생성 할 수 있다")
    @Test
    void test() {
        BDDMockito.given(userRepository.count()).willReturn(123L);

        long userCount = userRepository.count();

        assertThat(userCount).isEqualTo(123L);
        Mockito.verify(userRepository).count();
    }
}
