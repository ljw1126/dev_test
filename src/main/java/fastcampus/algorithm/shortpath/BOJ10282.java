package fastcampus.algorithm.shortpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 해킹 (골드4)
 * https://www.acmicpc.net/problem/10282
 *
 * n : 컴퓨터 개수
 * d : 의존성 개수
 * c : 해킹 당한 컴퓨터의 번호
 *
 * d 개 줄에 a, b, s가 주어짐
 * a가 b를 의존하고 b가 감염되면 a는 s초 후 감염된다
 * =>> b -> a (s초) 로 바꿔 생각해서 품
 *
 * -- 메모리 초과
 * 1 2 0
 * 2 1 0 이고 d[2] = 1, d[1] = 1 인 경우
 * 다익스트라 조건문에서 equal(=)하나 빠진 것 만으로 무한 루프 발생
 */
public class BOJ10282 {
    static StringBuilder sb = new StringBuilder();

    static int t;
    static int n, d, c;
    static int a, b, s;

    static int INF = 10000001;

    static List<Info>[] adj;
    static class Info implements Comparable<Info> {
        int idx;
        int timer; // 감염까지 걸리는 시간

        public Info(int idx, int timer) {
            this.idx = idx;
            this.timer = timer;
        }

        @Override
        public int compareTo(Info other) {
            return timer - other.timer; // 오름차순
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while(t > 0) {
            t -= 1;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수(노드)
            d = Integer.parseInt(st.nextToken()); // 의존성 개수(간선)
            c = Integer.parseInt(st.nextToken()); // 해킹 당한 컴퓨터 번호

            adj = new ArrayList[n + 1];
            for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            // d개 줄에 의존성 정보 주어짐, a -> b 의존
            for(int i = 1; i <= d; i++) {
                st = new StringTokenizer(br.readLine());

                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());

                adj[b].add(new Info(a, s)); //b가 감염되면 s초 후 a도 감염된다
            }

            dijkstra(c);
        }
    }

    static void dijkstra(int start) {
        Queue<Info> que = new PriorityQueue<>(); // 의미가 없네 ;
        que.add(new Info(start, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while(!que.isEmpty()) {
            Info cur = que.poll();

            if(dist[cur.idx] < cur.timer) continue;

            for(Info next : adj[cur.idx]) {
                if(dist[next.idx] <= dist[cur.idx] + next.timer) continue; // = 하나 차이로 무한루프 발생

                dist[next.idx] = dist[cur.idx] + next.timer;
                que.add(new Info(next.idx, dist[next.idx]));
            }
        }

        int cnt = 0;
        long time = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == INF) continue;

            time = Math.max(time, dist[i]);
            cnt += 1;
        }

        sb.append(cnt).append(" ").append(time).append("\n");
    }

    static void pro() {
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
