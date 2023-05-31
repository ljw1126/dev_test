package fastcampus.algorithm.bruteforce.extend;

import fastcampus.algorithm.MyReader;

/**
 * 연산자 끼워넣기, 실버 1
 * https://www.acmicpc.net/problem/14888
 *
 * int 범위 : -21 억 ~ 21억
 *
 * N개 숫자 있을 때 N-1개 연산자를 순서대로 뽑아서 조합
 *
 * 중복 x, 순서
 * 시간 복잡도 : O(nPm)
 * 공간 복잡도 : O(M)
 *
 * 연산자 입력: +, -, *, / 의 개수이다
 */
public class BOJ14888 {

    static int N, max, min;

    static int[] nums, operators, order;
    static StringBuilder sb = new StringBuilder();

    /*
    // SUCCESS
    static void input() {
        MyReader scan = new MyReader();
        N = scan.nextInt();
        nums = new int[N];

        for(int i = 0; i < N; i++) nums[i] = scan.nextInt();

        operators = new int[4];
        for(int i = 0; i < 4; i++) operators[i] = scan.nextInt();

        order = new int[N - 1];

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    static void recFunc(int k) {
        if(k == N) {
            int value = nums[0];
            for(int i = 1; i < N; i++) {
                value = cal(order[i - 1], value, nums[i]);
            }

            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(operators[i] == 0) continue;

            order[k - 1] = i;
            operators[i] -= 1;

            recFunc(k + 1);

            order[k - 1] = 0;
            operators[i] += 1;
        }
    }

    static int cal(int op, int operand1, int operand2) {
        if(op == 0) {
            return operand1 + operand2;
        } else if(op == 1) {
            return operand1 - operand2;
        } else if(op == 2) {
            return operand1 * operand2;
        } else {
            return operand1 / operand2;
        }
    }
    */

    // 강의 해설
    static void input() {
        MyReader scan = new MyReader();
        N = scan.nextInt();

        nums = new int[N + 1];
        operators = new int[5]; // 0번 인덱스는 비워둠
        order = new int[N + 1];

        for(int i = 1; i <= N; i++) nums[i] = scan.nextInt();
        for(int i = 1; i <= 4; i++) operators[i] = scan.nextInt();

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    static int calculator() {
        int result = nums[1];

        for(int i = 1; i <= N-1; i++) {
            if(order[i] == 1) { // 더하기
                result += nums[i + 1];
            } else if(order[i] == 2) { // 빼기
                result -= nums[i + 1];
            } else if(order[i] == 3) { // 곱
                result *= nums[i + 1];
            } else { // 나누기
                result /= nums[i + 1];
            }
        }

        return result;
    }

    static void recFunc(int k) { // 매개변수에 연산 결과 넘겨주는 방식으로도 풀이 가능
        if(k == N) {
            int value = calculator();
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        for(int op = 1; op <= 4; op++) {
            if(operators[op] == 0) continue;

            order[k] = op;
            operators[op] -= 1;

            recFunc(k + 1);

            order[k] = 0;
            operators[op] += 1;
        }
    }

    static int calculator(int operand1, int op, int operand2) {
        if(op == 1) {
            return operand1 + operand2;
        } else if(op == 2) {
            return operand1 - operand2;
        } else if(op == 3) {
            return operand1 * operand2;
        } else {
            return operand1 / operand2;
        }
    }


    static void recFunc(int k, int value) { // 초기 nums[1]
        if(k == N) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        for(int op = 1; op <= 4; op++) {
            if(operators[op] == 0) continue;

            operators[op] -= 1;

            int newValue = calculator(value, op, nums[k + 1]);
            recFunc(k + 1, newValue);

            operators[op] += 1;
        }
    }

    public static void main(String[] args) {
        input();

        recFunc(1, nums[1]);
        System.out.println(max);
        System.out.println(min);
    }

}
