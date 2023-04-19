package basic.data.structure;

public class MyBinaryTree {
    Node head = null;

    public class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public void insertNode(int value){
        if(this.head == null) {
            this.head = new Node(value);
        } else {
            Node node = this.head;
            while(node != null) {
                if(value < node.value) {
                    if(node.left != null) {
                        node = node.left;
                    } else {
                        node.left = new Node(value);
                        break;
                    }
                } else {
                    if(node.right != null) {
                        node = node.right;
                    } else {
                        node.right = new Node(value);
                        break;
                    }
                }
            }
        }
    }

    public Node search(int value){
        if(this.head != null) {
            Node node = this.head;
            while(node != null) {
                if(node.value == value) {
                    return node;
                } else if(value < node.value) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }

        return null;
    }

    /*
        case1. Node가 하나도 없을때
        case2-1 Node가 head 하나이고 head가 답일때
            2-2 head가 아닌경우 search 를 통해 값을 찾거나 못찾았을때
        case3 삭제할 노드가 존재하고
            3-1 삭제할 노드의 자식 노드가 없을때
            3-2 삭제할 노드의 자식 노드가 하나만 있을때
            3-3 삭제할 노드의 자식 노드가 두개 있을때
            [전략1] 삭제할 노드의 '오른쪽 자식중 가장 작은값'을 가져와 parent node와 연결하고 삭제할 노드와 연결되었던 정보를 인수인계함
            [전략2] 삭제할 노드의 '왼쪽 자식중 가장 큰 값'을 가져와 parent node와 연결하고 삭제할 노드와 연결되었던 정보를 인수인계함`
    */
    public boolean delete(int value){
        boolean existTargetNode = false;

        Node parentNode = this.head;
        Node currentNode = this.head;

        if(currentNode == null) {
            return false;
        } else  {
            if(currentNode.value == value && currentNode.left == null && currentNode.right == null) {
                this.head = null;
                return true;
            }

            while(currentNode != null) {
                if(currentNode.left != null && value < currentNode.value) {
                    parentNode = currentNode;
                    currentNode = currentNode.left;

                    if(currentNode.value == value) {
                        existTargetNode = true;
                        break;
                    }
                } else if(currentNode.right != null && currentNode.value < value) {
                    parentNode = currentNode;
                    currentNode = currentNode.right;

                    if(currentNode.value == value) {
                        existTargetNode = true;
                        break;
                    }
                }
            }
        }

        if(existTargetNode) {
            if(currentNode.left == null && currentNode.right == null) {
                if(value < parentNode.value) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            } else if(currentNode.left != null && currentNode.right == null) {
                if(value < parentNode.value) {
                    parentNode.left = currentNode.left;
                } else {
                    parentNode.right = currentNode.left;
                }
            } else if(currentNode.left == null && currentNode.right != null) {
                if(value < parentNode.value) {
                    parentNode.left = currentNode.right;
                } else {
                    parentNode.right = currentNode.right;
                }
            } else {
                // 자식이 두 개 있을때, 삭제할 노드의 오른쪽 영역에서 제일 작은 값(왼쪽값) 찾기
                Node parentChenageNode = currentNode.right;
                Node changeNode = currentNode.right; // *제일 작은 값

                while(changeNode.left != null) {
                    parentChenageNode = changeNode;
                    changeNode = changeNode.left;
                }

                // 가장 작은 왼쪽 노드를 찾았을 때
                if(changeNode.right == null) {
                    parentChenageNode.left = null;
                } else {
                    parentChenageNode.left = changeNode.right;
                }

                if(value < parentNode.value) {
                    parentNode.left = changeNode;
                } else {
                    parentNode.right = changeNode;
                }

                changeNode.left = currentNode.left;
                changeNode.right = currentNode.right;
                currentNode = null;
            }

            return true;
        }

        return false;
    }

    /*
       1. 데이터 입력
             10
          7        15
        6  8    13    18
              11 14  16 19
                      17

       2. 15 삭제
              10
          7        16
        6  8    13    18
              11 14  17  19

       3. 18, 17 삭제
               10
          7        16
        6  8    13    19
              11  14
    */
    public static void main(String[] args) {
        MyBinaryTree myTree = new MyBinaryTree();
        myTree.insertNode(10);
        myTree.insertNode(15);
        myTree.insertNode(13);
        myTree.insertNode(11);
        myTree.insertNode(14);
        myTree.insertNode(18);
        myTree.insertNode(16);
        myTree.insertNode(19);
        myTree.insertNode(17);
        myTree.insertNode(7);
        myTree.insertNode(8);
        myTree.insertNode(6);

        System.out.println("HEAD: " + myTree.head.value);
        System.out.println("HEAD LEFT: " + myTree.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree.head.right.left.value);
        System.out.println("HEAD RIGHT LEFT LEFT: " + myTree.head.right.left.left.value);
        System.out.println("HEAD RIGHT LEFT RIGHT: " + myTree.head.right.left.right.value);

        System.out.println("HEAD RIGHT RIGHT: " + myTree.head.right.right.value);
        System.out.println("HEAD RIGHT RIGHT LEFT: " + myTree.head.right.right.left.value);
        System.out.println("HEAD RIGHT RIGHT LEFT RIGHT: " + myTree.head.right.right.left.right.value);
        System.out.println("HEAD RIGHT RIGHT RIGHT: " + myTree.head.right.right.right.value);

        System.out.println(myTree.delete(15));
        System.out.println("====================15 삭제후 ");

        System.out.println("HEAD: " + myTree.head.value);
        System.out.println("HEAD LEFT: " + myTree.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree.head.right.left.value);
        System.out.println("HEAD RIGHT LEFT LEFT: " + myTree.head.right.left.left.value);
        System.out.println("HEAD RIGHT LEFT RIGHT: " + myTree.head.right.left.right.value);

        System.out.println("HEAD RIGHT RIGHT: " + myTree.head.right.right.value);
        System.out.println("HEAD RIGHT RIGHT LEFT: " + myTree.head.right.right.left.value);
        System.out.println("HEAD RIGHT RIGHT RIGHT: " + myTree.head.right.right.right.value);

        System.out.println(myTree.delete(18));
        System.out.println(myTree.delete(17));
        System.out.println("====================18, 17 삭제후 ");

        System.out.println("HEAD: " + myTree.head.value);
        System.out.println("HEAD LEFT: " + myTree.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree.head.right.left.value);
        System.out.println("HEAD RIGHT LEFT LEFT: " + myTree.head.right.left.left.value);
        System.out.println("HEAD RIGHT LEFT RIGHT: " + myTree.head.right.left.right.value);

        System.out.println("HEAD RIGHT RIGHT: " + myTree.head.right.right.value);
    }
}
