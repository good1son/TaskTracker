import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class InMemoryTaskManager implements TaskManager {
    static int id = 0;
    HashMap<Integer, Task> tasks;
    HashMap<Integer, SubTask> subTasks;
    HashMap<Integer, Epic> epics;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        subTasks = new HashMap<>();
        epics = new HashMap<>();
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

    @Override
    public void printAllTasks() {
        if (tasks.isEmpty())
            System.out.println("Список задач пуст");
        else {
            for (int id : tasks.keySet())
                System.out.println(tasks.get(id));
        }
    }

    @Override
    public void printAllEpics() {
        if (epics.isEmpty())
            System.out.println("Список Эпиков пуст");
        else {
            for (int id : epics.keySet())
                System.out.println(epics.get(id));
        }
    }

    @Override
    public void printAllSubTasks() {
        if (subTasks.isEmpty())
            System.out.println("Список ПодЗадач пуст");
        else {
            for (int id : epics.keySet())
                System.out.println(epics.get(id).subTasks);
        }
    }

    @Override
    public Task getTask(int id) {
        if (tasks.containsKey(id)) {
            Managers.getDefaultHistory().add(tasks.get(id));
            return tasks.get(id);
        }
        return new Task("Такой задачи по данному ID не существует!", id);
    }

    @Override
    public Epic getEpic(int id) {
        if (epics.containsKey(id)) {
            Managers.getDefaultHistory().add(epics.get(id));
            return epics.get(id);
        }
        return new Epic("Такой ЭпикЗадачи по данному ID не существует!", id);
    }

    @Override
    public SubTask getSubTask(int id) {
        if (subTasks.containsKey(id)) {
            Managers.getDefaultHistory().add(subTasks.get(id));
            return subTasks.get(id);
        }
        return new SubTask("Такой ПодЗадачи по данному ID не существует!", id);
    }

    @Override
    public void deleteAllTasks() {
        if (tasks.isEmpty())
            System.out.println("Список задач пуст");
        else {
            tasks.clear();
            System.out.println("Вы удалили все имеющиеся задачи");
        }
    }

    @Override
    public void deleteAllEpics() {
        if (epics.isEmpty())
            System.out.println("Список ЭпикЗадач пуст");
        else {
            epics.clear();
            subTasks.clear();
            System.out.println("Вы удалили все имеющиеся ЭпикЗадачи");
        }

    }

    @Override
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

    public void createTask(Epic epic) {
        epic.id = this.getId();
        epics.put(id, epic);
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

    @Override
    public void updateTask(String taskName, statusTask newStatus) {
        int id = getIdByName(taskName);
        Task task = tasks.get(id);
        task.status = newStatus;
        System.out.println("Новый статус Задачи \"" + task.name + "\": " + task.status);
    }

    @Override
    public void updateEpic(String epicName) {
        int id = getIdByName(epicName);
        Epic epic = epics.get(id);
        statusTask newStatus;

        if (epic.isNew())
            newStatus = statusTask.NEW;
        else if (epic.isDone())
            newStatus = statusTask.DONE;
        else newStatus = statusTask.IN_PROGRESS;

        epic.status = newStatus;
        System.out.println("Новый статус ЭпикЗадачи \"" + epic.name + "\": " + epic.status);

    }

    @Override
    public void updateSubTask(String subTaskName, statusTask newStatus) {
        int id = getIdByName(subTaskName);
        SubTask subtask = subTasks.get(id);
        subtask.status = newStatus;
        System.out.println("Новый статус ПодЗадачи \"" + subtask.name + "\": " + subtask.status);
        updateEpic(subtask.epic.name);
    }

    @Override
    public void deleteTask(int id) {
        System.out.println("Удаление задачи: " + tasks.get(id));
        InMemoryHistoryManager.history.removeNode(InMemoryHistoryManager.history.historyMap.get(id));
        tasks.remove(id);
    }

    @Override
    public void deleteEpic(int id) {
        System.out.println("Удаление ЭпикЗадачи: " + epics.get(id));
        Epic epic = epics.get(id);
        for (SubTask subTasks : epic.subTasks) {
            InMemoryHistoryManager.history.removeNode(InMemoryHistoryManager.history.historyMap.get(subTasks.id));
            this.subTasks.remove(subTasks.id);
        }
        epics.remove(id);
        InMemoryHistoryManager.history.removeNode(InMemoryHistoryManager.history.historyMap.get(id));
    }

    @Override
    public void deleteSubTask(int id) {
        System.out.println("Удаление ПодЗадачи: " + subTasks.get(id));
        Epic epic = subTasks.get(id).epic;
        epic.subTasks.remove(subTasks.remove(id));
        InMemoryHistoryManager.history.historyMap.remove(id);
        updateEpic(epic.name);
    }

}
