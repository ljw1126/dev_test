package basic.function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnumSearchByStringOrIntValueTest {

    static <T> List<T> enumValuesInList(Class<T> classType) {
        T[] o = classType.getEnumConstants();
        return o == null ? Collections.EMPTY_LIST : Arrays.asList(o);
    }

    static Month[] values() {
        return Month.values();
    }

    static Month findByName(String name) {
        return Arrays.stream(values()).filter(month -> month.name().equalsIgnoreCase(name))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    @DisplayName("")
    @Test
    void findByName() {
        // given
        String givenName = "december";

        // when
        Month result = findByName(givenName);

        // then
        Assertions.assertThat(result).isEqualTo(Month.DECEMBER);
    }

    @DisplayName("")
    @Test
    void findByNameException() {
        // given
        String givenName = "holiday";

        // when
        // then
        Assertions.assertThatThrownBy(() -> findByName(givenName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /*
    static Month findByCode(String code) {
        Month month = null;
        for(Month m : values()) {
            if(m.getCode().equalsIgnoreCase(code)) {
                month = m;
                break;
            }
        }
        return month;
    }
     */


    static Month findByCode(String code) {
        return Arrays.stream(values())
                .filter(month -> month.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


    @DisplayName("code 값이 존재하는 경우 Month 객체 반환를 반환한다")
    @Test
    void searchByCode() {
        // given
        String givenCode = "nov";

        // when
        Month result = findByCode(givenCode);

        // then
        assertThat(result).isEqualTo(Month.NOVEMBER);
    }

    @DisplayName("enum Month 에 없는 코드를 조회할 경우 IllegalArgumentException 을 던진다")
    @Test
    void givenMonthCode_whenCodeNotFound_thenThrowIllegalArgumentException() {
        // given
        String givenEmptyCode = "empty";

        // when
        // then
        assertThatThrownBy(() -> findByCode(givenEmptyCode))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /*
    static Month findByNumber(int monthNumber) {
        Month result = null;

        for(Month m : values()) {
            if(m.getNumber() == monthNumber) {
                result = m;
                break;
            }
        }

        return result;
    }
     */

    static Month findByNumber(int monthNumber) {
        return Arrays.stream(values()).filter(month -> month.getNumber() == monthNumber).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @DisplayName("number 값이 존재하는 경우 Month 객체 반환를 반환한다")
    @Test
    void searchByNumber() {
        // given
        int givenMonthNumber = 12;

        // when
        Month result = findByNumber(givenMonthNumber);

        // then
        assertThat(result).isEqualTo(Month.DECEMBER);
    }

    @DisplayName("없는 month number 를 조회할 경우 IllegalArgumentException 을 던진다")
    @Test
    void givenMonthNumber_whenCodeNotFound_thenThrowIllegalArgumentException() {
        // given
        int givenEmptyMonthNumber = 13;

        // when
        // then
        assertThatThrownBy(() -> findByNumber(givenEmptyMonthNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
