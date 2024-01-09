package fastcampus.algorithm.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ {
    static StringBuilder sb = new StringBuilder();

    static int N, M, K;

    static int[][] DIRECTION = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
    };

    static char[][] DATA;

    static String[][] DP;

    static List<String> query = new ArrayList<>();

    static Map<String, Integer> wordMap = new HashMap<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        DATA = new char[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] txt = st.nextToken().toCharArray();
            for(int j = 1; j <= M; j++) {
                DATA[i][j] = txt[j - 1];
            }
        }

        for(int i = 1; i <= K; i++) {
            query.add(br.readLine());
        }

        DP = new String[N + 1][M + 1];
    }

    static int getX(int fromX, int toX) {
        int result = fromX + toX;
        if(result < 1) result = N;
        else if(result > N) result = 1;

        return result;
    }

    static int getY(int fromY, int toY) {
        int result = fromY + toY;
        if(result < 1) result = M;
        else if(result > M) result = 1;

        return result;
    }

    // 시간초과 극복..
    static void rec(int x, int y, String txt, int len) {
        if(wordMap.containsKey(txt)) {
            wordMap.put(txt, wordMap.get(txt) + 1);
        } else {
            wordMap.put(txt, 1);
        }

        if(len == 5) return;

        for(int i = 0; i < 8; i++) {
            int dx = getX(x, DIRECTION[i][0]);
            int dy = getY(y, DIRECTION[i][1]);

            rec(dx, dy, txt + DATA[dx][dy], len + 1);
        }

    }

    static void pro() {

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                rec(i, j, DATA[i][j] + "", 1);
            }
        }

        for(String q : query) {
            sb.append(wordMap.getOrDefault(q, 0)).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
