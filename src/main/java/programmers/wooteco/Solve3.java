package programmers.wooteco;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 문제3. 지뢰찾기
 * *입출력 예시
 * 1번. board = ["EEEEE", "EEMEE", "EEEEE", "EEEEE"], y = 2, x = 0, result = ["B1E1B", "B1E1B", "B111B", "BBBBB"]
 * <p>
 * 2번. board = ["MME", "EEE", "EME"], y = 0, x = 0, result = ["XME", "EEE", "EME"]
 * <p>
 * 시간복잡도 : O(V + E)
 * 이때 간선은 8*N*N, 노드 수는 N * N
 */
public class Solve3 {

    private static final Data DATA1 = new Data(new String[]{"EEEEE", "EEMEE", "EEEEE", "EEEEE"},
            2, 0, new String[]{"B1E1B", "B1E1B", "B111B", "BBBBB"});
    private static final Data DATA2 = new Data(new String[]{"MME", "EEE", "EME"},
            0, 0, new String[]{"XME", "EEE", "EME"});
    private static final Data DATA3 = new Data(new String[]{"MME", "EEE", "EME"},
            2, 2, new String[]{"EEE", "EEE", "EE1"});
    private static final Data DATA4 = new Data(new String[]{"EEEEMEEEE", "EEEEEEEEM", "EEEEEEMEE", "EEEEEEEEE", "EEMEEEEEM", "EEEEEEEEE", "MEEEEEMEE", "EEEMEEEEE", "MEEEEEMEE"},
            0, 0, new String[]{"BBB1EEEEE", "BBB112EEE", "BBBBB1EEE", "B111B112E", "B1E1BBB1E", "12E1B112E", "EEE111EEE", "EEEEEEEEE", "EEEEEEEEE"});

    public static void main(String[] args) {
        String[] pro = pro(DATA1);

        for (String p : pro) {
            System.out.println(p);
        }
    }

    /**
     * 1. 바로 지뢰를 누르는 경우
     * 2. 빈 단추를 누르는 경우
     * - 인접한 8칸에 존재하는 지뢰(M)의 수를 구한다
     * - 이때 나타나는 숫자가 1이상이라면 그 단추 하나만 열리게 된다
     * - 만약 인접한 8개의 방향에 지뢰가 없다면, 열린 단추는 활성화 된채로 남음(B), 그리고 인접해 있는 8개 단추중 열리지 않은 단추가 연쇄적으로 열림
     * - 연쇄적으로 열린 단추 또한 각 단추에 낱타나는 숫자가 1이상이라면 그 단추 하나만 열리게 되며,
     */
    private static String[] pro(Data data) {
        String[] board = data.board;
        int y = data.y;
        int x = data.x;
        String[] expected = data.result;

        int rows = board.length;
        int cols = board[0].length();
        char[][] grid = new char[rows][cols];

        // String[]을 char[][]로 변환
        for (int i = 0; i < rows; i++) {
            grid[i] = board[i].toCharArray();
        }

        if (grid[y][x] == 'M') {
            grid[y][x] = 'X';
            return failResult(grid);
        }

        return bfs(grid, y, x, rows, cols);
    }

    private static String[] failResult(char[][] grid) {
        String[] result = new String[grid.length];
        for (int i = 0; i < grid.length; i++) {
            result[i] = new String(grid[i]);
        }

        return result;
    }

    private static final int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    private static String[] bfs(char[][] grid, int x, int y, int rows, int cols) {
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{x, y});

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int _x = cur[0];
            int _y = cur[1];

            int mineCount = countMines(grid, _x, _y, rows, cols);
            if (mineCount > 0) {
                grid[_x][_y] = Character.forDigit(mineCount, 10);
                continue;
            }

            // 주변에 지뢰가 없으면 'B'로 표기하고 탐색 노드 추가
            grid[_x][_y] = 'B';
            for (int i = 0; i < 8; i++) {
                int dx = _x + dir[i][0];
                int dy = _y + dir[i][1];

                if (dx < 0 || dy < 0 || dx >= rows || dy >= cols || grid[dx][dy] != 'E') continue;

                grid[dx][dy] = 'B'; // 큐에 넣기 전에 'B'로 바꿔서 중복 탐색 방지*
                que.add(new int[]{dx, dy});
            }
        }

        return successResult(grid, rows, cols);
    }

    private static int countMines(char[][] grid, int x, int y, int rows, int cols) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if (dx < 0 || dy < 0 || dx >= rows || dy >= cols) continue;
            if (grid[dx][dy] == 'M') count += 1;
        }

        return count;
    }

    private static String[] successResult(char[][] grid, int rows, int cols) {
        String[] result = new String[grid.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'M') {
                    grid[i][j] = 'E';
                }
            }

            result[i] = new String(grid[i]);
        }

        return result;
    }

    private static class Data {
        private final String[] board;
        private final int y;
        private final int x;
        private final String[] result;

        public Data(String[] board, int y, int x, String[] result) {
            this.board = board;
            this.y = y;
            this.x = x;
            this.result = result;
        }
    }
}
