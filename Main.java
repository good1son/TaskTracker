
public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        inMemoryTaskManager.createTask(new Epic("Переезд"),
                new SubTask("Собрать коробки"),
                new SubTask("Упаковать кошку"));
        inMemoryTaskManager.createTask(new Epic ("Забыться"), new SubTask("Сказать слова прощания"));
        inMemoryTaskManager.createTask(new Task("Просто задача №1"));
        inMemoryTaskManager.createTask(new Task("Просто задача №2"));

        inMemoryTaskManager.printAllTasks();
        inMemoryTaskManager.printAllEpics();
        inMemoryTaskManager.printAllSubTasks();

        System.out.println(inMemoryTaskManager.getEpic(4));
        System.out.println(Managers.getDefaultHistory().getHistory());
        System.out.println(inMemoryTaskManager.getEpic(1));
        System.out.println(Managers.getDefaultHistory().getHistory());
        System.out.println(inMemoryTaskManager.getTask(6));
        System.out.println(Managers.getDefaultHistory().getHistory());
        System.out.println(inMemoryTaskManager.getSubTask(2));
        System.out.println(Managers.getDefaultHistory().getHistory());
        System.out.println(inMemoryTaskManager.getSubTask(5));
        System.out.println(Managers.getDefaultHistory().getHistory());
        System.out.println(inMemoryTaskManager.getSubTask(3));
        System.out.println(Managers.getDefaultHistory().getHistory());
        System.out.println(inMemoryTaskManager.getTask(7));
        System.out.println(Managers.getDefaultHistory().getHistory());

    }
}