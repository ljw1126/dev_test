package fastcampus.algorithm.twopoint.extend;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 고냥이(골드4)
 * https://www.acmicpc.net/problem/16472
 *
 * - 시간복잡도 O(N)
 * - 비즈니스 로직에서 투 포인터 형식에 매몰되어 구하지 못함
 */
public class BOJ16472 {
    static StringBuilder sb = new StringBuilder();
    static int N, COUNT;
    static String TEXT;
    static int[] ALPHABET;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        InputProcessor inputProcessor = new InputProcessor();
        N = inputProcessor.nextInt();
        TEXT = inputProcessor.nextLine();

        ALPHABET = new int[27];
    }

    private static void inc(char c) {
        ALPHABET[c - 'a'] += 1;
        if(ALPHABET[c - 'a'] == 1) {
            COUNT += 1;
        }
    }

    private static void dec(char c) {
        ALPHABET[c - 'a'] -= 1;
        if(ALPHABET[c - 'a'] == 0) {
            COUNT -= 1;
        }
    }

    private static void pro() {
        int lng = TEXT.length();
        int result = 0;
        for(int R = 0, L = 0; R <= lng - 1; R++) {
            inc(TEXT.charAt(R));

            while(COUNT > N) {
                dec(TEXT.charAt(L));
                L += 1;
            }

            result = Math.max(result, R - L + 1);
        }

        sb.append(result);
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static class InputProcessor {
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
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";

            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
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
