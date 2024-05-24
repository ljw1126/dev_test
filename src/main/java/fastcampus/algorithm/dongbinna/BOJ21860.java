package fastcampus.algorithm.dongbinna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 폴더 정리 (골3)
 * https://www.acmicpc.net/problem/22860
 */
public class BOJ21860 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

    private static int N, M;
    private static Map<String, Folder> HIERARCHY = new HashMap<>();

    private static class Folder {
        private Set<Folder> childs = new HashSet<>();
        private Set<String> files = new HashSet<>();
        private int fileCount;
        private String name;

        public Folder(String name) {
            this.name = name;
        }

        public void addFile(String fileName) {
            files.add(fileName);
            fileCount += 1;
        }

        public void addFolder(Folder folder) {
            childs.add(folder);
        }

        public void merge(Folder other) {
            files.addAll(other.files);
            fileCount += other.fileCount;
        }

        public int uniqueFileSize() {
            return this.files.size();
        }

        public int fileCount() {
            return this.fileCount;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        N = inputProcessor.nextInt(); // 폴더의 총 개수
        M = inputProcessor.nextInt(); // 파일의 총 개수

        for (int i = 1; i <= N + M; i++) {
            String p = inputProcessor.next(); // 상위 폴더의 이름
            String f = inputProcessor.next(); // 폴더 또는 파일 이름
            int c = inputProcessor.nextInt(); // 폴더 1, 파일 0

            Folder parent = findFolder(p);
            if (c == 0) { // 파일
                parent.addFile(f);
            } else { // 폴더
                Folder child = findFolder(f);
                parent.addFolder(child);
            }
        }
    }

    private static Folder findFolder(String name) {
        if (HIERARCHY.containsKey(name)) return HIERARCHY.get(name);

        Folder created = new Folder(name);
        HIERARCHY.put(name, created);
        return created;
    }

    private static void dfs(Folder folder) {
        for (Folder child : folder.childs) {
            dfs(child);
            folder.merge(child);
        }
    }

    private static void pro() {
        dfs(findFolder("main"));

        int query = inputProcessor.nextInt();
        for (int i = 1; i <= query; i++) {
            String path = inputProcessor.next(); // 경로 정보
            int idx = path.lastIndexOf("/");
            String dir = path.substring(idx + 1);

            Folder folder = findFolder(dir);
            sb.append(folder.uniqueFileSize()).append(" ").append(folder.fileCount()).append("\n");
        }
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
