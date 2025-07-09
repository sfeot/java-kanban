package com.yandex.kanban.modal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void shouldBeEqualIfIdIsEqual() {
        Task task1 = new Task("Task 1", "Description 1");
        task1.setId(1);
        Task task2 = new Task("Task 1", "Description 1");
        task2.setId(1);
        assertEquals(task1, task2, "Tasks with same id should be equal");
    }

    @Test
    void shouldNotBeEqualIfIdIsDifferent() {
        Task task1 = new Task("Task 1", "Description 1");
        task1.setId(1);
        Task task2 = new Task("Task 1", "Description 1");
        task2.setId(2);
        assertNotEquals(task1, task2, "Tasks with different id should not be equal");
    }

    @Test
    void shouldBeEqualIfEpicIdIsEqual() {
        Epic epic1 = new Epic("Epic 1", "Description 1");
        epic1.setId(1);
        Epic epic2 = new Epic("Epic 1", "Description 1");
        epic2.setId(1);
        assertEquals(epic1, epic2, "Epics with same id should be equal");
    }

    @Test
    void shouldNotBeEqualIfEpicIdIsDifferent() {
        Epic epic1 = new Epic("Epic 1", "Description 1");
        epic1.setId(1);
        Epic epic2 = new Epic("Epic 1", "Description 1");
        epic2.setId(2);
        assertNotEquals(epic1, epic2, "Epics with different id should not be equal");
    }

    @Test
    void shouldBeEqualIfSubtaskIdIsEqual() {
        Subtask subtask1 = new Subtask("Subtask 1", "Description 1", 1);
        subtask1.setId(1);
        Subtask subtask2 = new Subtask("Subtask 1", "Description 1", 1);
        subtask2.setId(1);
        assertEquals(subtask1, subtask2, "Subtasks with same id should be equal");
    }

    @Test
    void shouldNotBeEqualIfSubtaskIdIsDifferent() {
        Subtask subtask1 = new Subtask("Subtask 1", "Description 1", 1);
        subtask1.setId(1);
        Subtask subtask2 = new Subtask("Subtask 1", "Description 1", 1);
        subtask2.setId(2);
        assertNotEquals(subtask1, subtask2, "Subtasks with different id should not be equal");
    }
}