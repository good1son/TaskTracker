public class Node {
    Task data;
    Node prev;
    Node next;

    public Node(Node prev, Task data, Node next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }
}
