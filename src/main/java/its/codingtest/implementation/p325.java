package its.codingtest.implementation;

import its.codingtest.Practice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 프로그래머스 - 열쇠와 자물쇠 (카카오)
 * - 직접 풀이
 * - 열쇠와 자물쇠의 돌기가 만나서는 안된다 (3개 틀림)
 * - 벗어나는 영역은 신경쓰지 않는다
 * - 각 행과 열 이동 범위가 -N ~ (N - 1)
 *
 * 아래 풀이에서는 자물쇠에 합산하고, 복구 하는 과정을 통해서 결과가 전부 1이면 참, 아니면 거짓 (그리고 다시 키를 빼줌)
 * https://github.com/ndb796/python-for-coding-test/blob/master/12/4.java
 */
public class p325 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro2();
        output();
    }

    // 0 : 홈부분, 1 : 돌기 부분
    private static final int[][] key = {
            {0, 0, 0},
            {1, 0, 0},
            {0, 1, 1}
    };

    private static final int[][] lock = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
    };

    private static void input() {
    }

    private static void pro() {
        int M = key[0].length; // 돌기를 찾고
        int N = lock[0].length; // 홈을 찾고

        int[][] rotated = new int[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                rotated[i][j] = key[i][j];
            }
        }

        int blanks = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(lock[i][j] == 0) { // 자물쇠의 홈을 찾는다
                    blanks += 1;
                }
            }
        }

        // 회전
        boolean result = false;
        out:
        for (int r = 0; r < 4; r++) {
            rotated = rotate(rotated, M);

            List<int[]> points = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                for(int j = 0; j < M; j++) {
                    if(rotated[i][j] == 1) { // 열쇠의 돌기 부분을 찾는다
                        points.add(new int[]{i, j});
                    }
                }
            }

            // 이동
            for (int row = -N; row < N; row++) {
                for (int col = -N; col < N; col++) {
                    if(blanks == insertKey(row, col, points, lock)) {
                        result = true;
                        break out;
                    }
                }
            }
        }

        sb.append(result);
    }

    private static int insertKey(int dx, int dy, List<int[]> points, int[][] lock) {
        int count = 0;
        int n = lock[0].length;
        boolean[][] used = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(lock[i][j] == 1) {
                    for(int[] p : points) {
                        if(p[0] + dx == i && p[1] + dy == j) return -1; // 열쇠와 자물쇠의 돌기가 만나면 안된다
                    }
                } else if(!used[i][j]){ // 홈 인 경우
                    for(int[] p : points) {
                        if(p[0] + dx == i && p[1] + dy == j) {
                            used[i][j] = true;
                            count += 1;
                        }
                    }
                }
            }
        }

        return count;
    }

    private static int[][] rotate(int[][] rotated, int m) {
        int[][] result = new int[m][m];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                result[j][m - 1 -i] = rotated[i][j];
            }
        }

        return result;
    }

    private static void pro2() {
        int M = key[0].length; // 돌기를 찾고
        int N = lock[0].length; // 홈을 찾고

        int[][] rotated = new int[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                rotated[i][j] = key[i][j];
            }
        }

        // 회전
        boolean answer = false;
        out:
        for (int r = 0; r < 4; r++) {
            rotated = rotate(rotated, M);

            // 이동
            for (int row = -N; row < N; row++) {
                for (int col = -N; col < N; col++) {
                    // 열쇠를 회전 후 이동 시킨 값을 자물쇠에 합산
                    matchKey(rotated, row, col, lock, N, M);

                    // 자물쇠가 모두 1인지 확인 (맞으면 종료)
                    if(isUnlock(lock, N)) {
                        answer = true;
                        break out;
                    }

                    removeKey(rotated, row, col, lock, N, M);
                }
            }
        }

        sb.append(answer);
    }

    private static void matchKey(int[][] rotated, int x, int y, int[][] lock, int n, int m) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                int dx = i + x;
                int dy = j + y;

                if(dx < 0 || dy < 0 || dx >= n || dy >= n) continue;

                lock[dx][dy] += rotated[i][j];
            }
        }
    }

    private static boolean isUnlock(int[][] lock, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(lock[i][j] != 1) return false;
            }
        }

        return true;
    }

    private static void removeKey(int[][] rotated, int x, int y, int[][] lock, int n, int m) {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                int dx = i + x;
                int dy = j + y;

                if(dx < 0 || dy < 0 || dx >= n || dy >= n) continue;

                lock[dx][dy] -= rotated[i][j];
            }
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
