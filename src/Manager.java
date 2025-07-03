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

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteTask(int id) {
        Task task = tasks.get(id);

        if (task instanceof Epic) {
            ((Epic) task).getSubtaskIds().forEach(subtaskId -> {
                tasks.remove(subtaskId);
            });
        }
        tasks.remove(id);
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Task updateTask(int id, String name, String desc) {
        Task task = tasks.get(id);

        if (task == null) {
            return null;
        }
        if (name != null) {
            task.setName(name);
        }
        if (desc != null) {
            task.setDesc(desc);
        }

        return task;
    }

    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        if (!tasks.containsKey(epicId)) {
            return null;
        }

        ArrayList<Subtask> subtasks = new ArrayList<>();
        Epic epic = (Epic) tasks.get(epicId);

        epic.getSubtaskIds().forEach(subtaskId -> {
            subtasks.add((Subtask) tasks.get(subtaskId));
        });

        return subtasks;
    }
}
