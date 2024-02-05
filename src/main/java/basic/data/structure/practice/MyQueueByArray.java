package basic.data.structure.practice;

import java.util.NoSuchElementException;

public class MyQueueByArray<T> {

    private int maxSize; // 크기
    private int size; // 현 재길이
    private T[] element;
    private int front; // 첫 번째 요소 커서, 데이터를 꺼내는 인덱스
    private int rear; // 미자막 요소 커서, 데이터를 넣는 인덱스

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
            element = (T[]) new Object[capacity];
        } catch (OutOfMemoryError o) {
            maxSize = 0;
        }
    }

    public void enqueue(T t) {
        if(isFull()) throw new OverflowQueueException();

        this.element[rear++] = t;
        this.size += 1;

        if(rear == maxSize)
            rear = 0;
    }

    public T dequeue() {
        if(isEmpty()) throw new EmptyQueueException();

        T el = this.element[front++];
        this.size -= 1;

        if(front == maxSize)
            front = 0;

        return el;
    }

    public T peek() {
        if(isEmpty())
            throw new EmptyQueueException();

        return this.element[front];
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size >= maxSize;
    }

    public int indexOf(T t) {
        if(isEmpty())
            throw new EmptyQueueException();

        for(int i = 0; i < size; i++) {
            int idx = (i + this.front) % maxSize;
            if(this.element[idx].equals(t)) {
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
        if(isEmpty()) throw new NoSuchElementException();

        for(int i = 0; i < size; i++) {
            int idx = (i + this.front) % maxSize;
            System.out.println(this.element[idx]);
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
