package its.codingtest.implementation;

import java.io.*;
import java.util.*;

/**
 * 백준 - 뱀 (골4)
 *
 * - 큐에 사과를 먹으면 (1,1) (1,2) (1,3) (1,4) 쌓이고, 사과가 없으면 앞에서부터 poll해서 처리하면 꼬리 제거 간단
 * - dx, dy로 머리 포인터를 나타냄
 * - 맵에서 1은 사과, 2는 뱀을 표기해서 2차원 정수 배열만으로 충분히 처리 가능했음
 */
public class p327 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int N, K, L;
    private static int[][] BOARD;
    private static Map<Integer, String> COMMAND_MAP;

    private static void input() {
        N = inputProcessor.nextInt(); // 보드의 크기
        K = inputProcessor.nextInt(); // 사과의 개수
        BOARD = new int[N + 1][N + 1];
        for(int i = 1; i <= K; i++) {
            int x = inputProcessor.nextInt();
            int y = inputProcessor.nextInt();

            BOARD[x][y] = 1; // 사과 표시
        }

        L = inputProcessor.nextInt(); // 방향 변환 회수
        COMMAND_MAP = new HashMap<>();
        for(int i = 1; i <= L; i++) {
            int x = inputProcessor.nextInt();
            String c = inputProcessor.next();

            COMMAND_MAP.put(x, c);
        }
    }

    private static final int[][] DIR = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private static void pro() {
        Deque<Integer> snake = new ArrayDeque<>();
        snake.add(1);
        snake.add(1);

        BOARD[1][1] = 2; // 뱀 표시

        int i = 0; // 오른쪽 이동
        int time = 0;
        int dx = 1;
        int dy = 1;
        while(!snake.isEmpty()) {
            dx += DIR[i][0];
            dy += DIR[i][1];

            if(dx < 1 || dy < 1 || dx > N || dy > N || BOARD[dx][dy] == 2) {
                time += 1;
                break;
            }

            if(BOARD[dx][dy] == 1) { // 사과가 있는 경우
                BOARD[dx][dy] = 2;
            } else { // 빈 공간
                int nx = snake.poll();
                int ny = snake.poll();
                BOARD[nx][ny] = 0; // 꼬리 제거
            }

            BOARD[dx][dy] = 2;
            snake.add(dx);
            snake.add(dy);

            time += 1;
            // 방향 전환*
            if(COMMAND_MAP.containsKey(time)) {
                String command = COMMAND_MAP.remove(time);
                i = nextDir(i, command);
            }
        }

        sb.append(time);
    }

    private static int nextDir(int i, String command) {
        if(command.equals("D")) { // 오른쪽 90도
            return (i + 1) % 4;
        }

        return (i - 1) < 0 ? 3 : (i - 1); // 왼쪽 90도
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
