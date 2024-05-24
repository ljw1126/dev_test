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
 * 폴더정리(large)
 * https://www.acmicpc.net/problem/22861
 * <p>
 * - 초기화 부분에서 실수, 폴더안에 폴더 추가할 때 DIRECTORY
 * - 그외에는 직접 풀이
 */
public class BOJ22861 {
    private static StringBuilder sb = new StringBuilder();
    private static InputProcessor inputProcessor = new InputProcessor();

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

        public void move(Folder from) {
            childs.addAll(from.childs);
            files.addAll(from.files);
            fileCount = files.size();
        }

        public int uniqueFileSize() {
            return this.files.size();
        }

        public int fileCount() {
            return this.fileCount;
        }

        public boolean sameName(String other) {
            return name.equals(other);
        }

        public void removeFolder(Folder other) {
            childs.remove(other);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        output();
    }

    private static void input() {
        int n = inputProcessor.nextInt(); // 폴더의 총 개수
        int m = inputProcessor.nextInt(); // 파일의 총 개수

        for (int i = 1; i <= n + m; i++) {
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

        int k = inputProcessor.nextInt(); // 옮기는 횟수
        for (int i = 1; i <= k; i++) {
            String from = inputProcessor.next();
            String to = inputProcessor.next();

            Folder fromDir = parseAndFindFolder(from);
            Folder toDir = parseAndFindFolder(to);

            toDir.move(fromDir);
            erase(fromDir);
        }
    }

    private static Folder parseAndFindFolder(String path) {
        int idx = path.lastIndexOf("/");
        String dirName = path.substring(idx + 1);
        return HIERARCHY.get(dirName);
    }

    private static void erase(Folder target) {
        Set<String> keySet = HIERARCHY.keySet();
        for (String dir : keySet) {
            if (target.sameName(dir)) continue;

            Folder folder = HIERARCHY.get(dir);
            folder.removeFolder(target);
        }
    }

    private static Folder findFolder(String name) {
        if (HIERARCHY.containsKey(name)) return HIERARCHY.get(name);

        Folder created = new Folder(name);
        HIERARCHY.put(name, created);
        return created;
    }

    private static void pro() {
        dfs(findFolder("main"));

        int query = inputProcessor.nextInt();
        for (int i = 1; i <= query; i++) {
            String path = inputProcessor.next(); // 경로 정보
            Folder folder = parseAndFindFolder(path);
            sb.append(folder.uniqueFileSize()).append(" ").append(folder.fileCount()).append("\n");
        }
    }

    private static void dfs(Folder folder) {
        for (Folder child : folder.childs) {
            dfs(child);
            folder.merge(child);
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
