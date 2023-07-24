package basic.function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Food {}
class Fruit extends Food {}
class Apple extends Fruit {}

class Tomato extends Fruit {}

class Meat extends Food {}
class Pork extends Meat {}

class Box<T> {
    private T t;

    private List<T> list = new ArrayList<>();

    public Box() {}
    public Box(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println("T : " + t.getClass().getName());
        System.out.println("U : " + u.getClass().getName());
    }

     public void add(T t) {
        list.add(t);
     }

     public void boxTest(Box<Number> numberBox) {
        // do something
     }

     /*
     public static T getSomething(int idx) {
        return null;
     }
    */

     public static <T> void doSomething(T e) {
        // do something
     }

}

class NaturalNumber<N extends Integer> {
    private N n;

    public NaturalNumber(N n) {
        this.n = n;
    }

    public boolean isEven() {
        return n.intValue() % 2 == 0;
    }
}

class A {}
interface B {}
interface C {}


interface PayloadListInterface<E, P> extends List<E> {
    void setPayload(int index, P val);
    // ...
}

class PayloadClass<E, P> implements PayloadListInterface<E, P> {

    @Override
    public void setPayload(int index, P val) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int i) {
        return null;
    }

    @Override
    public E set(int i, E e) {
        return null;
    }

    @Override
    public void add(int i, E e) {

    }

    @Override
    public E remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return null;
    }

    @Override
    public List<E> subList(int i, int i1) {
        return null;
    }
}

public class GenericsBoundedTypeParamTest {

    @DisplayName("")
    @Test
    void bounded_type_parameter() {
        Box<Integer> integerBox = new Box<>();
        integerBox.set(new Integer(10));

        // reason: no instance(s) of type variable(s) exist so that String conforms to Number
        // integerBox.inspect("some test"); // error

        integerBox.inspect(new Double(10.1));
    }

    @DisplayName("여러 개의 subtype을 명시할 때, 만약 그 중 클래스가 있으면 맨 처음에 명해야 한다")
    @Test
    void multiple_bounds() {
        class D <T extends A & B & C> {};  // Ok

        //class E <T extends B & A & C> {};  // Error
    }

    public static <T extends Comparable<T>> int countGraterThan(T[] anArray, T elem) {
        int count = 0;
        for(T e : anArray) {
            // Error : object is not primitive type when static method parameter defined just <T>
            // if(e > elem) count += 1;

            if(e.compareTo(elem) > 0) count += 1;
        }

        return count;
    }


    @DisplayName("제네릭 메서드에서 비교연산자를 사용할 경우 Comparable interface 구현한 매개변수타입 사용해야 한다")
    @Test
    void generics_method_test_by_wrapper_class_with_extends_comparable_interface() {
        // given
        Integer[] A = new Integer[]{1, 2, 3, 4};

        // when
        int result = countGraterThan(A, 5);

        // then
        Assertions.assertThat(result).isZero();
    }

    @DisplayName("Integer가 Number의 subtype이라도, Box<Integer> 는 Box<Number>의 subtype 이 아니다")
    @Test
    void invariance_by_generic_class() {
        Box<Number> box = new Box<>();
        box.add(new Integer(10)); // Ok
        box.add(new Double(20.1)); // Ok

        /*
          Error : Box<Integer> and Box<Double> are not subtype of Box<Number>
          Box<Integer> and Box<Double> just subtype of Object
          제네릭은 기본적으로 무공변의 특성을 가진다
        */
        // box.boxTest(new Box<Integer>(10));
        // box.boxTest(new Box<Double>(10.1));
    }


    @DisplayName("")
    @Test
    void test() {
        PayloadListInterface<String, String> payloadList = new PayloadClass<>();
        PayloadListInterface<String, Integer> payloadList1 = new PayloadClass<>();
        PayloadListInterface<String, Double> payloadList3 = new PayloadClass<>();
        PayloadListInterface<String, Exception> payloadList2 = new PayloadClass<>();
    }

    public static <T extends Food> void printClassName(T node) {
        System.out.println(node.getClass().getName());
    }

    @DisplayName("상한 경계 지정해서 Fruit 클래스와 Fruit 클래스 자식인 경우에만 메서드 호출 가능하다")
    @Test
    void upper_bounded_test() {
        printClassName(new Apple());
        printClassName(new Tomato());
        printClassName(new Pork()); // Error
    }

    public static void printClassNameByUpperBoundedCase(List<? extends Fruit> list) {
        StringBuilder sb = new StringBuilder();
        for(Fruit f : list) {
            sb.append(f.getClass().getName()).append("\n");
        }

        System.out.println(sb);
    }

    @DisplayName("")
    @Test
    void upper_bounded_wildcard_test() {
        List<Fruit> fruits = Arrays.asList(new Apple(), new Tomato());
        List<Meat> meats = Arrays.asList(new Meat());

        printClassNameByUpperBoundedCase(fruits); // Ok
        //printClassNameByUpperBoundedCase(meats);  // Error : 상한 경계 List<? extends Food>면 가능
    }


    // good
    public static <E> List<E> union1(List<? extends E> listOne, List<? extends E> listTwo) {
        return Stream.concat(listOne.stream(), listTwo.stream()).collect(Collectors.toList());
    }

    // good
    public static <E extends Number> List<E> union2(List<E> listOne, List<E> listTwo) {
        return Stream.concat(listOne.stream(), listTwo.stream()).collect(Collectors.toList());
    }

    // bad case
    public static <E> List<? extends E> union3(List<? extends E> listOne, List<? extends E> listTwo) { // return type 으로 인한 compile 에러
        return Stream.concat(listOne.stream(), listTwo.stream()).collect(Collectors.toList());
    }

    @DisplayName("")
    @Test
    void merge_type_parameter_test() {
        // given
        List<Number> listOne = Arrays.asList(1, 2, 3);
        List<Number> listTwo = Arrays.asList(4.4, 5.5, 6.6);

        // when
        List<Number> mergedList1 = union1(listOne, listTwo);
        List<Number> mergedList2 = union2(listOne, listTwo);
        List<? extends Number> mergedList3 = union3(listOne, listTwo);

        // then
        Assertions.assertThat(mergedList1).hasSize(6);
        Assertions.assertThat(mergedList2).hasSize(6);
        Assertions.assertThat(mergedList3).hasSize(6);
    }

    public static void addAppleAndTomato(List<? super Fruit> fruits) {
        fruits.add(new Apple());
        fruits.add(new Tomato());
    }

    @DisplayName("")
    @Test
    void lower_bounded_wildcard_test() {
        List<Food> food = new ArrayList<>();
        List<Fruit> fruits = new ArrayList<>();
        List<Apple> apples = new ArrayList<>();

        addAppleAndTomato(food);
        addAppleAndTomato(fruits);

        // compile time error
        // 하한 경계가 Fruit 이므로 추가 불가
        // addAppleAndTomato(apples);
    }
}
