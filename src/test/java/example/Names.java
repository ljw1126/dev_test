package example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Names {
    private final List<Name> names;

    public Names(List<Name> names) {
        //this.names = names; //얕은 복사
        this.names = new ArrayList<>(names);
    }

    public Name getName(int idx) {
        return this.names.get(idx); // 얕은 복사
    }

    public List<Name> getNamesByDefensiveCopy() {
        return new ArrayList<>(this.names); // 원본 컬렉션 참조 끊음, 단 컬렉션 요소 변경은 막지 못함
    }

    public List<Name> getNamesByUnmodifiableList() {
        return Collections.unmodifiableList(this.names); // 원본 컬렉션 참조를 가지는 얕은 복사, 외부 변경은 막지만, 내부 컬렉션 변경시 복사본도 영향 받음
    }
}
