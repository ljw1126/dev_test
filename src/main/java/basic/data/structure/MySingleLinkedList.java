package basic.data.structure;

public class MySingleLinkedList<T> {
    // head 부터 순차적으로 노드 검색, 마지막 노드의 next = null
    public Node<T> head = null;

    public class Node<T>{
        T value;
        Node<T> next;

        public Node(T t) {
            this.value = t;
            this.next = null;
        }
    }

    //노드 추가
    public void addNode(T data){
        if(this.head == null) {
            this.head = new Node<>(data);
        } else {
            Node<T> node = this.head;

            while(node.next != null) {
                node = node.next;
            }

            node.next = new Node(data);
        }
    }

    // 전체 노드 출력
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

    //해당 데이터 가진 노드 리턴
    public Node<T> search(T data){
        Node<T> result = null;

        if(this.head != null) {
            Node<T> node = this.head;
            while(node != null) {
                if(node.value == data) {
                    result = node;
                    break;
                } else {
                    node = node.next;
                }
            }
        }

        return result;
    }

    // 찾은 노드 다음에 삽입
    public void addNodeInside(T newData, T searchData){
        Node<T> searchNode = search(searchData);
        if(searchNode == null) {
            addNode(newData);
        } else {
            Node<T> searchNextNode = searchNode.next;
            searchNode.next = new Node<>(newData);
            searchNode.next.next = searchNextNode;
        }
    }

    public boolean delNode(T targetData){ // * 여기 틀림

        if(this.head == null) {
            return false;
        } else {
            Node<T> node = this.head;
            if(node.value == targetData) {
                this.head = node.next;
                return true;
            } else {
                while(node.next != null) {
                    if(node.next.value == targetData) {
                        node.next = node.next.next;
                        return true;
                    }

                    node = node.next;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MySingleLinkedList<Integer> mySingleLinkedList = new MySingleLinkedList();

        mySingleLinkedList.addNode(1);
        mySingleLinkedList.addNode(2);
        mySingleLinkedList.addNode(3);
        mySingleLinkedList.addNode(4);
        mySingleLinkedList.addNode(5);

        mySingleLinkedList.delNode(3);
        mySingleLinkedList.addNodeInside(7, 4);

        mySingleLinkedList.printAll();

    }
}
