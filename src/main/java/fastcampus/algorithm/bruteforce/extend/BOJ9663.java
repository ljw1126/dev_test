package fastcampus.algorithm.bruteforce.extend;

import fastcampus.algorithm.MyReader;

/**
 * N-Queen (골드4)
 * https://www.acmicpc.net/problem/9663
 *
 * n 주어짐 ( 1 <= N < 15)
 *
 */
public class BOJ9663 {

    static int N, result;

    static int[] col;

    static void input() {
        MyReader scan = new MyReader();
        N = scan.nextInt();
        col = new int[N + 1];
    }

    static boolean attackAble(int r1, int c1, int r2, int c2) {
        if(r1 == r2 || c1 == c2) return true;
        if(Math.abs(r1 - r2) == Math.abs(c1 - c2)) return true;
        /*
        if(r1 - c1 == r2 - c2) return true;
        if(r1 + c1 == r2 + c2) return true;
         */

        return false;
    }
    static boolean isValid() {
        for(int i = 1; i <= N; i++) {
            // (i, col[i])
            for(int j = 1; j < i; j++) {
                // (j, col[j])
                if(attackAble(i, col[i], j, col[j])) {
                    return false;
                }
            }
        }

        return true;
    }

    // 전체 조합을 구한 다음 구하니 시간 초과 발생 가능
    static void dfs(int row) {
        if(row == N + 1) {
            if(isValid()) result += 1;
            return;
        }

        for(int c = 1; c <= N; c++) {
            col[row] = c;
            dfs(row + 1);
            col[row] = 0;
        }
    }

    static void recFunc(int row) {
        if(row == N + 1) {
            result += 1;
            return;
        }

        for(int c = 1; c <= N; c++) {
            boolean possible = true;
            for(int i = 1; i <= row - 1; i++) {
                if(attackAble(i, col[i], row, c)) {
                    possible = false;
                    break;
                }
            }

            if(!possible) continue; // 처음부터 가능한 경우만 고려

            col[row] = c;
            recFunc(row + 1);
            col[row] = 0;
        }
    }

    public static void main(String[] args) {
        input();

        recFunc(1);
        System.out.println(result);
    }
}