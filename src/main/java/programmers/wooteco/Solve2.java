package programmers.wooteco;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 문제 2. DP, 격자형 그래프 경우의 수 구하는 문제 (chat-gpt)
 * <p>
 * *제한사항
 * - 1 <= width <= 250
 * - 1 <= height <= 250
 * - 1 <= diagonals 의 길이 <= width * height
 * - diagonals의 각 행은 두 정수 [x, y]로 이루어져 있으며, 왼쪽에서부터 x번째, 아래에서부터 y번째 사각형에 대각선이 있음을 의미함
 * - 1 <= x <= width
 * - 1 <= y <= height
 * - 똑같은 (x, y) 순서쌍은 2번이상 등장하지 않습니다.
 * <p>
 * <p>
 * 입출력 예
 * width = 2, height = 2, diagonals = [[1,1] [2, 2]], result = 12
 * <p>
 * width = 51, height = 37, diagonals = [[17, 19]], result = 3225685
 */
public class Solve2 {

    private static final Data DATA1 = new Data(2, 2, new int[][]{{1, 1}, {2, 2}}, 12);
    private static final Data DATA2 = new Data(51, 37, new int[][]{{17, 19}}, 3225685);


    public static void main(String[] args) throws IOException {
        //pro(DATA1);
        pro(DATA2);
    }

    // 시간복잡도는 O(width * heigth + diagonals 길이)
    private static void pro(Data data) throws IOException {
        int mod = 10_000_019;
        int width = data.width;
        int height = data.height;
        int[][] diagonals = data.diagonals;

        long[][] dp = new long[width + 1][height + 1];
        long[][] dpReverse = new long[width + 1][height + 1];

        /**
         * 시작 (0,0) -> 도착 (width, height)
         * 1 3 6
         * 1 2 3
         * 1 1 1
         */
        dp[0][0] = 1;
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                if (i > 0) dp[i][j] = (dp[i][j] + dp[i - 1][j]) % mod;
                if (j > 0) dp[i][j] = (dp[i][j] + dp[i][j - 1]) % mod;
            }
        }

        /**
         * 도착 (width, height) -> 시작 (0,0)
         *   1 1 1
         *   3 2 1
         *   6 3 1
         */
        dpReverse[width][height] = 1;
        for (int i = width; i >= 0; i--) {
            for (int j = height; j >= 0; j--) {
                if (i < width) dpReverse[i][j] = (dpReverse[i][j] + dpReverse[i + 1][j]) % mod;
                if (j < height) dpReverse[i][j] = (dpReverse[i][j] + dpReverse[i][j + 1]) % mod;
            }
        }

        // 대각선이 위치한 좌표의 경우의 수를 구해서 누적 합산한다
        // case 구할때 주의
        // - dp는 이동전 위치, dpReverse는 대각선 이동 후 위치를 구해서 곱하여 경우의 수를 구한다
        // - (1,1) 대각선에 대해 dp[1][0] * dpReverse[0][1] 나 dp[0][1] * dpReverse[1][0]
        long result = 0;
        for (int[] diagonal : diagonals) {
            int x = diagonal[0];
            int y = diagonal[1];

            long case1 = (dp[x][y - 1] * dpReverse[x - 1][y]) % mod;

            long case2 = (dp[x - 1][y] * dpReverse[x][y - 1]) % mod;

            result += (case1 + case2) % mod;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }


    private static final int MOD = 10_000_019;
    private static int minDist = Integer.MAX_VALUE;
    private static int width, height;
    private static boolean[][] diagonalsMap, visited;
    private static int result;

    // 좌측 하단(0,0)에서 우측 상단(width, height) 까지 경로를 탐색, 예제 입출력2 시간 초과 (풀이 x)
    private static void fail(Data data) {
        width = data.width;
        height = data.height;
        diagonalsMap = new boolean[width + 1][height + 1];
        visited = new boolean[width + 1][height + 1];
        visited[0][0] = true;

        for (int[] diagonal : data.diagonals) {
            int x = diagonal[0];
            int y = diagonal[1];
            diagonalsMap[x][y] = true;
        }

        dfs(0, 0, false, 0);

        System.out.println(result);
    }

    private static void dfs(int x, int y, boolean diagonalUsed, int dist) {
        if (x == width && y == height) {
            if (!diagonalUsed) return;

            if (dist < minDist) {
                result = 1;
                minDist = dist;
            } else if (dist == minDist) {
                result += 1;
                result %= MOD;
            }

            return;
        }

        // 위로 이동
        if (x + 1 <= width && !visited[x + 1][y]) {
            visited[x + 1][y] = true;
            dfs(x + 1, y, diagonalUsed, dist + 1);
            visited[x + 1][y] = false;
        }

        // 오른쪽 이동
        if (y + 1 <= height && !visited[x][y + 1]) {
            visited[x][y + 1] = true;
            dfs(x, y + 1, diagonalUsed, dist + 1);
            visited[x][y + 1] = false;
        }

        // 왼쪽 아래에서 오른쪽 위로 대각선 사용
        if (y + 1 <= height && !diagonalUsed && diagonalsMap[x][y + 1] && !visited[x - 1][y + 1]) {
            visited[x - 1][y + 1] = true;
            dfs(x - 1, y + 1, true, dist + 1);
            visited[x - 1][y + 1] = false;
        }

        // 왼쪽 위에서 오른쪽 아래로 대각선 사용
        if (x + 1 <= width && !diagonalUsed && diagonalsMap[x + 1][y] && !visited[x + 1][y - 1]) {
            visited[x + 1][y - 1] = true;
            dfs(x + 1, y - 1, true, dist + 1);
            visited[x + 1][y - 1] = false;
        }

    }

    private static class Data {
        private final int width;
        private final int height;
        private final int[][] diagonals;
        private final int result;

        public Data(int width, int height, int[][] diagonals, int result) {
            this.width = width;
            this.height = height;
            this.diagonals = diagonals;
            this.result = result;
        }
    }
}
