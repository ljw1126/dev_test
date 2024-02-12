package basic.data.structure.queue;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 좋은 친구(골드4)
 * https://www.acmicpc.net/problem/3078
 *
 * 직접 풀이 못함
 * - 최대치 long
 */
public class BOJ3078 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N명의 학생
        int K = Integer.parseInt(st.nextToken()); // 등수 차이 K 이하 까지

        Deque<Integer>[] data = new Deque[21];
        for(int i = 2; i < 21; i++) {
            data[i] = new ArrayDeque<>();
        }

        long result = 0L; // 최대치 생각 못함
        for(int i = 0; i < N; i++) {
            String name = br.readLine();
            int lng = name.length();

            while(!data[lng].isEmpty() && i - data[lng].peek() > K) {
                data[lng].poll();
            }

            result += data[lng].size();
            data[lng].offer(i);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

}
