import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task{
    ArrayList<SubTask> subTasks;

    public Epic(String name) {
        super(name);
        subTasks = new ArrayList<>();
    }

    public Epic(String name, int id) {
        super(name, id);
    }

    public void showSubTasks() {
        for (SubTask subTask : subTasks)
            System.out.println(subTask);
    }

    public boolean isNew() {
        for (SubTask subTask : subTasks) {
            if (!Objects.equals(subTask.status, statusTask.NEW))
                return false;
        }
        return true;
    }

    public boolean isDone() {
        for (SubTask subTask : subTasks) {
            if (!Objects.equals(subTask.status, statusTask.DONE))
                return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "ЭпикЗадача: " + this.name + " ID: " + this.id + " Статус: " + this.status;
    }
}
