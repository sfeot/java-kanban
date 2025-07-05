package com.yandex.kanban.modal;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(String name, String desc, int epicId) {
        super(name, desc);
        this.epicId = epicId;
    }

    public TaskType getType() {
        return TaskType.SUBTASK;
    }

    public int getEpicId() {
        return epicId;
    }
}
