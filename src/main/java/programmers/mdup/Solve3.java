package programmers.mdup;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 0     1      2    3
 * 0 1    63903 63901 63897
 * 1 17   63935 63999 63889
 * 2 273  x           63761
 * 3 4369 12561 28945 61713
 * <p>
 * 2,2로 움직일때 (1,2)가 63999인데, (0,2)일때 63999이거나 다른 노드일때 63999가 나와서 모든 경로 탐색 못함
 * 그래서 Set<Long> -> Set<Node> 로 변경
 * <p>
 * dfs 탐색할떄 4방향에 대해 이미 방문한 경로 처리가 없어서 전체를 다 탐색하게 됨..
 * >> 방문여부 확인 조건문 추가
 * <p>
 * 그리고 최소 인원을 고려해야 하는데 단순히 내림차순으로 하는게 말이 안된다 생각
 * >> 백트래킹 사용하여 최소 인원 구하도록 함
 */
public class Solve3 {

    private static final Data DATA1 = new Data(4, new int[][]{{3, 2}}, 1);
    private static final Data DATA2 = new Data(4, new int[][]{{3, 2}, {2, 3}, {4, 3}}, 3);
    private static final Data DATA3 = new Data(4, new int[][]{{1, 2}, {2, 3}, {3, 1}, {3, 4}}, 3);
    private static final Data DATA4 = new Data(3, new int[][]{{1, 3}, {2, 1}, {3, 3}}, 2);

    public static void main(String[] args) {
        System.out.println(solution(DATA1));
        System.out.println(solution(DATA2));
        System.out.println(solution(DATA3));
        System.out.println(solution(DATA4));
    }

    private static boolean[][] grid;
    private static int n;
    private static Set<Node> allPaths;
    private static long fullPath;
    private static int result;

    public static int solution(Data data) {
        n = data.n;
        grid = new boolean[n][n];
        allPaths = new HashSet<>();
        fullPath = (1L << (n * n)) - 1;
        result = Integer.MAX_VALUE;

        // 물 웅덩이 표시
        for (int[] w : data.water) {
            grid[w[0] - 1][w[1] - 1] = true;
            fullPath &= ~(1L << ((w[0] - 1) * n + (w[1] - 1))); // not 연산과 &연산을 해서 물 영역은 0으로 처리
        }

        //System.out.println(fullPath);
        //for (boolean[] g : grid) System.out.println(Arrays.toString(g));

        // 모든 가능한 경로 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!grid[i][j]) {
                    dfs(i, j, 0L);
                }
            }
        }

        return findMaxIndependentPaths();
    }

    private static void dfs(int x, int y, long path) {
        if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y]) {
            return;
        }

        long newPath = path | (1L << (x * n + y));
        Node node = new Node(x, y, newPath);
        if (allPaths.contains(node)) { // 1번 예제에서 이동시 (1,2) 63999 경우가 있고 (0,2)에서 63999 인 경우가 있음 ..
            return;
        }

        allPaths.add(node);
        int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int dx = x + dir[0];
            int dy = y + dir[1];

            if ((newPath & (1L << (dx * n + dy))) != 0) { // 이미 방문한 경우 처리
                continue;
            }

            dfs(dx, dy, newPath);
        }
    }

    private static int findMaxIndependentPaths() {
        List<Long> paths = allPaths.stream()
                .map(Node::getBitMask)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        //System.out.println(paths);
        //backtrack(paths, 0, 0L, 0);
        return backtrack2(paths, 0, 0L, 0);
    }

    //global state backtracking
    private static void backtrack(List<Long> paths, int index, long used, int count) {
        if (used == fullPath) {
            result = Math.min(result, count);
            return;
        }

        if (index == paths.size()) {
            return;
        }

        // 현재 경로를 선택하지 않는 경우
        backtrack(paths, index + 1, used, count);

        // 현재 경로를 선택하는 경우
        long path = paths.get(index);
        if ((path & used) == 0) {
            backtrack(paths, index + 1, used | path, count + 1);
        }
    }

    //return-based backtracking
    private static int backtrack2(List<Long> paths, int index, long used, int count) {
        if (used == fullPath) {
            return count;
        }

        if (index == paths.size()) {
            return Integer.MAX_VALUE;
        }

        // 현재 경로를 선택하지 않는 경우
        int withoutCurrentPath = backtrack2(paths, index + 1, used, count);

        //현재 경로를 선택하는 경우
        long path = paths.get(index);
        int withCurrentPath = Integer.MAX_VALUE;
        if ((used & path) == 0) {
            withCurrentPath = backtrack2(paths, index + 1, used | path, count + 1);
        }

        return Math.min(withoutCurrentPath, withCurrentPath);
    }

    private static class Node {
        private int x;
        private int y;
        private long bitMask;

        public Node(int x, int y, long bitMask) {
            this.x = x;
            this.y = y;
            this.bitMask = bitMask;
        }

        public long getBitMask() {
            return bitMask;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Node node = (Node) other;
            return x == node.x && y == node.y && bitMask == node.bitMask;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, bitMask);
        }
    }

    private static class Data {
        private final int n;
        private final int[][] water;
        private final int result;

        public Data(int n, int[][] water, int result) {
            this.n = n;
            this.water = water;
            this.result = result;
        }
    }
}
