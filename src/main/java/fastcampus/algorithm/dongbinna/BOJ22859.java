package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : HTML 파싱 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/22859
 * <p>
 * - 직접 풀이 못함
 * replaceAll과 String.indexOf, lastIndexOf 만으로 풀이가 인상 깊다(https://www.acmicpc.net/source/75820445)
 */
public class BOJ22859 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();


    public static void main(String[] args) throws IOException {
        input();
        //pro();
        output();
    }

    private static void input() {
        String html = inputProcessor.nextLine();
        html = html.replaceAll("<main>|</main>", "");
        String[] tokens = html.split("</div>|<p>|</p>");

        System.out.println(Arrays.toString(tokens));
        for (String token : tokens) {
            if (token.isEmpty()) continue; // blank()있으면 틀렸다함

            int startTitle = token.indexOf("title=\"");
            if (startTitle != -1) {
                int endTitle = token.indexOf("\">", startTitle);
                String title = token.substring(startTitle + 7, endTitle);
                sb.append("title : ").append(title).append("\n");
            } else {
                //\\s는 공백, .*? 이해가 안됨 ..
                String paragraph = token.replaceAll("<.*?>", "").replaceAll("\\s{2,}", " ");
                sb.append(paragraph).append("\n");
            }
        }
    }

    private static void pro() {
        String html = inputProcessor.nextLine();
        html = html.replaceAll("<main>|</main>", "");

        int left = 0;
        int right = html.length();
        while (left < right) {
            int divOpen = html.indexOf("title=\"", left);
            if (divOpen == -1) break;

            int titleEnd = html.indexOf("\">", divOpen);
            String title = html.substring(divOpen + 7, titleEnd);
            sb.append("title : " + title).append("\n");

            int divClose = html.indexOf("</div>", divOpen);

            int cursor = titleEnd;
            while (true) {
                int paragraphOpen = html.indexOf("<p>", cursor);

                if (paragraphOpen == -1) break;
                if (paragraphOpen > divClose) break;

                int paragraphClose = html.indexOf("</p>", paragraphOpen);

                String paragraphBody = html.substring(paragraphOpen + 3, paragraphClose);
                sb.append(erased(paragraphBody)).append("\n");

                cursor = paragraphClose;
            }

            left = divClose;
        }
    }

    private static String erased(String text) {
        String result = "";
        boolean openTag = false;
        boolean space = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '<') {
                openTag = true;
                continue;
            }

            if (c == '>') {
                openTag = false;
                continue;
            }

            if (openTag) continue;

            if (c == ' ') { // 두번 연속으로 공백이 반복되면 하나로 줄이기
                if (space) continue;

                space = true;
            } else {
                space = false;
            }

            result += c;
        }

        return result;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
