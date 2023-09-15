package fastcampus.algorithm.exam3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 정보상인 호석 (골5)
 * https://www.acmicpc.net/problem/22252
 *
 * - 직접 풀이
 * - 1번 행동은 push() 쿼리 1 은 지정 고릴라가 정보를 획득 (이때 내림차순 정렬)
 * - 2번 행동은 pop()이네 쿼리 2 는 지정 고릴라에게 가장 비싼 b개의 정보를 획득
 * - 고릴라가 가지는 정보의 가치는 내림차순 정렬이 필요할거 같다.
 * - Python이라고, 없는 고릴라를 호출할 때 처리가 필요하겠네
 */
public class BOJ22252 {
    static StringBuilder sb = new StringBuilder();

    static void process() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());

        Map<String, PriorityQueue<Integer>> storeMap = new HashMap<>();
        long ans = 0L; // long 처리 안해줘서 틀림

        while(Q > 0) {
            Q -= 1;

            st = new StringTokenizer(br.readLine());

            int queryNumber = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            storeMap.putIfAbsent(name, new PriorityQueue<>(Comparator.reverseOrder()));
            PriorityQueue<Integer> storeQue = storeMap.get(name);

            if(queryNumber == 1) {
                int k = Integer.parseInt(st.nextToken());
                for(int i = 1; i <= k; i++) {
                    storeQue.offer(Integer.parseInt(st.nextToken()));
                }
            } else {
                int request = Integer.parseInt(st.nextToken());

                if(storeQue.size() >= request) {
                    while(request > 0) {
                        request -= 1;
                        ans += storeQue.poll();
                    }
                } else {
                    while(!storeQue.isEmpty()) {
                        ans += storeQue.poll();
                    }
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        process();
    }
}
