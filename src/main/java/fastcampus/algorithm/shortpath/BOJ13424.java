package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 비밀 모임(골드4)
 * https://www.acmicpc.net/problem/13424
 *
 * - 직접 풀이
 * - 플로이드 워셜 기본 문제
 * - 절차적으로 풀이 가능
 */
public class BOJ13424 {
    static StringBuilder sb = new StringBuilder();

    static int T;

    static int N, M, K;

    static int[][] floyd;

    static int INF = 100001;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            // 1. 입력 처리
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 방의 개수 (노드)
            M = Integer.parseInt(st.nextToken()); // 비밀 통로의 개수 (간선)

            floyd = new int[N + 1][N + 1];
            for(int i = 1; i <= N; i++) {
                Arrays.fill(floyd[i], INF);
                floyd[i][i] = 0;
            }

            for(int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                floyd[from][to] = cost;
                floyd[to][from] = cost;
            }

            //2. 플로이드 워셜 실행
            pro();

            K = Integer.parseInt(br.readLine()); // 친구의 수
            st = new StringTokenizer(br.readLine());

            //3. 친구별 각 방에 대한 cost 합산
            int[] result = new int[N + 1];
            for(int i = 1; i <= K; i++) {
                int friend = Integer.parseInt(st.nextToken());

                for(int j = 1; j <= N; j++) {
                    result[j] += floyd[friend][j];
                }
            }

            // 4. 가장 작은 cost 가지는 방 번호(=인덱스) 구함
            int ans = Integer.MAX_VALUE;
            int cost = Integer.MAX_VALUE;
            for(int i = 1; i <= N; i++) {
                if(result[i] < cost) {
                    ans = i;
                    cost = result[i];
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void pro() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
