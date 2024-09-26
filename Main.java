
public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        inMemoryTaskManager.createTask(new Epic("Переезд"),
                new SubTask("Собрать коробки"),
                new SubTask("Убраться"),
                new SubTask("Упаковать кошку"));
        inMemoryTaskManager.createTask(new Epic ("Забыться"));// ,new SubTask("Сказать слова прощания"));
        //inMemoryTaskManager.createTask(new Task("Просто задача №1"));
        //inMemoryTaskManager.createTask(new Task("Просто задача №2"));

        //inMemoryTaskManager.printAllTasks();
        inMemoryTaskManager.printAllEpics();
        inMemoryTaskManager.printAllSubTasks();

        System.out.println(inMemoryTaskManager.getEpic(1));
        System.out.println(inMemoryTaskManager.getEpic(5));

        System.out.println();

        System.out.println(inMemoryTaskManager.getSubTask(4));
        System.out.println(inMemoryTaskManager.getSubTask(2));
        System.out.println(inMemoryTaskManager.getSubTask(3));
        System.out.println();
        System.out.println(InMemoryHistoryManager.history.getTasks());

        System.out.println();
        System.out.println(inMemoryTaskManager.getEpic(5));
        System.out.println(inMemoryTaskManager.getSubTask(2));

        System.out.println();
        System.out.println(InMemoryHistoryManager.history.getTasks());

        inMemoryTaskManager.deleteEpic(5);

        System.out.println(InMemoryHistoryManager.history.getTasks());

        inMemoryTaskManager.deleteEpic(1);
        System.out.println(InMemoryHistoryManager.history.getTasks());





        //System.out.println(inMemoryTaskManager.getEpic(4));

        //System.out.println(Managers.getDefaultHistory().getHistory());


    }
}