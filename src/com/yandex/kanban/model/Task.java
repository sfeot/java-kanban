package com.yandex.kanban.model;

import java.util.Objects;

public class Task {
    private String name;
    private String desc;
    private TaskStatus status;
    private int id;

    public Task(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.status = TaskStatus.NEW;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TaskType getType() {
        return TaskType.REGULAR;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
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

    public Task copy() {
        Task copyTask = new Task(this.name, this.desc);
        copyTask.setId(this.id);
        copyTask.setStatus(this.status);
        return copyTask;
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
