package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 우주 탐사선(골드3)
 * https://www.acmicpc.net/problem/17182
 *
 * - 플로이드 워셜 알고리즘
 * - 직접 풀지 못함 => 완전 탐색 생각해 놓고 겁먹지 말자 ..
 * - 문제 지문에 중복 방문 가능하다는게 함정인듯 하다
 * - 백트래킹으로 10! = 362만
 * - 플로이드 워셜 10^3 = 1000
 * - 총 시간 복잡도 = 1000 + 362만 (1초안에 가능)
 */
public class BOJ17182 {
    static StringBuilder sb = new StringBuilder();

    static int N, K;

    static int[][] floyd;

    static int RESULT;

    static boolean[] visit;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행성의 개수 (노드 수, 최대 10개)
        K = Integer.parseInt(st.nextToken()); // 출발 행성 위치

        floyd = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                floyd[i][j] = Integer.parseInt(st.nextToken()); // 가중치는 최대 1000
            }
        }

        visit = new boolean[N];
        RESULT = Integer.MAX_VALUE;
    }

    static void pro() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }

        dfs(K, 0, 0);

        System.out.println(RESULT);
    }

    static void dfs(int node, int depth, int total) {
        if(depth == N) {
            RESULT = Math.min(RESULT, total);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(visit[i]) continue;
            visit[i] = true;

            dfs(i, depth + 1, total + floyd[node][i]); // node -> i 로 이동

            visit[i] = false;
        }
    }


    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
