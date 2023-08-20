package basic.data.structure;

//
public class OpenAddressing {

    Node[] hashTable = null;
    int size;

    public OpenAddressing(int size) {
        this.hashTable = new Node[size];
    }

    public class Node {
        String key;
        String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return this.size;
    }

    public int hash(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean put(String key, String value) {
        int hash = hash(key);

        if(this.hashTable[hash] == null) {
            this.hashTable[hash] = new Node(key, value);
            return true;
        } else {
            while(this.hashTable[hash] != null) {
                if(this.hashTable[hash].key == key) {
                    this.hashTable[hash].value = value;
                    size += 1;
                    return true;
                }

                hash += 1;
                if(this.hashTable.length <= hash) return false;
            }

            this.hashTable[hash] = new Node(key, value);
            size += 1;
            return true;
        }

    }

    public String get(String key) {
        int hash = hash(key);

        if(this.hashTable[hash] == null) return null;

        while(this.hashTable[hash] != null) {
            if(this.hashTable[hash].key == key) {
                return this.hashTable[hash].value;
            }

            hash += 1;
            if(this.hashTable.length <= hash) break;
        }

        return null;
    }

    public static void main(String[] args) {
        OpenAddressing openAddressing = new OpenAddressing(26);
        openAddressing.put("DaveLee", "01022223333");
        openAddressing.put("fun-coding", "01033334444");
        openAddressing.put("David", "01044445555");
        openAddressing.put("Dave", "01055556666");

        System.out.println(openAddressing.get("DaveLee")); // 01022223333
        System.out.println(openAddressing.get("fun-coding")); // 01033334444
        System.out.println(openAddressing.get("David")); // 01044445555
        System.out.println(openAddressing.get("Dave")); // 01055556666

        System.out.println(openAddressing.hash("DaveLee")); // 16
        System.out.println(openAddressing.hash("fun-coding")); // 24
        System.out.println(openAddressing.hash("David")); // 16
        System.out.println(openAddressing.hash("Dave")); // 16
    }
}
