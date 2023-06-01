package fastcampus.algorithm.sort.extend;

import fastcampus.algorithm.MyReader;

import java.util.Arrays;

/**
 * 수열정렬(실버4) https://www.acmicpc.net/problem/1015
 *
 * N <= 50 이하이므로 int 가능
 *
 * 시간 복잡도 : O(NlogN)
 * 공간 복잡도: O(N)
 *
 * 객체 정렬의 경우 O(NlogN)이고, stable함 => 순서가 보장된다는 뜻
 */
public class BOJ1015 {
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();
    static int N;
    static Element[] A;
    static int[] P;

    static class Element implements Comparable<Element>{
        public int value;
        public int idx;

        @Override
        public int compareTo(Element other) {
            return value - other.value;
        }
    }
    static void input() {
        N = scan.nextInt();

        P = new int[N];
        A = new Element[N];
        for(int i = 0; i < N; i++) {
            A[i] = new Element();
            A[i].value = scan.nextInt();
            A[i].idx = i;
        }
    }

    static void pro() {
        // A 배열 정렬 => B 배열됨
        Arrays.sort(A);

        for(int i = 0; i < N; i++) {
            P[A[i].idx] = i;
        }

        for(int i = 0; i < N; i++) {
            sb.append(P[i]).append(' ');
        }

        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        input();
        pro();
    }
}
