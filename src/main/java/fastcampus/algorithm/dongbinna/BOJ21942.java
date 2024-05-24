package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 부품 대여장 (골2)
 * https://www.acmicpc.net/problem/21942
 * <p>
 * - 자료구조, 해시맵 문제
 * - 직접 풀이 x
 * - 날짜 변환이 어려웠음 (월별 일 수를 구하는 규칙이 존재했음)
 */
public class BOJ21942 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static final String BLANK = " ";
    private static int N, F, LIMIT;
    private static String L;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 정보의 개수
        L = inputProcessor.next(); // 대여기간
        F = inputProcessor.nextInt(); // 벌금

        String[] tokens = L.split("/");
        int day = Integer.parseInt(tokens[0]);

        String[] times = tokens[1].split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);

        LIMIT += day * 24 * 60;
        LIMIT += hour * 60;
        LIMIT += minute;
    }

    private static void pro() {
        Map<String, Map<String, Long>> bookInfoMap = new HashMap<>();
        Map<String, Long> feeMap = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String line = inputProcessor.nextLine();
            String[] tokens = line.split(BLANK);
            String date = tokens[0];
            String time = tokens[1];
            String parts = tokens[2];
            String nickname = tokens[3];

            long minute = toMinutes(date, time);
            if (!bookInfoMap.containsKey(nickname)) { // 신규 대여
                bookInfoMap.put(nickname, new HashMap<>());
                bookInfoMap.get(nickname).put(parts, minute);
            } else {
                Map<String, Long> userInfo = bookInfoMap.get(nickname);
                if (userInfo.containsKey(parts)) { // 부품 반납
                    long borrow = userInfo.remove(parts); // 부품 반납시 제거해야 함(실수)
                    long diff = minute - borrow - LIMIT;

                    if (diff > 0) {
                        feeMap.put(nickname, feeMap.getOrDefault(nickname, 0L) + diff * F);
                    }
                } else { // 다른 부품 대여
                    userInfo.put(parts, minute);
                }
            }
        }

        if (feeMap.isEmpty()) {
            sb.append(-1);
        } else {
            List<String> nicknames = new ArrayList<>(feeMap.keySet());
            Collections.sort(nicknames);

            nicknames.forEach(nickname -> {
                sb.append(nickname).append(BLANK).append(feeMap.get(nickname)).append("\n");
            });
        }
    }

    private static final int[] DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // 2021-01-01 00:00에서 얼마만큼 시간 걸렸는지
    private static long toMinutes(String date, String time) {
        String[] dates = date.split("-");
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);

        for (int i = 1; i < month; i++) {
            day += DAYS[i];
        }

        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);

        return ((long) (day - 1) * 24 * 60) + (hour * 60) + minute;
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
