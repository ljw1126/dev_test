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
 * - 두번째 직접 풀이
 * - 조상이 없는 경우 root에 해당하는 것은 생각해냄
 * - depth 차이로 자식을 구하는데 있어, for-loop로 +1 처리 했는데
 * 이또한 입력 조상정보를 활용하여 depths를 구할 수 있었고 루프 한번 돌아서 roots, childs
 * 다 구할 수 있었음
 */
public class BOJ21276_2 {
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static String[] names;

    static Map<String, Integer> nameIdx;
    static List<Integer>[] ancestor;

    static List<String>[] childs;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 사람의 수

        names = new String[N];
        nameIdx = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            String name = st.nextToken();
            names[i] = name;
            nameIdx.put(name, i); // {이름 : 인덱스}
        }


        M = Integer.parseInt(br.readLine()); // 기억하고 있는 정보 개수 X 조상 중에 Y가 있다

        ancestor = new ArrayList[N]; // 조상 정보
        childs = new ArrayList[N]; // 자식 정보 기록
        for(int i = 0; i < N; i++) {
            ancestor[i] = new ArrayList<>();
            childs[i] = new ArrayList();
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            String _child = st.nextToken();
            String _ancestor = st.nextToken();

            ancestor[nameIdx.get(_child)].add(nameIdx.get(_ancestor)); // 해당 idx의 조상 정보 기록
        }
    }

    static void pro() {
        // #1. 조상이 없으면 root 해당
        List<String> roots = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            if(ancestor[i].isEmpty()) {
                roots.add(names[i]); // 조상이 없다면 그 녀석이 조상이다
            }

            for(int j : ancestor[i]) {
                int depthI = ancestor[i].size(); // 조상 수를 통해 인덱스 확인 가능, 없는 경우 : 0
                int depthJ = ancestor[j].size();

                if(depthJ == depthI - 1) {
                    childs[j].add(names[i]);
                }
            }
        }

        sb.append(roots.size()).append("\n"); // 가문의 개수
        Collections.sort(roots); // 오름차순 정렬
        for(String root : roots) sb.append(root).append(" ");

        sb.append("\n");

        Arrays.sort(names); // 이름 오름차순 정렬, nameIdx에 인덱스가 있음
        for(String name : names) {
            int idx = nameIdx.get(name);

            sb.append(name).append(" ");

            List<String> child = childs[idx];
            sb.append(child.size()).append(" ");

            Collections.sort(child);
            for(String c : child) sb.append(c).append(" ");

            sb.append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        input();

        pro();

        System.out.println(sb.toString());
    }
}
