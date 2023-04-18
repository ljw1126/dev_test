package basic.data.structure;

/*
    *배열 순환하여 사용하도록 하기 위해 front, rear pointer 사용함

      i 0 1 2 3 4 5 6 7
  front 7
 maxSize = 10일 때
    index = (i + front) % maxSize;
 */
public class MyQueueByArray<T> {
    private int maxSize; // 크기
    private int size; // 현재 길이
    private T[] queue;
    private int front; // 첫 번째 요소 커서, 데이터를 꺼내는 인덱스
    private int rear; // 마지막 요소 커서, 데이터를 넣는 인덱스

    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() {}
    }

    public static class OverflowQueueException extends RuntimeException {
        public OverflowQueueException() {}
    }

    public MyQueueByArray(int capacity) {
        maxSize = capacity;
        size = front = rear = 0;
        try {
            queue = (T[]) new Object[capacity];
        } catch (OutOfMemoryError e) {
            maxSize = 0;
        }
    }

    public void enqueue(T t) { // rear
        if(isFull()) throw new OverflowQueueException();

        queue[rear++] = t;
        size++;

        if(rear == maxSize) rear = 0; // maxSize : 10 이면 마지막 인덱스는 9니깐
    }

    public T dequeue() { // front
        if(isEmpty()) throw new EmptyQueueException();

        T result = queue[front++]; // *여기 틀림
        size--;

        if(front == maxSize) front = 0; // * 여기 틀림

        return result;
    }

    public T peek() {
        if(isEmpty()) throw new EmptyQueueException();
        return queue[front];
    }

    public int indexOf(T t) {
        if(isEmpty()) throw new EmptyQueueException();

        for(int i = 0; i < size; i++) { // * 여기 틀림
            int idx = (i + front) % maxSize; // * 여기 틀림
            if(queue[idx].equals(t)) {
                return idx;
            }
        }

        return -1;
    }

    public void clear() {
        front = rear = size = 0; // 메모리 낭비하기 보다는 처음부터 넣는다는 개념
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public boolean isFull() {
        return size >= maxSize;
    }

    public void dump() {
        if(isEmpty()) throw new EmptyQueueException();

        for(int i = 0; i < size; i++) {
            int idx = (i + front) % maxSize;
            System.out.println(queue[idx]);
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
