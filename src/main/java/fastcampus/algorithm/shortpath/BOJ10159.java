package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 저울(골드4)
 * https://www.acmicpc.net/problem/10159
 *
 * - 플로이드 워셜
 * - 직접 풀지 못함
 * - 논리 연산자로 풀이하다니 ..
 *
 * - 느리더라도 꾸준하게
 * https://steady-coding.tistory.com/100
 * - 반대 방향으로 플로이드 워셜 하나 더 구한 후 or 연산
 */
public class BOJ10159 {
    static StringBuilder sb = new StringBuilder();
    static int n, m;

    static boolean[][] visit;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1][n + 1];
        for(int i = 1; i <= n; i++) visit[i][i] = true;

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            visit[from][to] = true;
        }
    }

    static void pro() {

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(visit[i][k] && visit[k][j]) visit[i][j] = true;
                }
            }
        }

        int result;
        for(int i = 1; i <= n; i++) {
            result = 0;
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                if(!visit[i][j] && !visit[j][i]) result += 1; // NAND의 경우, 비교 불가하니 카운팅
            }
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
