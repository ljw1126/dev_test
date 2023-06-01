package fastcampus.algorithm.sort.extend;

import fastcampus.algorithm.MyReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 파일정리(실버3) https://www.acmicpc.net/problem/20291
 *
 * 파일 확장자 별로 카운티 후 확장자명으로 오름차순 정렬
 *
 * N 최대 5만
 */
public class BOJ20291 {

    static MyReader scan = new MyReader();

    static StringBuilder sb = new StringBuilder();

    static int N;

    static Map<String, Integer> extMap = new HashMap<>();

    static class Element implements Comparable<Element> {
        public String ext;
        public int count;

        public Element(String ext, int count) {
            this.ext = ext;
            this.count = count;
        }

        @Override
        public int compareTo(Element other) {
            return ext.compareTo(other.ext);
        }

        @Override
        public String toString() {
            return ext + ' ' + count;
        }
    }
    static void input() {
        N = scan.nextInt();

        for(int i = 0; i < N; i++) {
            String fileName = scan.next();
            String ext = fileName.split("\\.")[1];

            extMap.put(ext, extMap.getOrDefault(ext, 0) + 1);
        }
    }

    static void pro() {
        List<Element> el = new ArrayList<>();
        for(String key : extMap.keySet()) {
            el.add(new Element(key, extMap.get(key)));
        }

        Collections.sort(el);

        for(Element e : el) {
            sb.append(e.toString()).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
