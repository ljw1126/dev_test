package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 회사 문화 (골드4) https://www.acmicpc.net/problem/14267
 *
 * - 심플한 방식을 생각하지 못함
 * - 같은 직원에게 여러번 칭찬 할 수 있다.
 */
public class BOJ14267 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[] SCORE;

    static List<Integer>[] employees;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        employees = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) employees[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int node = 1; node <= N; node++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1) continue;

            employees[parent].add(node);
        }

        SCORE = new int[N + 1];
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int _i = Integer.parseInt(st.nextToken());
            int _w = Integer.parseInt(st.nextToken());

            SCORE[_i] += _w;
        }
    }

    static void dfs(int x) {
        for(int y : employees[x]) {
            SCORE[y] += SCORE[x];
            dfs(y);
        }
    }

    static void pro() {
        dfs(1);

        for(int i = 1; i <= N; i++) sb.append(SCORE[i]).append(" ");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
