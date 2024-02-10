package basic.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

class MyStack<T> {
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyStack() {
        elements = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(T e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public T pop() {
        if(size == 0) throw new EmptyStackException();

        T result = elements[--size];
        elements[size] = null; // 다쓴 참조 해제
        return result;
    }

    public void pushAll(Iterable<? extends T> src) {
        for(T t : src) push(t);
    }

    public void popAll(Collection<? super T> dst) {
        while(!isEmpty()) dst.add(pop());
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if(elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
public class GenericsTestByPECS {

    @DisplayName("")
    @Test
    void unbounded_method_test() {
        MyStack<Number> numberMyStack = new MyStack<>();
        Iterable<Integer> integers = Arrays.asList(1, 2, 3);
        numberMyStack.pushAll(integers);

        Collection<Object> objects = new ArrayList<>();
        numberMyStack.popAll(objects);
    }
}
