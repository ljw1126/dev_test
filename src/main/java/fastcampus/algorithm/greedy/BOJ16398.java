package fastcampus.algorithm.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16398 {
    static StringBuilder sb = new StringBuilder();

    static int N;

    static int[] parent;

    static List<Node> adj = new ArrayList<>();
    static class Node implements Comparable<Node> {
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
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if(cost == 0) continue;

                adj.add(new Node(i, j, cost));
            }
        }
    }

    static int getParent(int node) {
        if(parent[node] == node) return node;

        return parent[node] = getParent(parent[node]);
    }

    static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if(parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    // 부모가 같다는 것은 동일한 합집합 또는 사이클 형성 뜻하고, 같지 않다는 것은 연결 가능
    static boolean unionFind(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        return parentA == parentB;
    }

    static void pro() {
        Collections.sort(adj); // cost 오름차순 정렬, 크루스칼 알고리즘에서 가장 시간 복잡도 큰 부분

        // 부모 노드는 자기 자신으로 초기화
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        long result = 0L; // cost 최대 1억이므로 주의
        for(Node node : adj) {
            if(!unionFind(node.from, node.to)) {
                result += node.cost;
                union(node.from, node.to);
            }
        }

        System.out.println(result);

        System.out.println(Arrays.toString(parent));
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
}
