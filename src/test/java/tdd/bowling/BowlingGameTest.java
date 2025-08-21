package tdd.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 참고. 토비의 스프링 : https://www.youtube.com/watch?v=8JLILHs0_yk
public class BowlingGameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @DisplayName("쓰러뜨린 핀이 0인 경우 점수도 0이다")
    @Test
    void gutterGame() {
        rollMany(20, 0);

        assertThat(game.score()).isEqualTo(0);
    }

    @DisplayName("핀 한개만 모두 쓰러뜨리는 경우 점수는 20이디ㅏ")
    @Test
    void allOnes() {
        rollMany(20, 1);

        assertThat(game.score()).isEqualTo(20);
    }

    @DisplayName("spare의 경우 다음 프레임의 첫번째 roll을 보너스로 합산한다")
    @Test
    void spare() {
        rollSpare();
        game.roll(3);
        rollMany(17, 0);

        assertThat(game.score()).isEqualTo(16);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    @DisplayName("스트라이크인 경우 다음 프레임의 점수 두개를 보너스 합산한다")
    @Test
    void ontStrike() {
        game.roll(10);
        game.roll(3);
        game.roll(4);
        rollMany(17, 0);

        assertThat(game.score()).isEqualTo(17 + 7);
    }

    @Test
    void perfectGame() {
        rollMany(12, 10);

        assertThat(game.score()).isEqualTo(300);
    }

    private void rollMany(int turns, int pins) {
        for (int i = 1; i <= turns; i++) {
            game.roll(pins);
        }
    }

}
