package com.yandex.kanban.modal;

import java.util.Objects;

public class Task {
    private String name;
    private String desc;
    private final int id;
    private TaskStatus status;

    public Task(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.status = TaskStatus.NEW;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        return "com.yandex.kanban.modal.Task{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
