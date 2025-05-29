package electiva5.parcial3.ms.patch.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import electiva5.parcial3.ms.patch.dto.ApiResponse;
import electiva5.parcial3.ms.patch.dto.TaskUpdateRequest;
import electiva5.parcial3.ms.patch.model.Task;
import electiva5.parcial3.ms.patch.service.TaskService;

@ActiveProfiles("test")
public class PatchControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private PatchController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateTaskNotFound() {
        TaskUpdateRequest updateRequest = new TaskUpdateRequest();
        updateRequest.setTitle("Actualizado");

        when(taskService.updateTask(1L, updateRequest)).thenThrow(new RuntimeException("Task not found"));

        ResponseEntity<ApiResponse<Task>> response = taskController.updateTask(1L, updateRequest);

        assertFalse(response.getBody().isSuccess());
        assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertEquals("Task not found", response.getBody().getMessage());
    }
}
