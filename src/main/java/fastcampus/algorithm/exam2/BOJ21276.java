package fastcampus.algorithm.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 직접 풀지 못함 ..
 *
 * 조상의 수 == depth라는 것을 생각하지 못하고
 * 위상정렬, 부모, depth 구하는 거에 매몰되서 시간 소비
 *
 * 자식 - 조상 주어질 때
 * -- 자식은 depth를 알 수 있다
 * -- 만약 조상이 없는 노드는 root 노드이다
 *
 */
public class BOJ21276 {

    static StringBuilder sb = new StringBuilder();

    static Map<String, Integer> data = new HashMap<>();
    static int N, M;

    static List<Integer>[] adj;

    static String[] names;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;



        N = Integer.parseInt(br.readLine()); // 노드 수
        st = new StringTokenizer(br.readLine());
        names = new String[N + 1];
        for(int i = 1; i <= N; i++) {
            String name = st.nextToken();
            data.put(name, i);
            names[i] = name; // 안함 ..
        }

        M = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) { // x 의 조상 중 y가 있다
            st = new StringTokenizer(br.readLine());
            String x = st.nextToken();
            String y = st.nextToken();

            adj[data.get(x)].add(data.get(y)); // 자손 = [조상, ..], 반대로 함
        }
    }

    // 위상 정렬까지 필요 없음
    static void pro() {
        List<String> roots = new ArrayList<>();
        List<String>[] child = new ArrayList[N + 1];
        for(int i = 1; i <= N ; i++) child[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            if(adj[i].isEmpty()) { // 기억하는 조상이 없다는 것은 해당 정점이 시조 중에 하나이다.
                roots.add(names[i]);
            }

            for(int j : adj[i]) {
                int depthA = adj[i].size(); // 조상 수만큼 depth를 나타내는 거니
                int depthB = adj[j].size();

                if(depthA - 1 == depthB) { // j가 i의 조상이다, i의 조상은 j 이다
                    child[j].add(names[i]);
                }
            }
        }

        sb.append(roots.size()).append("\n");
        Collections.sort(roots);
        for(String r : roots) sb.append(r).append(" ");

        sb.append("\n");

        Arrays.sort(names, 1, N + 1); // to : exclusive
        for(int i =1; i <= N; i++) {
            String name = names[i];
            int idx = data.get(name);
            sb.append(name).append(" ").append(child[idx].size()).append(" ");

            Collections.sort(child[idx]);
            for(String c : child[idx]) sb.append(c).append(" ");

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}
