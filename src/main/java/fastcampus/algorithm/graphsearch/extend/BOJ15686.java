package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 치킨 배달(골드5) https://www.acmicpc.net/problem/15686
 *
 * 조합 문제
 * - 주어진 치킨 집 중에 M개 뽑는 조합 (이때 순서 의미 없음)
 * - 순열로 구하게 될 경우 중복 조합에 대한 연산 수행하여 '시간초과 발생가능' (예.(1,2) 구했는데 (2,1) 구하게 됨)
 */
public class BOJ15686 {
    static int N, M, RESULT;

    static List<Node> house = new ArrayList<>();

    static List<Node> chickenStore = new ArrayList<>();

    static int[] selected;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int value = Integer.parseInt(st.nextToken());

                if(value == 1) house.add(new Node(i, j));
                else if(value == 2) chickenStore.add(new Node(i, j));
            }
        }

        selected = new int[M]; // M개 만큼 선택
        RESULT = Integer.MAX_VALUE;

        br.close();
    }

    static void dfs(int cnt, int start) {
        if(cnt == M) {
            int totalDistance = 0;
            for(Node h : house) {
                int distance = Integer.MAX_VALUE;
                for(int idx : selected) {
                    Node c = chickenStore.get(idx);
                    distance = Math.min(distance, Math.abs(h.x - c.x) + Math.abs(h.y - c.y));
                }
                totalDistance += distance;
            }

            RESULT = Math.min(RESULT, totalDistance);

            return;
        }

        for(int i = start; i < chickenStore.size(); i++) {
            selected[cnt] = i;
            dfs(cnt + 1, i + 1);
            selected[cnt] = 0;
        }
    }

    static void pro() {
        dfs(0, 0);
        System.out.println(RESULT);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
