package fastcampus.algorithm.topological.extend;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 최종순위 (골1)
 * https://www.acmicpc.net/problem/3665
 *
 * - 직접 풀이 못함 (어려운 문제)
 * - 인접리스트 아닌 인접행렬을 사용해야 "상대 등수" 변경시 용이함
 * - IN_DEGREE 필수
 * - 5가 1등이면 5이하에 들어가는 간선 모두 추가해야 함 (이걸 인접 행렬로 표현해야 편함)
 * - 팀이 최대 500까지 있으니 500 * 500 * 1byte(boolean) = 2.5kb
 * - 일반 위상 정렬의 경우 queue.size() >= 2 이상이라면 문제 답이 여러개 되나, 현재 문제는 확실한 순위를 찾아야 하므로 "?" 출력
 * - 사이클을 형성하는 경우는 IN_DEGREE == 0 인 노드가 없어 queue.size() == 0 이 된다
 *
 * IN_DEGREE
 * 1번 케이스 : 4 2 1 3 0
 * 2번 케이스 : 2 0 1
 * 3번 케이스 : 1 1 2 2
 *
 * 참고
 * https://comyoung.tistory.com/256
 */
public class BOJ3665 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();
    static String QUESTION_MARK = "?";
    static String IMPOSSIBLE = "IMPOSSIBLE";
    static int N, M;
    static boolean[][] EDGE;
    static int[] IN_DEGREE;

    public static void main(String[] args) throws IOException {
        int T = inputProcessor.nextInt();
        while(T > 0) {
            input();

            String result = pro();
            sb.append(result).append("\n");

            T -= 1;
        }

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 팀의 수
        IN_DEGREE = new int[N + 1];
        EDGE = new boolean[N + 1][N + 1];

        for(int i = 1; i <= N; i++) { // i - 1등은 teamNo 이다
            int teamNo = inputProcessor.nextInt();
            IN_DEGREE[teamNo] = i - 1;

            for(int j = 1; j <= N; j++) {
                if(j == teamNo) continue;
                if(!EDGE[j][teamNo]) {
                    EDGE[teamNo][j] = true;
                }
            }
        }

        M = inputProcessor.nextInt(); // 상대적인 등수가 바뀐 쌍의 수
        for(int i = 1; i <= M; i++) {
            int a = inputProcessor.nextInt();
            int b = inputProcessor.nextInt();
            swap(a, b);
        }
    }

    // (a,b)가 상대적인 등수가 바뀜
    private static void swap(int a, int b) {
        if(EDGE[a][b]) {
            EDGE[a][b] = false;
            EDGE[b][a] = true; // b -> a
            IN_DEGREE[b] -=1;
            IN_DEGREE[a] += 1;
        } else {
            EDGE[a][b] = true;
            EDGE[b][a] = false;
            IN_DEGREE[b] += 1;
            IN_DEGREE[a] -= 1;
        }
    }

    private static String pro() {
        Deque<Integer> que = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
            }
        }

        // 1등부터 순서대로 출력되므로 그만큼 반복문을 돌림
        StringBuilder result = new StringBuilder();
        for(int i = 1; i <= N ; i++) {
            if(que.size() >= 2) {  // 확실한 순위를 찾을 수 없다 == 경우의 수가 여러 개 인 경우
                return QUESTION_MARK;
            }

            if(que.size() == 0) {
                return IMPOSSIBLE;
            }

            int cur = que.poll();
            result.append(cur).append(" ");

            for(int j = 1; j <= N; j++) {
                if(EDGE[cur][j]) { // cur -> j 존재하면
                    EDGE[cur][j] = false;
                    IN_DEGREE[j] -= 1;
                    if(IN_DEGREE[j] == 0) {
                        que.add(j);
                    }
                }
            }
        }

        return result.toString();
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
            while(st == null || !st.hasMoreElements()) {
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
