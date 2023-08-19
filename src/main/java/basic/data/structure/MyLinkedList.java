package basic.data.structure;

import java.util.NoSuchElementException;

// java.util.LinkedList<E> 클래스 참고하여 작성
public class MyLinkedList<E> {
    int size;
    Node<E> first;
    Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkFirst(E e) {
        Node<E> f = this.first;
        Node<E> newNode = new Node<>((Node) null, e, f);

        this.first = newNode; // *누락 실수
        if(f == null) { // 노드가 하나 뿐인 경우
            this.last = newNode;
        } else {
            f.prev = newNode;
        }

        this.size += 1;
    }

    private void linkLast(E e) {
        Node<E> l = this.last;
        Node<E> newNode = new Node(l, e, (Node<E>)null); // 마지막 노드는 next가 null

        this.last = newNode;
        if(l == null) { // 노드가 하나 뿐인 경우
            this.first = newNode;
        } else {
            l.next = newNode;
        }

        this.size += 1; // AbstractList에 modCount 용도는 모르겠음
    }

    void linkBefore(E e, Node<E> target) { // target 노드 전에
        Node<E> prev = target.prev;
        Node<E> newNode = new Node(prev, e, target);
        if(prev == null) { // prev = null 이면 first 노드 뜻함
            this.first = newNode;
        } else {
            prev.next = newNode;
        }

        this.size += 1;
    }

    private E unlinkFirst(Node<E> f) {
        E element = f.item;
        Node<E> next = f.next;

        this.first = next;
        if(next == null) { // 노드가 한 개
            this.last = null;
        } else {
            next.prev = null;
        }

        f.item = null;
        f.next = null;
        this.size -= 1;
        return element;
    }

    private E unlinkLast(Node<E> l) {
        E element = l.item;
        Node<E> prev = l.prev;

        this.last = prev;
        if(prev == null) { // 노드가 한 개
            this.first = null;
        } else {
            prev.next = null;
        }

        l.item = null;
        l.prev = null;
        this.size -= 1;
        return element;
    }
    private E unlink(Node<E> x) {
        E element = x.item;
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        if(prev == null) { // prev = null 이면 x는 first
            this.first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if(next == null) { // next = null 이면 x는 last
            this.last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        this.size -= 1;

        return element;
    }

    public E getFirst() {
        Node<E> f = this.first;
        if(f == null) {
            throw new NoSuchElementException();
        } else {
            return f.item;
        }
    }

    public E getLast() {
        Node<E> l = this.last;
        if(l == null) {
            throw new NoSuchElementException();
        } else {
            return l.item;
        }
    }

    public void removeFirst() {
        Node<E> f = this.first;
        if(f == null) {
            throw new NoSuchElementException();
        } else {
            this.unlinkFirst(f);
        }
    }

    public void removeLast() {
        Node<E> l= this.last;
        if(l == null) {
            throw new NoSuchElementException();
        } else {
            this.unlinkLast(l);
        }
    }

    public void addFirst(E e) {
        this.linkFirst(e);
    }

    public void addLast(E e) {
        this.linkLast(e);
    }

    public boolean add(E e) {
        this.linkLast(e);
        return true;
    }

    public boolean remove(Object o) {
        Node x;
        if(o == null) {
            for(x = this.first; x != null; x = x.next) {
                if(x.item == null) {
                    this.unlink(x);
                    return true;
                }
            }
        } else {
            for(x = this.first; x != null; x = x.next) {
                if(o.equals(x.item)) {
                    this.unlink(x);
                    return true;
                }
            }
        }

        return false;
    }

    public int size() {
        return this.size;
    }

    public Object[] toArray() {
        Object[] result = new Object[this.size];
        int i = 0;
        for(Node<E> x = this.first; x != null; x = x.next) {
            result[i++] = x.item;
        }

        return result;
    }

}
