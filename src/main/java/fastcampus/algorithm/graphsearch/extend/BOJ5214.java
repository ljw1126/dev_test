package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5214 {
    static StringBuilder sb = new StringBuilder();

    static int N, K, M;

    static List<Integer>[] SUBWAY;

    static boolean[] VISIT; // 역의 거리

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 역의 수
        K = Integer.parseInt(st.nextToken()); // 하이퍼 튜브가 서로 연결하는 역의 개수
        M = Integer.parseInt(st.nextToken()); // 하이퍼 튜브 개수

        VISIT = new boolean[N + M + 2];
        SUBWAY = new ArrayList[N + M + 2];

        // 하이퍼 튜브 (0번 제외)
        for(int i = 1; i <= (N+M+1); i++) SUBWAY[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int hyperTube = N + i;
            for(int j = 1; j <= K; j++) {
                int value = Integer.parseInt(st.nextToken());
                SUBWAY[hyperTube].add(value);
                SUBWAY[value].add(hyperTube);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        que.add(0); // 초기 dist

        boolean find = false;
        while(!que.isEmpty()) {
            int x = que.poll(), dist = que.poll();
            if(x == N) {
                System.out.println((dist / 2) + 1);
                find = true;
                break;
            }

            for(int y : SUBWAY[x]) {
                if(VISIT[y]) continue;

                VISIT[y] = true;
                que.add(y);
                que.add(dist + 1);
            }
        }

        if(!find) System.out.println(-1);
    }

    static void pro() {
        bfs(1);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
