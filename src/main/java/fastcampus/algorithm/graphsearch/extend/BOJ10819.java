package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 차이를 최대로(실버2) https://www.acmicpc.net/problem/10819
 *
 * 순열 문제
 * 최대치 8! = 40320
 */
public class BOJ10819 {
    static int N, RESULT;

    static List<Integer> selected = new ArrayList<>();
    static boolean[] visit;

    static int[] arr;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        br.close();
    }

    static int cal(List<Integer> list) {
        int sum = 0;
        for(int i = 1; i < N; i++) {
            sum += (Math.abs(list.get(i - 1) - list.get(i)));
        }

        return sum;
    }

    static void dfs(int cnt) {
        if(cnt == N) {
            //System.out.println(Arrays.toString(selected.toArray()));
            RESULT = Math.max(RESULT, cal(selected));
            return;
        }

        for(int i = 0; i < N; i++) {
            if(visit[i]) continue;

            selected.add(arr[i]);
            visit[i] = true;

            dfs(cnt + 1);

            selected.remove(cnt);
            visit[i] = false;
        }
    }

    static void pro() {
        dfs(0);
        System.out.println(RESULT);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
