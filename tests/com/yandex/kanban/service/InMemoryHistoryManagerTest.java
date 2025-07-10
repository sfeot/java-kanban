package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void historyShouldStoreImmutableTasks() {
        Task task = new Task("Test Task", "Test Description");
        historyManager.add(task);

        task.setName("Updated Name");

        Task taskFromHistory = historyManager.getHistory().get(0);

        assertEquals("Test Task", taskFromHistory.getName(), "Task in history should not be mutable.");
    }
}
