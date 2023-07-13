package fastcampus.algorithm.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 팰린드롬? (골드4) https://www.acmicpc.net/problem/10942
 *
 * 수열 N ( 1 ~ 2000)
 * 질문의 개수 M ( 1 ~ 10^6)
 *
 * N * N 2차원 배열 선언시 int의 경우 2000 * 2000 * 4byte = 16Mb
 *
 * 3이상의 경우 양 끝수가 동일하고, 양 끝 사이가 펠린드롬이라면 DP[i + 1][j - 1]
 * 정답 제출하고 눈치챈 게 결국 작은 문제에서 큰문제를 품
 */
public class BOJ10942 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[] A;
    static boolean[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        pro2();

        M = Integer.parseInt(br.readLine());
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(DP[s][e] ? 1 : 0).append("\n");
        }
    }
    static void pro() {
        DP = new boolean[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            DP[i][i] = true;
        }

        for(int i = 1; i < N; i++) {
            if(A[i] == A[i + 1]) DP[i][i + 1] = true;
        }

        for(int len = 3; len <= N; len++) {
            for(int i = 1; i <= (N - len + 1); i++) {
                int j = i - 1 + len;
                if(A[i] == A[j] && DP[i + 1][j - 1]) { // 양 끝 수가 동일하고, 가운데 수가 펠린드롬인 경우
                    DP[i][j] = true;
                }
            }
        }
    }

   static void pro2() { // 정상 동작
        DP = new boolean[N + 1][N + 1];

        for(int s = 1; s <= N; s++) {
            for(int e = s; e <= N; e++) {
               int left = s;
               int right = e;
               boolean flag = true;

               while(left <= right) {
                   if(A[left] != A[right]) {
                       flag = false;
                       break;
                   }

                   left += 1;
                   right -=1;
               }

               DP[s][e] = flag;
            }
        }
    }

    static int[][] _DP;

    static void inputForTopDown() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // _DP 초기화
        _DP = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(_DP[i], Integer.MAX_VALUE);
            _DP[i][i] = 1;
        }

        for(int i = 1; i < N; i++) {
            if(A[i] == A[i + 1]) _DP[i][i + 1] = 1;
            else _DP[i][i + 1] = 0;
        }

        M = Integer.parseInt(br.readLine());
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(rec(s, e)).append("\n");
        }
    }
    static int rec(int i, int j) {
        if(i == j) return _DP[i][j];
        if(_DP[i][j] != Integer.MAX_VALUE) return _DP[i][j];

        return _DP[i][j] = (A[i] == A[j] && rec(i + 1, j - 1) == 1) ? 1 : 0;
    }

    public static void main(String[] args) throws Exception {
        //상향식
        //input();
        //System.out.println(sb);

        //하향식
        inputForTopDown();
        System.out.println(sb);
    }
}
