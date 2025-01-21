package backjun.bruteforce;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 단어수학(골4)
 * https://www.acmicpc.net/problem/1339
 *
 * - 처음 완전 탐색으로 풀이했으나, 공간복잡도와 시간이 오래 걸림
 * - 그리드로 풀이시 공간복잡도 20배, 시간 복잡도 10배 차이남
 *   - 원리는 간단했다
 *   - 10000A 1010C 100D 100G 10E B F 와 같이 자릿수에 대한 10의 제곱근을 가중치로 구한다
 *   - 가중치를 정렬하게 되면 배열은 기본 오름차순이라서 맨뒤에 가장 큰값이 위치한다
 *   - 가중치와 (9~1) 까지의 곱을 합산하면 결과값이 구해진다
 */
public class BOJ1339 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int n;
    private static String[] words;

    private static void input() {
        n = inputProcessor.nextInt();

        words = new String[n];
        for(int i = 0; i < n; i++) {
            words[i] = inputProcessor.nextLine();
        }
    }

    private static void pro() {
        // 알파벳에 대한 가중치를 구한다
        int[] alphabets = new int[27];
        for(String word : words) {
            int len = word.length();
            for(char c : word.toCharArray()) {
                alphabets[c - 'A'] += (int) Math.pow(10, --len);
            }
        }

        // 알파벳은 의미가 없어지고, 가중치만으로 오름차순으로 정렬해서 맨뒤에서 부터 1씩 감소시켜 곱한뒤에 합산
        Arrays.sort(alphabets);
        int multipleNumber = 9;
        int result = 0;
        for(int i = 26; i >= 0; i--) {
            if(alphabets[i] == 0) break;

            result += (alphabets[i] * multipleNumber);
            multipleNumber -= 1;
        }

        sb.append(result);
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
