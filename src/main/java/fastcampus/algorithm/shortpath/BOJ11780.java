package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 플로이드2(골드2)
 * https://www.acmicpc.net/problem/11780
 *
 * 플로이드 워셜 결과를 출력하고
 * 다음 줄 부터는
 * 1 * n  + 1 (1,1)로 가는 최소 비용에 포함되는 도시 개수 k와 i, j 경로를 출력한다
 *
 * 경로 수와 경로까지 출력해야 하는 문제
 * - 중간 경로를 구하는 것을 생각하지 못함
 * - 처음과 끝은 정해져 있는데, 그 사이 경로를 저장해 둔 뒤 합친다
 */
public class BOJ11780 {
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int[][] floyd;
    static int INF = 10000001;

    static List<Integer>[][] paths;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        floyd = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(floyd[i], INF);
            floyd[i][i] = 0;
        }

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            floyd[from][to] = Math.min(floyd[from][to], cost); // 중복 경로 있음
        }

        paths = new ArrayList[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                paths[i][j] = new ArrayList<>();
            }
        }
    }

    static void pro() {
        // (1,5) => (1,3) (3, 5) , 시작 [중간..] 끝 , 중간만 구하면 됨
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(floyd[i][j] > floyd[i][k] + floyd[k][j]) {
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                        tracking(i, k, j);
                    }
                }
            }
        }

        /*
        System.out.println();
        for(int i = 1; i <= n; i++) System.out.println(Arrays.toString(floyd[i]));
        */

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                sb.append(floyd[i][j] == INF ? 0 : floyd[i][j]).append(" "); // 못가는 경우가 있음
            }
            sb.append("\n");
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j || floyd[i][j] == INF) {
                    sb.append("0").append("\n");
                    continue;
                }

                int size = paths[i][j].size() + 2;
                sb.append(size).append(" ");
                sb.append(i).append(" ");

                for(int path : paths[i][j]) sb.append(path).append(" ");

                sb.append(j).append("\n");
            }
        }

        //System.out.println(sb);

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                System.out.println(paths[i][j]);
            }
        }


        System.out.println(sb);
    }

    static void tracking(int i, int k, int j) { // 중간 path 만을 구하네
        paths[i][j].clear();

        for(int path : paths[i][k]) {
            paths[i][j].add(path);
        }

        paths[i][j].add(k);

        for(int path : paths[k][j]) {
            paths[i][j].add(path);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
