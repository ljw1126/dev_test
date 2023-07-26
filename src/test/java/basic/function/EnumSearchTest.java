package basic.function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

enum Direction {
    EAST, WEST, SOUTH, NORTH
}
public class EnumSearchTest {

    static Direction[] values() {
        return Direction.values();
    }

    static Direction findByName(String name) {
        Direction result = null;
        for(Direction dir : values()) {
            if(dir.name().equalsIgnoreCase(name)) { // equalsIgnoreCase (String class)
                result = dir;
                break;
            }
        }

        return result;
    }

    @DisplayName("정확한 Direction 명이 주어졌을 때, findByName 통해 Direction enum 을 찾을 수 있다")
    @Test
    void whenValidDirectionNameProvided_thenDirectionIsFound() {
        // given
        String givenName = "EAST";

        // when
        Direction result = findByName(givenName);

        // then
        Assertions.assertThat(result).isEqualTo(Direction.EAST);
    }

    @DisplayName("케이스 구분없이 Direction 명이 주어졌을 때, findByName 통해 Direction enum 을 찾을 수 있다")
    @Test
    void whenInValidDirectionNameProvided_thenDirectionIsFound() {
        // given
        String givenName = "east";
        String givenName2 = "East";

        // when
        Direction result = findByName(givenName);
        Direction result2 = findByName(givenName2);

        // then
        Assertions.assertThat(result).isEqualTo(Direction.EAST);
        Assertions.assertThat(result2).isEqualTo(Direction.EAST);
    }

}
