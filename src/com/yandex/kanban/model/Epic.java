package com.yandex.kanban.model;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subtaskIds;

    public Epic(String name, String desc) {
        super(name, desc);
        subtaskIds = new ArrayList<>();
        setStatus(TaskStatus.NEW);
    }

    public TaskType getType() {
        return TaskType.EPIC;
    }

    public void addSubTask(int subtaskId) {
        if (!subtaskIds.contains(subtaskId)) {
            subtaskIds.add(subtaskId);
        }
    }

    public void removeSubTask(int subtaskId) {
        if (subtaskIds.contains(subtaskId)) {
            subtaskIds.remove(Integer.valueOf(subtaskId));
        }
    }

    public ArrayList<Integer> getSubtasks() {
        return new ArrayList<>(subtaskIds);
    }

    @Override
    public Task copy() {
        Epic copyTask = new Epic(this.getName(), this.getDesc());
        copyTask.setId(this.getId());
        copyTask.setStatus(this.getStatus());
        for (Integer subtaskId : subtaskIds) {
            copyTask.addSubTask(subtaskId);
        }
        return copyTask;
    }
}
