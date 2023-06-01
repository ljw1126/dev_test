package fastcampus.algorithm.sort.extend;

import fastcampus.algorithm.MyReader;

import java.util.Set;
import java.util.TreeSet;

/**
 * 단어정렬 (실버5) https://www.acmicpc.net/problem/1181
 *
 * N = 최대 2만
 *
 * 직접 품
 *
 * 중복 x
 * 1. 길이가 짧은 것, 2. 길이가 같으면 사전 순으로 정렬
 */
public class BOJ1181 {
    static MyReader scan = new MyReader();

    static StringBuilder sb = new StringBuilder();
    static int N;

    static Set<String> strings = new TreeSet<>((a, b) -> {
        if(a.length() == b.length()) return a.compareTo(b);
        return a.length() - b.length();
    });

    static void input() {
        N = scan.nextInt();

        for(int i = 0; i < N; i++) {
            strings.add(scan.next());
        }
    }


    static void pro() {
        for(String s : strings) {
            sb.append(s).append("\n");
        }

        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        input();
        pro();
    }
}
