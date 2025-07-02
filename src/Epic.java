import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, Subtask> subtasks;

    public Epic(String name, String desc) {
        super(name, desc);
    }

    public void addSubTask(Subtask subtask) {
        if (subtasks == null) {
            subtasks = new HashMap<>();
        } else {
            subtasks.put(subtask.getId(), subtask);
        }

        subtask.setEpicId(this.getId());
    }
}
