package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 광부 호석(플래티넘5)
 * https://www.acmicpc.net/problem/21279
 *
 * - 직절 풀이 못함
 * - 일단 n^2 풀이는 25 * 10^10 이라 시간초과 (가치 없음)
 * - 좌표는 최대 10^5 * 10^5 = 10^10 개가 있네
 * - 광물의 개수 N (최대50만)
 * - 호석이가 가진 돈 C (<= N)
 * - 아름다운 정도 v (최대 10^8)
 * - 최대치 노드가 50만이고 가진 돈C가 50만일때 50만개 선택 가능하고, vi가 전부 10^8 최대치 이면 => long 처리
 *
 *
 */
public class BOJ21279 {
    static StringBuilder sb = new StringBuilder();

    static int N, C, CNT;

    static long SUM;

    static int MAX = 100001;
    static List<Stone>[] X;
    static List<Stone>[] Y;

    static class Stone {
        int x;
        int y;
        int v; // 아름다운 정도

        public Stone(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }

    // 아름다움 수치가 최대가 되면서 C 이하 갯수를 선택한다..
    // 이때 너비 W, 높이 H 영역에 들어오는 모든 광물을 무조건 캐야 함
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 광물의 개수
        C = Integer.parseInt(st.nextToken()); // 호석이가 가진 돈

        X = new ArrayList[100002];
        Y = new ArrayList[100002];
        for(int i = 0; i <= 100001; i++) {
            X[i] = new ArrayList<>();
            Y[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            X[x].add(new Stone(x, y, v));
            Y[y].add(new Stone(x, y, v));
        }
    }

    static void add(int x, int y) {
        for(Stone stone : X[x]) {
            if(stone.y <= y) {
                CNT += 1;
                SUM += stone.v;
            }
        }
    }

    static void del(int y, int x) {
        for(Stone stone : Y[y]) {
            if(stone.x <= x) {
                CNT -= 1;
                SUM -= stone.v;
            }
        }
    }

    private static void pro() {
        long ans = 0L;
        int height = 100001;
        int width = -1;

        // 광석을 몇개 건드렸는지, 값어치는 얼마나 되는지 어떻게 알지????
        CNT = 0;
        SUM = 0L;
        while(width <= 100000 && height >= 0) {
            if(CNT > C) {
                height -= 1;
                del(height, width);
            } else {
                width += 1;
                add(width, height);
            }

            if(CNT <= C) {
                ans = Math.max(ans, SUM);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
