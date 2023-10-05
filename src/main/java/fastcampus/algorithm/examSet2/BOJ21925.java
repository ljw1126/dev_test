package fastcampus.algorithm.examSet2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 짝수 팰린드롬(골드3)
 * https://www.acmicpc.net/problem/21925
 *
 * - 팰린드롬을 우선 구함..
 * - 상향식(bottom-up으로 구할 때, dp[1] = 0 초기화 후
 *   2번 열부터 범위가 짝수인 경우에만 값을 갱신 , 이때 팰린 드롬이면 checked 처리 (전부 사용했는지 파악하기 위해서)
 * - 직접 풀이시 81%에서 시간초과 발생 => 짝수 펠린드롬 찾을 때 +2 씩 증가하도록 하니 통과함 !
 *
 * - 풀이 답안이랑 다름
 */
public class BOJ21925 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] palindrome;

    static int[] data;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        palindrome = new int[N + 1][N + 1];

        data = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        pre(); // 팰린드롬 연산

        int[] dp = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        if(palindrome[1][2] == 1) {
            dp[2] = 1;
            visited[1] = true;
            visited[2] = true;
        }

        for(int i = 4; i <= N; i += 2) { // 짝수만 비교하면 되니, 여기를 하면 log2(5000) 번으로 줄어듬
            for(int j = 1; j <= i; j += 2) { // 홀수만 구하면 되잖아 (i - j + 1) % 2 == 0 무의미
                if(palindrome[j][i] == 1) {
                    dp[i] = Math.max(dp[i], dp[j - 1] + 1);

                    for(int k = j; k <= i; k++) visited[k] = true;
                }
            }
        }

        // 결과
        boolean result = true;
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) result = false;
        }

        if(result) {
            sb.append(dp[N]);
        } else {
            sb.append("-1");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void pre() { // 팰린드롬 연산
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
    }

    static void pro() {

    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
