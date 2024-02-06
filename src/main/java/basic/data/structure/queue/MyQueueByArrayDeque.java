package basic.data.structure.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * ArrayDeque
 * - 1.6부터 등장
 * - offer(..), add(..) 동일
 * - Queue의 경우 데이터 추가시 tail이 움직이고, 제거시 head가 움직인다
 * - head = 0 고정인 상태에서 데이터 추가로 tail이 head와 같아지면 capacity 증가
 */
public class MyQueueByArrayDeque<E> {
    Object[] elements;
    int head;
    int tail;

    public MyQueueByArrayDeque() {
        this.elements = new Object[16]; // default capacity
    }

    static final int inc(int i, int modulus) {
        if(++i >= modulus) i = 0;
        return i;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    // tail을 증가시키면서 데이터를 추가, add, offer 둘 다 공통 사용
    public void addLast(E e) {
        if(e == null)
            throw new NullPointerException();

        final Object[] es = elements;
        es[this.tail] = e;
        this.tail = inc(tail, es.length);

        //head == tail일때 (데이터 꽉찼을때), grow(1) 호출
    }

    public boolean offer(E e) {
        return offerLast(e);
    }

    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    // 검색 후 the head of queue 데이터 제거
    public E remove() {
        return removeFirst();
    }

    private E removeFirst() {
        E e = pollFirst();
        if(e == null)
            throw new NoSuchElementException();

        return e;
    }

    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
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

    public E peek() {
        return elementAt(this.elements, this.head);
    }

    public Object[] toArray() {
        return Arrays.copyOfRange(this.elements, this.head, this.tail, Object[].class);
    }

    public static void main(String[] args) {
        MyQueueByArrayDeque<Integer> queue = new MyQueueByArrayDeque();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.peek());
        System.out.println(Arrays.toString(queue.toArray()));

        System.out.println(queue.poll());
        System.out.println(Arrays.toString(queue.toArray()));
        System.out.println(queue.poll());
        System.out.println(Arrays.toString(queue.toArray()));
    }
}
