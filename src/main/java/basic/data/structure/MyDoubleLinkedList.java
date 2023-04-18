package basic.data.structure;

public class MyDoubleLinkedList<T> {

    public Node<T> head = null;
    public Node<T> tail = null;

    public class Node<T>{
        T value;
        Node<T> prev;
        Node<T> next;
        public Node(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
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
            node.next.prev = node; // * 여기 틀림
            this.tail = node.next;
        }
    }

    // 전체 출력 -- easy
    public void printAll(){
        if(this.head != null) {
            Node<T> node = this.head;
            System.out.println(node.value);
            while(node.next != null) {
                node = node.next;
                System.out.println(node.value);
            }
        }
    }
    //순방향 검색 (head, next) -- easy
    public Node<T> searchFromHead(T searchData){
        Node<T> result = null;

        if(this.head != null) {
            Node<T> node = this.head;
            while(node != null) {
                if(node.value == searchData) {
                    result = node;
                } else {
                    node = node.next;
                }
            }
        }

        return result;
    }
    // 역방향 검색 (tail, prev) -- easy
    public Node<T> searchFromTail(T searchData){
        Node<T> result = null;

        if(this.tail != null) {
            Node<T> node = this.tail;
            while(node != null) {
                if(node.value == searchData) {
                    result = node;
                } else {
                    node = node.prev;
                }
            }
        }

        return result;
    }

    // 데이터를 임의 노드 앞에 노드를 추가하는 메서드 추가하기 -- case 고려하기
    public boolean insertToFront(T searchData, T newData){
        if(this.head == null) {
            addNode(newData);
            return true;
        } else if(this.head.value == searchData) {
            Node<T> headNode = this.head;
            this.head = new Node<>(newData);
            this.head.next = headNode;
            this.head.next.prev = this.head;

            return true;
        } else {
            Node<T> node = this.head.next;
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

    public boolean remove(T targetData) {
        Node<T> headNode = this.head;

        if(headNode != null) {
            // case1. head 노드가 대상이고 다음 노드가 없는 경우
            if(headNode.value == targetData && headNode.next == null) {
                this.head = null;
                this.tail = null;
                return true;
            } else if (headNode.value == targetData && headNode.next != null) { // case2. head 노드가 대상이고 다음 노드가 존재할 경우
                removeHead();
                return true;
            } else {
                Node<T> prevNode = headNode;
                Node<T> currentNode = headNode.next;

                /*
                    case3-1. 중간 노드일 겨우
                    case3-2. 마지막 노드일 경우
                 */
                while(currentNode != null) {
                    if(currentNode.value == targetData && currentNode.next != null) {
                        prevNode.next = currentNode.next;
                        currentNode.next.prev = prevNode;
                        return true;
                    } else if(currentNode.value == targetData && currentNode.next == null) {
                        prevNode.next = null;
                        this.tail = prevNode;
                        return true;
                    }

                    prevNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MyDoubleLinkedList<Integer> MyLinkedList = new MyDoubleLinkedList<Integer>();

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

        MyLinkedList.remove(4); // 1 2 3 4 5
        MyLinkedList.insertToFront(1, 0); // 0 1 2 2 3 4 5
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.addNode(6); // 0 1 2 2 3 4 5  6
        MyLinkedList.printAll();
    }
}
