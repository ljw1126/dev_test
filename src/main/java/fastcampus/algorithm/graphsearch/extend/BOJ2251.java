package fastcampus.algorithm.graphsearch.extend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 물통 (골드5) https://www.acmicpc.net/problem/2251
 *
 * 그래프에 대한 언질이 없음
 *
 * 상태(정점), 물을 부어서 다른 상태로 이동하는 것을 "간선"으로 정의
 *
 * 200 * 200 * 200 = 8 * 10^6 = 800만 가지
 *
 * 정점 개수  8 * 10^6
 * 간선 개수 8 * 10^6 * 6 ( A => B,C 붙는 경우, B => A,C붙는 경우, C => A, C붙는 경우)
 *
 * 시간복잡도 = 8 * 10^6
 */
public class BOJ2251 {
    static StringBuilder sb = new StringBuilder();

    static int [] LIMIT = new int[3];
    static boolean[][][] VISIT = new boolean[201][201][201];

    static List<Integer> RESULT = new ArrayList<>();
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 3; i++) {
            LIMIT[i] = Integer.parseInt(st.nextToken());
        }
    }

    static class State {
        public int[] liquid;

        public State(int[] _liquid) {
            liquid = new int[3];
            for(int i = 0; i < 3; i++) {
                liquid[i] = _liquid[i];
            }
        }

        public State moveTo(int from, int to, int[] _limit) {
            int[] _liquid = new int[]{liquid[0], liquid[1], liquid[2]};

            if(_liquid[from] + _liquid[to] >= _limit[to]) {
                _liquid[from] -= (_limit[to] - _liquid[to]);
                _liquid[to] = _limit[to];
            } else {
                _liquid[to] += _liquid[from];
                _liquid[from] = 0;
            }

            return new State(_liquid);
        }
    }

    static void dfs(State state) {
        VISIT[state.liquid[0]][state.liquid[1]][state.liquid[2]] = true;
        if(state.liquid[0] == 0) RESULT.add(state.liquid[2]);

        for(int from = 0; from < 3; from++) {
            for(int to = 0; to < 3; to++) {
                if(from == to) continue;

                State next = state.moveTo(from, to, LIMIT);
                if(VISIT[next.liquid[0]][next.liquid[1]][next.liquid[2]]) continue;

                dfs(next);
            }
        }
    }

    static void bfs(State state) {
        Queue<State> que = new LinkedList<>();
        que.add(state);
        VISIT[state.liquid[0]][state.liquid[1]][state.liquid[2]] = true;

        while(!que.isEmpty()) {
            State current = que.poll();
            if(current.liquid[0] == 0) RESULT.add(current.liquid[2]);

            for(int from = 0; from < 3; from++) {
                for(int to = 0; to < 3; to++) {
                    if(from == to) continue;

                    State next = current.moveTo(from, to, LIMIT);
                    if(VISIT[next.liquid[0]][next.liquid[1]][next.liquid[2]]) continue;

                    VISIT[next.liquid[0]][next.liquid[1]][next.liquid[2]] = true;
                    que.add(next);
                }
            }
        }
    }

    static void pro() {
        //dfs(new State(new int[]{0, 0, LIMIT[2]}));
        bfs(new State(new int[]{0, 0, LIMIT[2]}));

        Collections.sort(RESULT);
        for(int n : RESULT) sb.append(n).append(" ");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
