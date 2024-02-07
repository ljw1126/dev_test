package basic.data.structure.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * PriorityQueue
 * - java 1.5
 */
public class MyPriorityQueue<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    Object[] queue;

    int size;

    private final Comparator<? super E> comparator;

    public MyPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public MyPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if(initialCapacity < 1)
            throw new IllegalArgumentException();

        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    public void clear() {
        final Object[] es = this.queue;
        for(int i = 0, n = this.size; i < n; i++) {
            es[i] = null;
        }

        this.size = 0;
    }

    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        if(e == null)
            throw new NullPointerException();

        int i = this.size;

        // i >= queue.length 경우 사이즈를 i + 1 증가 시킴(생략)
        siftUp(i, e);
        this.size = i + 1;
        return true;
    }

    private void siftUp(int k, E x) { // k : 마지막 인덱스, x : 삽입할 데이터
        siftUpComparable(k, x, queue);
    }

    //마지막 인덱스에 추가되고, 부모 노드와 비교하며 heapify
    //Integer 라고 한다면 기본 최소힙으로 예상됨
    private static <T> void siftUpComparable(int k, T x, Object[] es) {
        Comparable<? super T> key = (Comparable<? super T>) x; // T타입 : Comparable 구현된 Wrapper class
        while(k > 0) {
            int parent = (k - 1) >>> 1; // k / 2
            Object e = es[parent];
            if(key.compareTo((T) e) >= 0)
                break;
            es[k] = e;
            k = parent;
        }

        es[k] = key;
    }

    public E poll() {
        final Object[] es;
        final E result;

        if((result = (E)(es = this.queue)[0]) != null) { // 루트값
            final int n;
            final E x = (E) es[(n = --this.size)]; // 마지막 값
            es[n] = null; // 마지막 인덱스 비움
            if(n > 0) {
                // Comparator 있는 경우, 없는 경우 나눠짐(우선 생략)
                siftDownComparable(0, x, es, n);
            }
        }

        return result;
    }

    // k : 시작점 (0), x : 마지막값, es : 큐, n : 배열 사이즈
    private static <T> void siftDownComparable(int k, T x, Object[] es, int n) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        int half = n >>> 1; // 가장 마지막 요소의 부모 인덱스
        while(k < half) { // half 이후는 리프노드이므로, sift연산을 할 필요가 없음
            int child = (k << 1) + 1; // 루트 0번부터 시작이면, 왼쪽 자식
            Object c = es[child];
            int right = child + 1;

            // 자식이 두명있고, left > right 일때, 최소힙이라면 c는 right가 되는게 맞다
            if(right < n &&
                    ((Comparable<? super T>)c).compareTo((T) es[right]) > 0) {
                c = es[child = right];
            }

            if(key.compareTo((T) c) <= 0) {
                break;
            }

            es[k] = c;
            k = child;
        }
        es[k] = key;
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.queue, this.size);
    }

    public static void main(String[] args) {
        MyPriorityQueue<Integer> que = new MyPriorityQueue<>();
        que.add(3);
        que.add(2);
        que.add(1);
        que.add(4);
        que.add(5);

        System.out.println(Arrays.toString(que.toArray())); // [1,3,2,4,5]

        que.poll();
        System.out.println(Arrays.toString(que.toArray())); // [2,3,5,4]
    }
}
