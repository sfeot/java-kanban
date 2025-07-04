package com.yandex.kanban.modal;

public class Subtask extends Task {
    private final int EpicId;

    public Subtask(int id, String name, String desc, int EpicId) {
        super(id, name, desc);
        this.EpicId = EpicId;
    }

    public int getEpicId() {
        return EpicId;
    }
}
