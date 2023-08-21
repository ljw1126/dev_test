package mockito;

import dev.test.MainApplication;
import dev.test.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

// 참고 https://www.baeldung.com/java-spring-mockito-mock-mockbean
@SpringBootTest(classes = MainApplication.class)
public class MockBeanTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    ApplicationContext context;

    @DisplayName("")
    @Test
    void mockUserRepositoryTest() {
        given(userRepository.count()).willReturn(123L);

        UserRepository mockUserRepository = context.getBean(UserRepository.class);
        long userCount = mockUserRepository.count();

        assertThat(userCount).isEqualTo(123L);
        Mockito.verify(mockUserRepository).count();
    }

    @DisplayName("")
    @Test
    void test() {
        given(userRepository.count()).willReturn(123L);

        long userCount = userRepository.count();

        assertThat(userCount).isEqualTo(123L);
    }

}
