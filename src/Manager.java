import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private HashMap<Integer, Task> tasks;
    private int nextTaskId = 1;

    public Task createRegularTask(String name, String desc) {
        Task task = new Task(nextTaskId++, name, desc);
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(String name, String desc) {
        Epic epic = new Epic(nextTaskId++, name, desc);
        tasks.put(epic.getId(), epic);
        return epic;
    }

    public Subtask createSubtask(String name, String desc, int epicId) {
        Task epic = tasks.get(epicId);

        if ((epic instanceof Epic)) {
            Subtask subtask = new Subtask(nextTaskId++, name, desc, epicId);

            tasks.put(subtask.getId(), subtask);
            ((Epic) epic).addSubTask(subtask.getId());

            return subtask;
        }
        return null;
    }
}
