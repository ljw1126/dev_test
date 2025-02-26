package backjun.bruteforce;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 순열장난(골5)
 * https://www.acmicpc.net/problem/10597
 *
 * 직접 풀이 못함
 * - 1 ~ N까지의 수로 이루어진 순열
 * - 순열은 최소 1개, 최대 50개의 수로 이루어진다니 최대수는 50이 된다
 * - boolean[] visited 배열로 방문 여부 체크하여 중복 제외하고, 최대치를 넘는지 확인
 * - 한 자리수로 하는 경우와 두 자리수로 하는 경우를 백트래킹 호출을 해줘야함
 */
public class BOJ10597 {

    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int[] nums;
    private static boolean[] visited;
    private static int len;
    private static boolean finished;

    private static void input() {
        String input = inputProcessor.next();
        len = input.length();

        nums = new int[len];
        for(int i = 0; i < len; i++) {
            nums[i] = input.charAt(i) - '0';
        }

        visited = new boolean[51];
    }

    private static void pro() {
        rec(0, 0, new ArrayList<>());
    }

    /*
        검증의 경우 1 ~ N까지 숫자가 다 있는지 확인하면 된다
        고로 재귀 호출시 최대값을 보낸 후 1 ~ max까지 방문 여부를 확인한다
     */
    private static void rec(int idx, int max, List<Integer> selected) {
        if(finished) return;
        if(idx == len) {
            for(int i = 1; i <= max; i++) {
                if(!visited[i]) return;
            }

            finished = true;
            for(int s : selected) {
                sb.append(s).append(" ");
            }

            return;
        }

        int a = nums[idx];
        if(!visited[a] && a > 0) {
            visited[a] = true;
            selected.add(a);
            rec(idx + 1, Math.max(max, a), selected);
            selected.remove(selected.size() - 1);
            visited[a] = false;
        }

        if(idx + 1 < len) {
            a *= 10;
            a += nums[idx + 1];

            if(a > 50) return;
            if(visited[a]) return;

            visited[a] = true;
            selected.add(a);
            rec(idx + 2, Math.max(max, a), selected);
            selected.remove(selected.size() - 1);
            visited[a] = false;
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
