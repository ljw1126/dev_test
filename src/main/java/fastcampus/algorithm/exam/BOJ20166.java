package fastcampus.algorithm.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ20166 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, R;
    static int[][] domino;

    static String[][] VISIT;

    static int RESULT;

    static class User {
        int X;
        int Y;
        String D;

        public User(int X, int Y, String D) {
            this.X = X;
            this.Y = Y;
            this.D = D;
        }

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }

        public String getD() {
            return D;
        }
    }

    static List<User> USERS = new ArrayList<>();
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        R = Integer.parseInt(st.nextToken()); // 라운드 수

        domino = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                domino[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        R *= 2;
        while(R > 0) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            String D = (R % 2 == 0) ? st.nextToken() : "";

            USERS.add(new User(X, Y, D));
            R -= 1;
        }

        VISIT = new String[N + 1][M + 1];
        for(String[] v : VISIT) Arrays.fill(v, "S");
    }

    static void pro(User user) {
        if("".equals(user.D)) {
            VISIT[user.X][user.Y] = "S";
            return;
        }

        Queue<Integer> que = new LinkedList<>();
        que.add(user.X);
        que.add(user.Y);
        if(VISIT[user.X][user.Y].equals("S")) {
            VISIT[user.X][user.Y] = "F";
            RESULT += 1;
        }

        int lastX = user.X;
        int lastY = user.Y;
        int[] dir = getDirection(user.D);
        while(!que.isEmpty()) {
            int pointX = que.poll();
            int pointY = que.poll();
            int score = domino[pointX][pointY];

            if(lastX != pointX) lastX = pointX;
            if(lastY != pointY) lastY = pointY;

            for(int i = 1; i < score; i++) {
                pointX += dir[0];
                pointY += dir[1];

                if(pointX > N || pointX <= 0 || pointY > M || pointY <= 0) break;
                if(VISIT[pointX][pointY].equals("F")) continue;

                VISIT[pointX][pointY] = "F";
                que.add(pointX);
                que.add(pointY);
                RESULT += 1;
            }
        }
    }

    static int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 동남 서북

    static int[] getDirection(String D) {
        if(D.equals("E")) return DIRECTION[0];
        if(D.equals("S")) return DIRECTION[1];
        if(D.equals("W")) return DIRECTION[2];
        else return DIRECTION[3];
    }

    static void play() {
        for(User user : USERS) {
            pro(user);
        }

        sb.append(RESULT).append("\n");
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                sb.append(VISIT[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        play();
    }
}
