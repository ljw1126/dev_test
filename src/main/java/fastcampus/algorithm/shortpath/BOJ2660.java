package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 회장뽑기 (골드5)
 *https://www.acmicpc.net/problem/2660
 *
 * - 플로이드 워셜 기본 문제 (직접 품)
 * - 각 노드별 연결되는 최단거리는 구하는데, 각 노드별 최단 거리 값 중 최대값이 해당 노드의 회장 점수이다
 */

public class BOJ2660 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] floyd;
    static int INF = 51;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        floyd = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(floyd[i], INF);

            floyd[i][i] = 0;
        }

        String line;
        while(!"-1 -1".equals(line = br.readLine())) {
            st = new StringTokenizer(line);

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            floyd[from][to] = 1;
            floyd[to][from] = 1;
        }
    }

    static void pro() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(i == j) continue;

                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }

        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        int result = INF;
        for(int i = 1; i <= N; i++) {
            int maxScore = 0;
            for(int j = 1; j <= N; j++) {
                maxScore = Math.max(maxScore, floyd[i][j]);
            }

            resultMap.putIfAbsent(maxScore, new ArrayList<>());
            resultMap.get(maxScore).add(i);
            result = Math.min(result, maxScore);
        }

        //최소값(회장점수) 후보
        sb.append(result).append(" ").append(resultMap.get(result).size()).append("\n");

        // 후보 출력
        List<Integer> list = resultMap.get(result);
        for(int i : list) sb.append(i).append(" ");

        System.out.println(sb);

    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
