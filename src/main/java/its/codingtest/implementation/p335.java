package its.codingtest.implementation;

import its.codingtest.Practice;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p335 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    // case 1
    private static final int n = 12;
    private static final int[] weak = {
            1, 5, 6, 10
    };
    private static final int[] dist = {
            1, 2, 3, 4
    };
    private static final int result = 2;

    /*
    // case 2
    private static final int n = 12;
    private static final int[] weak = {
            1, 3, 4, 9, 10
    };
    private static final int[] dist = {
            3, 5, 7
    };
    private static final int result = 1;
    */

    private static final int INF = 9; // dist가 1 ~ 8길이가 주어지므로
    private static int answer;

    private static void input() {

    }

    private static void pro() {
        answer = INF;
        Arrays.sort(dist); // 오름차순 정렬

        // 각 취약지점을 시작지점으로 해서 완전탐색 시작
        for(int i = 0; i < weak.length; i++) {
            rec(1, i, 0); // count : 점검 인원 수, position : 시작 지점, visited : 방문처리 (비트마스킹 기법)
        }

        if(answer == INF) sb.append(-1);
        else sb.append(answer);
    }

    /*

     */
    private static void rec(int count, int cur, int visited) {
        if(count > dist.length) return; // 작업자 수보다 많은 경우 탐색 종료
        if(count >= answer) return;

        // 현재 위치에서 시계방향으로 방문 가능한 취약점을 찾아 방문 처리
        for(int i = 0; i < weak.length; i++) {
            int next = (cur + i) % weak.length;
            int diff = weak[next] - weak[cur];

            if(next < cur) {
                diff += n; // weak[position] = 10 이고, weak[next] = 1 인 경우 diff = -9인데, n을 더해주면 거리가 구해짐
            }

            if(dist[dist.length - count] < diff) { // 거리 차가 작업원이 1시간 동안 처리 가능한가 (역순으로 작업자 조회)
                break;
            }

            visited |= (1 << next); // 방문 처리 (실수. i가 아니라 현재 cur 위치에서 i만큼 이동한 next를 마킹해줘야함)
        }

        // 모두 방문했다면
        if(visited == ((1 << weak.length) - 1)) {
            answer = count;
            return;
        }

        // 아직 방문을 다 하지 않은 경우
        for(int i = 0; i < weak.length; i++) {
            if((visited & (1 << i)) != 0) continue;

            rec(count + 1, i, visited); // 한명 더 추가해서 다음 weak[i]로 이동
        }
    }

    private static void output() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String result = "";

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return result;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
