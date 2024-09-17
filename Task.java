import java.util.List;
import java.util.Objects;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected statusTask status;

    public Task(String name) {
        this.name = name;
        this.status = statusTask.NEW;
    }

    public Task(String name, statusTask status) {
        this(name);
        this.status = status;
    }

    //Данный конструктор исключительно для вызова исключения, при не существующем указании ID в методе getTask(id);
    public Task(String name, int id) {
        this.name = name;
        this.id = id;
        this.status = statusTask.NONE;
    }

    public int getId() {
        return id;
    }

    public statusTask getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "Задача: " + this.name + " ID: " + this.id + " Статус: " + this.status;
    }
}
