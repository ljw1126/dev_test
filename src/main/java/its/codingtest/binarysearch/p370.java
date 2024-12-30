package its.codingtest.binarysearch;

import java.io.*;
import java.util.*;

/**
 * 프로그래머스 - 가사 검색 (카카오 2020)
 * https://school.programmers.co.kr/learn/courses/30/lessons/60060
 *
 * - 직접 풀이 못함
 * - 이진 탐색 사용
 *
 * - 1. 문자열 길이 별로 정방향, 역방향으로 리스트에 담음
 *   - 길이가 최대 10만개가 나와서 LinkedList로 추가했으나, 이진 탐색시 0 ~ i번 인덱스 순차 접근하여 시간초과 발생
 *   - ArrayList로 할 경우 O(1)만에 인덱스 접근 가능
 * - 2. 이진탐색 할 때 물음표를 각각 치환해서 범위 탐색
 *   - 같은 길이의 문자열이 없다면 -1 반환
 *   - left : ?를 a로 치환  (음수가 되는 첫번째 위치에 R)
 *   - right : ?를 z로 치환 (음수가 되는 첫번째 위치에 R)
 */
public class p370 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static final String[] words = {
            "frodo",
            "front",
            "frost",
            "frozen",
            "frame",
            "kakao"
    };

    private static final String[] queries = {
            "fro??",
            "????o",
            "fr???",
            "fro???",
            "pro?"
    };

    private static final int[] result = {
            3, 2, 4, 1, 0
    };

    private static void input() {

    }

    /*
        정렬 전
        {5=[frodo, front, frost, frame, kakao], 6=[frozen]}
        {5=[odorf, tnorf, tsorf, emarf, oakak], 6=[nezorf]}

        정렬 후
        {5=[frame, frodo, front, frost, kakao], 6=[frozen]}
        {5=[emarf, oakak, odorf, tnorf, tsorf], 6=[nezorf]}
     */
    private static void pro() {
        // LinkedList가 추가/삭제에 유리하지만 인덱스 접근시 0 ~ i 까지 순차적으로 접근해야 하므로 시간초과 발생
        List<List<String>> forward = new ArrayList<>();
        List<List<String>> reverse = new ArrayList<>();

        for(int i = 0; i <= 100000; i++) {
            forward.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for(String w : words) {
            int len = w.length();
            forward.get(len).add(w);
            reverse.get(len).add(reversed(w));
        }

        for(int i = 0; i <= 100000; i++) {
            if(forward.get(i).isEmpty()) continue;

            Collections.sort(forward.get(i));
            Collections.sort(reverse.get(i));
        }

        int[] answer = new int[queries.length];
        int idx = 0;
        for(String q : queries) {
            if(q.startsWith("?")) {
                answer[idx++] = countByRange(reverse, reversed(q));
            } else {
                answer[idx++] = countByRange(forward, q);
            }
        }

        sb.append(Arrays.toString(answer));
    }

    private static String reversed(String s) {
        String result = sb.append(s).reverse().toString();
        sb.setLength(0);
        return result;
    }

    private static int countByRange(List<List<String>> words, String query) {
        int left = lowerBound(words, query);
        int right = upperBound(words, query);

        return right - left;
    }

    private static int lowerBound(List<List<String>> words, String query) {
        int len = query.length();
        List<String> arr = words.get(len);
        if(arr.isEmpty()) return -1;

        query = query.replaceAll("\\?", "a");
        int L = 0;
        int R = arr.size();

        while(L < R) {
            int mid = (L + R) / 2;
            int v = query.compareTo(arr.get(mid));

            if(v < 0) { // 음수인 경우 (사전 순으로 높다)
                R = mid;
            } else { // 양수인 경우 (사전순으로 낮다 경우)
                L = mid + 1;
            }
        }

        return R;
    }

    private static int upperBound(List<List<String>> words, String query) {
        int len = query.length();
        List<String> arr = words.get(len);
        if(arr.isEmpty()) return -1;

        query = query.replaceAll("\\?", "z"); // z로 치환하기 때문에 첫번째 음수가 되는 위치를 찾아야 한다
        int L = 0;
        int R = arr.size();

        while(L < R) {
            int mid = (L + R) / 2;
            int v = query.compareTo(arr.get(mid));

            if(v < 0) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        return R;
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
