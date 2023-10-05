package fastcampus.algorithm.exam3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 공정 컨설턴트 호석(골드3)
 * https://www.acmicpc.net/problem/22254
 *
 * - 직접 풀이
 * - 1 ~ N까지 이진 탐색을 해서, 최소 라인 수를 구한다.
 * - 우선순위큐를 사용해서 limit를 안 넘는 경우 mid를 갱신하고 최소값이기때문에 R을 줄인다
 *  주문이 최대 10만건이 있고, 남은 시간 10억, 각 주문별 10억개가 소비될 때, 최소 10만건이 있어야 생산 가능
 */
public class BOJ22254 {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static long X;
    static long[] time;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());

        time = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            time[i] = Long.parseLong(st.nextToken());
        }
    }

    static class Line implements Comparable<Line> {
        int idx;
        long time;

        public Line(int idx, long time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Line o) { // 오름차순
            if(time < o.time) return -1;
            else if(time == o.time) return 0;
            return 1;
        }
    }

    static boolean possible(int lines, long limit) {
        Queue<Line> que = new PriorityQueue<>();
        for(int i = 1; i <= lines; i++) {
            que.add(new Line(i, time[i]));
        }

        int idx = lines + 1;
        while(idx <= N) {
            Line cur = que.poll();

            if(cur.time + time[idx] > limit) return false;

            cur.time += time[idx];
            que.add(cur);
            idx += 1;
        }

        return true;
    }

    static void pro() {
        int L = 1;
        int R = N;
        int ans = 0;

        while(L <= R) {
            int mid = (L + R) / 2; // 공정 라인 수

            if(possible(mid, X)) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
