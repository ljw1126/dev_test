package basic.data.structure.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyLinkedList<E> {
    int size = 0;
    Node<E> first;
    Node<E> last;

    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public boolean add(E e) {
        this.linkLast(e);
        return true;
    }

    public void addFirst(E e) {
        this.linkFirst(e);
    }

    public void addLast(E e) {
        this.linkLast(e);
    }

    public void linkFirst(E e) {
        Node<E> f = this.first;
        Node<E> newNode = new Node<>(null, e, f);

        this.first = newNode;
        if(f == null) {
            this.last = newNode;
        } else {
            f.prev = newNode;
        }

        this.size += 1;
    }

    public void linkLast(E e) {
        Node<E> l = this.last;
        Node<E> newNode = new Node<>(l, e, null);

        this.last = newNode;
        if(l == null) { // 노드가 하나 뿐
            this.first = newNode;
        } else {
            l.next = newNode;
        }

        this.size += 1;
    }

    // target 노드의 앞에 삽입
    public void linkBefore(E e, Node<E> target) {
        final Node<E> prev = target.prev;
        final Node<E> newNode = new Node<>(prev, e, target);

        target.prev = newNode;
        if(prev == null) {
            this.first = newNode;
        } else {
            prev.next = newNode;
        }

        this.size += 1;
    }

    public E unlink(Node<E> e) {
        final E item = e.item;
        final Node<E> prev = e.prev;
        final Node<E> next = e.next;

        if(prev == null) {
            this.first = next;
        } else {
            prev.next = next;
            e.prev = null;
        }

        if(next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
            e.next = null;
        }

        e.item = null;
        this.size -= 1;

        return item;
    }

    public boolean remove(Object o) {
        if(o == null) {
            for(Node<E> x = this.first; x != null; x = x.next) {
                if(x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for(Node<E> x = this.first; x != null; x = x.next) {
                if(o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public E removeFirst() {
        Node<E> f = this.first;
        if(f == null)
            throw new NoSuchElementException();

        return unlinkFirst(f);
    }

    public E removeLast() {
        Node<E> l = this.last;
        if(l == null)
            throw new NoSuchElementException();

        return unlinkLast(l);
    }

    private E unlinkFirst(Node<E> n) { // 조건문 실수
        final E item = n.item;
        final Node<E> next = n.next;

        this.first = next;
        if(next == null) {
            this.last = null;
        } else {
            next.prev = null;
        }

        n.item = null;
        this.size -= 1;

        return item;
    }

    private E unlinkLast(Node<E> n) { // 조건문 실수
        final E item = n.item;
        final Node<E> prev = n.prev;

        this.last = prev;
        if(prev == null) {
            this.first = null;
        } else {
            prev.next = null;
        }

        n.item = null;
        this.size -=1;

        return item;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];

        int idx = 0;
        for(Node<E> n = this.first; n != null; n = n.next) {
            result[idx++] = n.item;
        }

        return result;
    }

    public int indexOf(Object o) {
        if(o == null) { // 찾는 값이 null 인 경우
            int idx = 0;
            for(Node<E> n = this.first; n != null; n = n.next) {
                if(n.item == null) {
                    return idx;
                }
                idx += 1;
            }
        } else { // 찾는 값이 null이 아닌 경우
            int idx = 0;
            for(Node<E> n = this.first; n != null; n = n.next) {
                if(o.equals(n.item)) {
                    return idx;
                }
                idx += 1;
            }
        }

        return -1;
    }

    Node<E> node(int idx) {
        if(idx < (size >> 1)) { // 절반 사이즈에서 idx가 작으면 선행
            Node<E> n = this.first;
            for(int i = 0; i < idx; i++) {
                n = n.next;
            }
            return n;
        } else { // 하행
            Node<E> n = this.last;
            for(int i = size - 1; i > idx; i--) { // 부호
                n = n.prev;
            }
            return n;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        System.out.println(Arrays.toString(myLinkedList.toArray())); // 1 2 3 4 5

        myLinkedList.linkBefore(3, myLinkedList.node(myLinkedList.indexOf(2))); // 1 3 2 3 4 5
        System.out.println(Arrays.toString(myLinkedList.toArray()));

        myLinkedList.remove(4);
        myLinkedList.linkBefore(0, myLinkedList.node(myLinkedList.indexOf(1))); // 0 1 3 2 3 5
        System.out.println(Arrays.toString(myLinkedList.toArray()));
    }
}
