package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 네트워크 연결(골드4)
 * https://www.acmicpc.net/problem/1922
 *
 * - 최소 신장 트리 (MST, minimun spanning tree)
 * - 모든 노드가 연결된 트리이므로 간선의 개수는 '노드 - 1'개가 된다.
 */
public class BOJ1922 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static List<Node> adj = new ArrayList<>();

    static int[] parent;

    static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return cost - other.cost; // 오름차순
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 컴퓨터의 수(노드)
        M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수 (간선)


        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());


            adj.add(new Node(from, to, cost));
        }

        // 부모 노드는 자기 자신으로 초기화
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    // 정점에 연결된 부모 정점을 반환
    static int getParent(int node) {
        if(parent[node] == node) return node; // 재귀 종료 조건

        return parent[node] = getParent(parent[node]); // 여기서 최상위 부모 노드 갱신해주네
    }


    // 두 정점을 연결 (합집합)
    static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    // 두 정점이 같은 그래프(합집합) 내에 있는가
    static boolean unionFindParent(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        return parentA == parentB; // 부모가 같다면 동일한 그래프(합집합)
    }

    static void pro() {
        Collections.sort(adj);

        int result = 0;
        for(Node node : adj) {
            if(!unionFindParent(node.from, node.to)) {
                result += node.cost;
                union(node.from, node.to);
            }

            /*
            System.out.println(node);
            System.out.println(Arrays.toString(parent));
             */
        }

        System.out.println(result);

        //System.out.println(Arrays.toString(parent));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
