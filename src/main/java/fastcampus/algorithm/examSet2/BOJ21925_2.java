package fastcampus.algorithm.examSet2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 짝수 팰린드롬(골드3)
 * https://www.acmicpc.net/problem/21925
 *
 * - 직접 풀이했는데, 강의 답안 풀이로 풀어보기
 * - 그리드 알고리즘
 * - 핵심 아이디어 : 원소를 하나씩 확인함, "해당 위치에서 시작하는" 최소 크기의 짝수 팰린드롬을 찾으면 됨
 * - 큰 크기의 팰린드롬은 작은 크기의 팰린드롬으로 구성됨
 * - 그리고 모든 원소는 짝수 팰린드롬으로 포함되어야 하므로, 해당 위치에서 짝수 팰린드롬이 만들어지지 않으면 종료함
 * // 약간 투포인터랑 비슷한 느낌
 *
 * 사전에 palindrome을 미리 구하고 할 경우 100MB 정도 잡고 시작함(5000*5000*4byte)
 * 그리드 방식으로 반만 비교하도록 하면 메모리도 덜 잡아 먹고, 훨씬 빨리되는 것을 확인함
 */
public class BOJ21925_2 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] data;

    static int[][] palindrome;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        data = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        /*
        100MB 잡고 시작해버리네
        palindrome = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) palindrome[i][i] = 1;

        for(int i = 1; i < N; i++) {
            if(data[i] == data[i + 1]) palindrome[i][i + 1] = 1;
        }

        for(int len = 3; len <= N; len++) {
            for(int i = 1; i <= (N - len + 1); i++) {
                int j = i + len - 1;
                if(data[i] == data[j] && palindrome[i + 1][j - 1] == 1) {
                    palindrome[i][j] = 1;
                }
            }
        }
         */
    }

    static boolean isEvenPalindrome(int i, int j) {
        if(j - i + 1 % 2 == 1) return false;
        //if(palindrome[i][j] == 0) return false;
        for(int k = 0; k <= (j - i) / 2; k++) {
            if(data[i + k] != data[j - k]) return false;
        }

        return true;
    }

    static void pro() throws IOException {
        int result = 0;
        int i = 1;
        int j = 0;
        while(i < N) {
            boolean find = false;
            for(j = i + 1; j <= N; j += 2) { // j <= N까지 해야 i = 9일때 j = 10 비교 가능
                if(isEvenPalindrome(i, j)) {
                    find = true;
                    result += 1;
                    break;
                }
            }

            // 해당 위치에서 짝수 팰린드롬을 만들 수 없는 경우
            if(!find) {
                result = -1;
                break;
            }

            i = j + 1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
