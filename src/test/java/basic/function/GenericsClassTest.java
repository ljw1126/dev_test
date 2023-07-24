package basic.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Grocery {} // 식료 품점
class Noodle extends Grocery {}
class Pasta extends Noodle{}
class Ramen extends Noodle{}

class Beverage extends Grocery {}
class Coke extends Beverage {}
class Sprite extends Beverage {}

class Category<T> {
    private T t;

    public Category(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}

class Example<T> {
    T t;
    void set(T t) {
        this.t = t;
    }

    public static <T> void print(List<T> list) {
        for(Object o : list) System.out.println(o);
    }

    public static <T extends Number> void printAll(T t) {

    }

    public static <T extends Number> List<T> doSomething(List<T> list) {
        return Collections.EMPTY_LIST;
    }
}

class NoodleCategory<T extends Noodle> {
    private T t;

    public NoodleCategory(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    public <T> void printClassName(T t) {
        System.out.println("클래스 필드에 정의된 타입 = " + this.t.getClass().getName());
        System.out.println("제네릭 메서드에 정의된 타입 = " + t.getClass().getName());
    }
}

interface Animal {
    void sound();
}
class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("멍멍");
    }
}

class Cat implements Animal {
    @Override
    public void sound() {
        System.out.println("아용");
    }
}
class GenericsClassTest {

    @DisplayName("")
    @Test
    void interface_implement_test() {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.sound();
        cat.sound();
    }

    public static void print(List<Object> list) {
        for(Object o : list) System.out.println(o);
    }

    @DisplayName("")
    @Test
    void static_method_test() {
       List<Integer> integers = Arrays.asList(1, 2, 3);
       //print(integers);
    }

    @DisplayName("")
    @Test
    void generics_extends_class() {
        Noodle pasta = new Pasta();
        Noodle ramen = new Ramen();
    }

    @DisplayName("")
    @Test
    void test() {
        Object[] array = new Integer[10];

        //Example<Number> example = new Example<Integer>(); // Error, 기본적으로 제네릭은 무공변이기 때문에

        Example<Number> boxNumber = new Example<Number>(); // ok
        Example<Integer> boxInteger = new Example<Integer>(); // ok
    }

    @DisplayName("제네릭 클래스 안에 제니릭 메서드가 있을 때, 제네릭 메서드 내에서 제네릭 메서드의 타입 매개변수가 우선 순위를 가진다")
    @Test
    void genericsMethodTypeTest() {
        NoodleCategory<Noodle> noodleNoddleCategory = new NoodleCategory<>(new Noodle());
        noodleNoddleCategory.printClassName(new Pasta());
    }

    @DisplayName("NoodleCategory 클래스는 상한 경계를 Noodle 클래스로 지정하여 Noodle 클래스와 Noodle 클래스 자식들만 사용가능하다")
    @Test
    void upperBound() {
        NoodleCategory<Noodle> noodleNoddleCategory = new NoodleCategory<>(new Noodle());
        NoodleCategory<Ramen> ramenNoddleCategory = new NoodleCategory<>(new Ramen());

        /*  에러
            Type parameter 'basic.function.Coke' is not within its bound;
            should extend 'basic.function.Noodle
         */
        //NoddleCategory<Coke> cokeNoddleCategory = new NoddleCategory<>(new Coke());
    }

    @DisplayName("상한 경계는 타입을 안전하게 가져올 때, 하한 경계는 안전하게 값을 가져와 저장할 때 사용한다")
    @Test
    void producer_extends_consumer_super() {
        class CategoryHelper<E> {
            private List<E> list = new ArrayList<>();

            public void popNoodle(Category<? extends Noodle> category) { // 상한 경계 (extends)
                Noodle noodle = category.get(); // 꺼내는 건 OK

                // 하위 타입에 상위 타입 대입 가능하여 컴파일 에러 발생 (category가 Noodle 자식 클래스로 만들어진 경우 부모 클래스를 사용할 수 없다)
                //category.set(new Noodle());  // 저장은 에러
            }

            public void pushNoodle(Category<? super Noodle> category) { // 하한 경계 (super)
                category.set(new Noodle()); // 저장은 OK

                // Noodle 보다 상위 타입을 꺼낼 수 있기 때문에 컴파일 에러 발생 가능
                // Noodle noodle = category.get(); // 꺼내는 건 Error
            }

            // 상한 경계는 타입을 안전하게 가져 올 수 있다 -> 원소를 안전하게 생성가능
            public void pushAll(Collection<? extends E> box) {
                for(E e : box) list.add(e);
            }

            // 하한 경계는 안전하게 값을 저장할 수 있다. -> 인스턴스 필드를 소비해서 안전하게 저장 가능
            public void popAll(Collection<? super E> box) {
                box.addAll(list);
                list.clear();
            }
        }
    }
}