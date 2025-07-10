package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_HISTORY_SIZE = 10;
    private final List<Task> history;

    public InMemoryHistoryManager() {
        history = new LinkedList<>();
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }

        if (history.size() >= MAX_HISTORY_SIZE) {
            history.removeFirst();
        }

        history.add(task.copy());
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}