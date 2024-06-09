package fastcampus.algorithm.tree.extend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * K진 트리(골3)
 * https://www.acmicpc.net/problem/11812
 * <p>
 * - 공통 조상 문제
 * - 예외처리가 문제였음
 * - 부모 노드를 구하는 것도 공식 못 구함
 */
public class BOJ11812 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static long N;
    private static int K, Q;

    private static void input() {
        N = inputProcessor.nextLong();
        K = inputProcessor.nextInt(); // K진 트리
        Q = inputProcessor.nextInt(); // 쿼리 수
    }

    private static final String NEW_LINE = System.lineSeparator();

    // K = 1이고 2, 10^15 구한다면.. 10^15 - 1이라 long은 맞는데 .. 시간 초과
    // 크기가 너무 커서 수학공식 말고는 답이 없는 문제인듯
    private static void pro() {

        for (int i = 1; i <= Q; i++) {
            long from = inputProcessor.nextLong();
            long to = inputProcessor.nextLong();

            if (from == to) {
                sb.append(0).append(NEW_LINE);
                continue;
            }

            if (K == 1) { // 예외 처리만 하면 되었구나 ..
                sb.append(Math.abs(from - to)).append(NEW_LINE);
                continue;
            }

            sb.append(lca(from, to)).append(NEW_LINE);
        }
    }

    static long getDepth(long idx) {
        if (idx == 1) return 0;

        long line = 1;
        long h = 1;

        while (true) {
            line += Math.pow(K, h);
            h += 1;
            if (idx <= line) break;
        }

        return h - 1;
    }

    private static long lca(long from, long to) {
        long cntA = 0;
        long cntB = 0;

        long parentA = from;
        long parentB = to;
        while (parentA != parentB) {
            if (parentA > parentB) {
                parentA = parent(parentA);
                cntA += 1;
            } else {
                parentB = parent(parentB);
                cntB += 1;
            }
        }

        return cntA + cntB;
    }

    private static long parent(long child) {
        return (child - 2) / K + 1;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        private BufferedReader br;
        private StringTokenizer st;

        public InputProcessor() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
