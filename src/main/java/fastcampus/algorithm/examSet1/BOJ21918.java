package fastcampus.algorithm.examSet1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 전구(브론즈2)
 * https://www.acmicpc.net/problem/21918
 *
 * 1번 명령 1 [i x] : i번째 전구의 상태를 x로 변경한다.
 * 2번 명령 2 [l r] : l ~ r 까지의 전구의 상태를 변경한다.
 * 3번 명령 3[l r] : l ~ r 까지의 전구를 끈다.
 * 4번 명령 4 [l r] : l ~ r 까지의 전구를 킨다
 */
public class BOJ21918 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 전구의 개수
        M = Integer.parseInt(st.nextToken()); // 명령의 개수

        boolean[] light = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            light[i] = st.nextToken().equals("0") ? false : true;
        }

        while(M > 0) {
            M -= 1;

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 명령어
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1) {
                light[b] = c == 1;
            }else if(a == 2) {
                for(int i = b; i <= c; i++) {
                    light[i] = !light[i];
                }
            }else if(a == 3) {
                for(int i = b; i <= c; i++) {
                    light[i] = false;
                }
            }else if(a == 4) {
                for(int i = b; i <= c; i++) {
                    light[i] = true;
                }
            }
        }


        for(int i =1; i <= N; i++) {
            sb.append(light[i] ? 1 : 0).append(" ");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws Exception {
        input();
    }
}
