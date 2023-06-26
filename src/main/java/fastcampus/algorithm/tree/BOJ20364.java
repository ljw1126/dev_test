package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 부동산다툼(실버1) https://www.acmicpc.net/problem/20364
 *
 * 아이디어, 방향성 맞았음
 * - 거꾸로 부모 노드로 올라가는 방향으로 풀이 생각
 * - 그러나 최초로 만나는 노드 찾는 방법에 문제가 있었음
 *
 * 시간복잡도의 경우
 * 절반씩 줄어드니깐 log2^20 * 200,000 = 400만번 연산
 */
public class BOJ20364 {

    static StringBuilder sb = new StringBuilder();

    static int N, Q;

    static boolean[] REAL_ESTATE;

    static List<Integer> query = new ArrayList<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        REAL_ESTATE = new boolean[N + 1];

        for(int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            query.add(Integer.parseInt(st.nextToken()));
        }

        br.close();
    }

    static void dfs(int target) {
        int result = 0;
        int idx = target;

        while(idx != 0) {
            if(REAL_ESTATE[idx]) { // 제일 처음 만나는 점유땅을 찾으려면 break 하면 안됨
                result = idx;
            }

            idx /= 2;
        }

        if(result == 0) REAL_ESTATE[target] = true;

        sb.append(result).append("\n");
    }
    static void pro() {
        for(int q : query) {
            dfs(q);
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
