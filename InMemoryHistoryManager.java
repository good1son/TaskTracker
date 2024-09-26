import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    static TaskLinkedList history;
    //Map<Integer, Node> historyMap = new HashMap<>();

    public InMemoryHistoryManager() {
        history = new TaskLinkedList();
    }

    @Override
    public void add(Task task) {
        if (history.historyMap.containsKey(task.id))
            replace(task.id);
        else {
            history.linkFirst(task);
            history.historyMap.put(task.id, history.head);
            }

    }

    public void replace(int id) {
       history.replaceNode(history.historyMap.get(id));
    }

    @Override
    public void remove(int id) {
        history.removeNode(history.historyMap.get(id));
    }

    @Override
    public List<Task> getHistory() {
        return history.getTasks();
    }

    static class TaskLinkedList {
        Node head;
        Node tail;
        int size;
        Map<Integer, Node> historyMap;


        public TaskLinkedList() {
            head = null;
            tail = null;
            size = 0;
            historyMap = new HashMap<>();
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
            historyMap.put(task.id, newNode);
        }

        public void linkFirst(Task task) {
            if (isEmpty())
                initNewNode(task);
            else
            {
                Node newNode = new Node(null, task, head);
                head.prev = newNode;
                head = newNode;
                historyMap.put(task.id, newNode);
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
                historyMap.put(task.id, newNode);
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
            historyMap.clear();
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
}
