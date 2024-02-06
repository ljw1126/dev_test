package basic.data.structure.queue;

import java.util.NoSuchElementException;

/**
 * enqueue : this.rear 증가
 * dequeue : this.front 증가
 * 공통적으로 maxSize 초과시 0으로 초기화
 */
public class MyQueueByArray<T> {
    T[] elements;
    int front; // 데이터를 꺼내는 인덱스
    int rear; // 데이터를 넣는 인덱스
    int size;
    int maxSize;

    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() {
        }
    }

    public static class OverflowQueueException extends RuntimeException {
        public OverflowQueueException() {
        }
    }

    public MyQueueByArray(int capacity) {
        this.maxSize = capacity;
        this.size = this.front = this.rear = 0;

        try {
            this.elements = (T[]) new Object[capacity];
        } catch (OutOfMemoryError e) {
            System.out.println(e.getMessage());
            this.maxSize = 0;
        }
    }


    public void enqueue(T e) {
        if(isFull())
            throw new OverflowQueueException();

        this.elements[this.rear++] = e;
        this.size += 1;

        if(this.rear == this.maxSize) {
            this.rear = 0;
        }
    }

    public T dequeue() {
        if(isEmpty())
            throw new EmptyQueueException();

        T el = this.elements[this.front++];
        this.size -= 1;

        if(this.front == maxSize)
            this.front = 0;

        return el;
    }

    public T peek() {
        if(isEmpty())
            throw new NoSuchElementException();

        return this.elements[this.front];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.maxSize;
    }

    public int indexOf(T t) {
        if(isEmpty())
            throw new NoSuchElementException();

        for(int i = 0; i < size; i++) {
            int idx = (i + this.front) % maxSize;
            if(this.elements[idx].equals(t)) {
                return idx;
            }
        }

        return -1;
    }

    public void clear() {
        this.front = this.rear = this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void dump() {
        if(isEmpty())
            throw new NoSuchElementException();

        for(int i = 0; i < size; i++) {
            int idx = (i + front) % maxSize;
            System.out.println(this.elements[idx]);
        }
    }

    public static void main(String[] args) {
        MyQueueByArray<Integer> MyQueueByArray = new MyQueueByArray(10);
        MyQueueByArray.enqueue(1);
        MyQueueByArray.enqueue(2);
        System.out.println(MyQueueByArray.dequeue());
        MyQueueByArray.enqueue(3);
        System.out.println(MyQueueByArray.dequeue());
        System.out.println(MyQueueByArray.dequeue());

        System.out.println("=======================");

        MyQueueByArray.enqueue(6);
        MyQueueByArray.enqueue(7);
        MyQueueByArray.enqueue(8); // 5번 인덱스
        MyQueueByArray.enqueue(9);
        MyQueueByArray.enqueue(10);
        System.out.println(MyQueueByArray.peek());
        System.out.println(MyQueueByArray.isEmpty());
        System.out.println(MyQueueByArray.indexOf(10));
        System.out.println(MyQueueByArray.indexOf(77));

        System.out.println("=======================");
        MyQueueByArray.dump();
        MyQueueByArray.clear();
        System.out.println(MyQueueByArray.isEmpty());
    }
}
