package fastcampus.algorithm.examSet3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 영상처리 (실버2)
 * https://www.acmicpc.net/problem/21938
 *
 * - 문제가 이해 되지 않아 해설 참고하고 직접 풀이
 * 격자형 그래프 문자
 * 1) 우선 T 이상이면 255, 미만이면 0으로 치환 필요
 * 2) DFS로 255로 표기된 그룹 수 카운팅
 *
 * N개의 줄당 (R, G, B) *  M개씩 픽셀이 주어짐 주어짐
 * visited 방문배열을 사용하지 않고도, 0으로 방문처리 변경해서 간단하게 dfs 구현가능
 */
public class BOJ21938 {
    static StringBuilder sb = new StringBuilder();

    static int N, M, T;
    static int[][] pixels;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pixels = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                int sum = 0;
                for(int k = 1; k <= 3; k++) {
                    sum += Integer.parseInt(st.nextToken());
                }
                pixels[i][j] = sum / 3;
            }
        }

        T = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                pixels[i][j] = pixels[i][j] >= T ? 255 : 0;
            }
        }

        int result = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(pixels[i][j] == 255) {
                    marking(i, j);
                    result += 1;
                }
            }
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static void marking(int x, int y) {
        if(x < 1 || y < 1 || x > N || y > M) return;

        if(pixels[x][y] == 255) {
            pixels[x][y] = 0; // 방문처리

            for(int i = 0; i < 4; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];

                marking(dx, dy);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
