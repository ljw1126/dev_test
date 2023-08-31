package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ20166_SOL {
    static StringBuilder sb = new StringBuilder();

    static int n, m, k;
    static String[] board;
    static int[] dx = {0, 0, -1, -1, -1, 1, 1, 1};
    static int[] dy = {1, -1, -1, 0, 1, -1, 0, 1};
    static Map<String, Integer> wordMap = new HashMap<>();

    static List<String> queries = new ArrayList<>();

    static PrintWriter pw = new PrintWriter(System.out);

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 주어진 문자 개수 (길이x)

        board = new String[n];
        for(int i = 0; i < n; i++) {
            board[i] = br.readLine();
        }

        while(k > 0) {
            k -= 1;
            queries.add(br.readLine());
        }
    }

    static void dfs(int i, int j, String word, int len) {
        if(wordMap.containsKey(word)) wordMap.put(word, wordMap.get(word) + 1);
        else wordMap.put(word, 1);

        // 최대 5개를 밟으면 더 이상 탐색 하지 않는다
        if(len == 5) return;

        for(int dir = 0; dir < 8; dir++) {
            int ni = (i + dx[dir]) % n; // (i + dx[dir]) >= n 인 경우 0 으로
            int nj = (j + dy[dir]) % m; // (j + dy[dir]) >= m 인 경우 0 으로
            if(ni < 0) ni += n;
            if(nj < 0) nj += m;

            dfs(ni, nj, word + board[ni].charAt(nj), len + 1);
        }
    }

    static void pro() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // (i, j)에서 시작했을 때 만들 수 있는 문자열의 모든 경우 파악하기
                dfs(i, j, Character.toString(board[i].charAt(j)), 1);
            }
        }

        for(String q : queries) {
            sb.append(wordMap.getOrDefault(q, 0)).append("\n");
        }

        pw.println(sb.toString());
        pw.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
