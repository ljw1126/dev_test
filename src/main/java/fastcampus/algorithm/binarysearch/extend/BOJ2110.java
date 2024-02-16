package fastcampus.algorithm.binarysearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 공유기 설치(골드4) https://www.acmicpc.net/problem/2110
 *
 *
 * 집의 개수 N : 2 ~ 20만
 * 공유기 개수 : 2 ~ 20만
 * 집의 좌표 수 : 0 ~ 10억
 *
 * 문제 : 공유기 C개를 설치할 떄 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.
 * <-> 어떤 거리 D를 둘때, 공유기 C개를 설치가능한가 ? yes/no 이때 최대가 되는 D는 얼마인가
 * (매개변수 탐색)
 */
public class BOJ2110 {
    static int N, C;
    static int[] houses;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 집의 개수
        C = Integer.parseInt(st.nextToken()); // 공유기 개수

        houses = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            houses[i] = Integer.parseInt(st.nextToken());
        }
    }

    /**
     *그리드 기법 사용하여 정렬 후 제일 왼쪽(1번)집 기준으로 조건 만족하는지 조회 : O(N)
     *
     * D만큼 거리 차이를 둔다면 C개 만큼의 공유기를 설치할 수 있는가? yes/no
     */
    static boolean isValidDistance(int D) {

        int cnt = 1, last = houses[1];

        for(int i = 2; i <= N; i++) {
            if(houses[i] - last >= D) {
                cnt += 1;
                last = houses[i];
            }
        }

        return cnt >= C;
    }

    static void pro() {
        Arrays.sort(houses, 1, N + 1);

        int result = 0;
        int L = 0, R = 1000000000;
        while(L <= R) {
            int distance = (L + R) / 2;
            if(isValidDistance(distance)) {
                L = distance + 1;
                result = distance;
            } else {
                R = distance - 1;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
