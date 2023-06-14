package fastcampus.algorithm.graphsearch.extend;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 텀프로젝트 (골드3) https://www.acmicpc.net/problem/9466
 *
 * 다시 풀어보기
 * O(N^2)으로 풀경우 10^10이 되어 시간초과 발생 가능
 * O(N)으로 풀어야 함
 *
 * 사이클이 발생 (자기자신 포함) 한 경우를 찾아야 됨
 * 그리고 이미 finish 완료된 경우는 안 보도록 하여 중복 탐색을 방지
 *
 * 참고
 * https://bcp0109.tistory.com/32
 *
 * 비슷한 문제
 * 2668 숫자 고르기
 * 4803 트리 (그래프 탐색)
 */
public class BOJ9466 {

    static StringBuilder sb = new StringBuilder();

    static int T, N, TEAM_COUNT;

    static int[] student;

    static boolean[] visit, finish;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        while(T > 0) {
            T -= 1;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            student = new int[N + 1];
            for(int i = 1; i <= N; i++) {
                student[i] = Integer.parseInt(st.nextToken());
            }

            visit = new boolean[N + 1];
            finish = new boolean[N + 1];
            TEAM_COUNT = 0;

            pro();
        }

        br.close();
    }

    static void dfs(int x) {
        visit[x] = true;

        int y = student[x];
        if(!visit[y]) {
            dfs(y);
        } else if(!finish[y]) {
            int cnt = 1;
            while(x != y) {
                y = student[y];
                cnt += 1;
            }

            TEAM_COUNT += cnt;
        }

        finish[x] = true;
    }

    static void pro() {
        for(int i = 1; i <= N; i++) {
            if(!visit[i]) dfs(i);
        }

        sb.append(N - TEAM_COUNT).append("\n");
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }
}
