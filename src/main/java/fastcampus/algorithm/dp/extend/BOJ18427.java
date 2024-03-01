package fastcampus.algorithm.dp.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 함께 블록 쌓기 (골드4) https://www.acmicpc.net/problem/18427
 *
 * 다시 풀어보기
 * O(N * M * H) = O(50 * 10 * 1,000)
 */
public class BOJ18427 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, H;

    static List<Integer>[] student;

    static int[][] DP;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 학생당 가질 수 있는 최대 블록 수
        H = Integer.parseInt(st.nextToken()); // H 높이를 가지는 경우의 수를 구하게 됨

        student = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) student[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            String[] blocks = br.readLine().split(" ");
            for(int j = 0; j < blocks.length; j++) {
                student[i].add(Integer.parseInt(blocks[j]));
            }
        }
    }

    static void pro() {
        DP = new int[N + 1][H + 1];

        for(int i = 1; i <= N; i++) { // 학생 수 만큼 반복
            DP[i - 1][0] = 1;

            // 합산하여 구할 수 있는 경우
            for(int block : student[i]) {
                for(int h = block; h <= H; h++) {
                    DP[i][h] += DP[i - 1][h - block];
                    DP[i][h] %= 10007;
                }
            }

            // 이전 블록만으로 구할 수 있는 경우
            for(int h = 1; h <= H; h++) {
                DP[i][h] += DP[i - 1][h];
                DP[i][h] %= 10007;
            }
        }

        System.out.println(DP[N][H]);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
