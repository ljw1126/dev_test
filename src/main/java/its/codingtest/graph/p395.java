package its.codingtest.graph;

import its.codingtest.Practice;

import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * 4
 * 3
 * 4
 * 1
 * 1
 *
 * 출력
 * 2
 *
 * 0 1 2 3 4 parent 배열을 탑승구로 함
 * - "가장 큰 탑승구에 먼저 넣는다" 할 때
 * - 1번 비행기를 4번에 넣는다
 * - 4번의 부모는 자기 자신이므로 4, 3을 union하여 하나 차지했다는걸 표시
 *
 * - 2번 비행기는 1번에 넣는다
 * - 1번의 부모는 자기 자신이므로 1, 0을 union한다
 *
 * - 3번 비행기를 1번에 넣는다
 * - 1번의 부모는 0이므로 더이상 넣을 공간이 없음을 의미하므로 종료
 *
 */
public class p395 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    public static void main(String[] args) {
        input();
        pro();
        output();
    }

    private static int G, P;

    private static void input() {
        G = inputProcessor.nextInt(); // 탑승구의 수
        P = inputProcessor.nextInt(); // 비행기의 수
    }

    private static void pro() {
        int[] parent = new int[G + 1]; // 탑승구에 비행기를 넣을 수 있으면 n - 1, n 을 합집합
        for(int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int result = 0;
        while(P > 0) {
            int n = inputProcessor.nextInt(); // 가장 큰 출구에 도킹
            int p = findParent(n, parent);

            if(p == 0) break; // 부모 노드가 0이면 더이상 도킹할 공간이 없다는 의미

            union(n, n - 1, parent); // n 과 n - 1을 합집합해서 연결 
            result += 1;
            P -= 1;
        }

        sb.append(result);
    }

    private static int findParent(int n, int[] parent) {
        if(parent[n] == n) return n;

        return parent[n] = findParent(parent[n], parent);
    }

    private static void union(int a, int b, int[] parent) {
        int _a = findParent(a, parent);
        int _b = findParent(b, parent);
        if(_a < _b) {
            parent[_b] = _a;
        } else {
            parent[_a] = _b;
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
