package fastcampus.algorithm.sort.extend;

import fastcampus.algorithm.MyReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 화살표 그리기(실버4) https://www.acmicpc.net/problem/15970
 *
 * 각 점의 N개의 색깔 중 하나를 가짐
 * - 순자적으로 순회하는데 왼쪽, 오른쪽 중 짧은 쪽에 화살표 그리기
 * - 이때 처음과 마지막은 단방향 1개씩
 */
public class BOJ15970 {
    static int N;

    static List<List<Integer>> arr;

    // 참고. https://steady-coding.tistory.com/52
    /*
    static void pro() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int point = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());

            arr.get(color).add(point);
        }

        for(int i = 0; i <= N; i++) {
            Collections.sort(arr.get(i));
        }

        int ans = 0;
        for(int i = 1; i <= N; i++) {
            List<Integer> points = arr.get(i);
            for(int j = 0; j < points.size(); j++) {
                if(j == 0) {
                    ans += points.get(j + 1) - points.get(j);
                } else if(j == points.size() - 1) {
                    ans += points.get(j) - points.get(j - 1);
                } else {
                    int prev = points.get(j) - points.get(j - 1);
                    int next = points.get(j + 1) - points.get(j);

                    ans += Math.min(prev, next);
                }
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    */

    static MyReader scan = new MyReader();
    static List<Integer>[] A;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int point = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());

            A[color].add(point);
        }

        br.close();
    }

    static int calLeft(List<Integer> arr, int idx) {
        if(idx == 0) return Integer.MAX_VALUE;

        return arr.get(idx) - arr.get(idx - 1);
    }

    static int calRight(List<Integer> arr, int idx) {
        if(idx == arr.size() - 1) return Integer.MAX_VALUE;

        return arr.get(idx + 1) - arr.get(idx);
    }
    static void pro() {
        for(int i = 1; i <= N; i++) Collections.sort(A[i]);

        int ans = 0;
        for(int i = 1; i <= N; i++) {
            if(A[i].size() > 0) {
                for(int j = 0; j < A[i].size(); j++) {
                    int left = calLeft(A[i], j);
                    int right = calRight(A[i], j);

                    ans += Math.min(left, right);
                }
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception{
        input();
        pro();
    }
}
