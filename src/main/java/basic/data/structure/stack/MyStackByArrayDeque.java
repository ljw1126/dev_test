package basic.data.structure.stack;

import java.util.NoSuchElementException;

/**
 * ArrayDeque
 * - 자바 1.6 추가
 * - 데이터 추가 시 head 감소, 데이터 제거시 head 증가
 * - tail = 0 고정인 상태에서 데이터 계속 추가해서 head = 0이 되면 capacity 증가
 */
public class MyStackByArrayDeque<E> {

    Object[] elements;
    int head;
    int tail;

    public MyStackByArrayDeque() {
        this.elements = new Object[16]; // default capacity
    }

    public void push(E e) {
        addFirst(e);
    }

    private void addFirst(E e) {
        if(e == null)
            throw new NullPointerException();
        final Object[] es = elements;
        es[head = dec(head, es.length)] = e;

        // head == tail (데이터 꽉 채웠을때) grow(1)
    }

    static final int dec(int i, int modulus) {
        if(--i < 0) i = modulus - 1;
        return i;
    }

    public E pop() {
        return removeFirst();
    }

    private E removeFirst() {
        E e = pollFirst();
        if(e == null)
            throw new NoSuchElementException();

        return e;
    }

    private E pollFirst() {
        final Object[] es;
        final int h;
        E e = elementAt(es = elements, h = head);
        if(e != null) {
            es[h] = null;
            head = inc(h, es.length);
        }
        return e;
    }

    static final <E> E elementAt(Object[] es, int i) {
        return (E) es[i];
    }

    static final int inc(int i, int modulus) {
        if(++i >= modulus) i = 0;
        return i;
    }
}
