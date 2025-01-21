package backjun.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 스타트와 링크 - 실버 1
 * https://www.acmicpc.net/problem/14889
 *
 * - 직접 풀이
 * - used 방문 배열 만으로 처리할 경우 공간복잡도 절반이상향상됨
 */
public class BOJ14889 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n, half, result;
    private static int[][] data;

    private static void input() {
        n = inputProcessor.nextInt();
        data = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                data[i][j] = inputProcessor.nextInt();
            }
        }

        half = n / 2;
        result = Integer.MAX_VALUE;
    }

    private static void pro() {
        boolean[] used = new boolean[n + 1];
        rec(0, 1, used);

        sb.append(result);
    }

    private static void rec(int count, int start, boolean[] used) {
        if(count == half) {
            result = Math.min(result, diff(used));
            return;
        }

        for(int i = start; i <= n; i++) {
            if(used[i]) continue;

            used[i] = true;
            rec(count + 1, i + 1, used);
            used[i] = false;
        }
    }

    // 방문 배열만을 사용해서 팀을 나눌 수 있다, used 배열을 사용했으므로 1 ~ n 인덱스에 마킹
    private static int diff(boolean[] team) {
        int teamA = 0;
        int teamB = 0;
        for(int i = 1; i <= n - 1; i++) {
            for(int j = i + 1; j <= n; j++) {
                if(team[i] && team[j]) {
                    teamA += data[i][j];
                    teamA += data[j][i];
                } else if(!team[i] && !team[j]) {
                    teamB += data[i][j];
                    teamB += data[j][i];
                }
            }
        }

        return Math.abs(teamA - teamB);
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
