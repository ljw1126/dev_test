package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 가희와 로그 파일 (골3)
 * https://www.acmicpc.net/problem/21774
 * <p>
 * 직접 풀이 못함
 * - level별로 로그 묶는 아이디어 (날짜 순으로 입력 줘서 편함)
 * - 문자열 날짜를 String.compareTo()로 비교 가능
 * - 시간 복잡도 : O(QlogN)
 */
public class BOJ21774 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final String SHARP_DELIMITER = "\\#";
    private static int N, Q;
    private static List<List<String>> DATA;


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        Q = inputProcessor.nextInt();

        DATA = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            DATA.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            String text = inputProcessor.nextLine();
            String[] tokens = text.split(SHARP_DELIMITER);
            String date = tokens[0];
            int level = Integer.parseInt(tokens[1]);

            DATA.get(level).add(date);
        }
    }

    private static void pro() {
        for (int i = 1; i <= Q; i++) {
            String text = inputProcessor.nextLine();
            String[] tokens = text.split(SHARP_DELIMITER);

            String startDate = tokens[0];
            String endDate = tokens[1];
            int level = Integer.parseInt(tokens[2]);

            int count = 0;
            for (int j = level; j <= 6; j++) {
                count += countByRange(DATA.get(j), startDate, endDate);
            }

            sb.append(count).append("\n");
        }
    }

    private static int countByRange(List<String> dates, String startDate, String endDate) {
        if (dates.isEmpty()) {
            return 0;
        }

        int lower = lowerBound(dates, 0, dates.size(), startDate);
        int upper = upperBound(dates, 0, dates.size(), endDate);
        return upper - lower;
    }

    private static int lowerBound(List<String> dates, int start, int end, String startDate) {
        int L = start;
        int R = end;

        while (L < R) {
            int mid = (L + R) / 2;

            if (dates.get(mid).compareTo(startDate) < 0) { // target 이상의 첫번째 위치를 구함
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return L;
    }

    private static int upperBound(List<String> dates, int start, int end, String endDate) {
        int L = start;
        int R = end;

        while (L < R) {
            int mid = (L + R) / 2;

            if (dates.get(mid).compareTo(endDate) <= 0) { // 초과하는 첫번재 인덱스를 찾기 위함
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return L;
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
