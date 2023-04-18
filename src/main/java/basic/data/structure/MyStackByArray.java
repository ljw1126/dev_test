package basic.data.structure;

public class MyStackByArray<T> {
    private int max; // 스택 용량
    private int ptr; // 스택 포인터
    private T[] stack;

    public static class EmptyInStackException extends RuntimeException {
        public EmptyInStackException(){}
    }

    public static class OverflowInStackException extends RuntimeException {
        public OverflowInStackException(){}
    }

    public MyStackByArray(int capacity) {
        max = capacity;
        ptr = 0;
        try {
            stack = (T[]) new Object[capacity];
        } catch (OutOfMemoryError e) {
            max = 0;
        }
    }

    // 데이터 삽입
    public void push(T element) {
        if(ptr >= max) throw new OverflowInStackException();
        stack[ptr++] = element;
    }

    // 스택에 마지막 값 꺼냄
    public T pop() {
        if(ptr <= 0) throw new EmptyInStackException();
        return stack[--ptr];
    }

    // 스택에 마지막으로 들어간 값 확인
    public T peek() {
        if(ptr <= 0) throw new EmptyInStackException();
        return stack[ptr - 1];
    }

    // 검색 메서드
    public int indexOf(T element) {
        if(ptr <= 0) throw new EmptyInStackException();

        for(int i = 0; i < ptr; i++) {
            if(element.equals(stack[i])) {
                return i;
            }
        }
        return -1;
    }

    // 스택의 모든 요소 삭제
    public void clear() {
        ptr = 0; // 포인터만 초기화
    }

    // 스택 용량 확인
    public int capacity() {
        return max;
    }

    // 스택이 비어 있는지 확인
    public boolean isEmpty() {
        return ptr <= 0;
    }

    // 스택이 가득 찼는지 확인
    public boolean isFull() {
        return ptr >= max;
    }

    // 스택 안에 모든 데이터 출력(LIFO)
    public void dump() {
        if(ptr <= 0) {
            throw new EmptyInStackException();
        } else {
            for(int i = ptr -1 ; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }
    }

    public static void main(String[] args) {
        MyStackByArray<Integer> myStackByArray = new MyStackByArray(10);
        myStackByArray.push(1);
        myStackByArray.push(2);
        System.out.println(myStackByArray.pop());
        myStackByArray.push(3);
        System.out.println(myStackByArray.pop());
        System.out.println(myStackByArray.pop());

        System.out.println("=======================");

        myStackByArray.push(6);
        myStackByArray.push(7);
        myStackByArray.push(8);
        myStackByArray.push(9);
        myStackByArray.push(10);
        System.out.println(myStackByArray.peek());
        System.out.println(myStackByArray.isEmpty());
        System.out.println(myStackByArray.indexOf(8));

        System.out.println("=======================");
        myStackByArray.dump();
        myStackByArray.clear();
        System.out.println(myStackByArray.isEmpty());
    }
}
