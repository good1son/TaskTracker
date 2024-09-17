public class SubTask extends Task {
    Epic epic;
    
    public SubTask(String name) {
        super(name);
    }

    public SubTask(String name, statusTask status) {
        super(name, status);
    }

    public SubTask(String name, int id)
    {
        super(name, id);
        this.epic = new Epic("Нет Эпика", id);
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
    
    @Override
    public String toString() {
        return "ПодЗадача: " + this.name + "( " + epic.name + " ) " + " ID: " + this.id + " Статус: " + this.status;
    }
}
