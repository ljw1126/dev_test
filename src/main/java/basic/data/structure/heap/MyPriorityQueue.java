package basic.data.structure.heap;

import java.util.Arrays;
import java.util.Comparator;

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

    public MyPriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

    public MyPriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public MyPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if(initialCapacity < 1)
            throw new IllegalArgumentException();

        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        if(e == null)
            throw new NullPointerException();

        int i = size;

        // i >= queue.length 인 경우 사이즈 증가

        siftUp(i, e);
        this.size = i + 1;
        return true;
    }

    private void siftUp(int k, E x) { // k : size , x : add Element
        if(this.comparator != null)
            siftUpUsingComparator(k, x, this.queue, this.comparator);
        else
            siftUpComparable(k, x, this.queue);
    }

    // k : current size, x : add valud,
    private static <T> void siftUpUsingComparator(int k, T x, Object[] es, Comparator<? super T> comparator) {
        while(k > 0) {
            int parent = (k - 1) >>> 1; // 3이면 부모는 1이됨
            Object e = es[parent];

            if(comparator.compare(x, (T) e) >= 0)
                break;

            es[k] = e;
            k = parent;
        }
        es[k] = x;
    }

    // 마지막 노드에 삽입 후 up
    private static <T> void siftUpComparable(int k, T x, Object[] es) {
        Comparable<? super T> key = (Comparable<? super T>)  x;
        while(k > 0) {
            int parent = (k - 1) >>> 1; // 2개 있을 때 부모는 0번 노드
            Object e = es[parent];
            if(key.compareTo((T) e) >= 0)
                break;
            es[k] = e;
            k = parent;
        }
        es[k] = key; // k = 0 일때,
    }

    public E poll() {
        final Object[] es;
        final E result;

        if((result = (E)((es = this.queue)[0])) != null) {
            final int n;
            final E x = (E) es[n = --size]; // 마지막 인덱스에 값을 꺼냄
            es[n] = null; // 마지막 인덱스 비움
            if(n > 0) {
                final Comparator<? super E > cmp;
                if((cmp = comparator) == null)
                    siftDownComparable(0, x, es, n); //
                else
                    siftDownUsingComparator(0, x, es, n, cmp);
            }
        }

        return result;
    }

    private static <T> void siftDownUsingComparator(int k, T x, Object[] es, int n, Comparator<? super T> cmp) {
        int half = n >>> 1; // half부터 리프 노드
        while(k < half) {
            int child = (k << 1) + 1; // left
            Object c = es[child];
            int right = child + 1;

            if(right < n && ((Comparable<? super T>)c).compareTo((T) es[right]) > 0) {
                c = es[child = right];
            }

            if(cmp.compare(x, (T) c) <= 0)
                break;

            es[k] = c;
            k = child;
        }

        es[k] = x;
    }

    // k : 루트노드(0 고정), x : 마지막 인덱스 값, n = 현재 사이즈 - 1
    private static <T> void siftDownComparable(int k, T x, Object[] es, int n) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        int half = n >>> 1; // size의 절반, 해당 노드 리프노드
        while(k < half) {
            int child = (k << 1) + 1; // 2배 + 1, left child
            Object c = es[child];
            int right = child + 1;

            // 자식이 둘다 존재할 때
            if(right < n && ((Comparable<? super T>) c).compareTo((T) es[right]) > 0) {
                c = es[child = right];
            }

            if(key.compareTo((T) c) <= 0)
                break;

            es[k] = c;
            k = child;
        }
        es[k] = key;
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.queue, this.size);
    }

    public static void main(String[] args) {
        // 오름차순 : 1 2 4 5 3
        // 내림차순 : 5 4 2 1 3
        MyPriorityQueue<Integer> asc = new MyPriorityQueue<>(); // 오름차순
        asc.add(5);
        asc.add(4);
        asc.add(3);
        asc.add(2);
        asc.add(1);

        System.out.println(Arrays.toString(asc.toArray()));
        System.out.println(asc.poll());
        System.out.println(asc.poll());
        System.out.println(asc.poll());
        System.out.println(asc.poll());
        System.out.println(asc.poll());


        MyPriorityQueue<Integer> desc = new MyPriorityQueue<>((a, b) -> b - a); // 내림차순
        desc.add(1);
        desc.add(2);
        desc.add(3);
        desc.add(4);
        desc.add(5);

        System.out.println(Arrays.toString(desc.toArray()));

        System.out.println(desc.poll());
        System.out.println(desc.poll());
        System.out.println(desc.poll());
        System.out.println(desc.poll());
        System.out.println(desc.poll());
    }
}
