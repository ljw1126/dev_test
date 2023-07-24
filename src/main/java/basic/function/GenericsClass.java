package basic.function;

import java.util.Collections;
import java.util.List;

class Unknown{}

public class GenericsClass<T> {
    T something;

    public GenericsClass(T something) {
        this.something = something;
    }

    void print() {
        System.out.println(something.getClass().getName());
    }

    static <E> List<E> of() {
        return Collections.EMPTY_LIST;
    }
}

class Main {
    public static void main(String[] args) {
        GenericsClass<Unknown> genericsClass = new GenericsClass<>(new Unknown());
        genericsClass.print(); // {패캐지경로}.Unknown 출력
    }
}
