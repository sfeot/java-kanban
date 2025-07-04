package com.yandex.kanban.service;

import com.yandex.kanban.modal.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private final HashMap<Integer, Task> tasks;
    private int nextTaskId = 1;

    public Manager() {
        tasks = new HashMap<>();
    }

    public int createRegularTask(Task task) {
        task.setId(nextTaskId++);
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public int createEpic(Epic epic) {
        epic.setId(nextTaskId++);
        tasks.put(epic.getId(), epic);
        return epic.getId();
    }

    public int createSubtask(Subtask subtask) {
        subtask.setId(nextTaskId++);
        tasks.put(subtask.getId(), subtask);

        Epic epic = (Epic) tasks.get(subtask.getEpicId());

        if (epic != null) {
            epic.addSubTask(subtask.getId());
            calculateEpicStatus(epic);
        }

        return subtask.getId();
    }

    public ArrayList<Task> getAllRegularTasks() {
        ArrayList<Task> regularTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getType() == TaskType.REGULAR) {
                regularTasks.add(task);
            }
        }
        return regularTasks;
    }

    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getType() == TaskType.EPIC) {
                epics.add((Epic) task);
            }
        }
        return epics;
    }

    public ArrayList<Subtask> getAllSubtasks() {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getType() == TaskType.SUBTASK) {
                subtasks.add((Subtask) task);
            }
        }
        return subtasks;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteAllRegularTasks() {
        tasks.forEach((id, task) -> {
            if (task.getType() == TaskType.REGULAR) {
                tasks.remove(id);
            }
        });
    }

    public void deleteAllEpics() {
        tasks.forEach((id, task) -> {
            if (task.getType() == TaskType.EPIC || task.getType() == TaskType.SUBTASK) {
                tasks.remove(id);
            }
        });
    }

    public void deleteAllSubtasks() {
        tasks.forEach((id, task) -> {
            if (task.getType() == TaskType.SUBTASK) {
                Subtask subtask = (Subtask) task;
                Epic epic = (Epic) tasks.get(subtask.getEpicId());

                tasks.remove(id);
                epic.removeSubTask(id);

                calculateEpicStatus(epic);
            }
        });
    }

    public void deleteTask(int id) {
        Task task = tasks.get(id);

        if (task.getType() == TaskType.EPIC) {
            ((Epic) task).getSubtasks().forEach(tasks::remove);
        } else if (task.getType() == TaskType.SUBTASK) {
            Subtask subtask = (Subtask) task;
            Epic epic = (Epic) tasks.get(subtask.getEpicId());

            epic.removeSubTask(subtask.getId());
            TaskStatus status = calculateEpicStatus(epic);
            epic.setStatus(status);
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

        tasks.put(task.getId(), task);

        if (task.getType() != TaskType.SUBTASK) {
            return;
        }

        Subtask subtask = (Subtask) task;
        Epic epic = (Epic) tasks.get(subtask.getEpicId());

        if (epic != null) {
            epic.addSubTask(task.getId());
            TaskStatus newEpicStatus = calculateEpicStatus(epic);
            epic.setStatus(newEpicStatus);
        }
    }

    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        if (!tasks.containsKey(epicId)) {
            return null;
        }

        ArrayList<Subtask> subtasks = new ArrayList<>();
        Epic epic = (Epic) tasks.get(epicId);

        epic.getSubtasks().forEach(subtaskId -> {
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
