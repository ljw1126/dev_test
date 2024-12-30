package its.codingtest.implementation;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 기둥과 보 설치 - 프로그래머스(카카오2020)
 *
 * - 직접 풀이 못함
 * - 삭제 조건이 제대로 동작하지 않아 어려웠음
 * - 그리고 x, y 범위 검사 때문에 더 복잡도가 올랐는데, 배열 크기를 n + 2로 생성하면 수월하게 조건식 작성 가능했음
 * - 삭제의 경우, 기둥이나 보를 각각 false 처리 후 설치되어 있는 다른 구조물에 대해서 유효성 검사를 다시 실행하는게 훨씬 간편했음
 * - 이때 검사 범위는 x - 1 ~ x + 1, y - 1 ~ y + 1 (이때, 최소 0, 최대 n)
 */
public class p329 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    private static final int N = 5;
    private static final int[][] BUILD_FRAME = {
            {1, 0, 0, 1},
            {1, 1, 1, 1},
            {2, 1, 0, 1},
            {2, 2, 1, 1},
            {5, 0, 0, 1},
            {5, 1, 0, 1},
            {4, 2, 1, 1},
            {3, 2, 1, 1}
    };
/*
    private static final int N = 5;
    private static final int[][] BUILD_FRAME = {
            {0,0,0,1},
            {2,0,0,1},
            {4,0,0,1},
            {0,1,1,1},
            {1,1,1,1},
            {2,1,1,1},
            {3,1,1,1},
            {2,0,0,0},
            {1,1,1,0},
            {2,2,0,1}
    };*/

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static void input() {

    }

    private static void pro() {
        Result base = new Result(N);

        for(int[] frame : BUILD_FRAME) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2]; // 0: 기둥, 1: 보
            int b = frame[3]; // 0: 삭제, 1: 설치

            base.process(x, y, a, b);
        }


        int[][] answer = base.getResult();

        for(int[] a : answer) {
            System.out.println(Arrays.toString(a));
        }
    }

    private static class Result {
        private final boolean[][] pole;
        private final boolean[][] bar;
        private final int n;
        private int count = 0;

        public Result(int n) {
            this(new boolean[n + 2][n + 2], new boolean[n + 2][n + 2], n);
        }

        public Result(boolean[][] pole, boolean[][] bar, int n) {
            this.pole = pole;
            this.bar = bar;
            this.n = n;
        }

        public void process(int x, int y, int a, int b) {
            if(a == 0 && b == 1) {
                buildPole(x, y);
            } else if(a == 0 && b == 0) {
                deletePole(x, y);
            } else if(a == 1 && b == 1) {
                buildBar(x, y);
            } else if(a == 1 && b == 0) {
                deleteBar(x, y);
            }
        }

        //바닥이거나, 아래에 기둥이 있다면, 왼쪽에 보가 있다면, 오른쪽에 보가 있다면
        private void buildPole(int x, int y) {
            if(checkPole(x, y)) {
                pole[x][y] = true;
                count += 1;
            }
        }

        private boolean checkPole(int x, int y) {
            return y == 0
                    || (0 < y && pole[x][y - 1])
                    || (0 < x && bar[x - 1][y])
                    || bar[x][y];
        }

        // 위에 기둥이 있는 경우, x == 0 (n)에 기둥이 있을대 , 양옆에 기둥없이 보가 있을대
        private void deletePole(int x, int y) {
            pole[x][y] = false;
            if(canDelete(x, y)) {
                count -= 1;
            } else {
                pole[x][y] = true;
            }
        }

        // 왼쪽 아래 기둥이 있거나, 오른쪽 아래에 기둥이 있다면, 양옆에 보가 있다면
        private void buildBar(int x, int y) {
            if(checkBar(x, y)) {
                bar[x][y] = true;
                count += 1;
            }
        }

        // 왼쪽, 오른쪽에 기둥이 있거나, 양옆에 보가 있는 경우
        private boolean checkBar(int x, int y) {
            return (0 < y && pole[x][y - 1])
                    || (0 < y && pole[x + 1][y - 1])
                    || (0 < x && bar[x - 1][y] && bar[x + 1][y]);
        }

        private void deleteBar(int x, int y) {
            bar[x][y] = false;
            if(canDelete(x, y)) {
                count -= 1;
            } else {
                bar[x][y] = true;
            }
        }

        // 기둥이든 보든 삭제 처리 후에 기존 설치 가능한 구조물에 대해서 다시 검사 수행하는게 깔끔함
        private boolean canDelete(int x, int y) {
            int minX = Math.max(0, x - 1);
            int minY = Math.max(0, y - 1);
            // 원래 설치 할 수 있었던게 설치하지 못하는지 확인
            for(int i = minX; i <= x + 1; i++) {
                for(int j = minY; j <= y + 1; j++) {
                    if(pole[i][j] && !checkPole(i, j)) {
                        return false;
                    }

                    if(bar[i][j] && !checkBar(i, j)) {
                        return false;
                    }
                }
            }

            return true;
        }

        public int[][] getResult() {
            int[][] result = new int[count][3];
            int idx = 0;
            for(int i = 0; i <= n; i++) {
                for(int j = 0; j <= n; j++) {
                    if(pole[i][j]) {
                        result[idx++] = new int[] {i, j, 0};
                    }

                    if(bar[i][j]) {
                        result[idx++] = new int[] {i, j, 1};
                    }
                }
            }
            return result;
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
