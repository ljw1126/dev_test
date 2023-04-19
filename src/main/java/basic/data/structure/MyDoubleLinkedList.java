package basic.data.structure;

public class MyDoubleLinkedList<T> {

    public Node<T> head = null;
    public Node<T> tail = null;

    public class Node<T>{
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public void addNode(T data){
        if(this.head == null) {
            this.head = new Node<>(data);
            this.tail = this.head;
        } else {
            Node<T> node = this.head;

            while(node.next != null) {
                node = node.next;
            }

            node.next = new Node<>(data);
            node.next.prev = node; // 여기 틀림*
            this.tail = node.next;
        }
    }

    // 전체 출력 -- easy
    public void printAll(){
        Node<T> node = this.head;
        while(node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    //순방향 검색 (head, next) -- easy
    public Node<T> searchFromHead(T searchData){
       Node<T> result = null;
       Node<T> node = this.head;

       while(node != null) {
           if(node.value == searchData) {
               result = node;
               break;
           } else {
               node = node.next;
           }
       }

       return result;
    }

    // 역방향 검색 (tail, prev) -- easy
    public Node<T> searchFromTail(T searchData){
        Node<T> result = null;
        Node<T> node = this.tail;

        while(node != null) {
            if(node.value == searchData) {
                result = node;
                break;
            } else {
                node = node.prev;
            }
        }

        return result;
    }

    // 데이터를 임의 노드 앞에 노드를 추가하는 메서드 추가하기 -- case 고려하기*
    public boolean insertToFront(T searchData, T newData){
        Node<T> headNode = this.head;
        if(headNode == null) {
            addNode(newData);
            return true;
        } else if(headNode.value == searchData) { // 여기 틀림*
            this.head = new Node<>(newData);
            this.head.next = headNode;
            this.head.next.prev = this.head;
            return true;
        } else {
            Node<T> node = headNode.next;
            while(node != null) {
                if(node.value == searchData) {
                    Node<T> prevNode = node.prev;
                    prevNode.next = new Node<>(newData);
                    prevNode.next.prev = prevNode;
                    prevNode.next.next = node;
                    node.prev = prevNode.next;

                    return true;
                } else {
                    node = node.next;
                }
            }
        }

        return false;
    }

    public void removeHead() {
        if(this.head != null) {
            this.head.next.prev = null;
            this.head = this.head.next;
        }
    }

    public void removeTail() {
        if(this.tail != null) {
            this.tail.prev.next = null;
            this.tail = this.tail.prev;
        }
    }

    /*
        case1. head 노드가 대상이고 다음 노드가 없는 경우
        case2. head 노드가 대상이고 다음 노드가 존재할 경우*
        case3-1. 중간 노드인 경우
        case3-2. 마지막 노드인 경우
    */
    public boolean remove(T targetData) {
        Node<T> headNode = this.head;
        if(headNode.value == targetData && headNode.next == null) {
            this.head = null;
            this.tail = null;
            return true;
        } else if(headNode.value == targetData && headNode.next != null) {
            Node<T> nextNode = this.head.next;
            this.head.next.prev = null;
            this.head = nextNode;
            return true;
        } else {
            Node<T> node = headNode.next;
            while(node != null) {
                if(node.value == targetData && node.next != null) {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;

                    return true;
                }else if(node.value == targetData && node.next == null) {
                    node.prev.next = null;
                    this.tail = node.prev;

                    return true;
                }
                node = node.next;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MyDoubleLinkedList<Integer> MyLinkedList = new MyDoubleLinkedList<>();

        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);
        MyLinkedList.addNode(4);
        MyLinkedList.addNode(5);
        MyLinkedList.printAll();
        System.out.println("----------------"); // 1 2 3 4 5

        MyLinkedList.insertToFront(3, 2); // 1 2 2 3 4 5
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.remove(4);
        MyLinkedList.insertToFront(1, 0); // 0 1 2 2 3 5
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.addNode(6); // 0 1 2 2 3 5 6
        MyLinkedList.printAll();
    }
}
