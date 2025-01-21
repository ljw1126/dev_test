package backjun.bruteforce;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 스도쿠 (골4)
 * https://www.acmicpc.net/problem/2580
 *
 * - 3 * 3 그리드 유효성 검사 누락하여 실패
 */
public class BOJ2580 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static final int N = 9;
    private static final long allComplete = (1 << 10) - 2;
    private static int[][] maps;
    private static long[] rows;
    private static long[] cols;
    private static long[] boxes;
    private static List<int[]> points;
    private static boolean finished = false;

    private static void input() {
        maps = new int[N][N];

        rows = new long[N];
        cols = new long[N];
        boxes = new long[N]; // 서브 그리드 3 * 3
        points = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int v = inputProcessor.nextInt();
                int pos = (i / 3) * 3 + (j / 3);

                if(v == 0) {
                    points.add(new int[] {i, j});
                    continue;
                }

                maps[i][j] = v;
                rows[i] |= 1L << v;
                cols[j] |= 1L << v;
                boxes[pos] |= 1L << v;
            }
        }
    }

    private static void pro() {
        backTracking(0);
    }

    private static void backTracking(int idx) {
        if(!finished && idx == points.size()) {
            boolean isValid = true;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    int pos = (i / 3) * 3 + (j / 3);
                    if(rows[i] != allComplete || cols[j] != allComplete || boxes[pos] != allComplete) {
                        isValid = false;
                        break;
                    }
                }
            }

            if(isValid) {
                finished = true;
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        sb.append(maps[i][j]).append(" ");
                    }
                    sb.append("\n");
                }
            }

            return;
        }
        if(finished) return;

        int[] point = points.get(idx);
        int x = point[0];
        int y = point[1];
        int pos = (x / 3) * 3 + (y / 3);
        for(int i = 1; i <= N; i++) {
            long flag = 1L << i;
            if((rows[x] & flag) != 0 || (cols[y] & flag) != 0 || (boxes[pos] & flag) != 0) continue;

            rows[x] |= flag;
            cols[y] |= flag;
            boxes[pos] |= flag;
            maps[x][y] = i;

            backTracking(idx + 1);

            maps[x][y] = 0;
            rows[x] &= ~flag;
            cols[y] &= ~flag;
            boxes[pos] &= ~flag;
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
