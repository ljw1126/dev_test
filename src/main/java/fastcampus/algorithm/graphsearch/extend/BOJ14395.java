package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 4연산(골드5) https://www.acmicpc.net/problem/14395
 *
 * 연산자 순은 *,+,-,/
 *
 * 실수
 * (1) Long 타입 사용하지 않음.
 * (2) visit배열을 사용시 메모리 초과 발생, 최대 10^9 이면 1Gb 정도 -> Set 자료 구조 사용
 */
public class BOJ14395 {
    static long S, T;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        br.close();
    }

    static class Node {
        long value;
        String operator;

        public Node(long value, String operator) {
            this.value = value;
            this.operator = operator;
        }
    }

    static void func(long start) {
        String[] operators = new String[]{"*", "+", "-", "/"};

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(start, ""));

        Set<Long> values = new HashSet<>();
        values.add(start);

        boolean find = false;
        while(!que.isEmpty()) {
            Node n = que.poll();
            if(n.value == T) {
                find = true;
                System.out.println(n.operator);
                break;
            }

            for(int i = 0; i < 4; i++) {
                String op = operators[i];
                if(n.value == 0 && op.equals("/")) continue; // / by zero

                long value = cal(n.value, op);
                if(!values.contains(value)) {
                    que.add(new Node(value, n.operator + op));
                    values.add(value);
                }
            }
        }

        if(!find) System.out.println(-1);
    }

    static long cal(long value, String op) {
        long result;
        if(op.equals("*")) {
            result = value * value;
        }else if(op.equals("+")) {
            result = value + value;
        }else if(op.equals("-")) {
            result = 0;
        } else {
            result = 1;
        }

        return result;
    }

    static void pro() {
        if(S == T) System.out.println(0);
        else func(S);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
