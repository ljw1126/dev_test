package basic.data.structure;

public class SeparateChaining {

    Node[] hashTable;
    int size;

    public SeparateChaining(int capacity) {
        this.hashTable = new Node[capacity];
    }

    public class Node{
        String key;
        String value;
        Node next = null;

        public Node(String key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return this.size;
    }
    public final int hash(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public String get(String key) {
        int hash = hash(key);

        if(this.hashTable[hash] != null) {
            Node node = this.hashTable[hash];
            if(node.key == key) return node.value;

            while(node.next != null) {
                if(node.next.key == key) {
                    return node.next.value;
                }

                node = node.next;
            }
        }

        return null;
    }

    public boolean put(String key, String value) {
        int hash = hash(key);

        if(this.hashTable[hash] == null) {
            this.hashTable[hash] = new Node(key, value);
            this.size += 1;
            return true;
        } else {
            Node node = this.hashTable[hash];
            while(node.next != null) {
                if(node.key == key) {
                    node.value = value;
                    return true;
                }

                node = node.next;
            }

            node.next = new Node(key, value);
            this.size += 1;
            return true;
        }
    }

    public static void main(String[] args) {
        SeparateChaining separateChaining = new SeparateChaining(26);
        separateChaining.put("DaveLee", "01022223333");
        separateChaining.put("fun-coding", "01033334444");
        separateChaining.put("David", "01044445555");
        separateChaining.put("Dave", "01055556666");

        System.out.println(separateChaining.get("DaveLee")); // 01022223333
        System.out.println(separateChaining.get("fun-coding")); // 01033334444
        System.out.println(separateChaining.get("David")); // 01044445555
        System.out.println(separateChaining.get("Dave")); // 01055556666

        System.out.println(separateChaining.hash("DaveLee")); // 16
        System.out.println(separateChaining.hash("fun-coding")); // 24
        System.out.println(separateChaining.hash("David")); // 16
        System.out.println(separateChaining.hash("Dave")); // 16
    }
}
