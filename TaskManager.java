public interface TaskManager {


    void printAllTasks();
    void printAllEpics();
    void printAllSubTasks();

    Task getTask(int id);
    Epic getEpic(int id);
    SubTask getSubTask(int id);


    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubTasks();

    void updateTask(String taskName, statusTask newStatus);
    void updateEpic(String epicName);
    void updateSubTask(String subTaskName, statusTask newStatus);

    void deleteTask(int id);
    void deleteEpic(int id);
    void deleteSubTask(int id);

}
