package example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 얕은 복사(=) : 참조 주소를 그대로 가지므로, 변경시 원본, 복사본 서로 영향을 받음
 * 방어적 복사 : 컬렉션 참조 끊지만, 컬렉션 요소 참조는 유지되어서 변경시 상호 영향
 * 깊은 복사 : 참조 모두 끊고 신규 생성, 상호 변경 영향 x
 *
 * 참고
 * https://tecoble.techcourse.co.kr/post/2021-04-26-defensive-copy-vs-unmodifiable/
 */
public class CopyTest {

    @DisplayName("얕은 복사")
    @Test
    void 생성자에서_참조를_끊지않는경우_외부에서변경시_객체_내부도_변경된다() {
        List<Name> list = new ArrayList<>();
        list.add(new Name("a"));
        list.add(new Name("b"));

        Names names = new Names(list); // [a, b] -> [a, b, c]
        list.add(new Name("c")); // [a, b, c]
    }

    @Test
    void 방어적복사를_사용할경우_컬렉션_참조를_끊을수있다() {
        List<Name> list = new ArrayList<>();
        list.add(new Name("a"));
        list.add(new Name("b"));

        Names names = new Names(list); // [a, b] 유지
        list.add(new Name("c"));// [a, b, c]
    }

    @Test
    void 방어적복사를_사용해도_요소_참조는_끊을수없다() {
        Name a = new Name("a");
        Name b = new Name("b");

        List<Name> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        Names names = new Names(list); // [a, b] -> [c, d]

        a.setName("c");
        names.getName(1).setName("d");
    }

    @Test
    void 얕은복사_수정불가리스트를_사용해도_외부변경이_내부컬렉션에게_영향을끼친다() {
        List<Name> list = new ArrayList<>();
        list.add(new Name("a"));
        list.add(new Name("b"));

        // 원본 컬렉션에 대한 참조를 가지고 있으므로 얕은 복사에 해당
        // 외부에서 변경일어날 경우 내부 컬렉션도 영향 받음
        List<Name> unmodifiableList = Collections.unmodifiableList(list); // [a, b] -> [a, b, c];

        list.add(new Name("c"));
    }

}
