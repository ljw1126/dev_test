package lang;

import basic.function.PizzaStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class EnumValuesConvertToListTest {

    static <T extends Enum<T>> List<T> enumValuesInList(Class<T> enumClass) {
        T[] arr = enumClass.getEnumConstants();
        return Arrays.asList(arr);
    }

    /*
        List.of의 경우 ImmutableCollections 호출 분기
        Arrays.asList 의 경우 new ArrayList() 생성
     */
    @DisplayName("클래스 타입 토큰을 매개 변수로 하는 enumValuesInList 메서드에 Enum 클래스 타입 토큰을 인자로 전달하면 values List로 반환한다")
    @Test
    void enumValuesInList_test() {
        // given
        List<PizzaStatus> givenTypes = Arrays.asList(PizzaStatus.values());

        // when
        List<PizzaStatus> pizzaStatuses = enumValuesInList(PizzaStatus.class);

        // then
        assertThat(givenTypes).isEqualTo(pizzaStatuses);
    }

    static <T> List<T> enumValuesInListOrEmptyList(Class<T> enumClass) {
        T[] arr = enumClass.getEnumConstants();
        return arr == null ? Collections.EMPTY_LIST : Arrays.asList(arr);
    }

    @DisplayName("Enum 을 상속받는 클래스의 경우 Enum 요소가 담긴 List를 반환하고, 아닌 경우 EMPTY_LIST를 반환한다")
    @Test
    void enumValuesInListOrEmptyList_test() {
        // given
        List<PizzaStatus> givenEnumValuesList = Arrays.asList(PizzaStatus.values());

        // when
        List<PizzaStatus> pizzaStatuses = enumValuesInListOrEmptyList(PizzaStatus.class);
        List<Integer> emptyList = enumValuesInListOrEmptyList(Integer.class);

        // then
        assertThat(givenEnumValuesList).isEqualTo(pizzaStatuses);
        assertThat(emptyList).isEmpty();
    }

    /*
        [[ORDER, READY, DELIVERED]]
        List<EnumSet<PizzaStatus>> test1 = Arrays.asList(EnumSet.allOf(PizzaStatus.class));
        List<EnumSet<PizzaStatus>> test2  = List.of(EnumSet.allOf(PizzaStatus.class));
    */
    @DisplayName("EnumSet의 allOf 메서드를 사용하여 Enum List를 만들 수 있다")
    @Test
    void EnumSet_allOf_test() {
        // give
        List<PizzaStatus> givenPizzaStatusList = Arrays.asList(PizzaStatus.values());

        // when
        List<PizzaStatus> pizzaStatuses = new ArrayList<>(EnumSet.allOf(PizzaStatus.class));

        // then
        Assertions.assertThat(givenPizzaStatusList).isEqualTo(pizzaStatuses); // Ok
    }
}
