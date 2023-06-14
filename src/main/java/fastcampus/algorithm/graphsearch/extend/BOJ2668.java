package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 숫자 고르기(숫자5) https://www.acmicpc.net/problem/2668
 *
 * 9466 텀프로젝트와 동일한 로직으로 구함
 * 시간복잡도 : O(N)
 */
public class BOJ2668 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] NUMBERS;

    static boolean[] visit, finish;

    static List<Integer> result = new ArrayList<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        NUMBERS = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            NUMBERS[i] = Integer.parseInt(st.nextToken());
        }

        visit = new boolean[N + 1];
        finish = new boolean[N + 1];

        br.close();
    }

    static void dfs(int x) {
        visit[x] = true;

        int y = NUMBERS[x];
        if(!visit[y]) {
            dfs(y);
        } else if(!finish[y]){
            while(x != y) {
                result.add(y);
                y = NUMBERS[y];
            }
            result.add(x);
        }

        finish[x] = true;
    }

    static void pro() {
        for(int i = 1; i <= N; i++) {
            if(!visit[i]) dfs(i);
        }

        Collections.sort(result);
        sb.append(result.size()).append("\n");
        for(int i : result) sb.append(i).append("\n");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
