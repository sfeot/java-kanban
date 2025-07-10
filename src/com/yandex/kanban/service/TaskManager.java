package com.yandex.kanban.service;

import com.yandex.kanban.model.Epic;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;

import java.util.List;
import java.util.ArrayList;

public interface TaskManager {
    int createRegularTask(Task task);

    int createEpic(Epic epic);

    int createSubtask(Subtask subtask);

    ArrayList<Task> getAllRegularTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    void deleteAllRegularTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    void deleteTask(int id);

    Task getTask(int id);

    void updateTask(Task task);

    ArrayList<Subtask> getEpicSubtasks(int epicId);

    List<Task> getHistory();
}
