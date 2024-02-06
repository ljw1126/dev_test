package basic.data.structure.heap;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 데이터 추가시 마지막 인덱스에 값을 넣고 heapify(상향식)
 * 데이터 뺄 경우 root 값 빼고 마지막 인덱스 값을 root로 이동 후 heapify (하양식)
 * -- 아래는 최대힙*기준
 */
public class MyHeap {
    private List<Integer> myHeap;

    public MyHeap(int data) {
        this.myHeap = new LinkedList<>();
        this.myHeap.add(null);
        this.myHeap.add(data);
    }

    // 최대 힙 가정
    private boolean moveUp(int idx) {
        if(idx <= 1)
            return false;

        int parentIdx = idx / 2;
        return this.myHeap.get(parentIdx) < this.myHeap.get(idx);
    }

    public void insert(int data) {
        if(this.myHeap == null) {
            this.myHeap = new LinkedList<>();
            this.myHeap.add(null);
            this.myHeap.add(data);
        } else {
            this.myHeap.add(data);
            int insertIdx = this.myHeap.size() - 1;
            int parentIdx = 0;
            while(moveUp(insertIdx)) {
                parentIdx = insertIdx / 2;
                Collections.swap(this.myHeap, parentIdx, insertIdx);
                insertIdx = parentIdx;
            }
        }
    }


    private boolean moveDown(int idx) {
        int leftIdx = idx * 2;
        int rightIdx = leftIdx + 1;

        if(leftIdx >= this.myHeap.size()) { // 자식이 없는 경우
            return false;
        } else if(rightIdx >= this.myHeap.size()) { // 왼쪽 자식만 있는 경우
            if(this.myHeap.get(idx) < this.myHeap.get(leftIdx)) {
                return true;
            }
        } else { // 자식이 둘다 있는 경우
            if(this.myHeap.get(leftIdx) > this.myHeap.get(rightIdx)) { // 왼쪽이 더 큰 경우
                if(this.myHeap.get(leftIdx) > this.myHeap.get(idx)) {
                    return true;
                }
            } else {
                if(this.myHeap.get(idx) < this.myHeap.get(rightIdx)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int pop() {
        int result, popIdx, leftIdx, rightIdx;
        if(this.myHeap == null) {
            result = -1;
        } else {
            result = this.myHeap.get(1);
            int lastIdx = this.myHeap.size() - 1;
            this.myHeap.set(1, this.myHeap.get(lastIdx)); // 마지막 값을 root로 이동시킨다
            this.myHeap.remove(lastIdx);

            popIdx = 1;

            while(moveDown(popIdx)) {
                leftIdx = popIdx * 2;
                rightIdx = leftIdx + 1;

                if(leftIdx >= this.myHeap.size()) { // 왼쪽 자식만 있는 경우
                    if(this.myHeap.get(leftIdx) > this.myHeap.get(popIdx)) {
                        Collections.swap(this.myHeap, popIdx, leftIdx);
                        popIdx = leftIdx;
                    }
                } else { // 둘다 있는 경우
                    if(this.myHeap.get(leftIdx) > this.myHeap.get(rightIdx)) {
                        if(this.myHeap.get(leftIdx) > this.myHeap.get(popIdx)) {
                            Collections.swap(this.myHeap, popIdx, leftIdx);
                            popIdx = leftIdx;
                        }
                    } else {
                        if(this.myHeap.get(popIdx) < this.myHeap.get(rightIdx)) {
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
        System.out.println(myHeap.myHeap); // [null,20,10,15,5,4,8]

        System.out.println(myHeap.pop());  // 20
        System.out.println(myHeap.myHeap); // [null,15,10,8,5,4]
    }
}
