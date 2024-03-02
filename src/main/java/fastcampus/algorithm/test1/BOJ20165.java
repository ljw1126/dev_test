package fastcampus.algorithm.test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/**
 * 인내의 도미노 장인 호석 (골5)
 * https://www.acmicpc.net/problem/20165
 *
 * 최대치 (러프하게)
 * 100 * 100 * 5 = 50,000
 *
 * - height < BOARD[dx][dy] 비교 들어간데 틀렸음
 * - 3이라는게 3개를 쓰러띠린다는거지 높이 값이 문제가 아니었음
 */
public class BOJ20165 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String NEW_LINE = System.lineSeparator();
    static String BLANK = " ";
    static String F = "F";
    static String S = "S";


    static int N, M, R;
    static int[][] BOARD;
    static boolean[][] STATUS;
    static int SCORE;

    // 동서남북
    static int[][] DIR = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void pro() {
        while(R > 0) {
            int ackX = inputProcessor.nextInt();
            int ackY = inputProcessor.nextInt();
            String dir = inputProcessor.next();
            attack(ackX, ackY, getDir(dir));

            int defX = inputProcessor.nextInt();
            int defY = inputProcessor.nextInt();
            defence(defX, defY);

            R -= 1;
        }

        sb.append(SCORE).append(NEW_LINE);
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                sb.append(STATUS[i][j] ? F : S).append(BLANK);
            }
            sb.append(NEW_LINE);
        }
    }

    private static int[] getDir(String dir) {
        switch (dir) {
            case "E" : return DIR[0];
            case "W" : return DIR[1];
            case "S" : return DIR[2];
            case "N" : return DIR[3];
            default: throw new IllegalArgumentException();
        }
    }

    private static class Domino {
        int x;
        int y;
        int value;

        public Domino(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    private static void attack(int x, int y, int[] dir) {
        if(STATUS[x][y]) return;

        Deque<Domino> que = new ArrayDeque<>();
        que.add(new Domino(x, y, BOARD[x][y]));
        STATUS[x][y] = true;
        SCORE += 1;

        while(!que.isEmpty()) {
            Domino domino = que.poll();

            int dx = domino.x + dir[0];
            int dy = domino.y + dir[1];
            int height = domino.value - 1;

            if(dx < 1 || dy < 1 || dx > N || dy > M) continue;
            if(height < 1) continue;

            // height < BOARD[dx][dy] 틀림, 높이 대소 비교가 아니라 3이면 3개까지 쓰러뜨린다는 말이었네
            // 그래서 heigth > 1이상이면 그냥 도미노 넘어뜨리는 거고

            if(!STATUS[dx][dy] && height > 0) { // 넘어지지 않았고, height 가 0이상인 경우 처리를 해줘야 정상처리됨
                height = Math.max(height, BOARD[dx][dy]);
                STATUS[dx][dy] = true;
                SCORE += 1;
            }

            que.add(new Domino(dx, dy, height));
        }
    }

    private static void defence(int x, int y) {
        if(STATUS[x][y]) {
            STATUS[x][y] = false;
        }
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 행
        M = inputProcessor.nextInt(); // 열
        R = inputProcessor.nextInt(); // 라운드 횟수

        BOARD = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                BOARD[i][j] = inputProcessor.nextInt();
            }
        }

        STATUS = new boolean[N + 1][M + 1];
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
