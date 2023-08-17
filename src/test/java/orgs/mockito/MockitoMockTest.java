package orgs.mockito;

import dev.test.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class MockitoMockTest {

    @DisplayName("Mockito.mock() method를 사용하여 mock 객체를 생성할 수 있다")
    @Test
    void mockitoMock() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        BDDMockito.given(userRepository.count()).willReturn(123L);

        long userCount = userRepository.count();

        assertThat(userCount).isEqualTo(123L);
    }
}
