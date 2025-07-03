import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subtaskIds;

    public Epic(int id, String name, String desc) {
        super(id, name, desc);
        subtaskIds = new ArrayList<>();
        setStatus(TaskStatus.NEW);
    }

    public void addSubTask(int subtaskId) {
        subtaskIds.add(subtaskId);
    }

    public void removeSubTask(int subtaskId) {
        if (subtaskIds.contains(subtaskId)) {
            subtaskIds.remove((Integer) subtaskId);
        }
    }

    public ArrayList<Integer> getSubtaskIds() {
        return new ArrayList<>(subtaskIds);
    }
}
