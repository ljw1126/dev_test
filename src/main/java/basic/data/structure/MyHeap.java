package basic.data.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
    부모 노드 = 자식노드 / 2;
    왼쪽 자식 노드 = 부모노드 * 2;
    오른쪽 자식 노드 = 부모노드 * 2 + 1;

    1번 인덱스를 루트로 가정 (0번은 null 처리, 편의 위해, 배열을 손으로 그려보면 조건 이해됨)
    List로 처리하다보니 left, right index 비교하면서 heapify 해야 함
 */
public class MyHeap {
    private List<Integer> myHeap; // 최대 힙

    public MyHeap(int data) {
        myHeap = new ArrayList<>();
        myHeap.add(null);
        myHeap.add(data);
    }


    // insert시 마지막 인덱스에 넣고 heapify 하기 위한 함수
    public boolean move_up(int idx){
        if(idx <= 1) return false;

        int parentIdx = idx / 2;
        return this.myHeap.get(parentIdx) < this.myHeap.get(idx);
    }

    // 마지막 인덱스에 넣고, heapify
    public void insert(int data){
        if(this.myHeap == null) {
            myHeap = new ArrayList<>();
            myHeap.add(null);
            myHeap.add(data);
        } else {
            this.myHeap.add(data);
            int insertIdx = myHeap.size() - 1;
            int parentIdx = 0;

            while(move_up(insertIdx)) {
                parentIdx = insertIdx / 2;
                Collections.swap(this.myHeap, insertIdx, parentIdx);
                insertIdx = parentIdx;
            }
        }
    }
    /*
       pop() 할 경우 마지막 인덱스 값을 root에 올리고 heapify 하기 위한 함수 -- hard

       case1. 둘 다 없을 때 (완전 이진 트리는 왼쪽부터 채워지므로 오른쪽에만 데이터 있을 수 없음)
       case2. 왼쪽만 있을 때
       case3. 자식 노드 두 개다 있을 때
    */
    public boolean move_down(int idx){
        int leftChildIdx = idx * 2;
        int rightChildIdx = leftChildIdx + 1;

        if(leftChildIdx >= this.myHeap.size()) {
            return false;
        } else if(rightChildIdx >= this.myHeap.size()) {
            if(this.myHeap.get(idx) < this.myHeap.get(leftChildIdx)) {
                return true;
            }
        } else {
            if(this.myHeap.get(leftChildIdx) > this.myHeap.get(rightChildIdx)) {
                if(this.myHeap.get(leftChildIdx) > this.myHeap.get(idx)) {
                    return true;
                }
            } else {
                if(this.myHeap.get(idx) < this.myHeap.get(rightChildIdx)) {
                    return true;
                }
            }
        }

        return false;
    }

    // root 값을 꺼내고, 마지막 index 값을 root 위치에 올린 후 heapify
    public int pop(){
        int result, popIdx, leftIdx, rightIdx;

        if(this.myHeap == null) {
            result = -1;
        } else {
            result = this.myHeap.get(1);
            int lastIdx = this.myHeap.size() - 1;
            this.myHeap.set(1, this.myHeap.get(lastIdx)); // 1 : root
            this.myHeap.remove(lastIdx);

            popIdx = 1;

            while(this.move_down(popIdx)) {
                leftIdx = popIdx * 2;
                rightIdx = leftIdx + 1;

                //  왼쪽 자식만 있을 경우
                if(rightIdx >= this.myHeap.size()) {
                    if(this.myHeap.get(popIdx) < this.myHeap.get(leftIdx)) {
                        Collections.swap(this.myHeap, popIdx, leftIdx);
                        popIdx = leftIdx;
                    }
                } else {
                    // 자식이 둘 다 있을 경우
                    if(this.myHeap.get(leftIdx) > this.myHeap.get(rightIdx)) {
                        if(this.myHeap.get(leftIdx) > this.myHeap.get(popIdx)) {
                            Collections.swap(this.myHeap, leftIdx, popIdx);
                            popIdx = leftIdx;
                        }
                    } else {
                        if (this.myHeap.get(popIdx) < this.myHeap.get(rightIdx)) {
                            Collections.swap(this.myHeap, popIdx, rightIdx);
                            popIdx = rightIdx;
                        }
                    }
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(15);
        myHeap.insert(10);
        myHeap.insert(8);
        myHeap.insert(5);
        myHeap.insert(4);
        myHeap.insert(20);
        System.out.println(myHeap.myHeap); // [null,20,10,15,5,4]

        System.out.println(myHeap.pop());  // 20
        System.out.println(myHeap.myHeap); // [null,15,10,8,5,4]
    }

}
