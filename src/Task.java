import java.util.Objects;

public class Task {
    private final String name;
    private final String desc;
    private static int nextId = 1;
    private final int id;
    private TaskStatus status;

    public Task(String name, String desc) {
        this.id = nextId++;
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(desc, task.desc) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, id, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
