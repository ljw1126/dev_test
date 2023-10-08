package fastcampus.algorithm.examSet3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 가운데에서 만나기(골드4)
 * https://www.acmicpc.net/problem/21940
 *
 * - 플로이드 워셜로 시간 구하는데, k, i, j 선언도 틀림
 * - "왕복 시간들 중 최대가 최소가 되는 도시 X를 선택"에서 최대가를 이해하지 못해 틀림
 *
 */
public class BOJ21940 {
    static StringBuilder sb = new StringBuilder();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수

        int[][] cities = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) Arrays.fill(cities[i], 200001);

        for(int i = 1; i <= N; i++) cities[i][i] = 0;

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            cities[from][to] = time;
        }

        for(int k = 1; k <= N; k++) { // 플로이드
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    cities[i][j] = Math.min(cities[i][j], cities[i][k] + cities[k][j]);
                }
            }
        }

        int K = Integer.parseInt(br.readLine()); // 친구 인원
        st = new StringTokenizer(br.readLine());
        List<Integer> friends = new ArrayList<>();
        for(int i = 1; i <= K; i++) {
            friends.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> candidate = new ArrayList<>();
        int result = Integer.MAX_VALUE;
        for(int c = 1; c <= N; c++) {
            int total = 0; // 준형이와 친구들의 왕복 시간들 중 최대값
            for(int f : friends) {
                total = Math.max(total, cities[f][c] + cities[c][f]); // 왕복시간 최대
            }

            if(total < result) { // 왕복 시간들 중 최대가 최소가 되는 도시
                result = total;

                candidate.clear();
                candidate.add(c);
            } else if(total == result) {
                candidate.add(c);
            }
        }

        //for(int i = 1; i <= N; i++) System.out.println(Arrays.toString(cities[i]));

        for(int i : candidate) sb.append(i).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws Exception {
        input();
    }
}
