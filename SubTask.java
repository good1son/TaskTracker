public class SubTask extends Task {
    Epic epic;
    
    public SubTask(String name) {
        super(name);
    }

    public SubTask(String name, String status) {
        super(name, status);
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
    
    @Override
    public String toString() {
        return "ПодЗадача: " + this.name + "( " + epic.name + " ) " + " ID: " + this.id + " Статус: " + this.status;
    }
}
