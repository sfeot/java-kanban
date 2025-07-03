import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private final HashMap<Integer, Task> tasks;
    private int nextTaskId = 1;

    public Manager() {
        tasks = new HashMap<>();
    }

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
            ((Epic) task).getSubtaskIds().forEach(tasks::remove);
        } else if (task instanceof Subtask subtask) {
            Epic epic = (Epic) tasks.get(subtask.getEpicId());

            epic.removeSubTask(subtask.getId());
        }
        tasks.remove(id);
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public void updateTask(Task task) {
        if (task == null) {
            return;
        }

        Task existingTask = tasks.get(task.getId());

        if (existingTask == null) {
            return;
        }

        if (!existingTask.getClass().equals(task.getClass())) {
            return;
        }

        if (task instanceof Subtask) {
            updateSubtask((Subtask) existingTask, (Subtask) task);
        } else {
            tasks.put(task.getId(), task);
        }

        if (!(task instanceof Subtask subtask)) {
            return;
        }

        Epic epic = (Epic) tasks.get(subtask.getEpicId());
        TaskStatus newEpicStatus = calculateEpicStatus(epic);
        epic.setStatus(newEpicStatus);
    }

    private void updateSubtask(Subtask existing, Subtask updated) {
        int oldEpicId = existing.getEpicId();
        int newEpicId = updated.getEpicId();

        if (oldEpicId != newEpicId) {
            Epic oldEpic = (Epic) tasks.get(oldEpicId);
            if (oldEpic != null) {
                oldEpic.removeSubTask(existing.getId());
            }

            Epic newEpic = (Epic) tasks.get(newEpicId);
            if (newEpic != null) {
                newEpic.addSubTask(updated.getId());
            }
        }

        tasks.put(updated.getId(), updated);
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

    private TaskStatus calculateEpicStatus(Epic epic) {
        ArrayList<Subtask> subtasks = getEpicSubtasks(epic.getId());

        HashMap<TaskStatus, Integer> statusMap = new HashMap<>();
        statusMap.put(TaskStatus.NEW, 0);
        statusMap.put(TaskStatus.IN_PROGRESS, 0);
        statusMap.put(TaskStatus.DONE, 0);

        for (Subtask subtaskItem : subtasks) {
            if (statusMap.containsKey(subtaskItem.getStatus())) {
                statusMap.put(subtaskItem.getStatus(), statusMap.get(subtaskItem.getStatus()) + 1);
            } else {
                statusMap.put(subtaskItem.getStatus(), 1);
            }
        }

        if (statusMap.get(TaskStatus.NEW) == subtasks.size()) {
            return TaskStatus.NEW;
        } else if (statusMap.get(TaskStatus.DONE) == subtasks.size()) {
            return TaskStatus.DONE;
        }
        return TaskStatus.IN_PROGRESS;
    }
}
