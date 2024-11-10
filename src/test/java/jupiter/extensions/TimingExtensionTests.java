package jupiter.extensions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TimingExtension.class)
class TimingExtensionTests {

    @Test
    void sleep20ms() throws InterruptedException {
        Thread.sleep(20);
    }

    @Test
    void sleep50ms() throws InterruptedException {
        Thread.sleep(50);
    }
}
