package fastcampus.algorithm.exam1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문자열 지옥에 빠진 호석
 *
 * (실수*) 어떤 문자열이 몇번 등장 했는지 기록할 변수 필요
 * M[문자열 S] = 문자열 S의 등장 횟수
 * 모든 탐색을 미리해서, M이라는 자료구조를 채워 둠
 * => HashMap을 사용하는게 모두 평균 O(1)만큼 소요
 *
 * 탐색 경우의 수
 * = 시작 위치 * (k 문자열 개수만큼 될 때까지 매번 8방향 가능) // 신이 좋아하는 문자열의 길이 : 최대 5
 * = NM * 8^k = 100 * 8^5 = 100 *  32768 (1초안에 가능)
 *
 * 실수
 * - 재귀 함수 종료 조건 실수 (K 개만큼 주어진다 했지, 신이 원하는 단어 길이가 k라고 안함)
 * - HashMap 자료구조 사용하여 전처리 하지 않고 매번 호출하여 시간 초과
 * - 2중 반복문으로 모든 노드에 대한 탐색을 해야하는 부분을 생각하는데 시간 오래 걸림
 *
 *  int dx = x + DIR[i][0]; --> (x + DIR[i][0]) >= N 이면 0으로
 *  int dy = y + DIR[i][1]; --> (y + DIR[i][1]) >= M 이면 0으로
 *
 *  if(dx < 0) dx = N - 1;
 *  if(dx >= N) dx = 0;
 *  if(dy < 0) dy = M - 1;
 *  if(dy >= M) dy = 0;
 *
 */
public class BOJ20166 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static char[][] wordMatrix;
    static List<String> queries = new ArrayList<>();

    static Map<String, Integer> wordMap = new HashMap<>();

    static int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 신이 좋아하는 문자열의 길이

        wordMatrix = new char[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] words = st.nextToken().toCharArray();
            for(int j = 0; j < M; j++) {
                wordMatrix[i][j] = words[j];
            }
        }

        for(int i = 1; i <= K; i++) {
            queries.add(br.readLine()); // 신이 좋아하는 문자열
        }
    }

    static void rec(int x, int y, int len, String word) {
        if(wordMap.containsKey(word)) {
            wordMap.put(word, wordMap.get(word) + 1);
        } else {
            wordMap.put(word, 1);
        }

        if(len == 5) return; // K줄 단어 길이 K라고 하진 않음;

        for(int i = 0; i < 8; i++) {
            int dx = (x + DIR[i][0]) % N;
            int dy = (y + DIR[i][1]) % M;

            if(dx < 0) dx += N;
            if(dy < 0) dy += M;

            rec(dx, dy, len + 1, word + wordMatrix[dx][dy]);
        }
    }

    static void pro() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                rec(i, j, 1, wordMatrix[i][j] + "");
            }
        }

        for(String query : queries) {
            sb.append(wordMap.getOrDefault(query, 0)).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
