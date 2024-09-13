import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Manager {
    static int id = 0;
    HashMap<Integer, Task> tasks;
    HashMap<Integer, SubTask> subTasks;
    HashMap<Integer, Epic> epics;

    public Manager() {
        this.tasks = new HashMap<>();
        this.subTasks = new HashMap<>();
        this.epics = new HashMap<>();
    }

    public int getId() {
        return ++id;
    }

    public int getIdByName(String name) {
        for (int id : tasks.keySet()) {
            if (Objects.equals(tasks.get(id).name, name))
                return id;
        }
        for (int id : subTasks.keySet()) {
            if (Objects.equals(subTasks.get(id).name, name))
                return id;
        }
        for (int id : epics.keySet()) {
            if (Objects.equals(epics.get(id).name, name))
                return id;
        }
        return 0;
    }

    public void showAllTasks() {
        if (tasks.isEmpty())
            System.out.println("Список задач пуст");
        else {
            for (int id : tasks.keySet())
                System.out.println(tasks.get(id));
        }
    }

    public void showAllEpics() {
        if (epics.isEmpty())
            System.out.println("Список Эпиков пуст");
        else {
            for (int id : epics.keySet())
                System.out.println(epics.get(id));
        }
    }

    public void showAllSubTasks() {
        if (subTasks.isEmpty())
            System.out.println("Список ПодЗадач пуст");
        else {
            for (int id : epics.keySet())
                System.out.println(epics.get(id).subTasks);
        }
    }

    public void deleteAllTasks() {
        if (tasks.isEmpty())
            System.out.println("Список задач пуст");
        else {
            tasks.clear();
            System.out.println("Вы удалили все имеющиеся задачи");
        }
    }

    public void deleteAllEpics() {
        if (epics.isEmpty())
            System.out.println("Список ЭпикЗадач пуст");
        else {
            epics.clear();
            subTasks.clear();
            System.out.println("Вы удалили все имеющиеся ЭпикЗадачи");
        }

    }

    public void deleteAllSubTasks() {
        if (subTasks.isEmpty())
            System.out.println("Список ПодЗадач пуст");
        else {
            subTasks.clear();
            System.out.println("Вы удалили все имеющиеся ПодЗадачи");
        }
    }

    public void createTask(Task task) {
        task.id = this.getId();
        tasks.put(id, task);
    }

    public void createTask(Epic epic, SubTask... subTasks) {
        epic.id = this.getId();
        epics.put(id, epic);

        for (SubTask subTask : subTasks) {
            subTask.id = this.getId();
            subTask.setEpic(epic);
            epic.subTasks.add(subTask);
            this.subTasks.put(id, subTask);
        }
    }

    public void updateTask(String taskName, String newStatus) {
        int id = getIdByName(taskName);
        Task task = tasks.get(id);
        task.status = newStatus;
        System.out.println("Новый статус Задачи \"" + task.name + "\": " + task.status);
    }

    public void updateEpic(String epicName) {
        int id = getIdByName(epicName);
        Epic epic = epics.get(id);
        String newStatus;

        if (epic.isNew())
            newStatus = "NEW";
        else if (epic.isDone())
            newStatus = "DONE";
        else newStatus = "IN PROGRESS";

        epic.status = newStatus;
        System.out.println("Новый статус ЭпикЗадачи \"" + epic.name + "\": " + epic.status);

    }

    public void updateSubTask(String subTaskName, String newStatus) {
        int id = getIdByName(subTaskName);
        SubTask subtask = subTasks.get(id);
        subtask.status = newStatus;
        System.out.println("Новый статус ПодЗадачи \"" + subtask.name + "\": " + subtask.status);
        updateEpic(subtask.epic.name);
    }

    public void deleteTask(int id) {
        System.out.println("Удаление задачи: " + tasks.get(id));
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        System.out.println("Удаление ЭпикЗадачи: " + epics.get(id));
        Epic epic = epics.get(id);
        for (int ids : subTasks.keySet()) {
            if (Objects.equals(epic, subTasks.get(ids).epic))
                subTasks.remove(ids);
        }
        epics.remove(id);
    }

    public void deleteSubTask(int id) {
        System.out.println("Удаление ПодЗадачи: " + subTasks.get(id));
        Epic epic = subTasks.get(id).epic;
        epic.subTasks.remove(subTasks.remove(id));
        updateEpic(epic.name);
    }

}
