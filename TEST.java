import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TEST {
    public static void main(String[] args) {
        class TaskLinkedList {
            final Map<Integer, Node> idNode;
            Node head;
            Node tail;
            int size;

            public TaskLinkedList() {
                head = null;
                tail = null;
                size = 0;
                idNode = new HashMap<>();
            }

            public boolean isEmpty() {
                return (size == 0);
            }

            public boolean isLast() {
                return (size == 1);
            }

            public void initNewNode(Task task) {
                Node newNode = new Node(null, task, null);
                head = newNode;
                tail = newNode;
                idNode.put(task.id, newNode);
            }

            public void linkFirst(Task task) {
                if (isEmpty())
                    initNewNode(task);
                else
                {
                    Node newNode = new Node(null, task, head);
                    head.prev = newNode;
                    head = newNode;
                    idNode.put(task.id, newNode);
                }
                size++;
            }

            public void linkLast(Task task) {
                if (isEmpty())
                    initNewNode(task);
                else
                {
                    Node newNode = new Node(tail, task, null);
                    tail.next = newNode;
                    tail = newNode;
                    idNode.put(task.id, newNode);
                }
                size++;
            }

            public void removeFirst() {
                if (!isLast() && !isEmpty()) {
                    head.next.prev = null;
                    head = head.next;
                    size--;
                }
                else
                    clear();

            }

            public void removeLast() {
                if (!isLast() && !isEmpty()) {
                    tail.prev.next = null;
                    tail = tail.prev;
                    size--;
                }
                else
                    clear();
            }

            public void clear() {
                head = null;
                tail = null;
                size = 0;
                idNode.clear();
            }

            public void removeNode(Node node) {
                if (isLast())
                    clear();
                else if (node == head)
                    removeFirst();
                else if (node == tail)
                    removeLast();
                else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                    size--;
                }

            }

            public void replaceNode(Node node) {
                linkFirst(node.data);
                removeNode(node);
            }

            public List<Task> getTasks() {
                List<Task> tasksList = new ArrayList<>();
                if (isEmpty())
                    System.out.println("Список задач пуст");
                else
                {
                    Node currentHead = head;
                    while (currentHead != null) {
                        tasksList.add(currentHead.data);
                        currentHead = currentHead.next;
                    }
                }
                return tasksList;
            }
        }

        TaskLinkedList taskList = new TaskLinkedList();

        taskList.linkLast(new Task("Задача № 2", 2));
        taskList.linkFirst(new Task("Задача № 1", 1));
        taskList.linkLast(new Task("Задача № 3", 3));

        System.out.println(taskList.getTasks());

        taskList.replaceNode(taskList.idNode.get(3));
        System.out.println(taskList.getTasks());
        System.out.println(taskList.size);
        System.out.println();
       /* taskList.removeNode(taskList.idNode.get(2));

        System.out.println(taskList.getTasks());
        taskList.removeFirst();
        System.out.println(taskList.getTasks());
        taskList.removeFirst();
        System.out.println(taskList.getTasks());

        taskList.removeFirst();*/
        /*taskList.linkFirst(new Task("Задача № 2"));
        taskList.linkFirst(new Task("Задача № 3"));
        taskList.linkLast(new Task("Задача № 33"));
        System.out.println(taskList.size);
        System.out.println(taskList.isEmpty());
        System.out.println(taskList.getTasks());*/

    }
}
