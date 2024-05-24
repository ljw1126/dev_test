package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 가희야 거기서 자는거 아니야(브1)
 * https://www.acmicpc.net/problem/21771
 * <p>
 * - 직접 풀이 못함
 * - P의 개수만큼 카운트 하고 , 배개 넓이와 비교해서 출력하면 되는 문제 ..
 */
public class BOJ21771 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int R, C, Rg, Cg, Rp, Cp;
    private static char[][] ROOM;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }


    private static void input() {
        // 방
        R = inputProcessor.nextInt();
        C = inputProcessor.nextInt();

        // 가희
        Rg = inputProcessor.nextInt();
        Cg = inputProcessor.nextInt();

        // 베게
        Rp = inputProcessor.nextInt();
        Cp = inputProcessor.nextInt();

        ROOM = new char[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            String line = inputProcessor.nextLine();
            for (int j = 1; j <= C; j++) {
                ROOM[i][j] = line.charAt(j - 1);
            }
        }
    }


    private static void pro() {
        int count = 0;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (ROOM[i][j] == 'P') {
                    count += 1;
                }
            }
        }

        int total = Rp * Cp;
        if (count < total) {
            sb.append(1);
        } else {
            sb.append(0);
        }
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
