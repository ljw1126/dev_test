package basic.data.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyQueue<T>{
    private final List<T> queue = new LinkedList<>();

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        return isEmpty() ? null : queue.remove(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue<Integer> mq = new MyQueue();
        System.out.println(mq.dequeue());
        mq.enqueue(1);
        mq.enqueue(2);
        mq.enqueue(3);
        System.out.println(mq.dequeue());
        System.out.println(mq.dequeue());
        System.out.println(mq.dequeue());
    }
}
