package jupiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(5)
public class TimeoutTest {

    @DisplayName("")
    @Test
    //@Timeout(1)
    void timeout() throws InterruptedException {
        Thread.sleep(10000);
    }

    @DisplayName("unit 으로 시간 단위를 지정할 수 있다")
    @Test
    //@Timeout(value = 1, unit = TimeUnit.MINUTES)
    void timoutByMinutes() throws InterruptedException {
        Thread.sleep(2 * 60 * 1000);
    }
}
