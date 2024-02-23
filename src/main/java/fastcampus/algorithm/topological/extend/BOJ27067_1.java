package fastcampus.algorithm.topological.extend;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 데이터 순서 복원(골드2)
 * https://www.acmicpc.net/problem/27067
 *
 * 직접 풀이함
 * - 각 2차원 배열을 만들어서 채움
 * - 3개를 비교해서 하나만 true이고, 나머지 2개는 false인 경우 스왑을 함
 * - 그렇게 구한 배열로 IN_DEGREE 계산 후 위상 정렬
 *
 * 절차지향 방법이라 별로인거 같음.. _2 풀이가 대단함
 */
public class BOJ27067_1 {
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N;
    static int[] IN_DEGREE;
    static boolean[][] A, B, C;

    public static void main(String[] args) throws IOException {
        input();

        pro();

        output();
    }

    private static void input() {
        N = inputProcessor.nextInt();
        A = new boolean[N + 1][N + 1];
        B = new boolean[N + 1][N + 1];
        C = new boolean[N + 1][N + 1];

        initData(A);
        initData(B);
        initData(C);

        IN_DEGREE = new int[N + 1];
    }

    private static void initData(boolean[][] edge) {
        for(int i = 1; i <= N; i++) {
            int data = inputProcessor.nextInt();

            for(int j = 1; j <= N; j++) {
                if(j == data) continue;
                if(!edge[j][data]) {
                    edge[data][j] = true;
                }
            }
        }
    }


    private static void pro() {
        correctEdge(A, B, C);
        fillInDegree(A, IN_DEGREE);
        topologicalSort();
    }

    private static void topologicalSort() {
        Deque<Integer> que = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(IN_DEGREE[i] == 0) {
                que.add(i);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();
            sb.append(cur).append(" ");

            for(int i = 1; i <= N; i++) {
                if(A[cur][i]) {
                    A[cur][i] = false;
                    IN_DEGREE[i] -= 1;
                    if(IN_DEGREE[i] == 0) que.add(i);
                }
            }
        }
    }

    private static void fillInDegree(boolean[][] arr, int[] inDegree) {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(arr[i][j]) {
                    inDegree[j] += 1;
                }
            }
        }
    }


    private static void correctEdge(boolean[][] a, boolean[][] b, boolean[][] c) {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(a[i][j] == true && b[i][j] == false && c[i][j] == false) {
                    swap(a, i, j);
                }else if(a[i][j] == false && b[i][j] == true && c[i][j] == false) {
                    swap(b, i, j);
                }else if(a[i][j] == false && b[i][j] == false && c[i][j] == true) {
                    swap(c, i, j);
                }
            }
        }
    }

    private static void swap(boolean[][] arr, int i, int j) {
        arr[i][j] = false;
        arr[j][i] = true;
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
