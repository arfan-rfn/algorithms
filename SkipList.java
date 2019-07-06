import java.util.Random;

/**
 * SkipList
 */
public class SkipList {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for (int i = 0; i < 50; i++) {
            skipList.insert(new Random().nextInt(100));
        }
        // skipList.insert(3);
        // skipList.insert(5);
        // skipList.insert(6);
        // skipList.insert(8);
        // skipList.insert(9);
        // skipList.insert(12);
        // skipList.insert(15);
        // skipList.insert(16);
        // skipList.insert(18);
        // skipList.insert(23);
        // skipList.insert(26);
        // skipList.insert(33);
        // skipList.insert(35);
        // skipList.insert(36);
        // skipList.insert(37);
        // skipList.insert(39);
        // skipList.insert(Integer.MAX_VALUE);
        // skipList.insert(Integer.MIN_VALUE);
        System.out.println(skipList.toString());
        // System.out.println(skipList.find(Integer.MIN_VALUE));
        // System.out.println(skipList.find(Integer.MAX_VALUE));
        // skipList.remove(Integer.MIN_VALUE);
        // skipList.remove(Integer.MIN_VALUE);
        // skipList.remove(Integer.MAX_VALUE);
        // skipList.remove(Integer.MAX_VALUE);
        // skipList.remove(Integer.MAX_VALUE);
        System.out.println(skipList.remove(Integer.MAX_VALUE));
        System.out.println(skipList.toString());
    }

    public class Node {
        public int data;
        public Node next;
        public Node down;

        public Node(int data, Node next, Node down) {
            this.data = data;
            this.next = next;
            this.down = down;
        }

        @Override
        public String toString() {
            return "Data: " + data;
        }
    }

    private Node head;
    private int size;
    private int level;

    public SkipList() {
        init();
        this.size = 0;
        this.level = 0;
    }

    public int size() {
        return size;
    }

    public Node top() {
        return head;
    }

    private void init() {
        Node tail = new Node(Integer.MAX_VALUE, null, null);
        Node top = new Node(Integer.MIN_VALUE, tail, null);
        this.head = top;
    }

    public void insert(int data) {
        size++;
        int kLevel = insertionLevel();
        if (kLevel > level) {
            addNewLevel();
        }
        insert(data, kLevel);
    }

    private void insert(int data, int kLevel) {
        int levelCount = level;
        Node temp = head;
        Node newNode = new Node(data, null, null);
        while (temp != null) {
            while (temp.next != null && temp.next.data < data) {
                temp = temp.next;
            }
            if (kLevel == levelCount) {
                newNode.next = temp.next;
                temp.next = newNode;
            } else if (kLevel > levelCount) {
                Node node = new Node(data, null, null);
                node.next = temp.next;
                temp.next = node;
                newNode.down = node;
                newNode = node;
            }
            temp = temp.down;
            levelCount--;
        }
    }

    public Node remove(int data) {
        Node temp = head;
        Node removed = null;
        while (temp.down != null) {
            while (temp.next != null && temp.next.data < data) {
                temp = temp.next;
            }
            if (temp.next.data == data && temp.next.next != null) {
                temp.next = temp.next.next;
            }
            temp = temp.down;

        }
        while (temp.next != null && temp.next.data < data) {
            temp = temp.next;
        }
        if (temp.next.data == data && temp.next.next != null) {
            removed = temp.next;
            temp.next = temp.next.next;
            size--;
        }
        return removed;
    }

    public Node find(int data) {
        Node temp = head;
        Node negInfinity = head;
        while (temp.down != null) {
            while (temp.next != null && temp.next.data <= data) {
                temp = temp.next;
            }
            temp = temp.down;
        }
        while (negInfinity.down != null) {
            negInfinity = negInfinity.down;
        }
        while (data < temp.data) {
            temp = temp.next;
        }
        if (temp != negInfinity && temp.next != null && temp.data == data) {
            return temp;
        }
        return null;
    }

    private void addNewLevel() {
        Node tail = new Node(Integer.MAX_VALUE, null, null);
        Node top = new Node(Integer.MIN_VALUE, tail, head);
        Node oldTail = head;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
        }
        tail.down = oldTail;
        head = top;
        level++;
    }

    private int insertionLevel() {
        int count = 0;
        for (int i = 0; i < Math.log(size); i++) {
            if (flipCoin()) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private boolean flipCoin() {
        return new Random().nextBoolean();
    }

    @Override
    public String toString() {
        String str = "";
        Node temp = head;
        int levelCount = level;
        while (temp != null) {
            Node eachLevel = temp;
            str += "\n(level #" + levelCount + ") ";
            while (eachLevel != null) {
                str += eachLevel.data + " ";
                eachLevel = eachLevel.next;
            }
            levelCount--;
            temp = temp.down;
        }
        return str;
    }
}