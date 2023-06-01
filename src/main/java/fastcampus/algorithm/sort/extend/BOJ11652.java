package fastcampus.algorithm.sort.extend;

import fastcampus.algorithm.MyReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 카드(실버4) https://www.acmicpc.net/problem/11652
 *
 * 숫자 범위가 int를 초과하므로 long을 사용해야 함!
 *
 * 정렬 : O(NlogN)
 * 카운티 세기: O(N)
 *
 * 시간 복잡도 : O(NlogN) => HashTable 사용하면 O(N) https://creampuffy.tistory.com/124
 * 공간 복잡도 : O(N)
 */
public class BOJ11652 {
    static MyReader scan = new MyReader();

    static int N;
    static long[] a;

    static void input() {
        N = scan.nextInt();
        a = new long[N];
        for(int i = 0; i < N; i++) {
            a[i] = scan.nextLong();
        }
    }

    /**
     * Current Count = 지금 보고 있는 숫자가 등장한 횟수
     * Mode Count = 지금까지의 최빈값의 등장 횟수
     * Mode = 지끔까지의 최빈값
     */
    static void pro() {
        Arrays.sort(a);

        long mode = a[0];
        int modeCount = 1, currentCount = 1;

        for(int i = 1; i < N; i++) {
            if(a[i-1] == a[i]) {
                currentCount += 1;
            } else {
                currentCount = 1;
            }

            if(modeCount < currentCount) {
                modeCount = currentCount;
                mode = a[i];
            }
        }

        System.out.println(mode);
    }

    static void proByHashMap() {
        Arrays.sort(a);

        Map<Long, Integer> cardMap = new HashMap<>();
        int maxCount = 0;
        long result = 0L;
        for(long i : a) {
            cardMap.put(i, cardMap.getOrDefault(i, 0) + 1);

            if(cardMap.get(i) > maxCount) {
                maxCount = cardMap.get(i);
                result = i;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        input();
        proByHashMap();
    }
}
