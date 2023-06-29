package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 가장 긴 증가하는 부분 수열 4(골드4) https://www.acmicpc.net/problem/14002ㅍ
 *
 * - 동적 프로그래밍, 역추적
 */
public class BOJ14002 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] A, DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        DP = new int[N];
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        Arrays.fill(DP, 1);

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(A[i] > A[j]) DP[i] = Math.max(DP[i], DP[j] + 1);
            }
        }

        int max = Arrays.stream(DP).max().getAsInt();
        sb.append(max).append("\n");

        int cnt = max;
        Stack<Integer> stack = new Stack<>();
        for(int i = (N - 1); i >= 0; i--) {
            if(cnt < 0) break;
            if(cnt == DP[i]) {
                stack.push(A[i]);
                cnt -= 1;
            }
        }

        while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
