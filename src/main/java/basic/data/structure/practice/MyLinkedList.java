package basic.data.structure.practice;

import java.util.Arrays;
import java.util.NoSuchElementException;

// Doubly LinkedList
public class MyLinkedList<E> {
    int size = 0;
    Node<E> first;
    Node<E> last;
    public MyLinkedList() {
    }

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

    public void addFirst(E e) {
        this.linkFirst(e);
    }

    private void linkFirst(E e) {
        Node<E> f = this.first;
        Node<E> newNode = new Node<>(null, e, f);

        this.first = newNode;
        if(f == null) { // 노드가 한 개 뿐
            this.last = newNode;
        } else {
            f.prev = newNode;
        }

        this.size += 1;
    }

    public boolean add(E e) {
        this.linkLast(e);
        return true;
    }

    public void addLast(E e) {
        linkLast(e);
    }

    // target 노드 앞에 삽입
    public void linkBefore(E e, Node<E> target) { // target 노드 앞에 e를 삽입
        Node<E> prev = target.prev;
        Node<E> newNode = new Node<>(prev, e, target);

        if(prev == null) {
            this.first = newNode;
        } else {
            prev.next = newNode;
        }

        this.size += 1;
    }

    private void linkLast(E e) {
        Node<E> l = this.last;
        Node<E> newNode = new Node<>(this.last, e, null);

        this.last = newNode;
        if(l == null) { // 노드가 총 한 개
            this.first = newNode;
        } else {
            l.next = newNode;
        }

        this.size += 1;
    }

    private E unlink(Node<E> x) {
        E el = x.item;
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        if(prev == null) { // first라는 뜻이지
            this.first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if(next == null) { // last라는 뜻이지
            this.last = next.prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        this.size -= 1;

        return el;
    }

    public boolean remove(Object o) {
        if(o == null) {
            for(Node<E> n = this.first; n != null; n = n.next) {
                if(n.item == null) {
                    this.unlink(n);
                    return true;
                }
            }
        } else {
            for(Node<E> n = this.first; n != null; n = n.next) {
                if(o.equals(n.item)) {
                    this.unlink(n);
                    return true;
                }
            }
        }

        return false;
    }

    private E unlinkFirst(Node<E> f) {

        return null;
    }

    private E unlinkLast(Node<E> l) {

        return null;
    }

    public void removeFirst() {
        Node<E> f = this.first;
        if(f == null)
            throw new NoSuchElementException();

        this.unlinkFirst(this.first);
    }

    public void removeLast() {
        Node<E> l = this.last;
        if(l == null)
            throw new NoSuchElementException();
        this.unlinkLast(this.last);
    }

    // index에 위치한 node 반환 -- index < (size >> 1) 인 경우 first 조회, 아닌 경우 last 역순 조회
    Node<E> node(int index) {
        if(index < (size >> 1)) {
           Node<E> f = this.first;
           for(int i = 0; i < index; i++) {
               f = f.next;
           }

           return f;
        } else {
            Node<E> l = this.last;
            for(int i = size - 1; i > index; i--) { // 여기 틀림
                l = l.prev;
            }

            return l;
        }
    }

    Object[] toArray() {
        Object[] o = new Object[size];
        int idx = 0;
        for(Node<E> n = this.first; n != null; n = n.next) {
            o[idx++] = n.item;
        }

        return o;
    }

    // Object에 대한 index를 반환
    public int indexOf(Object o) {
        int idx = 0;
        if(o == null) {
            for(Node<E> n = this.first; n != null; n = n.next) {
                if(n == null) {
                    return idx;
                }
                idx += 1;
            }
        } else {
            for(Node<E> n = this.first; n != null; n = n.next) {
                if(o.equals(n.item)) {
                    return idx;
                }
                idx += 1;
            }
        }

        return -1;
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
