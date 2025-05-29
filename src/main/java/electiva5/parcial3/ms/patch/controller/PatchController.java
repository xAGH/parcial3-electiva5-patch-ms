package electiva5.parcial3.ms.patch.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import electiva5.parcial3.ms.patch.dto.ApiResponse;
import electiva5.parcial3.ms.patch.dto.TaskUpdateRequest;
import electiva5.parcial3.ms.patch.model.Task;
import electiva5.parcial3.ms.patch.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class PatchController {

    private final TaskService taskService;

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable("id") Long id,
            @RequestBody TaskUpdateRequest task) {
        try {
            Task updated = taskService.updateTask(id, task);
            return ResponseEntity.ok(new ApiResponse<>(true, "Task updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Task not found", null));
        }
    }
}
