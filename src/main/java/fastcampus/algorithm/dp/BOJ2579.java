package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 계산 오르기 (실버3) https://www.acmicpc.net/problem/2579
 *
 * 처음 점화식 문제 풀이로 상향식으로 풀이 가능하나
 * 2차원 배열로 풀 경우 backtracking 까지 생각해 볼 수 있으므로 풀어 볼 수 있도록 하자
 *
 * - 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다.
 * - 연속된 세 계단을 밟을 수 는 없다. (단 시작점은 계단에 포함되지 않는다.)
 * - 마지막 도착 계단은 반드시 밟아야 한다.
 */
public class BOJ2579 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] A;

    static int[][] DP;

    static Node[][] _DP;

    static class Node {
        int value;
        int prevX;
        int prevY;

        public Node(int value, int prevX, int prevY) {
            this.value = value;
            this.prevX = prevX;
            this.prevY = prevY;
        }
    }

    static void inputForBottomUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        DP = new int[N + 1][2]; // 0 의 경우 이전 계단을 밟지 않은 경우, 이전 계단을 밟은 경우
    }
    static void executeByBottomUp() {
        DP[1][0] = A[1];

        if(N >= 2) {
            DP[2][0] = A[2];
            DP[2][1] = A[1] + A[2];
        }

        for(int i = 3; i <= N; i++) {
            DP[i][0] = Math.max(DP[i - 2][0], DP[i - 2][1]) + A[i];
            DP[i][1] = DP[i - 1][0] + A[i];
        }

        System.out.println(Math.max(DP[N][0], DP[N][1]));
    }

    static void executeByBottomUpForBacktracking() {
        _DP = new Node[N + 1][2];
        _DP[1][0] = new Node(A[1], 0, 0);
        _DP[1][1] = new Node(0, 0, 0);

        if(N >= 2) {
            _DP[2][0] = new Node(A[2], 0, 0);
            _DP[2][1] = new Node(A[1] + A[2], 1, 0);
        }

        for(int i = 3; i <= N; i++) {
            _DP[i][0] = _DP[i - 2][0].value > _DP[i - 2][1].value
                    ? new Node(_DP[i - 2][0].value + A[i], i - 2, 0)
                    : new Node(_DP[i - 2][1].value + A[i], i - 2, 1);

            _DP[i][1] = new Node(_DP[i - 1][0].value + A[i], i - 1, 0);
        }

        Node result = _DP[N][0].value > _DP[N][1].value ? _DP[N][0] : _DP[N][1];
        System.out.println(result.value);

        Stack<Integer> backtracking = new Stack<>();
        backtracking.push(A[N]);
        while(result != null) {
            backtracking.push(A[result.prevX]);
            result = _DP[result.prevX][result.prevY];
        }

        while(!backtracking.isEmpty()) {
            sb.append(backtracking.pop()).append(" ");
        }

        System.out.println(sb.toString()); // 0 10 20 25 20
    }

    static int[][] COME;
    static void executeBacktracking2() { // COME 이라는 기록 배열과 함께 backtraking 수행
        COME = new int[N + 1][2];
        DP = new int[N + 1][2];

        //초기화
        DP[1][0] = A[1];
        DP[1][1] = 0;

        COME[0][0] = -1;
        COME[0][1] = -1;

        COME[1][0] = 0;

        if(N >= 2) {
            DP[2][0] = A[2];
            DP[2][1] = A[1] + A[2];

            COME[2][0] = 0; // [i - 2][0] 뜻 함
            COME[2][1] = 0; // [i - 1][0] 뜻 함
        }

        for(int i = 3; i <= N; i++) {
            int max = DP[i - 2][0] > DP[i - 2][1] ? 0 : 1;

            DP[i][0] = DP[i - 2][max] + A[i]; // i - 1을 밟지 않는 경우
            DP[i][1] = DP[i - 1][0] + A[i]; // i - 1을 밟는 경우

            COME[i][0] = max; // [i - 2][max]
            COME[i][1] = 0; // [i - 1][0];
        }

        int maxIdx = DP[N][0] > DP[N][1] ? 0 : 1;
        System.out.println(DP[N][maxIdx]);


        int idx1 = N;
        int idx2 = maxIdx;
        int prevIdx = -1; // 없으니깐 -1 에러 출력
        Stack<Integer> backtracking = new Stack<>();
        while(idx1 != -1) {
            backtracking.push(A[idx1]);

            prevIdx = COME[idx1][idx2];
            idx1 = idx2 == 0 ? idx1 - 2 : idx1 - 1;
            idx2 = prevIdx;
        }

        while(!backtracking.isEmpty()) sb.append(backtracking.pop()).append(" ");


        System.out.println(sb);
    }


    static void inputForTopDown() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        DP = new int[N + 1][2]; // 0 의 경우 이전 계단을 밟지 않은 경우, 이전 계단을 밟은 경우
        for(int i = 0; i <= N; i++) Arrays.fill(DP[i], -1);

        DP[1][0] = A[1];
        DP[1][1] = 0;

        if(N >= 2) {
            DP[2][0] = A[2];
            DP[2][1] = A[1] + A[2];
        }

        int result = Math.max(executeByTopDown(N, 0), executeByTopDown(N, 1));
        System.out.println(result);

        for(int i = 0; i <= N; i++) System.out.println(Arrays.toString(DP[i]));
    }

    static int executeByTopDown(int depth, int num) {
        if(depth == 1 || depth == 2) return DP[depth][num];

        if(DP[depth][num] != -1) return DP[depth][num];

        int value = 0;
        if(num == 0) {
            value = Math.max(executeByTopDown(depth - 2, 0), executeByTopDown(depth - 2, 1)) + A[depth];
        } else if(num == 1) {
            value = executeByTopDown(depth - 1, 0)  + A[depth];
        }

        return DP[depth][num] = value;
    }

    public static void main(String[] args) throws Exception {
        //inputForBottomUp();
        //executeByBottomUp();

        //inputForTopDown();

        // backtracking
        inputForBottomUp();
        //executeByBottomUpForBacktracking();
        executeBacktracking2();
    }


}
