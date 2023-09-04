package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 끝나지 않는 파티 (골드5)
 *
 * - 직접 풀이
 * - 플로이드 워셜 기본 문제
 * - 최대치 Integer 범위내 = 500 * 1,000,000 = 5억
 * - 각 노드별 최단 경로 구하고 시간 내에 이동 가능 여부에 따라 출력 구분
 */
public class BOJ11265 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int A, B, C;

    static int[][] floyd;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 파티장 크기(노드 수)
        M = Integer.parseInt(st.nextToken()); // 서비스 요청 손님 수(쿼리 수)

        floyd = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                floyd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pro();

        while(M > 0) {
            M -= 1;

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(floyd[A][B] <= C) sb.append("Enjoy other party").append("\n");
            else sb.append("Stay here").append("\n");
        }

        System.out.println(sb);
    }

    static void pro() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }

        for(int i = 1; i <= N; i++) System.out.println(Arrays.toString(floyd[i]));
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
