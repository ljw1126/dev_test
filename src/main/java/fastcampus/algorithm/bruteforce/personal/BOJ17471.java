package fastcampus.algorithm.bruteforce.personal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 게리맨더링(골3)
 * https://www.acmicpc.net/problem/17471
 * <p>
 * - 직접풀이 못함
 * - 두 그룹으로 나누는 조합의 수
 * - 1 ~ 10까지 N이 있고, 두 그룹으로 나눠지는 경우의 수는 최대 2^10
 * - 이때 한 그룹으로 모두 쏠리는 경우는 제외해야 하므로 2^10 - 2
 * - 순서가 의미없는 조합을 구하면 되므로 (2^10 -2) / 2 = 511이 최대 시간 복잡도
 * <p>
 * - 실수한 부분
 * - 리스트에 담을 필요 없이 GROUP 배열에 0, 1로 구분하여 표시하면 되었음 (리스트에 담는 바람에 중복된 조합 구함)
 * - 그리고 bfs/dfs를 돌아서 그룹수가 2인 경우에만 각 그룹의 인구수 합의 차를 구하면 되었음
 * - 이때 당연히 그룹이 같아야 한다
 */
public class BOJ17471 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static int N, RESULT;
    private static int[] PEOPLE;
    private static List<List<Integer>> ADJ;
    private static boolean[] VISIT;
    private static int[] GROUP;

    /*
        A, B 그룹으로 나눌때 중복 조합이 발생하네 ..
        - 2^10 - 2 (한쪽으로 다 쏠리는 경우 제외)
        - 조합이라면 순서 가중요하지 않기 때문에 (1022) / 2 = 511 번이면 되는데 ..
     */
    private static void input() {
        N = inputProcessor.nextInt();

        PEOPLE = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            PEOPLE[i] = inputProcessor.nextInt();
        }

        ADJ = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            ADJ.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            int count = inputProcessor.nextInt();
            for (int j = 1; j <= count; j++) {
                int node = inputProcessor.nextInt();
                ADJ.get(i).add(node);
                ADJ.get(node).add(i);
            }
        }

        RESULT = Integer.MAX_VALUE;
        VISIT = new boolean[N + 1];
        GROUP = new int[N + 1];
    }

    private static void pro() {
        dfs(1);

        if (RESULT == Integer.MAX_VALUE) {
            sb.append(-1);
        } else {
            sb.append(RESULT);
        }
    }

    private static void dfs(int idx) {
        if (idx == N + 1) {
            boolean[] checked = new boolean[N + 1];
            int groupCnt = 0;
            for (int i = 1; i <= N; i++) {
                if (!checked[i]) {
                    bfs(i, checked);
                    groupCnt += 1;
                }
            }

            // 두 그룹으로 나뉜다면
            if (groupCnt == 2) {
                int sumA = sum(0);
                int sumB = sum(1);

                RESULT = Math.min(RESULT, Math.abs(sumA - sumB));
            }

            return;
        }

        GROUP[idx] = 1;
        dfs(idx + 1);

        GROUP[idx] = 0;
        dfs(idx + 1);
    }

    private static void bfs(int start, boolean[] checked) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(start);

        checked[start] = true;

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int next : ADJ.get(cur)) {
                if (GROUP[cur] != GROUP[next]) continue;
                if (checked[next]) continue;

                checked[next] = true;
                que.add(next);
            }
        }
    }

    private static int sum(int groupNo) {
        int sum = 0;

        for (int i = 1; i <= N; i++) {
            if (GROUP[i] == groupNo) {
                sum += PEOPLE[i];
            }
        }

        return sum;
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
