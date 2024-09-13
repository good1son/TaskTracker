import java.util.Objects;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected String status;

    public Task(String name) {
        this.name = name;
        this.status = "NEW";
    }

    public Task(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "Задача: " + this.name + " ID: " + this.id + " Статус: " + this.status;
    }
}
