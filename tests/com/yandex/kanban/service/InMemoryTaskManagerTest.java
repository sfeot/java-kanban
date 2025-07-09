package com.yandex.kanban.service;

import com.yandex.kanban.modal.Epic;
import com.yandex.kanban.modal.Subtask;
import com.yandex.kanban.modal.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = Managers.getDefault();
    }

    @Test
    void shouldAddAndGetTasks() {
        Task task = new Task("Test Task", "Test Description");
        int taskId = taskManager.createRegularTask(task);
        Task retrievedTask = taskManager.getTask(taskId);

        assertNotNull(retrievedTask);
        assertEquals(task, retrievedTask);
    }

    @Test
    void shouldAddAndGetEpics() {
        Epic epic = new Epic("Test Epic", "Test Description");
        int epicId = taskManager.createEpic(epic);
        Epic retrievedEpic = (Epic) taskManager.getTask(epicId);

        assertNotNull(retrievedEpic);
        assertEquals(epic, retrievedEpic);
    }

    @Test
    void shouldAddAndGetSubtasks() {
        Epic epic = new Epic("Test Epic", "Test Description");
        int epicId = taskManager.createEpic(epic);
        Subtask subtask = new Subtask("Test Subtask", "Test Description", epicId);
        int subtaskId = taskManager.createSubtask(subtask);
        Subtask retrievedSubtask = (Subtask) taskManager.getTask(subtaskId);

        assertNotNull(retrievedSubtask);
        assertEquals(subtask, retrievedSubtask);
    }

    @Test
    void generatedIdShouldNotConflict() {
        Task task1 = new Task("Task 1", "Description 1");
        int task1Id = taskManager.createRegularTask(task1);

        Task task2 = new Task("Task 2", "Description 2");
        int task2Id = taskManager.createRegularTask(task2);

        assertNotEquals(task1Id, task2Id);
    }

    @Test
    void taskShouldBeImmutableWhenAdded() {
        Task task = new Task("Task", "Description");
        Task originalTask = new Task(task.getName(), task.getDesc());
        originalTask.setId(task.getId());
        originalTask.setStatus(task.getStatus());

        taskManager.createRegularTask(task);

        assertEquals(originalTask.getName(), task.getName());
        assertEquals(originalTask.getDesc(), task.getDesc());
        assertEquals(originalTask.getStatus(), task.getStatus());
    }

    @Test
    void subtaskCannotBeItsOwnEpic() {
        Subtask subtask = new Subtask("Subtask", "description", 1);
        int subtaskId = taskManager.createSubtask(subtask);
        assertNotEquals(subtaskId, subtask.getEpicId());
    }

    @Test
    void epicCannotBeItsOwnSubtask() {
        Epic epic = new Epic("Epic", "Description");
        int epicId = taskManager.createEpic(epic);
        assertFalse(epic.getSubtasks().contains(epicId));
    }
}
