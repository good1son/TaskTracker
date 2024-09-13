
public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        manager.createTask(new Epic("Переезд"), new SubTask("Собрать коробки"),
                new SubTask("Упаковать кошку"));
        manager.createTask(new Epic ("Забыться"), new SubTask("Сказать слова прощания"));
        manager.createTask(new Task("Просто задача №1"));
        manager.createTask(new Task("Просто задача №2"));

        manager.showAllTasks();
        manager.showAllEpics();
        manager.showAllSubTasks();

        manager.updateSubTask("Собрать коробки", "DONE");
        manager.updateSubTask("Упаковать кошку", "DONE");
        manager.showAllEpics();

        manager.deleteTask(6);
        manager.updateTask("Просто задача №2", "DONE");
        manager.showAllTasks();

        manager.deleteAllSubTasks();
        manager.showAllSubTasks();
        manager.deleteAllEpics();
        manager.showAllEpics();

    }
}