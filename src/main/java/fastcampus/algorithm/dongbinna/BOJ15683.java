package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 재귀 방식으로 풀이했는데, 나보다 4배는 덜 메모리 사용
// https://www.acmicpc.net/source/77533146
public class BOJ15683 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final int MIN_CCTV = 1;
    private static final int MAX_CCTV = 5;


    private static int N, M, RESULT;
    private static int[][] ROOM, COPIED;
    private static List<Cctv> CCTVS = new ArrayList<>();
    private static int[] ROTATE;

    private static class Cctv {
        private int x;
        private int y;
        private int no;

        public Cctv(int x, int y, int no) {
            this.x = x;
            this.y = y;
            this.no = no;
        }
    }


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        M = inputProcessor.nextInt();

        ROOM = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int value = inputProcessor.nextInt();
                ROOM[i][j] = value;

                if (MIN_CCTV <= value && value <= MAX_CCTV) {
                    CCTVS.add(new Cctv(i, j, value));
                }
            }
        }

        ROTATE = new int[CCTVS.size()];
        Arrays.fill(ROTATE, -1);

        COPIED = new int[N + 1][M + 1];

        RESULT = 65;
    }

    private static void pro() {
        randomRotate(0);
        sb.append(RESULT);
    }

    private static void randomRotate(int idx) {
        if (idx == CCTVS.size()) {
            calculate();
            return;
        }

        for (int i = 0; i < 4; i++) {
            ROTATE[idx] = i;
            randomRotate(idx + 1);
            ROTATE[idx] = -1;
        }
    }

    private static void calculate() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                COPIED[i][j] = ROOM[i][j];
            }
        }

        for (int i = 0; i < CCTVS.size(); i++) {
            int dir = ROTATE[i]; // 0, 1, 2, 3
            Cctv cctv = CCTVS.get(i);
            drawSharp(cctv, dir);
        }

        int cnt = N * M;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (COPIED[i][j] != 0) {
                    cnt -= 1;
                }
            }
        }

        RESULT = Math.min(RESULT, cnt);
    }

    // cctv 번호, dir 방향
    private static void drawSharp(Cctv cctv, int dir) {
        if (cctv.no == 1) {
            if (dir == 0) {
                drawRight(cctv);
            } else if (dir == 1) {
                drawDown(cctv);
            } else if (dir == 2) {
                drawLeft(cctv);
            } else if (dir == 3) {
                drawUp(cctv);
            }
        }

        if (cctv.no == 2) {
            if (dir == 0 || dir == 2) { // 수평
                drawRight(cctv);
                drawLeft(cctv);
            } else { // 수직
                drawDown(cctv);
                drawUp(cctv);
            }
        }

        if (cctv.no == 3) {
            if (dir == 0) {
                drawUp(cctv);
                drawRight(cctv);
            } else if (dir == 1) {
                drawRight(cctv);
                drawDown(cctv);
            } else if (dir == 2) {
                drawDown(cctv);
                drawLeft(cctv);
            } else if (dir == 3) {
                drawUp(cctv);
                drawLeft(cctv);
            }
        }

        if (cctv.no == 4) {
            if (dir == 0) {
                drawUp(cctv);
                drawRight(cctv);
                drawLeft(cctv);
            } else if (dir == 1) {
                drawUp(cctv);
                drawRight(cctv);
                drawDown(cctv);
            } else if (dir == 2) {
                drawDown(cctv);
                drawLeft(cctv);
                drawRight(cctv);
            } else if (dir == 3) {
                drawUp(cctv);
                drawLeft(cctv);
                drawDown(cctv);
            }
        }

        if (cctv.no == 5) {
            drawUp(cctv);
            drawLeft(cctv);
            drawRight(cctv);
            drawDown(cctv);
        }
    }

    private static void drawUp(Cctv cctv) {
        for (int row = cctv.x - 1; row >= 1; row--) {
            if (isCctv(COPIED[row][cctv.y])) continue;
            if (COPIED[row][cctv.y] == 6) {
                break;
            }
            COPIED[row][cctv.y] = -1;
        }
    }

    private static void drawLeft(Cctv cctv) {
        for (int col = cctv.y - 1; col >= 1; col--) {
            if (isCctv(COPIED[cctv.x][col])) continue;
            if (COPIED[cctv.x][col] == 6) {
                break;
            }
            COPIED[cctv.x][col] = -1;
        }
    }

    private static void drawDown(Cctv cctv) {
        for (int row = cctv.x + 1; row <= N; row++) {
            if (isCctv(COPIED[row][cctv.y])) continue;
            if (COPIED[row][cctv.y] == 6) {
                break;
            }
            COPIED[row][cctv.y] = -1;
        }
    }

    private static void drawRight(Cctv cctv) {
        for (int col = cctv.y + 1; col <= M; col++) {
            if (isCctv(COPIED[cctv.x][col])) continue;
            if (COPIED[cctv.x][col] == 6) {
                break;
            }
            COPIED[cctv.x][col] = -1;
        }
    }

    private static boolean isCctv(int no) {
        return MIN_CCTV <= no && no <= MAX_CCTV;
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
