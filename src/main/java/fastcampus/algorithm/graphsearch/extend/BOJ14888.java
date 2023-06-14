package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 연산자 끼워넣기(실버1) https://www.acmicpc.net/problem/14888
 *
 * 수열 N개가 주어지고 연산자가 N - 1개 주어짐
 * 첫번째 - 기술블로그 참고
 * 두번째, 세번째 - 직접 품
 */
public class BOJ14888 {

    static StringBuilder sb = new StringBuilder();

    static int N, ADD, SUB, MUL, DIV;

    static int MAX_VALUE = Integer.MIN_VALUE;
    static int MIN_VALUE = Integer.MAX_VALUE;

    static int[] operand;

    static int[] operator;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        operand = new int[N];
        for(int i = 0; i < N; i++) {
            operand[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        operator = new int[4];
        for(int i = 0; i < 4; i++) {
            int value = Integer.parseInt(st.nextToken());
            if(i == 0) ADD = value;
            else if(i == 1) SUB = value;
            else if(i == 2) MUL = value;
            else DIV = value;
        }

        br.close();
    }

    static void dfs(int idx, int data) {
        if(idx == N) {
            MIN_VALUE = Math.min(MIN_VALUE, data);
            MAX_VALUE = Math.max(MAX_VALUE, data);
        } else {

            // +
            if(ADD > 0) {
                ADD -= 1;
                dfs(idx + 1,  data + operand[idx]);
                ADD += 1;
            }

            // -
            if(SUB > 0) {
                SUB -= 1;
                dfs(idx + 1,  data - operand[idx]);
                SUB += 1;
            }

            // *
            if(MUL > 0) {
                MUL -= 1;
                dfs(idx + 1, data * operand[idx]);
                MUL += 1;
            }

            // /
            if(DIV > 0) {
                DIV -= 1;
                dfs(idx + 1, data / operand[idx]);
                DIV += 1;
            }
        }
    }


    static void pro() {
        dfs(1, operand[0]);

        sb.append(MAX_VALUE).append("\n").append(MIN_VALUE);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    /*
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int MAX_VALUE = Integer.MIN_VALUE;
    static int MIN_VALUE = Integer.MAX_VALUE;

    static int[] operand;

    static int[] operator;

    static int[] selected;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        operand = new int[N];
        for(int i = 0; i < N; i++) {
            operand[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        operator = new int[4];
        for(int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        selected = new int[N - 1]; // 연산자는 피연산자 개수에 -1한 값

        br.close();
    }

    static int cal(int v1, int v2, int _op) {
        int result = 0;
        switch(_op) {
            case 0 : {
                result = v1 + v2;
                break;
            }
            case 1 : {
                result = v1 - v2;
                break;
            }
            case 2 : {
                result = v1 * v2;
                break;
            }
            case 3 : {
                result = v1 / v2;
                break;
            }
        }

        return result;
    }

    static void dfs(int cnt) {
        if(cnt == N - 1) {
            int result = operand[0];

            for(int i = 1; i < N; i++) {
                result = cal(result, operand[i], selected[i - 1]);
            }

            MAX_VALUE = Math.max(MAX_VALUE, result);
            MIN_VALUE = Math.min(MIN_VALUE, result);

            return;
        }

        for(int i = 0; i < 4; i++) {
            if(operator[i] == 0) continue;

            operator[i] -= 1;
            selected[cnt] = i;

            dfs(cnt + 1);

            selected[cnt] = 0;
            operator[i] += 1;
        }
    }


    static void pro() {
        dfs(0);

        sb.append(MAX_VALUE).append("\n").append(MIN_VALUE);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

     */

    /*
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int MAX_VALUE = Integer.MIN_VALUE;
    static int MIN_VALUE = Integer.MAX_VALUE;

    static List<Integer> operand = new ArrayList<>(); // 피연산자

    static List<String> operators = new ArrayList<>();

    static String[] op = new String[]{"+", "-", "*", "/"};

    static List<String> selected = new ArrayList<>();

    static boolean[] visit;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            operand.add(Integer.parseInt(st.nextToken()));
        }

        visit = new boolean[N - 1];  // 연산자는 N - 1개가 주어짐

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            if(cnt == 0) continue;

            for(int j = 0; j < cnt; j++) operators.add(op[i]);
        }

        br.close();
    }

    static int cal(int v1, int v2, String _op) {
        int result = 0;
        switch(_op) {
            case "+" : {
                result = v1 + v2;
                break;
            }
            case "-" : {
                result = v1 - v2;
                break;
            }
            case "*" : {
                result = v1 * v2;
                break;
            }
            case "/" : {
                result = v1 / v2;
                break;
            }
        }

        return result;
    }

    static void dfs(int cnt) {
        if(cnt == N - 1) {
            int result = operand.get(0);

            for(int i = 1; i < N; i++) {
                result = cal(result, operand.get(i), selected.get(i - 1));
            }

            MAX_VALUE = Math.max(MAX_VALUE, result);
            MIN_VALUE = Math.min(MIN_VALUE, result);

            return;
        }

        for(int i = 0; i < N - 1; i++) {
            if(visit[i]) continue;

            selected.add(operators.get(i));
            visit[i] = true;

            dfs(cnt + 1);

            visit[i] = false;
            selected.remove(selected.size() - 1);
        }
    }


    static void pro() {
        dfs(0);

        sb.append(MAX_VALUE).append("\n").append(MIN_VALUE);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

     */
}
