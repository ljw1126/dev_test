package nextstep.ref;

import nextstep.ref.ex4.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class PrivateFieldTest {

    @DisplayName("")
    @Test
    void setPrivateField() throws NoSuchFieldException, IllegalAccessException {
        // given, when
        Person person = new Person();
        Class<Person> clazz = Person.class;

        byte ageValue = 26;
        short uidNumberValue = 5555;
        int pinCodeValue = 411057;
        long contactNumberValue = 123456789L;
        float heightValue = 6.12342f;
        double weightValue = 75.254;
        char genderValue = 'M';
        boolean activeValue = true;
        String nameValue = "HongGilDong";

        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.setByte(person, ageValue);

        Field uidNumber = clazz.getDeclaredField("uidNumber");
        uidNumber.setAccessible(true);
        uidNumber.setShort(person, uidNumberValue);

        Field pinCode = clazz.getDeclaredField("pinCode");
        pinCode.setAccessible(true);
        pinCode.setInt(person, pinCodeValue);

        Field contactNumber = clazz.getDeclaredField("contactNumber");
        contactNumber.setAccessible(true);
        contactNumber.setLong(person, contactNumberValue);

        Field height = clazz.getDeclaredField("height");
        height.setAccessible(true);
        height.setFloat(person, heightValue);

        Field weight = clazz.getDeclaredField("weight");
        weight.setAccessible(true);
        weight.setDouble(person, weightValue);

        Field gender = clazz.getDeclaredField("gender");
        gender.setAccessible(true);
        gender.setChar(person, genderValue);

        Field active = clazz.getDeclaredField("active");
        active.setAccessible(true);
        active.setBoolean(person, activeValue);

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, nameValue);

        // then
        assertThat(person).extracting("age", "uidNumber", "pinCode", "contactNumber", "height", "weight", "gender", "active", "name")
                .containsExactly(ageValue, uidNumberValue, pinCodeValue, contactNumberValue, heightValue, weightValue, genderValue, activeValue, nameValue);
    }

    @DisplayName("")
    @Test
    void getPrivateField() throws NoSuchFieldException, IllegalAccessException {
        nextstep.ref.ex5.Person person = new nextstep.ref.ex5.Person();
        Class<? extends nextstep.ref.ex5.Person> clazz = person.getClass();

        byte ageResult = 26;
        short uidNumberResult = 5555;
        int pinCodeResult = 411057;
        long contactNumberResult = 123456789L;
        float heightResult = 6.12342f;
        double weightResult = 75.254;
        char genderResult = 'M';
        boolean activeResult = true;
        String nameResult = "HongGilDong";

        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        assertThat(age.getByte(person)).isEqualTo(ageResult);

        Field uidNumber = clazz.getDeclaredField("uidNumber");
        uidNumber.setAccessible(true);
        assertThat(uidNumber.getShort(person)).isEqualTo(uidNumberResult);

        Field pinCode = clazz.getDeclaredField("pinCode");
        pinCode.setAccessible(true);
        assertThat(pinCode.getInt(person)).isEqualTo(pinCodeResult);

        Field contactNumber = clazz.getDeclaredField("contactNumber");
        contactNumber.setAccessible(true);
        assertThat(contactNumber.getLong(person)).isEqualTo(contactNumberResult);

        Field height = clazz.getDeclaredField("height");
        height.setAccessible(true);
        assertThat(height.getFloat(person)).isEqualTo(heightResult);

        Field weight = clazz.getDeclaredField("weight");
        weight.setAccessible(true);
        assertThat(weight.getDouble(person)).isEqualTo(weightResult);

        Field gender = clazz.getDeclaredField("gender");
        gender.setAccessible(true);
        assertThat(gender.getChar(person)).isEqualTo(genderResult);

        Field active = clazz.getDeclaredField("active");
        active.setAccessible(true);
        assertThat(active.getBoolean(person)).isEqualTo(activeResult);

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        assertThat(name.get(person)).isEqualTo(nameResult);
    }
}
