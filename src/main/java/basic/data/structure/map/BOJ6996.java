package basic.data.structure.map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 애너그램(브론즈1)
 * https://www.acmicpc.net/problem/6996
 *
 */
public class BOJ6996 {
    static StringBuilder sb = new StringBuilder();
    static int T;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T > 0) {
            T -= 1;

            Map<Character, Integer> angramMap = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            String fir = st.nextToken();
            String sec = st.nextToken();

            for(int i = 0; i < fir.length(); i++) {
                angramMap.put(fir.charAt(i), angramMap.getOrDefault(fir.charAt(i), 0) + 1);
            }


            for(int i = 0; i < sec.length(); i++) {
                int value = angramMap.getOrDefault(sec.charAt(i), 0);
                angramMap.put(sec.charAt(i), value - 1);
            }

            boolean isValid = true;
            for(Character c : angramMap.keySet()) {
                if(angramMap.get(c) != 0) {
                    isValid = false;
                    break;
                }
            }

            if(isValid) sb.append(fir).append(" & ").append(sec).append(" are anagrams.").append("\n");
            else sb.append(fir).append(" & ").append(sec).append(" are NOT anagrams.").append("\n");
        }

        System.out.println(sb.toString());
    }


    public static void main(String[] args) throws Exception {
        input();
    }
}
