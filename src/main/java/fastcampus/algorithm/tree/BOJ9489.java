package fastcampus.algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사촌(골드4) https://www.acmicpc.net/problem/9489
 *
 * - 부모노드가 같지 않고, 부모노드의 조상노드가 같다면 형제다 (포인트)
 * - 참고 https://velog.io/@tunaman95/%EB%B0%B1%EC%A4%80-9489%EB%B2%88-%EC%82%AC%EC%B4%8C-Python-Java
 * - 처응에 parent 와 depth로 구해서 풀었는데 틀렸다함
 * DATA [0, 1, 3, 4, 5, 8, 9, 15, 30, 31, 32]
 * PARENT [-1, 0, 1, 1, 1, 2, 2, 3, 4, 4, 4]
 *
 * DATA [0, 3, 5, 6, 8, 9, 10, 13, 15, 16, 22, 23, 25]
 * PARENT [-1, 0, 1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 6]
 *
 * DATA [0, 1, 3, 4, 5, 8, 9, 15, 30, 31, 32]
 * PARENT [-1, 0, 1, 1, 1, 2, 2, 3, 4, 4, 4]
 */
public class BOJ9489 {
    static StringBuilder sb = new StringBuilder();

    static int N, K, TARGET_IDX;

    static int[] DATA, PARENT;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = "";
        while(true) {
            input = br.readLine();
            if("0 0".equals(input)) break;

            st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken()); // 노드의 수
            K = Integer.parseInt(st.nextToken()); // 사촌의 수

            st = new StringTokenizer(br.readLine());
            DATA = new int[N + 1];
            PARENT = new int[N + 1];

            PARENT[0] = -1;

            int parentIdx = 0;
            for(int i = 1; i <= N; i++) {
                DATA[i] = Integer.parseInt(st.nextToken());
                if(DATA[i] == K) TARGET_IDX = i;
                if(i > 1 && DATA[i] - DATA[i - 1] != 1) parentIdx += 1;

                PARENT[i] = parentIdx;
            }

            pro();
        }
    }
    static void pro() { // K의 사촌을 찾으려함
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            if(PARENT[i] != PARENT[TARGET_IDX] && PARENT[PARENT[i]] == PARENT[PARENT[TARGET_IDX]]) cnt += 1;
        }

        sb.append(cnt).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
