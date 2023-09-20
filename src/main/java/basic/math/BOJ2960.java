package basic.math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 에라토스테네스의 체(실버4)
 * https://www.acmicpc.net/problem/2960
 *
 * - 소수 판별 알고리즘
 * - 여러 개의 소수가 있을 때 빠르게 소수를 구한다
 * - 해당 문제는 소수 P를 지우고, 원래는 지우지 않음
 *
 * 절차.
 * 2 ~ N 까지 있을 때 순차 조회하면서
 * 1) 가장 먼저 소수를 판별할 범위만큼 배열을 할당해 그 인덱스에 해당하는 값을 넣어준다
 * 2) 2부터 시작해서 특정 숫자의 배수에 해당하는 숫자들을 모두 지운다 (1은 소수가 아니므로 제외)
 * 3) 3의 배수를 지울 떄 이미 지워진 숫자의 경우 건너뜀
 * 4) 2부터 남아있는 숫자를 출력
 */
public class BOJ2960 {
    static StringBuilder sb = new StringBuilder();

    static void process() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // K번째 지워진 수를 출력한다.

        int[] data = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            data[i] = i;
        }

        int cnt = 0;
        Loop1 :
        for(int i = 2; i <= N; i++) {
            if(data[i] == 0) continue;
            for(int j = i; j <= N; j += i) {
                if(data[j] == 0) continue;

                data[j] = 0;
                cnt += 1;

                if(cnt == K) {
                    sb.append(j);
                    break Loop1;
                }
            }
        }

        System.out.println(sb.toString());
    }

    static void pro() {

    }

    public static void main(String[] args) throws Exception {
        process();
        pro();
    }

}
