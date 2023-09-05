package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 도시건설 (골드4)
 * https://www.acmicpc.net/problem/21924
 *
 * - 직접 풀이
 * - 크루스칼 알고리즘 (시간 복잡도 : ElogE)
 * - 모든 노드가 연결 되어 있지 않다면 -1 출력 -> parent 가 모두 동일하지 않으면 종료
 * - 비용 최대치는 일자로 연결되어 있고 노드가 10^5개, 비용이 각각 10^6인 경우 10^11 예상되어 long으로 처리
 * - *입력 초기화시 총 비용을 구함
 * - *크루스칼 알고리즘 수행시 연결 간선의 개수를 카운팅함
 * - *전체 노드수 - 1 == 연결 간선 수 인 경우 최소 신장 트리가 완료되었다고 판단 가능
 *
 * 쓸데없이 stream api 사용해서 추후 계산하려 했는데, 이렇게 줄일 수 있었다는 것을 깨달음
 */
public class BOJ21924 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static long totalCost;
    static List<Info> information = new ArrayList<>();

    static int[] parent;

    static class Info implements Comparable<Info> {
        int from;
        int to;
        int cost;

        public Info(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info other) {
            return cost - other.cost; // 오름차순 (-1)
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getCost() {
            return cost;
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물의 개수 (노드)
        M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선)

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            information.add(new Info(from, to, cost));
            totalCost += cost;
        }
    }

    static int getParent(int node) {
        if(parent[node] == node) return parent[node];

        return parent[node] = getParent(parent[node]);
    }

    static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    // 같은 경우 동일한 그래프 속해 있기 때문에 사이클 형성 가능
    static boolean unionFindParent(int a, int b) {
        return getParent(a) == getParent(b);
    }

    static void pro() {
        Collections.sort(information); // 비용 오름 차순

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i]= i;

        long minTotalCost = 0L;
        int edgeCnt = 0;
        for(Info info : information) {
            if(!unionFindParent(info.from, info.to)) {
                minTotalCost += info.cost;
                edgeCnt += 1;

                union(info.from, info.to);
            }
        }

        if(edgeCnt == N - 1) { // 간선의 개수가 노드 - 1 이면 트리가 완성됨
            sb.append(totalCost - minTotalCost);
        } else {
            sb.append("-1");
        }

        System.out.println(sb.toString());

        System.out.println(Arrays.toString(parent));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
