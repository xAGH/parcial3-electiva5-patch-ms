package electiva5.parcial3.ms.patch.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import electiva5.parcial3.ms.patch.dto.TaskUpdateRequest;
import electiva5.parcial3.ms.patch.model.Task;
import electiva5.parcial3.ms.patch.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService
        implements ITaskService {
    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id).filter(task -> !task.isDeleted());
    }

    @Override
    public Task updateTask(Long id, TaskUpdateRequest updatedTask) {
        Task existing = Optional.of(taskRepository.findById(id))
                .flatMap(opt -> opt.filter(task -> !task.isDeleted()))
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Optional.ofNullable(updatedTask.getTitle())
                .ifPresent(existing::setTitle);

        Optional.ofNullable(updatedTask.getDescription())
                .ifPresent(existing::setDescription);

        Optional.ofNullable(updatedTask.getStatus())
                .ifPresent(existing::setStatus);

        return taskRepository.save(existing);
    }

}
